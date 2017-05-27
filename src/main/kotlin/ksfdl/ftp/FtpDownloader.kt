package ksfdl.ftp

import java.io.File
import java.net.URL

interface FtpDownloader {
    fun download(url: URL, targetDir: File)
}
