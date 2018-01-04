// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.slate

import scala.scalajs.js

import anduin.component.icon.Iconv2
import anduin.scalajs.slate.Slate.{Change, Value}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

private[slate] final case class AlignButtonBar(
  value: Value,
  onChange: Change => Callback
) {
  def apply(): ScalaComponent.Unmounted[_, _, _] = AlignButtonBar.component(this)
}

private[slate] object AlignButtonBar {

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[AlignButtonBar, _]) {

    private def hasAlign(value: Value) = {
      value.blocks.some(block => block.nodeType == TextAlignNode.nodeType)
    }

    private def getAlign(value: Value) = {
      val data = value.blocks.filter(block => block.nodeType == TextAlignNode.nodeType).first().data
      DataUtil.value(data, "textAlign")
    }

    def render(props: AlignButtonBar): VdomElement = {
      <.div(
        List(
          ("left", Iconv2.leftAlign(), "Left align"),
          ("center", Iconv2.centerAlign(), "Center align"),
          ("right", Iconv2.rightAlign(), "Right align")
        ).toVdomArray { case (align, icon, tip) =>
          ToolbarButton(
            key = align,
            tip = tip,
            active = hasAlign(props.value) && getAlign(props.value) == align,
            onClick = {
              val change = props.value.change().setBlock(js.Dynamic.literal(
                `type` = TextAlignNode.nodeType,
                data = js.Dynamic.literal(
                  textAlign = align,
                  originalType = props.value.blocks.first().nodeType
                )
              ))
              props.onChange(change)
            }
          )(icon)
        }
      )
    }
  }

  private val component = ScalaComponent.builder[AlignButtonBar](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
