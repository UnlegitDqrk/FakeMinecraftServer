package me.unlegitdqrk.fakeminecraftserver;

import me.unlegitdqrk.fakeminecraftserver.data.StatusResponse;

import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static javax.imageio.ImageIO.read;
import static javax.imageio.ImageIO.write;

/**
 * @author UnlegitDqrk
 */

public class FakeMinecraftServer {
    public static StatusResponse response = null;

    public static final int START_PORT = 25565;

    public static final String ENGINE = "classic";

    public static final int MAX_PLAYERS = 100;
    public static final int ONLINE_PLAYERS = 10;

    public static final int PROTOCOL_VERSION = 47;
    public static final String PROTOCOL_TEXT = "1.8.8";

    public static final String MOTD_LINE1 = "&4Ein fake Minecraftserver";
    public static final String MOTD_LINE2 = "&cDeveloped by UnlegitDqrk";

    public static final String KICK_MESSAGE = "&4Just a Fakeserver!\n&aDeveloped by UnlegitDqrk";
    public static final List<String> SAMPLES = Arrays.asList("&4Fakeserver", "&5by", "&aUnlegitDqrk");

    public static final File SERVER_ICON = new File("server-icon.png");

    public static void start() throws Exception {
        String serverIconAsString = null;

        if (SERVER_ICON.exists()) {
            BufferedImage image = read(SERVER_ICON);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            write(image, "png", baos);
            serverIconAsString = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
        } else System.err.println("Starting fake server without icon!");

        String motd = MOTD_LINE1 + "\n" + MOTD_LINE2;

        Message description;

        if (ENGINE.equalsIgnoreCase("json"))
            description = ChatConverter.toJSONChat(ChatConverter.replaceColors(motd).replace("\\n", "\n"));
        else {
            description = new Message();
            description.text = ChatConverter.replaceColors(motd).replace("\\n", "\n");
        }

        response = new StatusResponse(PROTOCOL_TEXT, PROTOCOL_VERSION,
                MAX_PLAYERS, ONLINE_PLAYERS,
                description, serverIconAsString);

        new SLPServer(START_PORT).run();
    }

}
