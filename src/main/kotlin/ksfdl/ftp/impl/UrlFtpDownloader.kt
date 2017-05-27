package ksfdl.ftp.impl

import ksfdl.ext.url.fileName
import ksfdl.ftp.FtpDownloader
import java.io.File
import java.net.URL

class UrlFtpDownloader : FtpDownloader {
    override fun download(url: URL, targetDir: File) {
        val bytes = url.readBytes()
        val localFile = File(targetDir, url.fileName)
        localFile.writeBytes(bytes)
    }
}
