package org.example.mini;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.example.handlers.FirstServerHandler;

public class MiniNettyServer {
    public static void main(String[] args) {
        // 接入新链接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 维护旧链接的新消息
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 服务器启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)   // 配置父group和子group
                .channel(NioServerSocketChannel.class)  // 表示接受nio请求
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        // 业务代码
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
        });
        bindPort(serverBootstrap, 1001);
    }

    private static void bindPort(ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("Binding port: "+port);
            } else {
                bindPort(serverBootstrap, port+1);
            }
        });
    }
}
