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

package me.unlegitdqrk.fakeminecraftserver.data;

import me.unlegitdqrk.fakeminecraftserver.ChatConverter;
import me.unlegitdqrk.fakeminecraftserver.Message;
import me.unlegitdqrk.fakeminecraftserver.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author UnlegitDqrk
 */

public class StatusResponse {
    private Version version;
    private Players players;
    private Message description;

    public String favicon;

    public StatusResponse(String name, int protocol, int max, int online, Message description,String favicon) {
        this.version = new Version(name, protocol);
        this.players = new Players(max, online);
        this.description = description;
        this.favicon = favicon;
    }

    public class Version {
        private String name;
        private int protocol;

        public Version(String name, int protocol) {
            this.name = name;
            this.protocol = protocol;
        }
    }

    public class Players {
        private int max;
        private int online;
        private List<Sample> sample;

        public Players(int max, int online) {
            this.max = max;
            this.online = online;
            this.sample = new ArrayList<>();

            for (String sample : Settings.SAMPLES) {
                this.sample.add(new Sample(ChatConverter.replaceColors(sample)));
            }
        }

        public class Sample {
            private String name;
            private String id;

            public Sample(String name) {
                this.name = name;
                this.id = UUID.randomUUID().toString();
            }
        }
    }
}
