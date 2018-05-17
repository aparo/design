// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.menu

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

import anduin.style.Style

object MenuDivider {
  private val component = ScalaComponent.static(this.getClass.getSimpleName)(
    <.div(Style.backgroundColor.gray4.flexbox.flex.margin.ver8.width.pc100, ^.height := "1px")
  )()
  def apply(): VdomElement = component
}
