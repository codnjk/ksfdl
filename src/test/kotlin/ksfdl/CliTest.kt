package ksfdl

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import ksfdl.ftp.FtpDownloader
import org.junit.Test
import java.io.File
import java.net.URL

class CliTest {

    val targetDir = File(System.getProperty("user.dir"))
    val ftpDownloader = mock<FtpDownloader>()
    val sut = Cli(ftpDownloader)

    @Test
    fun `should call download`() {
        // given
        val fileUrl = URL("ftp://user@testdomain/test.file")
        val args = listOf(fileUrl.toString())
        // when
        sut.accept(args)
        // then
        verify(ftpDownloader).download(fileUrl, targetDir)
    }

    @Test(expected = OneFileUrlParameterExpected::class)
    fun `should fail if no parameter is provided`() {
        // given
        val noArgs = emptyList<String>()
        // expect boom
        sut.accept(noArgs)
    }
}
