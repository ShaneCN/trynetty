package org.example.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("init: 9, 100",buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        print("writeBytes(1,2,3,4)", buffer);

        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        buffer.writeBytes(new byte[]{5});
        print("writeInt(12)",buffer);


    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("================"+action+"================");
        System.out.println("capacitiy():"+buffer.capacity());
        System.out.println("max capacity():"+buffer.maxCapacity());
        System.out.println("readerIndex():"+buffer.readerIndex());
        System.out.println("readableBytes()"+buffer.readableBytes());
        System.out.println("isReadable()"+buffer.isReadable());
        System.out.println("writeIndex()"+buffer.writerIndex());
        System.out.println("writableBytes"+buffer.writableBytes());
        System.out.println("isWritable():"+buffer.isWritable());
        System.out.println("maxWritableBytes():"+buffer.maxWritableBytes());
        System.out.println();

    }
}
