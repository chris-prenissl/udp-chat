package com.christophprenissl.udpchat.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpChatRepository {

    suspend fun sendMessage(message: String, ipAddress: String, port: Int) {
        withContext(Dispatchers.IO) {
            val socket = DatagramSocket()
            val sendData = message.toByteArray()
            val sendPacket =
                DatagramPacket(sendData, sendData.size, InetAddress.getByName(ipAddress), port)
            socket.send(sendPacket)
            socket.close()
        }
    }
}