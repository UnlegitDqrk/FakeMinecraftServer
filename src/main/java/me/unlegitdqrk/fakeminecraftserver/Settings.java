/*
 * Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved
 *
 * You are unauthorized to remove this copyright.
 * You have to give Credits to the Author in your project and link this GitHub site: https://github.com/UnlegitDqrk
 */

/*
Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

You need to name the author and his GitHub Page.

Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

The name of the author may not be used to endorse or promote products derived
from this software without specific prior written permission.

You may not use the software for commercial software hosting services.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package me.unlegitdqrk.fakeminecraftserver;

import java.io.File;
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

    public static final File SERVER_ICON = new File("server-icon.png");

}
