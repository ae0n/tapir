package sttp.tapir.util

import sttp.tapir.{Endpoint, ShadowedEndpoint}

object ShadowedEndpointChecker {

  def findShadowedEndpoints(e: List[Endpoint[_, _, _, _]]): List[ShadowedEndpoint[_, _, _, _]] = {
    val grouppedEndpoints = e.groupBy(x => normalizePath(x.input.show)).filter(c => c._2.size > 1)
    if (grouppedEndpoints.isEmpty) List() else extractOverlaps(grouppedEndpoints)
  }

  def extractOverlaps(g: Map[String, List[Endpoint[_, _, _, _]]]): List[ShadowedEndpoint[_, _, _, _]] = {
    //g.filter(c => c._2.size > 1).flatMap(c=>c._2.tail.map(e=>ShadowedEndpoint(e,c._2.head))).toList
    List()
  }

  def normalizePath(i: String): String = {
    i.replace("/*", "")
  }
}
