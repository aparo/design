package design.anduin.docs.pages.components.icon

import design.anduin.components.icon.{Icon, IconFolder}
import design.anduin.components.tag.Tag
import design.anduin.docs.app.main.Pages
import design.anduin.docs.components._
import design.anduin.macros.Source
import design.anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageIconFolder {

  private val defaultTag = <.div(
    Tag(color = Tag.Light.Primary)("Default")
  )

  private def renderColor(color: IconFolder.Color): VdomElement = {
    val m = Style.margin.bottom8
    <.div(
      ^.key := color.getClass.getSimpleName,
      ^.width := "72px",
      Style.flexbox.flex.flexbox.column.flexbox.itemsCenter,
      <.div(m, Icon(size = Icon.Size.Px32, name = Icon.Folder(color = color))()),
      <.div(m, Tag(color = Tag.Light.Gray)(color.getClass.getSimpleName)),
      color match {
        case Icon.Folder.Brown => defaultTag
        case _                 => EmptyVdom
      }
    )
  }

  def render(ctl: Pages.Ctl): VdomElement = {
    <.div(
      Header("Folder Icon")(),
      Toc(headings = Source.getTocHeadings)(),
      Markdown(
        s"""
           |`Icon.Folder` is a special [Icon's name] that offers further
           |customization for folder icon:
           |
           |[Icon's name]: ${ctl.urlFor(Pages.Icon("#name")).value}
           |""".stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(
          name = Icon.Folder(
            color = Icon.Folder.Success,
            glyph = Some(Icon.Glyph.Check)
          ),
          size = Icon.Size.Px32
        )()
      }))(),
      Markdown(
        s"""
           |Like [File Icons], `Icon.Folder` also has specific designs for all
           |common sizes:
           |
           |[File Icons]: ${ctl.urlFor(Pages.IconFile()).value}
         """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        val margin = Style.margin.right12
        <.div(
          Style.flexbox.flex.flexbox.itemsCenter,
          <.div(Icon(size = Icon.Size.Px32, name = Icon.Folder())(), margin),
          <.div(Icon(size = Icon.Size.Px24, name = Icon.Folder())(), margin),
          <.div(Icon(size = Icon.Size.Px16, name = Icon.Folder())())
        )
      }))(),
      Markdown(
        """
          |# Color
          |
          |```scala
          |color: IconFolder.Color = Icon.Folder.Brown
          |```
          |
          |The folder's color can be changed via the `color` prop. 
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(
          name = Icon.Folder(color = Icon.Folder.Warning),
          size = Icon.Size.Px32
        )()
      }))(),
      Markdown(
        """
          |There are 6 options, with `Brown` is the default one. Note that these
          |options are available under `Icon.Folder`:
        """.stripMargin
      )(), {
        import Icon.Folder._
        val icons = Vector(Brown, Gray, Danger, Warning, Success, Primary)
        ExampleSimple()(<.div(Style.flexbox.flex, icons.toVdomArray(renderColor)))
      },
      Markdown(
        s"""
           |# Glyph
           |
           |```scala
           |glyph: Option[IconGlyph] = None
           |```
           |
           |32-px Folder Icon can be decorated with a [glyph]:
           |
           |[glyph]: ${ctl.urlFor(Pages.IconGlyph()).value}
        """.stripMargin
      )(),
      ExampleRich(Source.annotate({
        Icon(
          name = Icon.Folder(glyph = Some(Icon.Glyph.LightBolt)),
          size = Icon.Size.Px32
        )()
      }))(),
      Markdown(
        s"""
           |This works with all colors:
        """.stripMargin
      )(),
      ExampleSimple()({
        import Icon.Folder._
        def renderIcon(color: IconFolder.Color): VdomElement = {
          val name = Icon.Folder(color = color, glyph = Some(Icon.Glyph.LightBolt))
          <.div(
            ^.key := color.getClass.getSimpleName,
            Style.margin.right12,
            Icon(name = name, size = Icon.Size.Px32)()
          )
        }
        <.div(Style.flexbox.flex, Vector(Brown, Gray, Danger, Warning, Success, Primary).toVdomArray(renderIcon))
      }),
      Markdown(
        s"""
           |
         """.stripMargin
      )()
    )
  }
}
