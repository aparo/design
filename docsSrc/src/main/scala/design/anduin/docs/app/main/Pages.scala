package design.anduin.docs.app.main

import japgolly.scalajs.react.extra.router.RouterCtl

object Pages {
  sealed trait Page
  // entry
  case object Home extends Page

  // style
  sealed trait StyleT extends Page
  case class Logo(hash: String = "") extends StyleT
  case class Style(hash: String = "") extends StyleT
  case class Layout(hash: String = "") extends StyleT
  case class Color(hash: String = "") extends StyleT
  case class Typography(hash: String = "") extends StyleT

  // component
  sealed trait ComponentT extends Page
  case class Component(hash: String = "") extends ComponentT
  case class Playground(hash: String = "") extends ComponentT

  case class Avatar(hash: String = "") extends ComponentT
  sealed trait ButtonT extends ComponentT
  case class Button(hash: String = "") extends ButtonT
  case class ButtonBox(hash: String = "") extends ButtonT
  case class Card(hash: String = "") extends ComponentT
  case class Checkbox(hash: String = "") extends ComponentT
  case class DateTime(hash: String = "") extends ComponentT
  case class Dialog(hash: String = "") extends ComponentT
  sealed trait DropdownT extends ComponentT
  case class Dropdown(hash: String = "") extends DropdownT
  case class DropdownMulti(hash: String = "") extends DropdownT
  case class Field(hash: String = "") extends ComponentT
  case class Filter(hash: String = "") extends ComponentT
  sealed trait IconT extends ComponentT
  case class Icon(hash: String = "") extends IconT
  case class IconFile(hash: String = "") extends IconT
  case class IconFolder(hash: String = "") extends IconT
  case class IconGlyph(hash: String = "") extends IconT
  case class IconNego(hash: String = "") extends IconT
  case class IconProduct(hash: String = "") extends IconT
  case class Menu(hash: String = "") extends ComponentT
  case class Modal(hash: String = "") extends ComponentT
  case class Popover(hash: String = "") extends ComponentT
  case class Portal(hash: String = "") extends ComponentT
  case class Progress(hash: String = "") extends ComponentT
  sealed trait RadioT extends ComponentT
  case class Radio(hash: String = "") extends RadioT
  case class RadioBox(hash: String = "") extends RadioT
  case class Stepper(hash: String = "") extends ComponentT
  sealed trait SuggestT extends ComponentT
  case class Suggest(hash: String = "") extends SuggestT
  case class SuggestMulti(hash: String = "") extends SuggestT
  case class Tab(hash: String = "") extends ComponentT
  case class Table(hash: String = "") extends ComponentT
  case class Tag(hash: String = "") extends ComponentT
  sealed trait TextBoxT extends ComponentT
  case class TextBox(hash: String = "") extends ComponentT
  case class TextBoxValue(hash: String = "") extends ComponentT
  case class TextBoxAppearance(hash: String = "") extends ComponentT
  case class Toggle(hash: String = "") extends ComponentT
  case class Tooltip(hash: String = "") extends ComponentT
  case class Toast(hash: String = "") extends ComponentT
  case class Tree(hash: String = "") extends ComponentT
  case class Well(hash: String = "") extends ComponentT

  type Ctl = RouterCtl[Pages.Page]
}
