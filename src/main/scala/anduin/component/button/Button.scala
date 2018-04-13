// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.button

import anduin.component.util.ComponentUtils
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Button(
  tpe: Button.Tpe = Button.TpeDefault,
  color: Button.Color = Button.ColorWhite,
  size: Button.Size = Button.SizeMedium,
  onClick: Callback = Callback.empty,
  isMinimal: Boolean = false,
  isFullWidth: Boolean = false,
  isDisabled: Boolean = false, // if tpe != link
  href: String = "" // if tpe == link
) {
  def apply(children: VdomNode*): VdomElement = {
    Button.component(this)(children: _*)
  }
}

object Button {

  private final val ComponentName = ComponentUtils.name(this)

  sealed trait Color {
    val minimal: TagMod
    val full: TagMod
    val shared: TagMod
  }
  case object ColorWhite extends Color {
    val minimal: TagMod = Style.color.gray8.hover.shadowBorderGray4.active.shadowBorderGray4
    val full: TagMod = TagMod(minimal, Style.backgroundColor.gray1.shadow.borderGray4s)
    private val sharedHover = Style.hover.colorPrimary4.hover.backgroundWhite
    private val sharedActive = Style.active.colorPrimary4.active.backgroundGray2
    val shared: TagMod = TagMod(sharedHover, sharedActive)
  }
  private case object ColorBase {
    val minimal: TagMod = Style.hover.colorWhite.active.colorWhite
    val full: TagMod = Style.color.white.shadow.size2
  }
  case object ColorPrimary extends Color {
    private val selfMinimal = Style.color.primary4.hover.shadowBorderPrimary5s.active.shadowBorderPrimary5s
    val minimal: TagMod = TagMod(ColorBase.minimal, selfMinimal)
    val full: TagMod = TagMod(ColorBase.full, Style.backgroundColor.primary4.shadow.borderPrimary5s)
    val shared: TagMod = Style.hover.backgroundPrimary3.active.backgroundPrimary5
  }
  case object ColorSuccess extends Color {
    private val selfMinimal = Style.color.success4.hover.shadowBorderSuccess5s.active.shadowBorderSuccess5s
    val minimal: TagMod = TagMod(ColorBase.minimal, selfMinimal)
    val full: TagMod = TagMod(ColorBase.full, Style.backgroundColor.success4.shadow.borderSuccess5s)
    val shared: TagMod = Style.hover.backgroundSuccess3.active.backgroundSuccess5
  }
  case object ColorWarning extends Color {
    private val selfMinimal = Style.color.warning4.hover.shadowBorderWarning5s.active.shadowBorderWarning5s
    val minimal: TagMod = TagMod(ColorBase.minimal, selfMinimal)
    val full: TagMod = TagMod(ColorBase.full, Style.backgroundColor.warning4.shadow.borderWarning5s)
    val shared: TagMod = Style.hover.backgroundWarning3.active.backgroundWarning5
  }
  case object ColorDanger extends Color {
    private val selfMinimal = Style.color.danger4.hover.shadowBorderDanger5s.active.shadowBorderDanger5s
    val minimal: TagMod = TagMod(ColorBase.minimal, selfMinimal)
    val full: TagMod = TagMod(ColorBase.full, Style.backgroundColor.danger4.shadow.borderDanger5s)
    val shared: TagMod = Style.hover.backgroundDanger3.active.backgroundDanger5
  }

  sealed trait Tpe { val value: String }
  case object TpeLink extends Tpe { val value = "link" }
  case object TpeDefault extends Tpe { val value = "button" }
  case object TpeSubmit extends Tpe { val value = "submit" }
  case object TpeReset extends Tpe { val value = "reset" }

  sealed trait Size { val style: TagMod }
  case object SizeLarge extends Size { val style: TagMod = Style.padding.ver12.padding.hor16.fontSize.size5 }
  case object SizeMedium extends Size { val style: TagMod = Style.padding.ver8.padding.hor16.fontSize.size6 }
  case object SizeSmall extends Size { val style: TagMod = Style.padding.ver4.padding.hor8.fontSize.size7 }

  private val commonStyles = TagMod(
    Style.lineHeight.size1.fontWeight.size500.borderRadius.r1,
    Style.focus.outline.transition.allWithOutline,
    // disabled styles
    Style.disabled.colorGray6.disabled.backgroundGray2,
    Style.disabled.shadowBorderGray4,
    // help with usage with icon
    Style.flexbox.flex
  )

  private case class Backend(scope: BackendScope[Button, _]) {

    private def onClick(e: ReactEventFromHtml) = {
      for {
        _ <- e.preventDefaultCB
        _ <- e.stopPropagationCB
        props <- scope.props
        _ <- props.onClick
      } yield ()
    }

    def render(props: Button, children: PropsChildren): VdomElement = {
      val commonMods = TagMod(
        commonStyles,
        // specific styles
        TagMod.when(props.isFullWidth) {
          Style.width.percent100.flexbox.justifyCenter
        },
        props.size.style,
        if (props.isMinimal) props.color.minimal else props.color.full,
        props.color.shared,
        // behaviours
        ^.onClick ==> onClick,
        children
      )
      props.tpe match {
        // link that looks like a button
        case TpeLink => <.a(^.href := props.href, commonMods)
        // real button
        case _ =>
          val tpe = props.tpe.value
          <.button(^.tpe := tpe, ^.disabled := props.isDisabled, commonMods)
      }
    }
  }

  private val component = ScalaComponent
    .builder[Button](ComponentName)
    .stateless
    .renderBackendWithChildren[Backend]
    .build
}
