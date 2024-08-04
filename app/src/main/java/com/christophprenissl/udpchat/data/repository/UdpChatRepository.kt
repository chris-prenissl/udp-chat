package com.christophprenissl.udpchat.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpChatRepository {
    private var destinationIpAddress: String = "10.0.2.2"
    private var sendPort: Int = 12345
    private var receivePort: Int = 54321

    fun setup(destinationIpAddress: String, sendPort: Int, receivePort: Int) {
        this.destinationIpAddress = destinationIpAddress
        this.sendPort = sendPort
        this.receivePort = receivePort
    }

    fun receiveMessages() = flow {
        val socket = DatagramSocket(receivePort)
        val receiveData = ByteArray(1024)
        val receivePacket = DatagramPacket(receiveData, receiveData.size)
        while (true) {
            socket.receive(receivePacket)
            val message = String(receivePacket.data, 0, receivePacket.length)
            emit(message)
        }
    }
        .flowOn(Dispatchers.IO)

    suspend fun sendMessage(message: String) {
        withContext(Dispatchers.IO) {
            val socket = DatagramSocket()
            val sendData = message.toByteArray()
            val sendPacket =
                DatagramPacket(
                    sendData,
                    sendData.size,
                    InetAddress.getByName(destinationIpAddress),
                    sendPort
                )
            socket.send(sendPacket)
            socket.close()
        }
    }
}