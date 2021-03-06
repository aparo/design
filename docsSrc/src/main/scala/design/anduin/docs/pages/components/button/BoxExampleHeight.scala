// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.docs.pages.components.button

import design.anduin.components.button.{Button, ButtonStyle}
import design.anduin.components.button.Button.Height._
import design.anduin.components.tag.Tag
import design.anduin.docs.components.ExampleSimple
import design.anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class BoxExampleHeight() {
  def apply(): VdomElement = BoxExampleHeight.component(this)
}

object BoxExampleHeight {

  private type Props = BoxExampleHeight

  private val sizes = List(Fix24, Fix32, Fix40)

  private val tag: VdomElement = {
    <.div(
      Style.flexbox.flex.flexbox.justifyCenter.margin.top8,
      Tag(color = Tag.Light.Primary)("Default")
    )
  }

  private def renderButton(props: Props)(height: ButtonStyle.Height): VdomElement = {
    val name = height.getClass.getSimpleName
    val tagOpt = if (height == Fix32) Some(tag) else None
    val button = Button(style = Button.Style.Full(height = height))(name)
    <.div(^.key := name, Style.margin.right8, button, tagOpt)
  }

  private def render(props: Props): VdomElement = {
    ExampleSimple()({
      <.div(
        Style.flexbox.flex,
        sizes.toVdomArray(renderButton(props))
      )
    })
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
