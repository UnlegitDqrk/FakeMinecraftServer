/*
 * Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved
 *
 * You are unauthorized to remove this copyright.
 * You have to give Credits to the Author in your project and link this GitHub site: https://github.com/UnlegitDqrk
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
