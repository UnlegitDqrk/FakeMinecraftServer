package me.unlegitdqrk.fakeminecraftserver;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.unlegitdqrk.fakeminecraftserver.streams.MojewInputStream;
import me.unlegitdqrk.fakeminecraftserver.streams.MojewOutputStream;


/**
   * @author UnlegitDqrk
 */

public class BasePacketHandler extends ChannelInboundHandlerAdapter {
    private final Gson gson = new Gson();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MojewInputStream inputStream = new MojewInputStream((ByteBuf) msg);

        // handshake
        int length = inputStream.readInt();
        int id = inputStream.readInt();

        if (id == 0) {
            // status request
            try {
                int version = inputStream.readInt();
                String address = inputStream.readUTF();
                int port = inputStream.readUnsignedShort();
                int state = inputStream.readInt();
                System.out.println("Received request: " + length + ", " + id + ", " + version + ", " + address + ", " + port + ", " + state);
            } catch (Exception ignored) {
                // status request packet is sent inconsistently, so we ignore it
            }

            inputStream.close();

            // status response
            String response = gson.toJson(FakeMinecraftServer.response).replace(ChatConverter.ESCAPE + "", "\\u00A7"); // Mojew's parser needs this escaped (classic)

            if (FakeMinecraftServer.response.favicon == null)
                System.out.println("Sending response: " + response);
            else
                System.out.println("Sent response with image data.");

            MojewOutputStream outputStream = new MojewOutputStream(Unpooled.buffer());
            MojewOutputStream dataOutputStream = new MojewOutputStream(Unpooled.buffer());

            dataOutputStream.writeInt(0);
            dataOutputStream.writeUTF(response);
            dataOutputStream.close();

            outputStream.writeInt(dataOutputStream.writtenBytes());
            outputStream.write(dataOutputStream.getData());
            outputStream.close();

            ctx.writeAndFlush(outputStream.buffer());
        } else if (id == 1) {
            // ping request
            long time = inputStream.readLong();
            System.out.println("Received ping packet: " + length + ", " + id + ", " + time);

            // ping response
            MojewOutputStream outputStream = new MojewOutputStream(Unpooled.buffer());
            MojewOutputStream dataOutputStream = new MojewOutputStream(Unpooled.buffer());

            dataOutputStream.writeInt(1);
            dataOutputStream.writeLong(time);
            dataOutputStream.close();

            outputStream.writeInt(dataOutputStream.writtenBytes());
            outputStream.write(dataOutputStream.getData());
            outputStream.close();

            ctx.writeAndFlush(outputStream.buffer());
        } else if (id == 2) {
            // TODO: Fixing kick message

            // login attempt
            System.out.println("Received login packet: " + length + ", " + id);

            // login response
            MojewOutputStream outputStream = new MojewOutputStream(Unpooled.buffer());
            MojewOutputStream dataOutputStream = new MojewOutputStream(Unpooled.buffer());

            dataOutputStream.writeInt(2);
            dataOutputStream.writeUTF("{text:\""+ ChatConverter.replaceColors(FakeMinecraftServer.KICK_MESSAGE).replace("\\n", "\n") + "\", color: white}");
            dataOutputStream.close();

            outputStream.writeInt(dataOutputStream.writtenBytes());
            outputStream.write(dataOutputStream.getData());
            outputStream.close();

            ctx.writeAndFlush(outputStream.buffer());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
