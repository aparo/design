// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.component.image

import scala.concurrent.Promise

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.raw.{Event, File, FileReader}

import anduin.component.portal.modal.Modal
import anduin.style.Style

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class UploadAndCrop(
  uploadButtonLabel: String,
  cropWidthPercent: Double,
  cropHeightPercent: Double,
  aspectRatio: Double,
  targetSize: Option[Size],
  minSize: Option[Size],
  maxSize: Option[Size],
  onUpload: String => Task[String]
) {
  def apply(): VdomElement = UploadAndCrop.component(this)
}

object UploadAndCrop {

  private type Props = UploadAndCrop

  sealed trait Status
  object FileNotSelected extends Status
  case class FileSelected(src: String) extends Status
  case class Uploading(src: String) extends Status
  case class DidUpload(src: String) extends Status

  private case class State(status: Status = FileNotSelected)

  private case class Backend(scope: BackendScope[Props, State]) {

    private def onDidSelectFile(file: File) = {
      Callback.future {
        val promise = Promise[Callback]()

        val reader = new FileReader()
        reader.addEventListener(
          "load",
          (_: Event) => {
            @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf")) // scalastyle:ignore
            val dataUrl = reader.result.asInstanceOf[String]
            promise.success {
              scope.modState(_.copy(status = FileSelected(dataUrl)))
            }
          }
        )
        reader.readAsDataURL(file)

        promise.future
      }
    }

    private def closeModal(closeCropModal: Callback) = {
      scope.modState(_.copy(status = FileNotSelected), closeCropModal)
    }

    private def chooseAnotherFile(file: File) = {
      onDidSelectFile(file)
    }

    private def startUploading(closeCropModal: Callback)(src: String) = {
      scope.modState(_.copy(status = Uploading(src)), closeCropModal)
    }

    private def didUpload(props: Props)(src: String) = {
      for {
        _ <- Callback
          .future {
            props
              .onUpload(src)
              .executeWithOptions(_.enableAutoCancelableRunLoops)
              .runAsync
              .map { newSource =>
                scope.modState(_.copy(status = DidUpload(newSource)))
              }
          }
          .delayMs(5 * 1000)
      } yield ()
    }

    def render(props: Props, state: State): VdomElement = {
      state.status match {
        case FileNotSelected =>
          BrowseFile(
            onDidSelect = onDidSelectFile
          )()

        case FileSelected(src) =>
          Modal(
            isOpened = Some(true),
            size = Modal.Size600,
            renderTarget = _ => EmptyVdom,
            renderContent = close => {
              CropImageModal(
                src = src,
                cropWidthPercent = props.cropWidthPercent,
                cropHeightPercent = props.cropHeightPercent,
                aspectRatio = props.aspectRatio,
                minSize = props.minSize,
                maxSize = props.maxSize,
                onCancel = closeModal(close),
                onChooseAnotherFile = chooseAnotherFile,
                onStartUploading = startUploading(close)
              )()
            }
          )()

        case Uploading(src) =>
          UploadingCroppedImage(src, didUpload(props))()

        case DidUpload(src) =>
          BrowseFile(
            onDidSelect = onDidSelectFile
          )(<.img(Style.border.none, ^.src := src))
      }
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .initialState(State())
    .renderBackend[Backend]
    .build
}
