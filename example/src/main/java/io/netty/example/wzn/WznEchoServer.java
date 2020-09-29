package io.netty.example.wzn;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.echo.EchoClientHandler;
import io.netty.example.echo.EchoServer;

/**
 * @ClassName WznEchoServer
 * @Description TODO
 * @Author wangzhennan@hetao101.com
 * @Date 2020-09-24 11:42
 * @Version 1.0
 */
public class WznEchoServer {

    private final int port;

    public WznEchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //用于引导绑定和启动
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //指定 指定nio处理事件 接收新连接  接收数据 写数据等
            serverBootstrap.group(group)
                    //指定nio 处理事件
                    .channel(NioServerSocketChannel.class)
                    //指定端口
                    .localAddress(port)
                    //添加监听
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {

                            ch.pipeline().addLast( new EchoServerHandler());

                        }
                    });
            ChannelFuture sync = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName()+"started and listen on “"+sync.channel().localAddress());
            sync.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        WznEchoServer wzn= new WznEchoServer(65535);
        wzn.start();
    }
}
