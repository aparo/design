// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.editor

import japgolly.scalajs.react.ScalaFnComponent

// scalastyle:off underscore.import
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

object PlainTextEditor {

  final case class Props(html: String, maxLengthOpt: Option[Int] = None)

  val component = ScalaFnComponent[Props] { props =>
    val text = Serializer.deserialize(props.html).document.text
    val subText = props.maxLengthOpt
      .map { max =>
        s"${text.substring(0, max)}..."
      }
      .getOrElse(text)

    // TODO: @nghuuphuoc Don't need to wrap in `div` when upgrading to React 16
    <.div(subText)
  }
}
