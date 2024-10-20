import sandbox.Graphs._

class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val graph: AdjacencyList[Int, String] = Map(
      0 -> Set.empty,
      1 -> Set.empty,
      2 -> Set((3, "third")),
      3 -> Set((1, "first")),
      4 -> Set((1, "first"), (0, "zero")),
      5 -> Set((0, "zero"), (2, "second")),
    )

    println(graph.topsort().mkString(", "))
  }
}
