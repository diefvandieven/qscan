package io.diefvandieven.qscan

import com.google.zxing.BinaryBitmap
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.qrcode.QRCodeReader
import java.io.InputStream
import javax.imageio.ImageIO

class QScan {
    fun scan(inputStream: InputStream): String {
        val image = ImageIO.read(inputStream)
        val source = BufferedImageLuminanceSource(image)
        val bitmap = BinaryBitmap(GlobalHistogramBinarizer(source))
        val result = QRCodeReader().decode(bitmap)
        return result.text
    }
}