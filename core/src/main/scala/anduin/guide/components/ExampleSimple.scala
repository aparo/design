package anduin.guide.components

import anduin.guide.components.ExampleSimple.BgColor
import anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class ExampleSimple(
  label: String = "",
  bgColor: ExampleSimple.BgColor = BgColor.White
) {
  def apply(children: VdomNode*): VdomElement = {
    ExampleSimple.component(this)(children: _*)
  }
}

object ExampleSimple {

  private type Props = ExampleSimple

  sealed abstract class BgColor(val inner: TagMod, val outer: TagMod)
  object BgColor {
    object White extends BgColor(Style.background.white, Style.background.gray1)
    object Gray2 extends BgColor(Style.background.gray2, Style.background.gray1)
  }

  private val border = Style.borderRadius.px4.border.all.borderColor.gray3

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val figure = <.figure(
      props.bgColor.outer,
      border,
      Style.padding.all4.fontSize.px13.lineHeight.px20.fontFamily.sans,
      // example
      <.div(border, Style.padding.all16, props.bgColor.inner, children),
      // label
      <.figcaption(Style.padding.hor16.padding.top4, Markdown(props.label)()).when(!props.label.isEmpty)
    )
    <.div(Style.padding.ver16, figure)
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
