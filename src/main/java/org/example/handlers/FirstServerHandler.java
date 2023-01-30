package org.example.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端读取到数据: "+byteBuf.toString(StandardCharsets.UTF_8));

        ByteBuf byteBufResponse = getByteBuf(ctx);
        ctx.writeAndFlush(byteBufResponse);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("加油啊！刘六六！".getBytes(StandardCharsets.UTF_8));
        return byteBuf;
    }
}
