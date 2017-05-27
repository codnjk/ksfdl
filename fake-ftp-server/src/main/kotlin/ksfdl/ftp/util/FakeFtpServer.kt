package ksfdl.ftp.util

import org.apache.ftpserver.FtpServer
import org.apache.ftpserver.FtpServerFactory
import org.apache.ftpserver.listener.ListenerFactory
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory
import java.io.File
import java.net.ServerSocket

class FakeFtpServer(val usersPropsFile: File = File("fakeftpserver/user.properties")) {

    private val serverFactory = FtpServerFactory()
    private lateinit var server: FtpServer
    private var _port = 0

    val port: Int
        get() = _port

    fun start() {
        serverFactory.userManager = with(PropertiesUserManagerFactory()) {
            file = usersPropsFile
            createUserManager()
        }

        useFreePort { freePort ->
            val listener = with(ListenerFactory()) {
                port = freePort
                createListener()
            }
            serverFactory.addListener("default", listener)
            _port = freePort
        }

        server = serverFactory.createServer().apply { start() }

        println("${this::class.java.simpleName} on port $port started")
    }

    fun stop() {
        server.stop()
        println("${this::class.java.simpleName} on port $port stopped")
    }
}

private fun useFreePort(consumer: (Int) -> Unit) = ServerSocket(0).use { consumer(it.localPort) }
