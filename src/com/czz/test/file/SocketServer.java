package com.czz.test.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : czz
 * @version : 1.0.0
 * @create : 2020-09-12 10:26:00
 * @description :
 */
public class SocketServer {
    public static void main(String[] args) {
        acceptFile(8001,"D:\\资料\\传输文件");
    }

    public static void acceptFile(int port,String filePath){
        Socket accept = null;
        ServerSocket serverSocket =null;
        try{
        //创建服务端口
        serverSocket = new ServerSocket(port);
        //监听端口
        accept = serverSocket.accept();
        System.out.println("已连接"+accept.getInetAddress());

        //创建数据接收流
        InputStream ain = accept.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(new BufferedInputStream(ain));
        //创建数据回传流
        OutputStream aout = accept.getOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(aout));
            dos.writeUTF("已存放在:"+filePath);
            dos.flush();
            //文件接收
            File file = new File(filePath);
            FileOutputStream fout = new FileOutputStream(file);
            System.out.println("=====接收文件开始====="+System.currentTimeMillis());
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1){
                fout.write(bytes,0,length);
                fout.flush();
            }
            System.out.println("=====接收文件结束====="+System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (accept != null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
