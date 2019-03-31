package anduin.guide.pages.components.textbox

import anduin.component.field.Field
import anduin.guide.components._
import anduin.component.input.textbox.{TextBox, TextBoxTpe}
import anduin.guide.app.main.Pages
import anduin.mcro.Source
import anduin.scalajs.textmask.TextMask
import japgolly.scalajs.react.vdom.html_<^._
import anduin.style.Style

object PageTextBox {

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Text Box", Some(TextBox))(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Text boxes let users enter and edit text:
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        DemoState.Str("Anduin", (value, onChange) => {
          <.div(Style.width.px256)(
            TextBox(value = value, onChange = onChange)()
          )
        })()
      }))(),
      Markdown(
        """
          |# Type
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val d = TextMask.RegExp("\\d")
        val tpes = List[(TextBoxTpe, String)](
          (TextBox.Tpe.Password, ""),
          (TextBox.Tpe.Text, ""),
          (TextBox.Tpe.EmailNative, ""),
          (TextBox.Tpe.EmailMask, ""),
          (TextBox.Tpe.DateNative, ""),
          (TextBox.Tpe.DateMask, ""),
          (TextBox.Tpe.Currency, ""),
          (TextBox.Tpe.Percentage, ""),
          (TextBox.Tpe.NumberInt, ""),
          (TextBox.Tpe.NumberFloat, ""),
          (TextBox.Tpe.Array(List(d, d, d, d)), "4 digits"),
          (TextBox.Tpe.Func(s => List.fill(s.length)(d)), "n digits"),
          (TextBox.Tpe.Area(3), "")
        )
        val layout = Field.Layout.Hor(right = Field.Layout.Grow(2))
        val nodes = tpes.toVdomArray { tpe =>
          val id = tpe._1.getClass.getSimpleName
          <.div(^.key := id, Style.margin.bottom16)(
            DemoState.Str("", (value, onChange) => {
              val label = Some(s"$id ($value)")
              Field(layout, id, label)({
                TextBox(value, onChange, tpe._1, tpe._2)()
              })
            })()
          )
        }
        <.div(nodes)
      }))()
    )
  }
}
