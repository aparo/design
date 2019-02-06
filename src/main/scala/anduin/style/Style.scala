// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.style

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

// scalastyle:off number.of.methods
class Style(val classes: List[String]) {

  def value: String = classes.mkString(" ").trim

  // Layout
  def display: Display = Display(classes)
  def overflow: Overflow = Overflow(classes)
  def position: Position = Position(classes)
  def zIndex: ZIndex = ZIndex(classes)

  // Misc
  def cursor: Cursor = Cursor(classes)
  def opacity: Opacity = Opacity(classes)
  def pointerEvents: PointerEvents = PointerEvents(classes)
  def shadow: Shadow = Shadow(classes)

  // Spacing
  def margin: Margin = Margin(classes)
  def padding: Padding = Padding(classes)

  // Sizing
  def width: Width = Width(classes)
  def maxWidth: MaxWidth = MaxWidth(classes)
  def height: Height = Height(classes)
  def minHeight: MinHeight = MinHeight(classes)

  // Background
  def background: Background = Background(classes)

  // Border
  def border: Border = Border(classes)
  def borderColor: BorderColor = BorderColor(classes)
  def borderRadius: BorderRadius = BorderRadius(classes)
  def borderStyle: BorderStyle = BorderStyle(classes)
  def borderWidth: BorderWidth = BorderWidth(classes)

  // Flexbox
  def flexbox: Flexbox = Flexbox(classes)

  // Typography
  def color: Color = Color(classes)
  def fontFamily: FontFamily = FontFamily(classes)
  def fontSize: FontSize = FontSize(classes)
  def fontWeight: FontWeight = FontWeight(classes)
  def lineHeight: LineHeight = LineHeight(classes)
  def typography: Typography = Typography(classes)
  def listStyle: ListStyle = ListStyle(classes)
  def fontStyle: FontStyle = FontStyle(classes)
  def textTransform: TextTransform = TextTransform(classes)
  def textDecoration: TextDecoration = TextDecoration(classes)
  def textAlign: TextAlign = TextAlign(classes)
  def verticalAlign: VerticalAlign = VerticalAlign(classes)
  def whiteSpace: WhiteSpace = WhiteSpace(classes)
  def overflowWrap: OverflowWrap = OverflowWrap(classes)

  def animation: Animation = Animation(classes)
  def focus: Focus = Focus(classes)
  def hover: Hover = Hover(classes)
  def transition: Transition = Transition(classes)

  override def toString: String = value
}
// scalastyle:on number.of.methods

object Style extends Style(classes = List.empty) {
  implicit def convertToTagMod(builder: Style): TagMod = ^.cls := builder.value
}
