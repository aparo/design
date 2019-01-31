// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.guide.app.main.layout

import anduin.guide.app.main.Pages
import anduin.guide.app.main.Pages._
import anduin.guide.app.main.Pages.{Layout => PagesLayout}
import anduin.style.Style


// scalastyle:off underscore.import
import anduin.guide.app.main.layout.NavElements._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class LayoutNav(ctl: Pages.Ctl, page: Page) {
  def apply(): VdomElement = LayoutNav.component(this)
}

object LayoutNav {

  private type Props = LayoutNav

  private def render(props: Props) = {

    implicit val implicitProps: NavElements.Props =
      NavElements.Props(props.ctl, props.page)

    <.div(
      Style.color.gray4.hover.colorGray7.transition.all,
      // The padding was defined here instead of the parent to increase the
      // hit area for the above hover effect
      ^.padding := "0 56px",
      // eliminate the first ul
      ^.marginLeft := "-20px",
      ul(
        li(
          Title("Home", Some(Home))
        ),
        h("Guide"),
        li(
          Title("Style", Some(Pages.Style()), Some(_.isInstanceOf[StyleT])),
          ul(
            li(Title("Color", Some(Color()))),
            li(
              Title("Typography", Some(Typography()), Some(_.isInstanceOf[TypographyT])),
              ul(li(Title("Fixed line height", Some(FixedLineHeight()))))
            ),
            li(Title("Space", Some(Space()))),
            li(Title("Layout", Some(PagesLayout()))),
            li(Title("Logo", Some(Logo()))),
          )
        ),
        li(
          Title("Component", Some(Component()), Some(_.isInstanceOf[ComponentT])),
          ul(
            li(
              Title("Button", Some(Button()), Some(_.isInstanceOf[ButtonT])),
              ul(li(Title("Box", Some(ButtonBox())))),
            ),
            li(
              Title("Icon", Some(Icon()), Some(_.isInstanceOf[IconT])),
              ul(
                li(Title("Glyph", Some(IconGlyph()))),
                li(Title("Negotiation", Some(IconNego()))),
                li(Title("File", Some(IconFile()))),
                li(Title("Folder", Some(IconFolder()))),
              ),
            ),
            li(Title("Progress", Some(ProgressIndicator()))),
            li(Title("Toggle", Some(Toggle()))),
            h("Container"),
            li(Title("Card", Some(Card()))),
            li(Title("Table", Some(Table()))),
            li(Title("Well", Some(Well()))),
            li(Title("Modal", Some(Modal()))),
            li(Title("Tab", Some(Tab()))),
            h("Form"),
            li(
              Title("Dropdown", Some(Dropdown()), Some(_.isInstanceOf[DropdownT])),
            ),
          )
        ),
        li(
          Title("Copy", Some(Copy()))
        ),
        h(""),
        li(Title("Resources", Some(Resources()))),
        li(Title("Work with us", Some(Careers()))),
      )
    )
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_P(render)
    .build
}
