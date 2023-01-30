package org.example.mini;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.handlers.FirstClientHandler;

import java.net.InetSocketAddress;

public class MiniNettyClient {
    private static final int MAX_RETRY = 5;
    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new FirstClientHandler());
            }
        });


        bootstrap.connect("127.0.0.1", 1001).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });
    }

    private static void connect(Bootstrap bootstrap,String ip, int port, int retry) {
        bootstrap.connect(ip, port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功");
            } else if (0 == retry) {
                System.out.println("重试超过最大次数，连接失败");
            } else {
                int times = MAX_RETRY - retry + 1;
            }
        });
    }
}
