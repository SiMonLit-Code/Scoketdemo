package com.czz.test.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-09-12 10:05:00
 * @description :
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        String host = "localhost";
        Socket socket = new Socket(host,port);
        //获取输入流
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(inputStream));
        //获取输出流
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(outputStream));

        Scanner sc = new Scanner(System.in);

        boolean flag = false;

        while (!flag) {

            System.out.println("请输入正方形的边长:");
            double length = sc.nextDouble();

            dos.writeDouble(length);
            dos.flush();

            double area = dis.readDouble();

            System.out.println("服务器返回的计算面积为:" + area);

            while (true) {

                System.out.println("继续计算？(Y/N)");

                String str = sc.next();

                if (str.equalsIgnoreCase("N")) {
                    dos.writeInt(0);
                    dos.flush();
                    flag = true;
                    break;
                } else if (str.equalsIgnoreCase("Y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }
            }
        }
    }
}
