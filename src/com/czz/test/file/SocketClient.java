package com.czz.test.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-09-12 10:27:00
 * @description :
 */
public class SocketClient {
    public static void main(String[] args) {
//       sendFile("localhost",8001,"D:\\env\\helloworld.txt");
//    }
//    public static void sendFile(String host,int port,String filePath){
        String host = "localhost";
        int port = 8001;
        String filePath = "D:\\env\\helloworld.txt";
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            Socket socket = new Socket(host,port);
            InputStream ain = socket.getInputStream();
            dis = new DataInputStream(new DataInputStream(ain));
            OutputStream aout = socket.getOutputStream();
            dos = new DataOutputStream(aout);
            File file = new File(filePath);
            FileInputStream fis =new FileInputStream(file);

            System.out.println("=====开始传输文件====="+System.currentTimeMillis());
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) != -1){
                dos.write(bytes,0,length);
                dos.flush();
            }
            System.out.println("=====传输文件结束====="+System.currentTimeMillis());
            String s = dis.readUTF();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null){
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null){
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
