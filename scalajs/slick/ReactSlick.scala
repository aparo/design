// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.scalajs.slick

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object ReactSlick {

  @JSImport("react-slick", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait RawComponent extends js.Object {
    def slickPrev(): Unit = js.native
    def slickNext(): Unit = js.native
  }
}
