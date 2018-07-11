// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.loader.placeholder

case class PlaceholderProps(
  width: Double = 400,
  height: Double = 130,
  speed: Double = 2,
  animate: Boolean = true,
  preserveAspectRatio: String = "xMidYMid meet",
  primaryColor: String = "#f0f0f0",
  secondaryColor: String = "#e0e0e0",
  primaryOpacity: Double = 1,
  secondaryOpacity: Double = 1
)
