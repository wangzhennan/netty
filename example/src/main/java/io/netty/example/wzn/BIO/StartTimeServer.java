package io.netty.example.wzn.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName StartTimeServer
 * @Description TODO
 * @Author wangzhennan@hetao101.com
 * @Date 2020-09-29 11:10
 * @Version 1.0
 */
public class StartTimeServer {


    public static void main(String[] args) throws IOException {
        int port =8080;

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("服务开启!!,端口:"+port);
            Socket socket;
            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(50, 1000);

            while (true){

                socket = server.accept();
                //普通实现
               // new Thread(new StartTimeServerHandler(socket)).start();
                //利用线程池
                timeServerHandlerExecutePool.execute(new StartTimeServerHandler(socket));
            }
        } finally {

        }
    }
}
