package io.netty.example.wzn.BIO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @ClassName StartTimeServerHandler
 * @Description TODO
 * @Author wangzhennan@hetao101.com
 * @Date 2020-09-29 11:16
 * @Version 1.0
 */
public class StartTimeServerHandler implements Runnable{


    private Socket socket;

    public StartTimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("thread name is"+name);
        //读取数据
        BufferedReader in  =null;
        PrintWriter out =null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if(body ==null){
                    break;
                }
                System.out.println(Thread.currentThread().getId()+"接受数据:"+body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() :"BAD ORDER";
                System.out.println(currentTime);
                out.println(currentTime);
            }


        } catch (IOException e) {
           if(in != null){
               try {
                   in.close();
               }catch (IOException e1){
                   e1.printStackTrace();
               }

           }
           if(out != null){
               out.close();
               out =null;
           }
           if(this.socket != null){
               try {
                   this.socket.close();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
        }  finally {


        }

    }
}
