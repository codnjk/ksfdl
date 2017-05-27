package ksfdl.test.util

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import java.io.File

fun containsFile(fileName: String) = object : Matcher<File> {
    override val description: String
        get() = "is a dir that contains a file named '$fileName'"

    override fun invoke(actual: File) =
        if (File(actual, fileName).exists()) MatchResult.Match
        else MatchResult.Mismatch("$actual does not contain file named $fileName")
}

fun hasSubDir(dirName: String): Matcher<File> = object : Matcher<File> {
    override val description
        get() = "has sub dir named '$dirName'"

    override fun invoke(actual: File): MatchResult {
        val subDir = actual.resolve(dirName)
        return if (with(subDir) { exists() && isDirectory })
            MatchResult.Match
        else
            MatchResult.Mismatch("'$actual' has no such sub dir named $dirName.")
    }
}
