package ksfdl

import ksfdl.ftp.FtpDownloader
import ksfdl.ftp.impl.UrlFtpDownloader
import java.io.File
import java.net.URL

class Cli(val ftpDownloader: FtpDownloader) {
    fun accept(args: List<String>) {
        val urlString = args.getOrNull(0) ?: throw OneFileUrlParameterExpected()
        val userDir = File(System.getProperty("user.dir"))
        ftpDownloader.download(URL(urlString), userDir)
    }
}

fun main(args: Array<String>) {
    val cli = Cli(UrlFtpDownloader())
    val argList = listOf(*args)
    cli.accept(argList)
}

class OneFileUrlParameterExpected : RuntimeException()
