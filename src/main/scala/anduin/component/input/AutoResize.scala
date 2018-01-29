// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.input

import scala.scalajs.js

import japgolly.scalajs.react.component.Js.UnmountedWithRawType

import anduin.scalajs.reactinputautosize.ReactInputAutosize.RawComponent

// scalastyle:off underscore.import
import japgolly.scalajs.react._
// scalastyle:on underscore.import

object AutoResize {

  private val component = JsComponent[Props, Children.None, Null](RawComponent)

  // See https://github.com/JedWatson/react-input-autosize
  final class Props(
    val value: String = "",
    val onChange: js.UndefOr[js.Function1[ReactEventFromInput, Unit]] = js.undefined,
    val onKeyDown: js.UndefOr[js.Function1[ReactKeyboardEventFromInput, Unit]] = js.undefined
  ) extends js.Object

  def apply(
    value: String,
    onChange: ReactEventFromInput => Callback,
    onKeyDown: ReactKeyboardEventFromInput => Callback
  ): UnmountedWithRawType[_, _, _] = {
    component(
      new Props(
        value = value,
        onChange = js.defined { v =>
          onChange(v).runNow()
        },
        onKeyDown = js.defined { v =>
          onKeyDown(v).runNow()
        }
      )
    )
  }
}
