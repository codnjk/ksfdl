package ksfdl.ext.url

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import java.net.URL

class ExtensionKtTest {
    @Test
    fun `should get fileName from url`() {
        // given
        val sut = URL("file:a/path/to/file_name.txt")
        // when
        val result = sut.fileName
        // then
        assertThat(result, equalTo("file_name.txt"))
    }

    @Test
    fun `should get fileNameWithoutExtension from url`() {
        // given
        val sut = URL("file:a/path/to/file_name.txt")
        // when
        val result = sut.fileNameWithoutExtension
        // then
        assertThat(result, equalTo("file_name"))
    }
}
