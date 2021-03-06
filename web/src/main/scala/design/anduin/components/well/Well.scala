// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.components.well

import design.anduin.components.button.Button
import design.anduin.components.icon.Icon
import design.anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Well(
  color: Well.Color = Well.ColorGray,
  title: String = "",
  close: Callback = Callback.empty
) {
  def apply(children: VdomNode*): VdomElement = {
    Well.component(this)(children: _*)
  }
}

object Well {

  sealed trait Color {
    private[Well] val iconName: Icon.Name
    private[Well] val iconColor: Style
    private[Well] val bg: Style
  }
  case object ColorGray extends Color {
    private[Well] val iconName = Icon.Glyph.Bookmark
    private[Well] val iconColor = Style.color.gray7
    private[Well] val bg = Style.background.gray1
    private[Well] val close = Style.background.gray3
  }
  case object ColorPrimary extends Color {
    private[Well] val iconName = Icon.Glyph.Info
    private[Well] val iconColor = Style.color.primary4
    private[Well] val bg = Style.background.primary1
  }
  case object ColorSuccess extends Color {
    private[Well] val iconName = Icon.Glyph.Check
    private[Well] val iconColor = Style.color.success4
    private[Well] val bg = Style.background.success1
  }
  case object ColorWarning extends Color {
    private[Well] val iconName = Icon.Glyph.Warning
    private[Well] val iconColor = Style.color.warning4
    private[Well] val bg = Style.background.warning1
  }
  case object ColorDanger extends Color {
    private[Well] val iconName = Icon.Glyph.Error
    private[Well] val iconColor = Style.color.danger4
    private[Well] val bg = Style.background.danger1
  }

  private def renderClose(props: Well): VdomNode = {
    if (props.close == Callback.empty) {
      EmptyVdom
    } else {
      val button = Button(
        onClick = props.close,
        style = Button.Style.Minimal(icon = Some(Icon.Glyph.Cross))
      )()
      <.div(^.padding := "6px 6px 0 0", button)
    }
  }

  private def renderIcon(props: Well): VdomElement = {
    <.div(
      ^.padding := "14px 0 0 12px",
      props.color.iconColor,
      Icon(name = props.color.iconName)()
    )
  }

  private def render(props: Well, children: PropsChildren): VdomElement = {
    <.div(
      props.color.bg,
      Style.borderRadius.px2.flexbox.flex,
      <.div(Style.flexbox.none, renderIcon(props)),
      <.div(
        Style.flexbox.fill.padding.ver12.padding.hor8,
        TagMod.when(props.title.nonEmpty) {
          <.h4(Style.margin.bottom4.color.gray7, props.title)
        },
        children
      ),
      <.div(Style.flexbox.none, renderClose(props))
    )
  }

  private val component = ScalaComponent
    .builder[Well](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
