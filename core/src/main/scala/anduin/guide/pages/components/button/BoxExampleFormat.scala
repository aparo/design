// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.pages.components.button

import anduin.component.button.Button
import anduin.component.icon.Icon
import anduin.component.menu.VerticalDivider
import anduin.guide.components.ExampleSimple
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class BoxExampleFormat(
  ) {
  def apply(): VdomElement = BoxExampleFormat.component(this)
}

object BoxExampleFormat {

  private type Props = BoxExampleFormat

  private case class State(isBold: Boolean, isItalic: Boolean)

  private class Backend(scope: BackendScope[Props, State]) {

    def render(props: Props, state: State): VdomElement = {
      ExampleSimple()({
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          Button(
            style = Button.Style.Ghost(isSelected = state.isBold, icon = Some(Icon.Glyph.Bold)),
            onClick = scope.modState(_.copy(isBold = !state.isBold))
          )(),
          <.div(Style.margin.left4),
          Button(
            style = Button.Style.Ghost(isSelected = state.isItalic, icon = Some(Icon.Glyph.Italic)),
            onClick = scope.modState(_.copy(isItalic = !state.isItalic))
          )(),
          <.div(Style.margin.left16),
          VerticalDivider()(),
          <.div(Style.margin.left16),
          <.p(
            Style.fontSize.px17,
            TagMod.when(state.isBold)(Style.fontWeight.bold),
            TagMod.when(state.isItalic)(Style.fontStyle.italic),
            "Sample text"
          )
        )
      })
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialState(State(isBold = false, isItalic = false))
    .renderBackend[Backend]
    .build
}
