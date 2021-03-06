// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.style

private[style] final case class FontStyle(classes: List[String] = List.empty) {
  def italic: Style = new Style(classes :+ "italic")
}
