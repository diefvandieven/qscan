package io.diefvandieven.qscan

import com.google.zxing.BinaryBitmap
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.qrcode.QRCodeReader
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO

class QScanner(private val file: File?) {
  private var inputStream: InputStream? = null

  constructor(inputStream: InputStream) : this(null) {
    this.inputStream = inputStream
  }

  private fun validate() {
    when (file) {
      null -> if (inputStream == null) {
        throw IllegalArgumentException("Input file or input stream must not be null.")
      }
      else -> inputStream = file.inputStream()
    }
  }

  fun scan(): String {
    validate()
    val image = ImageIO.read(inputStream)
    val source = BufferedImageLuminanceSource(image)
    val bitmap = BinaryBitmap(GlobalHistogramBinarizer(source))
    val result = QRCodeReader().decode(bitmap)
    return result.text
  }
}