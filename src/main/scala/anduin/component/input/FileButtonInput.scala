// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.input

import anduin.component.button.{Button, ButtonStyle}
import anduin.component.icon.Icon
import anduin.style.Style
import org.scalajs.dom.FileList

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class FileButtonInput(
  color: ButtonStyle.Color = ButtonStyle.ColorWhite,
  size: ButtonStyle.Size = ButtonStyle.Size32,
  style: ButtonStyle.Style = ButtonStyle.StyleFull,
  isFullWidth: Boolean = false,
  isSelected: Boolean = false,
  isDisabled: Boolean = false,
  icon: Option[Icon.Name] = None,
  // Specific behaviours for FileButton
  onChange: FileList => Callback,
  shouldClearAfterChanged: Boolean = false,
  acceptTypes: String = "",
  isMultiple: Boolean
) {
  def apply(children: VdomNode*): VdomElement = {
    FileButtonInput.component(this)(children: _*)
  }
}

object FileButtonInput {

  private val ComponentName = this.getClass.getSimpleName

  private class Backend(scope: BackendScope[FileButtonInput, _]) {

    private def onChange(e: ReactEventFromInput): Callback = {
      for {
        props <- scope.props
        _ <- props.onChange(e.target.files)
        _ <- Callback { e.target.value = "" }.when(props.shouldClearAfterChanged)
      } yield ()
    }

    def render(props: FileButtonInput, children: PropsChildren): VdomElement = {
      if (props.isDisabled) {
        // when disabled we simply swap it with a disabled Button with
        // corresponding styles. This is because the actual input and the
        // element that has style (div) are not the same element, so
        // ":disabled" styles are not triggered
        Button(
          props.color,
          props.size,
          props.style,
          props.isFullWidth,
          props.isSelected,
          isDisabled = true,
          icon = props.icon
        )(children)
      } else {
        <.div(
          Style.position.relative,
          ButtonStyle.getStyles(
            color = props.color,
            size = props.size,
            style = props.style,
            isSelected = props.isSelected,
            isFullWidth = props.isFullWidth
          ),
          ButtonStyle.renderIcon(props.icon, children),
          children,
          // this is the actual input element that opens file browser
          <.input(
            Style.position.absolute.coordinate.fill.opacity.pc0.width.pc100.height.pc100.cursor.pointer,
            ^.tpe := "file",
            ^.accept := props.acceptTypes,
            ^.multiple := props.isMultiple,
            ^.onChange ==> onChange
          )
        )
      }
    }
  }

  private val component = ScalaComponent
    .builder[FileButtonInput](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
