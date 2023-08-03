/*
 * Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved
 *
 * You are unauthorized to remove this copyright.
 * You have to give Credits to the Author in your project and link this GitHub site: https://github.com/UnlegitDqrk
 */

package me.unlegitdqrk.fakeminecraftserver;

import java.util.Arrays;
import java.util.List;

public class Settings {

    public static final int START_PORT = 25565;

    public static final String ENGINE = "classic";
    public static final int SOCKET_TIMEOUT_IN_SECONDS = 3;

    public static final int MAX_PLAYERS = 100;
    public static final int ONLINE_PLAYERS = 10;

    public static final int PROTOCOL_VERSION = 47;
    public static final String PROTOCOL_TEXT = "1.8.8";

    public static final String MOTD_LINE1 = "&4Ein fake Minecraftserver";
    public static final String MOTD_LINE2 = "&cDeveloped by UnlegitDqrk";

    public static final String KICK_MESSAGE = "&4Just a Fakeserver!\n&aDeveloped by UnlegitDqrk";
    public static final List<String> SAMPLES = Arrays.asList("&4Fakeserver", "&5by", "&aUnlegitDqrk");

}
