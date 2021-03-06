package design.anduin.docs.components

import design.anduin.style.Style
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

final case class Example(
  content: (String, VdomElement),
  isBgGray: Boolean = false
) {
  def apply(): VdomElement = Example.component(this)
}

object Example {

  private type Props = Example

  private def renderElement(props: Props, element: VdomElement): VdomElement = {
    <.div(
      Style.borderRadius.px4.border.all.borderColor.gray3,
      Style.padding.all16,
      // Sometimes we need a gray background
      if (props.isBgGray) Style.background.gray2
      else Style.background.gray0,
      // Ensure the example is shown in correct font size
      // and line height (since these values in Guide is
      // bigger than in the actual app
      Style.fontSize.px13.lineHeight.px20.fontFamily.sans,
      element
    )
  }

  private def render(props: Props): VdomElement = {
    val (source, element) = props.content
    <.div(
      Style.borderRadius.px4.border.all.borderColor.gray3,
      Style.background.gray1.overflow.hidden,
      <.div(Style.padding.all4, renderElement(props, element)),
      <.div(CodeBlock(content = source)())
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
