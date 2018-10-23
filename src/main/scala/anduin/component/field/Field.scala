// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.field

import anduin.component.portal.Popover
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Field(
  layout: Field.Layout,
  id: String,
  input: VdomNode,
  label: Option[String] = None,
  help: Option[VdomNode] = None,
  desc: Option[String] = None,
  error: Option[String] = None
) {
  def apply(): VdomElement = Field.component(this)
}

object Field {

  sealed trait Layout
  case object LayoutHor extends Layout

  private type Props = Field

  private object Static {
    val desc = Style.fontSize.px10.lineHeight.px16.color.gray7
    val label = Style.fontWeight.semiBold
    val help = TagMod(Style.fontSize.px12.color.gray6.hover.colorPrimary4, "[?]")
    val error = Style.fontSize.px12.color.danger5
  }

  private def renderHelp(props: Props): Option[VdomElement] = {
    props.help.map { text =>
      Popover(
        renderTarget = (toggle, _) => {
          <.span(" ", <.button(^.onClick --> toggle, Static.help))
        },
        renderContent = _ => {
          <.div(Style.padding.hor16.padding.ver12.maxWidth.px256, text)
        },
        targetTag = <.span
      )()
    }
  }

  private def renderHor(props: Props): VdomElement = {
    <.div(
      Style.flexbox.flex,
      <.div(
        Style.flexbox.fixed.margin.right24,
        <.p(
          ^.paddingTop := "6px",
          props.label.map(<.label(^.htmlFor := props.id, Static.label, _)),
          renderHelp(props)
        ),
        props.desc.map(<.p(Static.desc, _))
      ),
      <.div(
        Style.flexbox.fixed,
        props.input,
        props.error.map(<.p(Static.error, Style.margin.top8, _))
      )
    )
  }

  private def render(props: Props): VdomElement = props.layout match {
    case LayoutHor => renderHor(props)
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
