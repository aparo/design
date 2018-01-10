// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.option

import anduin.component.icon.Iconv2
import anduin.stylesheet.tachyons.Tachyons

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class CheckMark(
  selected: Boolean,
  disabled: Boolean = false,
  onClick: Callback
) {
  def apply(children: VdomNode*): ScalaComponent.Unmounted[_, _, _] = {
    CheckMark.component(this)(children: _*)
  }
}

object CheckMark {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[CheckMark, _]) {

    def render(props: CheckMark, children: PropsChildren): VdomElement = {
      <.div(
        ^.classSet(
          "at-check-mark" -> true,
          "-disabled" -> props.disabled,
          "-selected" -> props.selected
        ),
        ^.onClick --> props.onClick,
        TagMod.when(props.selected)(
          <.span(
            ^.classSet(
              "at-icon" -> true,
              Tachyons.flexbox.flex.flexbox.justifyCenter.flexbox.itemsCenter.borderRadius.pill.color.white.value -> true
            ),
            Iconv2.checkmark()
          )
        ),
        children
      )
    }
  }

  private val component = ScalaComponent.builder[CheckMark](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
