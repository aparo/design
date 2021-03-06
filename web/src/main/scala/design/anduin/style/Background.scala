// Copyright (C) 2014-2019 Anduin Transactions Inc.

package design.anduin.style

// scalastyle:off number.of.methods
private[style] final case class Background(classes: List[String] = List.empty) {
  // normal
  def transparent: Style = new Style(classes :+ "bg-transparent")
  def currentColor: Style = new Style(classes :+ "bg-current")
  def gray9: Style = new Style(classes :+ "bg-gray-9")
  def gray8: Style = new Style(classes :+ "bg-gray-8")
  def gray7: Style = new Style(classes :+ "bg-gray-7")
  def gray6: Style = new Style(classes :+ "bg-gray-6")
  def gray5: Style = new Style(classes :+ "bg-gray-5")
  def gray4: Style = new Style(classes :+ "bg-gray-4")
  def gray3: Style = new Style(classes :+ "bg-gray-3")
  def gray2: Style = new Style(classes :+ "bg-gray-2")
  def gray1: Style = new Style(classes :+ "bg-gray-1")
  def gray0: Style = new Style(classes :+ "bg-gray-0")
  def primary5: Style = new Style(classes :+ "bg-primary-5")
  def primary4: Style = new Style(classes :+ "bg-primary-4")
  def primary3: Style = new Style(classes :+ "bg-primary-3")
  def primary2: Style = new Style(classes :+ "bg-primary-2")
  def primary1: Style = new Style(classes :+ "bg-primary-1")
  def success5: Style = new Style(classes :+ "bg-success-5")
  def success4: Style = new Style(classes :+ "bg-success-4")
  def success3: Style = new Style(classes :+ "bg-success-3")
  def success2: Style = new Style(classes :+ "bg-success-2")
  def success1: Style = new Style(classes :+ "bg-success-1")
  def warning5: Style = new Style(classes :+ "bg-warning-5")
  def warning4: Style = new Style(classes :+ "bg-warning-4")
  def warning3: Style = new Style(classes :+ "bg-warning-3")
  def warning2: Style = new Style(classes :+ "bg-warning-2")
  def warning1: Style = new Style(classes :+ "bg-warning-1")
  def danger5: Style = new Style(classes :+ "bg-danger-5")
  def danger4: Style = new Style(classes :+ "bg-danger-4")
  def danger3: Style = new Style(classes :+ "bg-danger-3")
  def danger2: Style = new Style(classes :+ "bg-danger-2")
  def danger1: Style = new Style(classes :+ "bg-danger-1")
  // hover
  def hoverGray9: Style = new Style(classes :+ "hover:bg-gray-9")
  def hoverGray8: Style = new Style(classes :+ "hover:bg-gray-8")
  def hoverGray7: Style = new Style(classes :+ "hover:bg-gray-7")
  def hoverGray6: Style = new Style(classes :+ "hover:bg-gray-6")
  def hoverGray5: Style = new Style(classes :+ "hover:bg-gray-5")
  def hoverGray4: Style = new Style(classes :+ "hover:bg-gray-4")
  def hoverGray3: Style = new Style(classes :+ "hover:bg-gray-3")
  def hoverGray2: Style = new Style(classes :+ "hover:bg-gray-2")
  def hoverGray1: Style = new Style(classes :+ "hover:bg-gray-1")
  def hoverGray0: Style = new Style(classes :+ "hover:bg-gray-0")
  def hoverPrimary5: Style = new Style(classes :+ "hover:bg-primary-5")
  def hoverPrimary4: Style = new Style(classes :+ "hover:bg-primary-4")
  def hoverPrimary3: Style = new Style(classes :+ "hover:bg-primary-3")
  def hoverPrimary2: Style = new Style(classes :+ "hover:bg-primary-2")
  def hoverPrimary1: Style = new Style(classes :+ "hover:bg-primary-1")
  def hoverSuccess5: Style = new Style(classes :+ "hover:bg-success-5")
  def hoverSuccess4: Style = new Style(classes :+ "hover:bg-success-4")
  def hoverSuccess3: Style = new Style(classes :+ "hover:bg-success-3")
  def hoverSuccess2: Style = new Style(classes :+ "hover:bg-success-2")
  def hoverSuccess1: Style = new Style(classes :+ "hover:bg-success-1")
  def hoverWarning5: Style = new Style(classes :+ "hover:bg-warning-5")
  def hoverWarning4: Style = new Style(classes :+ "hover:bg-warning-4")
  def hoverWarning3: Style = new Style(classes :+ "hover:bg-warning-3")
  def hoverWarning2: Style = new Style(classes :+ "hover:bg-warning-2")
  def hoverWarning1: Style = new Style(classes :+ "hover:bg-warning-1")
  def hoverDanger5: Style = new Style(classes :+ "hover:bg-danger-5")
  def hoverDanger4: Style = new Style(classes :+ "hover:bg-danger-4")
  def hoverDanger3: Style = new Style(classes :+ "hover:bg-danger-3")
  def hoverDanger2: Style = new Style(classes :+ "hover:bg-danger-2")
  def hoverDanger1: Style = new Style(classes :+ "hover:bg-danger-1")
  // active
  def activeGray9: Style = new Style(classes :+ "active:bg-gray-9")
  def activeGray8: Style = new Style(classes :+ "active:bg-gray-8")
  def activeGray7: Style = new Style(classes :+ "active:bg-gray-7")
  def activeGray6: Style = new Style(classes :+ "active:bg-gray-6")
  def activeGray5: Style = new Style(classes :+ "active:bg-gray-5")
  def activeGray4: Style = new Style(classes :+ "active:bg-gray-4")
  def activeGray3: Style = new Style(classes :+ "active:bg-gray-3")
  def activeGray2: Style = new Style(classes :+ "active:bg-gray-2")
  def activeGray1: Style = new Style(classes :+ "active:bg-gray-1")
  def activeGray0: Style = new Style(classes :+ "active:bg-gray-0")
  def activePrimary5: Style = new Style(classes :+ "active:bg-primary-5")
  def activePrimary4: Style = new Style(classes :+ "active:bg-primary-4")
  def activePrimary3: Style = new Style(classes :+ "active:bg-primary-3")
  def activePrimary2: Style = new Style(classes :+ "active:bg-primary-2")
  def activePrimary1: Style = new Style(classes :+ "active:bg-primary-1")
  def activeSuccess5: Style = new Style(classes :+ "active:bg-success-5")
  def activeSuccess4: Style = new Style(classes :+ "active:bg-success-4")
  def activeSuccess3: Style = new Style(classes :+ "active:bg-success-3")
  def activeSuccess2: Style = new Style(classes :+ "active:bg-success-2")
  def activeSuccess1: Style = new Style(classes :+ "active:bg-success-1")
  def activeWarning5: Style = new Style(classes :+ "active:bg-warning-5")
  def activeWarning4: Style = new Style(classes :+ "active:bg-warning-4")
  def activeWarning3: Style = new Style(classes :+ "active:bg-warning-3")
  def activeWarning2: Style = new Style(classes :+ "active:bg-warning-2")
  def activeWarning1: Style = new Style(classes :+ "active:bg-warning-1")
  def activeDanger5: Style = new Style(classes :+ "active:bg-danger-5")
  def activeDanger4: Style = new Style(classes :+ "active:bg-danger-4")
  def activeDanger3: Style = new Style(classes :+ "active:bg-danger-3")
  def activeDanger2: Style = new Style(classes :+ "active:bg-danger-2")
  def activeDanger1: Style = new Style(classes :+ "active:bg-danger-1")
}
