// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.components.menu

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import design.anduin.style.Style
// scalastyle:on underscore.import

final case class VerticalDivider() {
  def apply(): VdomElement = VerticalDivider.component()
}

object VerticalDivider {
  private val render = <.div(Style.background.gray4.margin.hor4, ^.width := "1px", ^.height := "20px")
  private val component = ScalaComponent.static(this.getClass.getSimpleName)(render)
}
