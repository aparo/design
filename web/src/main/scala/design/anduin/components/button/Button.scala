// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.components.button

import design.anduin.components.icon.Icon
import design.anduin.style.{Style => SStyle}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Button(
  style: ButtonStyle = Button.Style.Full(),
  tpe: ButtonType = Button.Tpe.Plain(),
  title: String = "",
  // actually these 2 props don't make much sense when being used with
  // Button.Tpe.Link. However, they are placed here (top-level) due to our
  // massive current usages.
  isDisabled: Boolean = false,
  onClick: Callback = Callback.empty,
  onDoubleClick: Callback = Callback.empty
) {
  def apply(children: VdomNode*): VdomElement = {
    Button.component(this)(children: _*)
  }
}

// scalastyle:off number.of.types
object Button {

  private type Props = Button

  // Tpe ===

  object Target {
    object Blank extends ButtonType.Target.Blank
    object Self extends ButtonType.Target.Self
    object Parent extends ButtonType.Target.Parent
  }

  object Tpe {
    final case class Link(href: String, target: ButtonType.Target = Target.Self) extends ButtonType.Link
    case class Plain(isAutoFocus: Boolean = false) extends ButtonType.Plain
    case class Submit(isAutoFocus: Boolean = false) extends ButtonType.Submit
    case class Reset(isAutoFocus: Boolean = false) extends ButtonType.Reset
  }

  // Style ===

  object Height {
    object Fix24 extends ButtonStyle.Height.Fix24
    object Fix32 extends ButtonStyle.Height.Fix32
    object Fix40 extends ButtonStyle.Height.Fix40
    object Free extends ButtonStyle.Height.Free
  }

  object Color {
    object Gray0 extends ButtonStyle.Color.Gray0
    object Gray9 extends ButtonStyle.Color.Gray9
    object Primary extends ButtonStyle.Color.Primary
    object Success extends ButtonStyle.Color.Success
    object Danger extends ButtonStyle.Color.Danger
  }

  object Style {
    final case class Text(
      color: ButtonStyle.Color = Color.Primary,
      isBlock: Boolean = false
    ) extends ButtonStyle.Text
    final case class Full(
      color: ButtonStyle.Color = Color.Gray0,
      height: ButtonStyle.Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.Full
    final case class Ghost(
      color: ButtonStyle.Color = Color.Gray9,
      height: ButtonStyle.Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.Ghost
    final case class Minimal(
      color: ButtonStyle.Color = Color.Gray9,
      height: ButtonStyle.Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.Minimal
  }

  private[components] def getStyles(props: Props, children: Option[PropsChildren]): TagMod = TagMod(
    props.style.container, {
      val isIconOnly = props.style.iconInfo.isDefined && children.exists(_.isEmpty)
      if (isIconOnly) props.style.sizeSquare else props.style.sizeRect
    },
    if (props.isDisabled) props.style.colorDisabled else props.style.colorNormal
  )

  private[components] def getBehaviours(props: Props): TagMod = TagMod(
    if (props.isDisabled) props.tpe.disabled else props.tpe.normal,
    TagMod.when(!props.isDisabled) {
      TagMod(
        ^.onClick --> props.onClick,
        ^.onDoubleClick --> props.onDoubleClick
      )
    }
  )

  private def getIcon(props: Props, children: PropsChildren): Option[VdomElement] = {
    props.style.iconInfo.map { node =>
      <.span(
        // if there is no children then there is no need for margin
        TagMod.when(children.nonEmpty)(SStyle.margin.right8),
        // if there is no children then icon will use text color. Only when
        // used with children then icon needs to be lighter
        TagMod.when(children.nonEmpty) {
          if (props.isDisabled) props.style.iconColorDisabled else props.style.iconColorNormal
        },
        node()
      )
    }
  }

  private[components] def getContent(props: Props, children: PropsChildren): TagMod = TagMod(
    <.span(props.style.body, getIcon(props, children), children),
    props.style.overlay
  )

  private[components] def getMods(props: Props, children: PropsChildren): TagMod = TagMod(
    getStyles(props, Some(children)),
    getBehaviours(props),
    getContent(props, children)
  )

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val content = getMods(props, children)
    val title = TagMod.when(props.title.nonEmpty)(^.title := props.title)
    props.tpe match {
      case _: Tpe.Link => <.a(title, content)
      case _           => <.button(title, content)
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
// scalastyle:on number.of.types
