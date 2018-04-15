// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.portal

import org.scalajs.dom.document
import org.scalajs.dom.raw.Element

import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Popover(
  popoverClassName: String = Style.padding.all8.value,
  position: Popover.Position = Popover.PositionBottom,
  verticalOffset: Double = 0,
  horizontalOffset: Double = 0,
  closeOnEsc: Boolean = true,
  closeOnInsideClick: Boolean = true,
  closeOnOutsideClick: Boolean = true,
  isPortalClicked: (Element, Element) => CallbackTo[Boolean] = PortalWithState.IsPortalClicked,
  renderTarget: (Callback, Status) => VdomElement,
  renderContent: Callback => VdomElement
) {
  def apply(): VdomElement = {
    Popover.component(this)
  }
}

object Popover {

  // Position
  sealed trait Position {
    def className: String
  }
  case object PositionTopLeft extends Position { val className = "-top-left" }
  case object PositionTop extends Position { val className = "-top" }
  case object PositionTopRight extends Position { val className = "-top-right" }

  case object PositionRightTop extends Position { val className = "-right-top" }
  case object PositionRight extends Position { val className = "-right" }
  case object PositionRightBottom extends Position { val className = "-right-bottom" }

  case object PositionBottomLeft extends Position { val className = "-bottom-left" }
  case object PositionBottom extends Position { val className = "-bottom" }
  case object PositionBottomRight extends Position { val className = "-bottom-right" }

  case object PositionLeftTop extends Position { val className = "-left-top" }
  case object PositionLeft extends Position { val className = "-left" }
  case object PositionLeftBottom extends Position { val className = "-left-bottom" }

  private val ComponentName = this.getClass.getSimpleName

  private case class Backend(scope: BackendScope[Popover, _]) {

    private val targetRef = Ref[Element]
    private val portalRef = Ref[Element]

    // scalastyle:off cyclomatic.complexity
    private def onOpenPortal = {
      for {
        props <- scope.props
        target <- targetRef.get
        content <- portalRef.get
        _ <- Callback {
          val rect = target.getBoundingClientRect()
          val (h, w) = (content.clientHeight, content.clientWidth)
          val (top, left) = props.position match {
            case PositionTopLeft  => (rect.top - h, rect.left)
            case PositionTop      => (rect.top - h, rect.left + 0.5 * rect.width - 0.5 * w)
            case PositionTopRight => (rect.top - h, rect.left + rect.width - w)

            case PositionRightTop    => (rect.top, rect.left + rect.width)
            case PositionRight       => (rect.top + 0.5 * rect.height - 0.5 * h, rect.left + rect.width)
            case PositionRightBottom => (rect.top + rect.height - h, rect.left + rect.width)

            case PositionBottomRight => (rect.top + rect.height, rect.left + rect.width - w)
            case PositionBottom      => (rect.top + rect.height, rect.left + 0.5 * rect.width - 0.5 * w)
            case PositionBottomLeft  => (rect.top + rect.height, rect.left)

            case PositionLeftTop    => (rect.top, rect.left - w)
            case PositionLeft       => (rect.top + 0.5 * rect.height - 0.5 * h, rect.left - w)
            case PositionLeftBottom => (rect.top + rect.height - h, rect.left - w)
          }
          val offsetTop = top + props.verticalOffset + document.body.scrollTop
          val offsetLeft = left + props.horizontalOffset + document.body.scrollLeft
          content.setAttribute("style", s"top: ${offsetTop}px; left: ${offsetLeft}px")
        }
      } yield ()
    }
    // scalastyle:on cyclomatic.complexity

    def render(props: Popover): VdomElement = {
      PortalWithState(
        onOpen = onOpenPortal,
        closeOnEsc = props.closeOnEsc,
        closeOnInsideClick = props.closeOnInsideClick,
        closeOnOutsideClick = props.closeOnOutsideClick,
        isPortalClicked = props.isPortalClicked,
        renderChildren = renderer =>
          <.div(
            <.div.withRef(targetRef)(
              props.renderTarget(renderer.openPortal, renderer.status)
            ),
            renderer.portal(
              <.div.withRef(portalRef)(
                ^.classSet(
                  "at-popover" -> true,
                  props.position.className -> true,
                  props.popoverClassName -> true,
                  "-show" -> (renderer.status == StatusOpen),
                  "-hide" -> (renderer.status == StatusHide)
                ),
                props.renderContent(renderer.closePortal)
              )
            )
        )
      )()
    }
  }

  private val component = ScalaComponent
    .builder[Popover](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
