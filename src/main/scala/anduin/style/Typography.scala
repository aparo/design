// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.style

private[style] final case class Typography(classes: List[String] = List.empty) {
  def truncate: Style = new Style(classes :+ "truncate")
  def truncate2: Style = new Style(classes :+ "ty-tr")
}
