// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.stylesheet.tachyons

// See https://github.com/tachyons-css/tachyons/blob/master/src/_skins.css
// scalastyle:off number.of.methods
private[tachyons] final case class Color(classes: List[String] = List.empty) {

  def darkGrayDarken: Tachyons = new Tachyons(classes :+ "c-dark-gray-darken")
  def darkGrayLighten: Tachyons = new Tachyons(classes :+ "c-dark-gray-lighten")
  def darkGray: Tachyons = new Tachyons(classes :+ "c-dark-gray")
  def grayDarken: Tachyons = new Tachyons(classes :+ "c-gray-darken")
  def grayLighten: Tachyons = new Tachyons(classes :+ "c-gray-lighten")
  def gray: Tachyons = new Tachyons(classes :+ "c-gray")
  def lightGrayDarken: Tachyons = new Tachyons(classes :+ "c-light-gray-darken")
  def lightGrayLighten: Tachyons = new Tachyons(classes :+ "c-light-gray-lighten")
  def lightGray: Tachyons = new Tachyons(classes :+ "c-light-gray")
  def white: Tachyons = new Tachyons(classes :+ "c-white")

  def primary: Tachyons = new Tachyons(classes :+ "c-primary")
  def primaryDarken: Tachyons = new Tachyons(classes :+ "c-primary-darken")
  def primaryLighten1: Tachyons = new Tachyons(classes :+ "c-primary-lighten-1")
  def primaryLighten2: Tachyons = new Tachyons(classes :+ "c-primary-lighten-2")
  def primaryLighten3: Tachyons = new Tachyons(classes :+ "c-primary-lighten-3")
  def success: Tachyons = new Tachyons(classes :+ "c-success")
  def successDarken: Tachyons = new Tachyons(classes :+ "c-success-darken")
  def successLighten1: Tachyons = new Tachyons(classes :+ "c-success-lighten-1")
  def successLighten2: Tachyons = new Tachyons(classes :+ "c-success-lighten-2")
  def successLighten3: Tachyons = new Tachyons(classes :+ "c-success-lighten-3")
  def warning: Tachyons = new Tachyons(classes :+ "c-warning")
  def warningDarken: Tachyons = new Tachyons(classes :+ "c-warning-darken")
  def warningLighten1: Tachyons = new Tachyons(classes :+ "c-warning-lighten-1")
  def warningLighten2: Tachyons = new Tachyons(classes :+ "c-warning-lighten-2")
  def warningLighten3: Tachyons = new Tachyons(classes :+ "c-warning-lighten-3")
  def danger: Tachyons = new Tachyons(classes :+ "c-danger")
  def dangerDarken: Tachyons = new Tachyons(classes :+ "c-danger-darken")
  def dangerLighten1: Tachyons = new Tachyons(classes :+ "c-danger-lighten-1")
  def dangerLighten2: Tachyons = new Tachyons(classes :+ "c-danger-lighten-2")
  def dangerLighten3: Tachyons = new Tachyons(classes :+ "c-danger-lighten-3")

  def inherit: Tachyons = new Tachyons(classes :+ "c-inherit")
}
