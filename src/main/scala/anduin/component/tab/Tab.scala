// Copyright (C) 2014-2017 Anduin Transactions Inc.

package anduin.component.tab

import org.scalajs.dom.html.Div

import anduin.component.util.JavaScriptUtils

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object Tab {

  private val ComponentName = this.getClass.getSimpleName

  type ClickPanelHandler = Int => Callback

  final case class Header(children: TagMod*)
  final case class Content(children: TagMod*)

  final case class Panel(
    header: Header,
    active: Boolean = false,
    headerClass: String = "",
    content: Content = Content(),
    target: String = JavaScriptUtils.VoidMethod,
    onClick: Option[ClickPanelHandler] = None,
    enabled: Boolean = true,
    lazyloadContent: Boolean = false)

  private case class Props(
    panels: Seq[Panel],
    activeIndex: Int,
    isVertical: Boolean,
    additionalTabHeaderClasses: String = "",
    additionalTabContentClasses: String = ""
  )
  private case class State(activeIndex: Int, isVertical: Boolean = false)

  private case class Backend(t: BackendScope[Props, State]) {

    def onClickTab(panel: Panel, index: Int)(evt: ReactEventFromHtml): Callback = {
      for {
        _ <- evt.preventDefaultCB
        _ <- t.modState(_.copy(activeIndex = index))
        _ <- Callback.traverseOption(panel.onClick) { onClick =>
          onClick(index)
        }
      } yield ()
    }

    def render(props: Props, state: State): VdomTagOf[Div] = {
      val verticalTab = if (state.isVertical) {
        "tab-vertical -left"
      } else {
        ""
      }

      <.div(^.cls := "tab-wrapper w-100",
        <.ul(^.cls := s"tab-nav $verticalTab ${props.additionalTabHeaderClasses}",
          props.panels.zipWithIndex.toTagMod { case (item, index) =>
            <.li(
              ^.classSet(
                s"tab-item ${item.headerClass}" -> true,
                "-active" -> (index == state.activeIndex),
                "disabled" -> !item.enabled
              ),
              (^.onClick ==> onClickTab(item, index))
                .when(item.target == JavaScriptUtils.VoidMethod && item.enabled),
              <.a(
                ^.cls := "link",
                ^.href := item.target,
                TagMod(item.header.children: _*)
              )
            )
          }
        ),
        TagMod.when(props.panels.nonEmpty)(<.div(
          ^.cls := s"tab-content ${props.additionalTabContentClasses}",
          props.panels.zipWithIndex.toTagMod { case (item, index) =>
            <.div(
              ^.key := index,
              ^.classSet(
                "tab-panel" -> true,
                "active" -> (index == state.activeIndex)
              ),
              TagMod.when(!item.lazyloadContent || index == state.activeIndex)(TagMod(item.header.children: _*))
            )
          }
        ))
      )
    }
  }

  private val component = ScalaComponent.builder[Props](ComponentName)
    .initialStateFromProps { props =>
      // Determine the active tab index if it is set
      val index = props.panels.indexWhere(_.active)
      val activeIndex = if (index < 0) props.activeIndex else index
      State(activeIndex, props.isVertical)
    }
    .renderBackend[Backend]
    .componentWillReceiveProps { scope =>
      val nextActiveIndex = scope.nextProps.panels.indexWhere(_.active)
      Callback.when(nextActiveIndex >= 0 && nextActiveIndex != scope.state.activeIndex) {
        scope.modState(_.copy(activeIndex = nextActiveIndex))
      }
    }
    .build

  def apply(
    activeIndex: Int = 0,
    isVertical: Boolean = false,
    additionalTabHeaderClasses: String = "",
    additionalTabContentClasses: String = ""
  )(panels: Panel*): ScalaComponent.Unmounted[_, _, _] = {
    component(Props(panels, activeIndex, isVertical, additionalTabHeaderClasses, additionalTabContentClasses))
  }
}
