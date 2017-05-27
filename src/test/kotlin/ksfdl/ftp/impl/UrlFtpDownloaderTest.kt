package ksfdl.ftp.impl

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import ksfdl.ftp.util.FakeFtpServer
import ksfdl.test.util.containsFile
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.net.URL

class UrlFtpDownloaderTest {
    @Rule @JvmField
    val tempFolder = TemporaryFolder()
    val targetDir by lazy { tempFolder.root }
    val sut = UrlFtpDownloader()

    companion object {
        val ftpServerHome = File("src/test/resources/fakeftpserver/home/")
        val fakeFtpServer by lazy { FakeFtpServer() }
        fun localFile(file: String) = File(ftpServerHome, file)

        @BeforeClass @JvmStatic
        fun startServer() = fakeFtpServer.start()

        @AfterClass @JvmStatic
        fun stopServer() = fakeFtpServer.stop()
    }

    @Test
    fun `should can download file`() {
        // given
        val port = fakeFtpServer.port
        val fileUrl = URL("ftp://reader:reader@localhost:${port}/halloFtp.txt")
        // when
        sut.download(fileUrl, targetDir)
        // then
        assertThat(targetDir, containsFile(fileUrl.file))
        val downloadedFile = File(targetDir, fileUrl.file)
        val file = fileUrl.file
        assertThat(downloadedFile.readText(), equalTo(localFile(file).readText()))
    }

}
