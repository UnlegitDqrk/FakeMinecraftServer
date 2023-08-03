/*
 * Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved
 *
 * You are unauthorized to remove this copyright.
 * You have to give Credits to the Author in your project and link this GitHub site: https://github.com/UnlegitDqrk
 */

package me.unlegitdqrk.fakeminecraftserver.network;

import com.google.gson.Gson;
import me.unlegitdqrk.fakeminecraftserver.ChatConverter;
import me.unlegitdqrk.fakeminecraftserver.FakeMinecraftServer;
import me.unlegitdqrk.fakeminecraftserver.Settings;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @author UnlegitDqrk
 */

public class ResponderThread extends Thread {

    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final String remoteHost;
    private int clientProtocolVersion;
    private Thread thread = null;
    private boolean enabled = false;

    public ResponderThread(Socket socket) throws IOException {
        this.socket = socket;

        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());

        this.remoteHost = socket.getRemoteSocketAddress().toString().substring(1);

        socket.setSoTimeout(Settings.SOCKET_TIMEOUT_IN_SECONDS * 1000);

        this.enabled = true;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        boolean showMotd = false;

        try {
            while (this.socket.isConnected() && this.enabled) {
                final int length = ByteBufUtils.readVarInt(this.inputStream);
                final int packetId = ByteBufUtils.readVarInt(this.inputStream);

                System.out.println("length: " + length + "  packet id: " + packetId);

                if (length == 0) return;

                // handshake
                if (packetId == 0) {
                    if (!showMotd) {
                        final int version = ByteBufUtils.readVarInt(this.inputStream);
                        @SuppressWarnings("unused") final String ip = ByteBufUtils.readUTF8(this.inputStream);
                        @SuppressWarnings("unused") final int port = this.inputStream.readUnsignedShort();
                        final int state = ByteBufUtils.readVarInt(this.inputStream);

                        System.out.println("(State request) length:" + length + " id:" + packetId + " version:" + version + " state:" + state);
                        clientProtocolVersion = version;

                        // state  1=status  2=login
                        if (state == 1) {
                            // ping / status request
                            showMotd = true;
                            System.out.println("ping: " + this.remoteHost);
                        } else if (state == 2) {
                            // login attempt
                            writeData("{text:\"" + ChatConverter.replaceColors(Settings.KICK_MESSAGE) + "\", color: white}");
                            System.out.println("Kick: " + this.remoteHost + " - " + Settings.KICK_MESSAGE);
                            return;
                        }
                    } else {
                        // info packet
                        System.out.println("(Info request) length:" + length + " id:" + packetId);
                        final String motd = createInfo();

                        if (motd == null || motd.isEmpty()) {
                            System.out.println("Info is not initialized!");
                            return;
                        }

                        writeData(motd);
                        showMotd = false;
                    }
                } else if (packetId == 1) {
                    long receivedLong = this.inputStream.readLong();
                    System.out.println("Pong: " + receivedLong);

                    ByteBufUtils.writeVarInt(this.outputStream, 9);
                    ByteBufUtils.writeVarInt(this.outputStream, 1);

                    this.outputStream.writeLong(receivedLong);
                    this.outputStream.flush();
                } else {
                    System.out.println("Unknown packet: " + packetId);
                    return;
                }
            }
        }
        // Ignore this unnecessary error
        catch (EOFException ignored) {
            System.out.println("(end of socket)");
        } catch (SocketTimeoutException ignored) {
            System.out.println("(socket timeout)");
        } catch (SocketException ignored) {
            System.out.println("(socket closed)");
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeSocket();
            this.thread = null;
        }
    }

    public final int getClientProtocolVersion() {
        return clientProtocolVersion;
    }

    private final void closeSocket() {
        this.enabled = false;

        FakeMinecraftServer.safeClose(this.inputStream);
        FakeMinecraftServer.safeClose(this.outputStream);
        FakeMinecraftServer.safeClose(this.socket);

        if (this.thread != null) this.thread.interrupt();
    }

    private final void writeData(final String data) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        ByteBufUtils.writeVarInt(dataOutputStream, 0);
        ByteBufUtils.writeUTF8(dataOutputStream, data);
        ByteBufUtils.writeVarInt(this.outputStream, byteArrayOutputStream.size());

        this.outputStream.write(byteArrayOutputStream.toByteArray());
        this.outputStream.flush();
    }

    private final String createInfo() {
        return new Gson().toJson(FakeMinecraftServer.getResponse());
    }
}
