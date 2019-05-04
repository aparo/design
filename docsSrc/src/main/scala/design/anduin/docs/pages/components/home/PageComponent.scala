package design.anduin.docs.pages.components.home

import design.anduin.components.button.Button
import design.anduin.components.icon.Icon
import design.anduin.docs.app.main.Pages
import design.anduin.macros.Source
import design.anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._
import design.anduin.docs.components._

object PageComponent {
  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Components", None)(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        """
          |Components helps you reuse styles and functionality.
          |
          |# Global
          |
          |**Components are global packages of `platform`.** They should be
          |available anywhere in our apps:
          |
          |```scala
          |import design.anduin.components.button.Button
          |import design.anduin.components.icon.Icon
          |
          |// Related components are grouped under the same package
          |import design.anduin.components.portal.{Modal, Tooltip, Popover}
          |import design.anduin.components.container.{Table, Well}
          |```
          |
          |# Generic
          |
          |**Components are free from business logic**, so they can be used
          |anywhere inside our platform, or even outside. One should not expect
          |a component to know about any business model (like Deal, User or
          |Entity).
          |
          |In technical words, it means a component does not import anything
          |that is outside of the `platform` project.
          |
          |# Children
          |
          |**Components accept `VdomNode*` as their children.** `VdomNode` is
          |the equivalent of React's `element` or `node`. This means only
          |render-able contents (e.g. `VdomElement`, `VdomNode` such as
          |text…) can be used as a component's children.
          |
          |**Components does not accept `TagMod` as children** to avoid implicit
          |modification from consumer (e.g. adding classes or attributes). A
          |component should explicitly define the attributes that can be
          |modified by its consumers.
          |
          |```scala
          |Button(...)(
          |  Icon(...)(), // GOOD
          |  "Upload", // GOOD
          |  Style.margin.right8 // BAD
          |)
          |```
          |
          |Learn more:
          |- [`​VdomNode` vs `TagMod​`](https://github.com/japgolly/scalajs-react/blob/master/doc/VDOM.md#types)
          |- [​React's `Components` vs `Elements​`](https://reactjs.org/blog/2015/12/18/react-components-elements-and-instances.html)
          |
          |# Spacing
          |
          |**Components don't have any predefined margin.** Moreover, they
          |don't accept `TagMod` as children, so it's suggested to use a
          |wrapper to provide necessary spacing when use them:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            // a "span" wrapper around the Icon
            <.span(Style.margin.right8, Icon(name = Icon.Glyph.Download)()),
            "Download"
          )
        )
      )(),
      Markdown(
        """
          |Of course you can also wrap the adjacent element:
        """.stripMargin
      )(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.flexbox.flex.flexbox.itemsCenter,
            Icon(name = Icon.Glyph.Upload)(),
            // a "span" wrapper around the text
            <.span(Style.margin.left8, "Upload")
          )
        )
      )(),
      Markdown(
        s"""
           |# Display & Alignment
           |
           |**Components are usually block element,** even those that
           |originally are inline like Button or Tag. This is intentionally to
           |enforce proper alignment.
           |
           |More specific, `inline` elements are placed horizontally out of
           |the box. However, they are aligned by `baseline`, which is usually
           |not good enough for us, as we want absolute centering alignment.
           |
           |On the other hand, `block` elements are placed vertically, so if the
           |engineer want them to be horizontal, they need to use proper
           |technique like [Flexbox][1], which should have better control and
           |yield more stable result.
           |
           |[1]: ${ctl.urlFor(Pages.Layout()).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.left8
        <.div(

          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Icon(name = Icon.Glyph.LightBolt)()),
          <.div(Button()("Button"), margin),
          <.span("Text", margin)
        )
      }))(),
      Markdown(
        """
          |
          |[This Code Pen][1] explains more in detail why we prefer `block`
          |over `inline` display, with step by step examples.
          |
          |[1]: https://codepen.io/dvkndn/pen/wmQmbm
          |
          |# Render prop
          |
          |From [React's official documentation][2]:
          |
          |[2]: https://reactjs.org/docs/render-props.html
          |
          |> The term "render prop" refers to a simple technique for sharing
          |> code between React components using a prop whose value is a
          |> function.
          |>
          |> A component with a render prop takes a function that returns a
          |> React element and calls it instead of implementing its own render
          |> logic.
          |>
          |> ```text
          |> <DataProvider render={data => (
          |>   <h1>Hello {data.target}</h1>
          |> )}/>
          |> ```
          |
          |**"Render prop" also let the host component to skip render when
          |it's not necessary.** For example, in Tab component, although the
          |render functions of all panels are provided, only the one of
          |currently visible Panel is executed.
          |
          |However, like HOC, be careful when you create function inside your
          |render method. Such function will be [created new on every
          |render][3], which *might* affect performance and [potentially lose
          | the state][4] of it and its children.
          |
          |[3]: https://reactjs.org/docs/render-props.html#be-careful-when-using-render-props-with-reactpurecomponent
          |[4]: https://reactjs.org/docs/higher-order-components.html#dont-use-hocs-inside-the-render-method
          |
          |```scala
          |private def render(...) = {
          |  ...
          |  // This will create new function on every render
          |  Table.Column("Email", member => Table.Cell(member.email))
          |  ...
          |}
          |```
          |
          |```scala
          |// This function is defined once and will be the same for
          |// every render
          |private def renderEmail(member: Member) =
          |  Table.Cell(member.email)
          |
          |private def render(...) = {
          |  ...
          |  Table.Column("Email", renderEmail)
          |  ...
          |}
          |```
          |
          |Through out this guide, we will usually use in-place function (
          |i.e. defining function inside `render` method) for the sake of
          |simplicity. However, in practice, it is suggested to define such
          |function as object's method.
        """.stripMargin
      )()
    )
  }
}
