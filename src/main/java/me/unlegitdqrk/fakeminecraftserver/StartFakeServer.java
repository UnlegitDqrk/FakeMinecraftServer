package me.unlegitdqrk.fakeminecraftserver;

import me.unlegitdqrk.fakeminecraftserver.data.StatusResponse;

import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import static javax.imageio.ImageIO.read;
import static javax.imageio.ImageIO.write;

/**
 * @author UnlegitDqrk
 */

public class StartFakeServer {
    public static StatusResponse response = null;

    public static final int START_PORT = 25565;

    public static final int MAX_PLAYERS = 100;
    public static final int ONLINE_PLAYERS = 10;
    public static final int PROTOCOL_VERSION = 47;
    public static final String PROTOCOL_TEXT = "1.8.8";
    public static final String MOTD_LINE1 = "&4Ein fake Minecraftserver";
    public static final String MOTD_LINE2 = "&cDeveloped by UnlegitDqrk";
    public static final List<String> SAMPLES = Arrays.asList("&cSample 1", "&bSample 2", "&aSample 3");
    public static final File SERVER_ICON = new File("server-icon.png");

    public static void start() throws Exception {
        String serverIconAsString = null;

        if (SERVER_ICON.exists()) {
            BufferedImage image = read(SERVER_ICON);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            write(image, "png", baos);
            serverIconAsString = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
        }

        Message description = new Message();
        String motd = MOTD_LINE1 + "\n" + MOTD_LINE2;
        description.text = ColorConverter.replaceColors(motd).replace("\\n", "\n");

        response = new StatusResponse(ColorConverter.replaceColors(PROTOCOL_TEXT), PROTOCOL_VERSION,
                                        MAX_PLAYERS, ONLINE_PLAYERS,
                                        description, serverIconAsString);

        new SLPServer(START_PORT).run();
    }

}
