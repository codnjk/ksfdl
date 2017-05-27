package ksfdl.ext.url

import java.io.File
import java.net.URL

inline val URL.fileName: String
    get() = File(file).name

inline val URL.fileNameWithoutExtension: String
    get() = fileName.substringBeforeLast('.')
