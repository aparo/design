// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.component.tab

import anduin.style.{CssVar, Style}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[tab] final case class TabMinimal(
  titles: Seq[(VdomNode, Int)],
  content: VdomNode,
  active: Int,
  setActive: Int => Callback
) {
  def apply(): VdomElement = TabMinimal.component(this)
}

private[tab] object TabMinimal {

  private type Props = TabMinimal

  private val activeStyles = TagMod(
    Style.backgroundColor.white.color.gray8.borderRadius.px2,
    ^.borderBottomColor := CssVar.Color.primary4
  )

  private val normalStyles = TagMod(
    Style.backgroundColor.gray1.color.gray7
  )

  private def renderButton(props: Props)(titleTup: (VdomNode, Int)): VdomElement = {
    val (title, index) = titleTup
    val isActive = props.active == index
    <.button(
      // === Styles
      Style.focus.outline.transition.allWithOutline.padding.hor16.padding.ver12,
      if (isActive) activeStyles else normalStyles,
      TagMod.when(index != 0) { ^.marginLeft := "-1px" },
      // === Behaviours
      ^.tpe := "button",
      ^.onClick --> props.setActive(index),
      ^.disabled := isActive,
      title
    )
  }

  private def renderHeader(props: Props): VdomElement = {
    <.header(
      Style.flexbox.flex.flexbox.justifyCenter.lineHeight.px16.fontWeight.medium,
      props.titles.toTagMod { renderButton(props) }
    )
  }

  private def renderContent(props: Props): VdomElement = {
    <.div(
      props.content
    )
  }

  private def render(props: Props): VdomElement = {
    <.div(renderHeader(props), renderContent(props))
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build

}
