// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.components.menu

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import design.anduin.components.util.ComponentUtils
// scalastyle:on underscore.import

import design.anduin.style.Style

case class Menu() {
  def apply(children: VdomNode*): VdomElement = Menu.component(children: _*)
}

object Menu {
  private def render(children: PropsChildren): VdomElement = {
    <.div(
      ComponentUtils.testId(this, "Container"),
      Style.padding.ver8.overflow.autoY.overflow.hiddenX,
      ^.maxWidth := "640px",
      ^.maxHeight := "480px",
      ^.minWidth := "192px",
      children
    )
  }

  private val component = ScalaComponent
    .builder[Unit](this.getClass.getSimpleName)
    .stateless
    .render_C(render)
    .build
}
