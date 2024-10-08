package io.github.thibaultbee.srtdroid.example.tests

import android.util.Log
import io.github.thibaultbee.srtdroid.core.enums.SockOpt
import io.github.thibaultbee.srtdroid.ktx.CoroutineSrtSocket
import io.github.thibaultbee.srtdroid.ktx.connect
import io.github.thibaultbee.srtdroid.ktx.send
import kotlinx.coroutines.delay

class TestClient : Test {
    private val numOfMessages = 100

    companion object {
        private val TAG = TestClient::class.simpleName
    }

    override val name: String = this::class.simpleName!!

    override suspend fun run(ip: String, port: Int) {
        val socket = CoroutineSrtSocket()

        try {
            Log.i(TAG, "Will send $numOfMessages messages to the server")

            socket.setSockFlag(SockOpt.SENDER, 1)
            socket.connect(ip, port)
            Log.i(
                TAG,
                "Is connected: ${socket.isConnected}. Will send $numOfMessages messages to the server"
            )

            repeat(numOfMessages) {
                socket.send("This message should be sent to the other side")
            }
            // If session is close too early, last msg will not be receive by server
            delay(1000)

            Log.i(TAG, "Sent $numOfMessages messages")
        } catch (e: Exception) {
            throw e
        } finally {
            socket.close()
        }
    }

}