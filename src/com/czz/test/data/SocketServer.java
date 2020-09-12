package com.czz.test.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-09-12 09:42:00
 * @description :
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        //创建服务器接口，并指定端口号
        ServerSocket serverSocket = new ServerSocket(port);
        //监听客户端
        Socket socket = serverSocket.accept();
        //获取输入流
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(inputStream));

        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

        do {
            double length = dis.readDouble();
            System.out.println("服务器端收到的边长数据为：" + length);
            double result = length * length;
            dos.writeDouble(result);
            dos.flush();
        } while (dis.readInt() != 0);

        socket.close();
        serverSocket.close();

    }
}
