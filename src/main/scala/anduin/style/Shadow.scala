// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.style

private[style] final case class Shadow(classes: List[String] = List.empty) {
  def blur1Light: Style = new Style(classes :+ "shadow-1")
  def blur1Dark: Style = new Style(classes :+ "shadow-2")
  def blur8: Style = new Style(classes :+ "shadow-3")
  def blur12: Style = new Style(classes :+ "shadow-4")

  def borderGray4s: Style = new Style(classes :+ "shadow-border-gray-4s")
  def borderPrimary5s: Style = new Style(classes :+ "shadow-border-primary-5s")
  def borderSuccess5s: Style = new Style(classes :+ "shadow-border-success-5s")
  def borderWarning5s: Style = new Style(classes :+ "shadow-border-warning-5s")
  def borderDanger5s: Style = new Style(classes :+ "shadow-border-danger-5s")
}
