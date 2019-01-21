// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.component.button

import anduin.component.icon.Icon
import anduin.style.{Style => SStyle}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Button(
  style: Button.Style = Button.Style.Full(),
  tpe: ButtonType = Button.Tpe.Plain(),
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

  trait Height {
    def square: TagMod
    def rect: TagMod
  }
  object Height {
    object Fix24 extends ButtonStyle.Height.Fix24
    object Fix32 extends ButtonStyle.Height.Fix32
    object Fix40 extends ButtonStyle.Height.Fix40
    object Free extends ButtonStyle.Height.Free
  }

  trait Color
  object Color {
    object White extends ButtonStyle.Color.White
    object Black extends ButtonStyle.Color.Black
    object Blue extends ButtonStyle.Color.Blue
    object Green extends ButtonStyle.Color.Green
    object Red extends ButtonStyle.Color.Red
  }

  trait Style {
    def icon: Option[Icon.Name]
    def container: TagMod
    def body: TagMod
    def overlay: TagMod
    // these are separated because isDisabled is
    // not a property of Style but Button
    def colorDisabled: TagMod
    def colorNormal: TagMod
    // these are separated because children is
    // not a property of Style but Button
    def sizeSquare: TagMod // Icon with no children
    def sizeRect: TagMod // Icon with children
  }
  object Style {

    final case class Link(
      color: Color = Color.Blue,
      isBlock: Boolean = false
    ) extends ButtonStyle.BStyle.Link

    final case class Full(
      color: Color = Color.White,
      height: Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.BStyle.Full

    final case class Ghost(
      color: Color = Color.Black,
      height: Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.BStyle.Ghost

    final case class Minimal(
      color: Color = Color.Black,
      height: Height = Height.Fix32,
      icon: Option[Icon.Name] = None,
      isFullWidth: Boolean = false,
      isSelected: Boolean = false,
      isBusy: Boolean = false
    ) extends ButtonStyle.BStyle.Minimal
  }

  private[component] def getStyles(props: Props, children: Option[PropsChildren]): TagMod = TagMod(
    props.style.container, {
      val isIconOnly = props.style.icon.isDefined && children.exists(_.isEmpty)
      if (isIconOnly) props.style.sizeSquare else props.style.sizeRect
    },
    if (props.isDisabled) props.style.colorDisabled else props.style.colorNormal
  )

  private[component] def getBehaviours(props: Props): TagMod = TagMod(
    if (props.isDisabled) props.tpe.disabled else props.tpe.normal,
    TagMod.when(!props.isDisabled) {
      TagMod(
        ^.onClick --> props.onClick,
        ^.onDoubleClick --> props.onDoubleClick
      )
    }
  )

  private[component] def getContent(props: Props, children: PropsChildren): TagMod = {
    val icon = props.style.icon.map(name => {
      val margin = TagMod.when(children.nonEmpty)(SStyle.margin.right8)
      <.span(margin, Icon(name = name)())
    })
    TagMod(
      <.span(props.style.body, icon, children),
      props.style.overlay
    )
  }

  private[component] def getMods(props: Props, children: PropsChildren): TagMod = TagMod(
    getStyles(props, Some(children)),
    getBehaviours(props),
    getContent(props, children)
  )

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val content = getMods(props, children)
    props.tpe match {
      case _: Tpe.Link => <.a(content)
      case _           => <.button(content)
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
// scalastyle:on number.of.types
