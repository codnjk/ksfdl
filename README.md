## ksfdl

Simple ftp downloader written in Kotlin programming language.

### Build
```bash
$ ./gradlew clean build
```
This step creates the distribution packages located in the `build/distributions` directory.

### Usage

There are 2 possibilities to run the application. The file will be safed to the exection directory.

**Using Gradle** 

```bash
$ ./gradlew run <ftp-file-url>
# ftp-file-url like: ftp://[<user>:<password>@]<host>[:<port>]/<file-path>
```

**Using Distribution**

Run following command in the unpacked distribution folder:

```bash
$ ./bin/ksfdl <ftp-file-url>
# ftp-file-url like: ftp://[<user>:<password>@]<host>[:<port>]/<file-path>
```
