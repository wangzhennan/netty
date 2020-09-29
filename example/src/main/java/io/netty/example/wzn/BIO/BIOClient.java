package io.netty.example.wzn.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName BIOClient
 * @Description TODO
 * @Author wangzhennan@hetao101.com
 * @Date 2020-09-29 13:51
 * @Version 1.0
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reqeust();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reqeust();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reqeust();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread3.start();
    }

    public static void reqeust() throws IOException {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1",8080);

            in  =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");

            System.out.println("send order 2 server succeed");
            String resp = in.readLine();
            System.out.println("now "+ resp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
                out =null;
            }
            if( null !=in){
                in.close();
            }
            if(null != socket){
                socket.close();
            }

        }


    }
}
