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
    Version version;
    Players players;
    Message description;

    public String favicon;

    public StatusResponse(String name, int protocol, int max, int online, Message description,String favicon) {
        this.version = new Version(name, protocol);
        this.players = new Players(max, online);
        this.description = description;
        this.favicon = favicon;
    }

    public class Version {
        String name;
        int protocol;

        public Version(String name, int protocol) {
            this.name = name;
            this.protocol = protocol;
        }
    }

    class Players {
        int max;
        int online;
        List<Sample> sample;

        Players(int max, int online) {
            this.max = max;
            this.online = online;
            this.sample = new ArrayList<>();

            for (String sample : Settings.SAMPLES) {
                this.sample.add(new Sample(ChatConverter.replaceColors(sample)));
            }
        }

        class Sample {
            String name;
            String id;

            Sample(String name) {
                this.name = name;
                this.id = UUID.randomUUID().toString();
            }
        }
    }
}
