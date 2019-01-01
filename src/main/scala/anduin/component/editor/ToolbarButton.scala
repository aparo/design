// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.component.editor

import anduin.component.button.Button
import anduin.component.tooltip.Tooltip
// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[editor] final case class ToolbarButton(
  key: String,
  tip: String,
  active: Boolean,
  onClick: Callback
) {
  def apply(children: VdomNode*): VdomElement = {
    ToolbarButton.component.withKey(key)(this)(children: _*)
  }
}

private[editor] object ToolbarButton {

  private val ComponentName = this.getClass.getSimpleName

  private class Backend(backendScope: BackendScope[ToolbarButton, Unit]) {

    val _ = backendScope

    def render(props: ToolbarButton, children: PropsChildren): VdomElement = {
      Tooltip(
        targetTag = <.span,
        renderTarget = Button(
          style = Button.Style.Minimal(isSelected = props.active),
          onClick = props.onClick
        )(children),
        renderContent = () => props.tip,
        isDisabled = props.tip.nonEmpty
      )()
    }
  }

  private val component = ScalaComponent
    .builder[ToolbarButton](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
