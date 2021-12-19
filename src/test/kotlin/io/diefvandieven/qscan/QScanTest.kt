package io.diefvandieven.qscan

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class QScanTest : FunSpec() {
    val classLoader = QScan::class.java.classLoader
    val qscan: QScan = QScan()

    @Test
    fun `a simple qr scan should succeed`() {
        qscan.scan(classLoader.getResourceAsStream("hello_qscan.png")!!) shouldBe "Hello QScan!"
    }
}