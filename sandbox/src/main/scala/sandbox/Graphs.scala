package sandbox

object Graphs:
    type AdjacencyList[V, E] = Map[V, scala.collection.Set[(V, E)]]

    extension[V, E] (g: AdjacencyList[V, E])
        def topsort(): List[V] =
            import scala.collection.mutable.{ ListBuffer, Map, Stack }
            val visited = Map.newBuilder[V, Boolean]
                .addAll(g.mapValues(_ => false))
                .result()
                 
            val stackBuilder = Stack.newBuilder[V]
            stackBuilder.sizeHint(g.size)
            
            val stack = stackBuilder.result()

            def dfs(v: V, s: Stack[V], visited: Map[V, Boolean]): Unit =
                if !visited(v) then
                    visited.update(v, true)
                    val neighbours = g(v)
                    neighbours.foreach: (node, _) =>
                        dfs(node, s, visited)
                    s.push(v)
            
            for v <- g.keys do
                dfs(v, stack, visited)

            val listBuffer = ListBuffer[V]()

            while stack.nonEmpty do
                listBuffer += stack.pop()
            
            listBuffer.toList
end Graphs
