package me.unlegitdqrk.fakeminecraftserver.data;

import me.unlegitdqrk.fakeminecraftserver.Message;

/**
 * @author UnlegitDqrk
 */

public class StatusResponse {
    private final Version version;
    private final Players players;
    private final Message description;
    private final String serverIconAsString;

    public Message getDescription() {
        return description;
    }

    public Players getPlayers() {
        return players;
    }

    public String getServerIconAsString() {
        return serverIconAsString;
    }

    public Version getVersion() {
        return version;
    }

    public StatusResponse(String name, int protocol, int max, int online, Message description, String serverIconAsString) {
        this.version = new Version(name, protocol);
        this.players = new Players(max, online);
        this.description = description;
        this.serverIconAsString = serverIconAsString;
    }

    public class Version {
        private final String name;
        private final int protocol;

        public String getName() {
            return name;
        }

        public int getProtocol() {
            return protocol;
        }

        public Version(String name, int protocol) {
            this.name = name;
            this.protocol = protocol;
        }
    }

    private class Players {
        private final int maxPlayers;
        private final int onlinePlayers;

        public int getMaxPlayers() {
            return maxPlayers;
        }

        public int getOnlinePlayers() {
            return onlinePlayers;
        }

        private Players(int maxPlayers, int onlinePlayers) {
            this.maxPlayers = maxPlayers;
            this.onlinePlayers = onlinePlayers;
        }
    }
}
