package design.anduin.docs.pages.components.stepper

import design.anduin.docs.components._
import design.anduin.components.button.Button
import design.anduin.components.stepper.Stepper
import design.anduin.docs.app.main.Pages
import design.anduin.macros.Source
import design.anduin.style.Style
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

object PageStepper {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Stepper", Some(Stepper))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate({
          val button = (callback: Option[Callback]) => {
            Button(onClick = callback.getOrEmpty, isDisabled = callback.isEmpty)
          }
          val footer = (props: Stepper.RenderProps) => {
            <.div(
              Style.flexbox.flex,
              <.div(button(props.back)("back"), Style.margin.right8),
              <.div(button(props.next)("next"))
            )
          }
          Stepper(
            // xxxxxxxxxxxxxxxxxxxxxxx
            Vector(
              Stepper.Step("Title 1", p => <.div("content 1", footer(p))),
              Stepper.Step("Unbalanced Title 2", p => <.div("content 2", footer(p))),
              Stepper.Step("Title 3", p => <.div("content 3", footer(p)))
            )
          )()
        }),
        isBgGray = true
      )()
    )
  }
}
