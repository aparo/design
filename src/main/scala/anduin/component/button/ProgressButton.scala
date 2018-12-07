// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.button

import anduin.component.progressindicators.CircleIndicator
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class ProgressButton(
  status: ProgressButton.Status = ProgressButton.Status.Default,
  labels: ProgressButton.Status => String,
  // passing through
  onClick: Callback = Callback.empty,
  color: ButtonStyle.Color = ButtonStyle.ColorWhite,
  tpe: Button.Tpe = Button.TpeButton,
  isDisabled: Boolean = false,
  isFullWidth: Boolean = false,
  style: ButtonStyle.Style = ButtonStyle.StyleFull
) {
  def apply(): VdomElement = {
    ProgressButton.component(this)
  }
}
// scalastyle:on multiple.string.literals

object ProgressButton {

  private val ComponentName = this.getClass.getSimpleName

  sealed trait Status
  object Status {
    case object Default extends Status
    case object Loading extends Status
    case object Disabled extends Status
    case object Success extends Status
    case object Error extends Status
  }

  private class Backend(backendScope: BackendScope[ProgressButton, Unit]) {
    val _ = backendScope

    def render(props: ProgressButton): VdomElement = {
      Button(
        tpe = props.tpe,
        color = props.color,
        style = props.style,
        onClick = props.onClick,
        isDisabled = props.status == Status.Disabled || props.status == Status.Loading || props.isDisabled,
        isFullWidth = props.isFullWidth
      )(
        if (props.status == Status.Loading) {
          <.div(Style.margin.right8, CircleIndicator()())
        } else {
          EmptyVdom
        },
        props.labels(props.status)
      )
    }
  }

  private val component = ScalaComponent
    .builder[ProgressButton](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
