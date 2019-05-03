// Copyright (C) 2014-2019 Anduin Transactions Inc.

package anduin.scalajs.slate

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}

import anduin.scalajs.immutable.{ImmutableList, ImmutableMap, ImmutableSet}

// scalastyle:off multiple.string.literals
object Slate {

  final class ValueJson(
    val document: js.Object,
    val `object`: String
  ) extends js.Object

  // See https://docs.slatejs.org/slate-core/value
  @JSImport("slate", "Value")
  @js.native
  object Value extends js.Object {
    def fromJSON(json: js.Object): Value = js.native
  }

  @js.native
  trait Value extends js.Object {
    val data: Data = js.native
    val document: Document = js.native
    val selection: Selection = js.native

    def activeMarks: ImmutableSet[Mark] = js.native
    def blocks: ImmutableList[Block] = js.native
    def inlines: ImmutableList[Inline] = js.native
    def isBlurred: Boolean = js.native
    def toJSON(): js.Object = js.native
  }

  // See https://docs.slatejs.org/slate-core/document
  @JSImport("slate", "Document")
  @js.native
  final class Document extends Node

  @JSImport("slate", "Document")
  @js.native
  object Document extends js.Object {
    def fromJSON(attrs: js.Object): Document = js.native
  }

  @JSImport("slate", "Editor")
  @js.native
  object Editor extends js.Object

  @js.native
  trait Editor extends js.Object {
    val operations: ImmutableList[Operation] = js.native
    val readOnly: Boolean = js.native
    val value: Value = js.native

    def addMark(mark: String): Editor = js.native
    def removeMark(mark: String): Editor = js.native
    def toggleMark(mark: String): Editor = js.native
    def focus(): Editor = js.native
    def undo(): Editor = js.native
    def redo(): Editor = js.native
    def moveToEnd(): Editor = js.native
    def unwrapInline(inlineType: String): Editor = js.native
    def wrapInline(props: js.Object): Editor = js.native
    def insertText(text: String): Editor = js.native
    def insertFragment(document: Document): Editor = js.native
    def moveFocusBackward(num: Int): Editor = js.native
    def setBlocks(block: String): Editor = js.native
    def setBlocks(props: js.Object): Editor = js.native
    def unwrapBlock(block: String): Editor = js.native
    def wrapBlock(block: String): Editor = js.native
    def insertInlineAtRange(range: Range, properties: js.Object): Editor = js.native
    def wrapInlineAtRange(range: Range, properties: js.Object): Editor = js.native
    def moveOffsetsTo(anchorOffset: Int, focusOffset: Int): Editor = js.native
    def deselect(): Editor = js.native
    def moveFocusToStartOfNode(node: Node): Editor = js.native
  }

  final class WrapInlineProps(
    @JSName("type") val inlineType: String,
    val data: js.Object
  ) extends js.Object

  // See https://docs.slatejs.org/slate-core/mark
  @JSImport("slate", "Mark")
  @js.native
  final class Mark(
    @JSName("type") val markType: String,
    val kind: String = "mark"
  ) extends js.Object

  // See https://docs.slatejs.org/slate-core/node
  @js.native
  trait Node extends js.Object {
    val key: String = js.native
    val `object`: String = js.native
    val data: Data = js.native
    @JSName("type") val nodeType: String = js.native
    val nodes: js.UndefOr[ImmutableList[Node]] = js.native
    val text: String = js.native

    def getClosest(key: String, @JSName("match") find: js.Function1[Node, Boolean]): js.UndefOr[Node] = js.native
    def getParent(node: String): js.UndefOr[Node] = js.native
    def getTexts(): ImmutableList[Text] = js.native
    def set(key: String, value: js.Object): Node = js.native
    def toJSON(): js.Object = js.native
  }

  // See https://docs.slatejs.org/slate-core/inline
  @JSImport("slate", "Inline")
  @js.native
  final class Inline(
    val data: Data = js.native,
    @JSName("type") val inlineType: String = js.native // link, hashtag, for example
  ) extends js.Object

  // See https://docs.slatejs.org/slate-core/data
  @JSImport("slate", "Data")
  @js.native
  final class Data extends ImmutableMap[String, js.Object]

  // See https://docs.slatejs.org/slate-core/block
  @JSImport("slate", "Block")
  @js.native
  final class Block extends Node

  @JSImport("slate", "Block")
  @js.native
  object Block extends js.Object {
    def fromJSON(attrs: js.Object): Document = js.native
  }

  // See https://docs.slatejs.org/slate-core/range
  @JSImport("slate", "Range")
  @js.native
  object Range extends js.Object {
    def create(properties: js.Object): Range = js.native
  }

  final class Range(
    val anchorKey: String,
    val anchorOffset: Int,
    val focusKey: String,
    val focusOffset: Int
  ) extends js.Object

  // See https://docs.slatejs.org/slate-core/text
  @JSImport("slate", "Text")
  @js.native
  final class Text extends Node

  @JSImport("slate", "Selection")
  @js.native
  object Selection extends js.Object

  @js.native
  trait Selection extends js.Object {
    val isExpanded: Boolean = js.native
  }

  @JSImport("slate", "Operation")
  @js.native
  final class Operation(
    val properties: js.UndefOr[js.Object] = js.native,
    @JSName("type") val tpe: String = js.native
  ) extends js.Object
}
// scalastyle:on multiple.string.literals
