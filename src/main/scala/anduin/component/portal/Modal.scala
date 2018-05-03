// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.portal

import org.scalajs.dom.raw.{Element, HTMLElement}

import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Modal(
  title: String,
  size: Modal.Size = Modal.SizeSmall,
  isOpen: Boolean = false,
  // ===
  isClosable: Boolean = true,
  isClosableOnEsc: Boolean = true,
  isClosableOnOutsideClick: Boolean = true,
  // ===
  // (open callback) => target Vdom
  renderTarget: (Callback) => VdomNode,
  // (title, close callback, isCloseable) => header Vdom
  renderHeader: (String, Boolean, Callback) => VdomNode = Modal.defaultRenderHeader,
  // (close callback) => content Vdom
  renderContent: Callback => VdomNode,
  // ===
  onOpen: Callback = Callback.empty,
  onClose: Callback = Callback.empty
) {
  def apply(): VdomElement = Modal.component(this)
}

object Modal {

  private val ComponentName = this.getClass.getSimpleName

  private def defaultRenderHeader(title: String, isClosable: Boolean, close: Callback) =
    ModalHeader(title = title, isClosable = isClosable, close = close)()

  sealed trait Size { val style: TagMod }
  case object SizeSmall extends Size { val style: TagMod = ^.width := "480px" }
  case object SizeMedium extends Size { val style: TagMod = ^.width := "960px" }
  case object SizeLarge extends Size { val style: TagMod = ^.width := "1160px" }
  case object SizeFull extends Size { val style: TagMod = Style.width.pc100.height.pc100 }

  private case class Backend(scope: BackendScope[Modal, _]) {

    private val modalRef = Ref[HTMLElement]

    private def isPortalClicked(target: Element) = {
      val t = for {
        modal <- modalRef.get
      } yield !target.contains(modal)
      t.asCallback.map(_.getOrElse(false))
    }

    def render(props: Modal): VdomElement = {
      PortalWrapper(
        isOpen = props.isOpen,
        closeOnEsc = props.isClosable && props.isClosableOnEsc,
        closeOnOutsideClick = props.isClosable && props.isClosableOnOutsideClick,
        isPortalClicked = (target, _) => isPortalClicked(target),
        onOpen = props.onOpen,
        onClose = props.onClose,
        renderTarget = (open, _, _) => props.renderTarget(open),
        renderContent = (close, _) => {
          <.div(
            Style.position.fixed.coordinate.fill.zIndex.idx999,
            ^.background := "rgba(48, 64, 77, 0.9)",
            TagMod.when(props.size != SizeFull) { Style.overflow.autoY.padding.ver32 },
            <.div.withRef(modalRef)(
              Style.backgroundColor.gray1.borderRadius.px2.overflow.hidden.margin.horAuto,
              props.size.style,
              props.renderHeader(props.title, props.isClosable, close),
              props.renderContent(close)
            )
          )
        }
      )()
    }
  }

  private val component = ScalaComponent
    .builder[Modal](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
