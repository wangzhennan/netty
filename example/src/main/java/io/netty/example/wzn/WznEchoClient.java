package io.netty.example.wzn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.echo.EchoClientHandler;

import java.net.InetSocketAddress;

/**
 * @ClassName WznEchoClient
 * @Description TODO
 * @Author wangzhennan@hetao101.com
 * @Date 2020-09-24 14:56
 * @Version 1.0
 */
public class WznEchoClient {

    private final String host;

    private final int port;

    public WznEchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }



    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect().sync();
            System.out.println("clinet start");
            sync.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new WznEchoClient("localhost",65535).start();
    }
}
