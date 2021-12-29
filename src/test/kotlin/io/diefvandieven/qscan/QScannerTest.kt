package io.diefvandieven.qscan

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

class QScannerTest : FunSpec() {
  private val classLoader: ClassLoader = QScanner::class.java.classLoader

  @Test
  fun `should fail with null File`() {
    shouldThrow<IllegalArgumentException> { QScanner(null).scan() }
  }

  @Test
  fun `a simple qr scan of InputStream should succeed`() {
    QScanner(classLoader.getResourceAsStream("hello_qscan.png")!!).scan() shouldBe "Hello QScan!"
  }

  @Test
  fun `a simple qr scan of File should succeed`() {
    QScanner(File(classLoader.getResource("hello_qscan.png")!!.toURI())).scan() shouldBe "Hello QScan!"
  }
}