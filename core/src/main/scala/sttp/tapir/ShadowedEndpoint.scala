package sttp.tapir

import sttp.model.Method

case class ShadowedEndpoint(e: Endpoint[_, _, _, _], by: Endpoint[_, _, _, _]) {
  override def toString = e.input.show + ", is shadowed by: " + by.input.show
}
