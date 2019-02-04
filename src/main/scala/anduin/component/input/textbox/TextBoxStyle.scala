// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.component.input.textbox

import anduin.component.icon.Icon
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

// Internal styling logic of TextBox
private[component] object TextBoxStyle {

  private type Props = TextBox

  // Border

  private val borderStatic = Style.border.all.borderWidth.px1.borderRadius.px2

  // Common styles for both input and context

  private def getCommon(props: Props): TagMod = TagMod(
    borderStatic,
    Style.padding.hor12.padding.ver8,
    props.tpe.getSize(props.size)
  )

  // Input

  private val inputStatic = TagMod(
    borderStatic,
    Style.display.block.width.pc100.padding.hor12.padding.ver8,
    Style.focus.spread.focus.border.transition.allWithShadow
  )

  private def getColor(props: Props): TagMod =
    if (props.isDisabled) Style.color.gray6 else Style.color.gray8

  private def getBackground(props: Props): TagMod = {
    if (props.isReadOnly) {
      Style.background.gray1
    } else if (props.isDisabled) {
      Style.background.gray2
    } else {
      Style.background.white
    }
  }

  private def getBorderColor(props: Props): TagMod = props.status match {
    case TextBox.StatusInvalid => Style.borderColor.red4
    case TextBox.StatusValid   => Style.borderColor.green4
    case _                     => Style.borderColor.gray4
  }

  def getInput(props: Props): TagMod = TagMod(
    inputStatic,
    getCommon(props),
    getBorderColor(props),
    getBackground(props),
    getColor(props),
    TagMod.when(props.context.isDefined) {
      Style.borderRadius.right
    }
  )

  // Context

  private val contextStatic = TagMod(
    Style.background.gray2.color.gray6,
    Style.borderRadius.left.borderColor.gray4,
    Style.flexbox.flex.flexbox.itemsCenter,
    ^.borderRightWidth := "0"
  )

  def getContext(props: Props): TagMod =
    TagMod(getCommon(props), contextStatic)

  // Icon

  private val iconWrapper = TagMod(
    Style.position.absolute.margin.right8.margin.verAuto,
    Style.position.pinTop.position.pinBottom.position.pinRight,
    Style.background.white.height.px16
  )

  def getIcon(props: Props): Option[VdomElement] = props.status match {
    case TextBox.StatusValid =>
      Some(<.div(iconWrapper, Icon(Icon.Glyph.Check)(), Style.color.green4))
    // case StatusBusy => ??? should have some icon here
    case _ => None
  }
}
