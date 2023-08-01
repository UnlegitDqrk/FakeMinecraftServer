package me.unlegitdqrk.fakeminecraftserver;

import java.util.List;

/**
 * @author UnlegitDqrk
 */

public class Message {
    public String text;

    public boolean bold;
    public boolean italic;
    public boolean underlined;
    public boolean strikethrough;
    public boolean obfuscated;
    public List<Message> extra;

    public ColorConverter.Color color;

    public ColorConverter.ClickEvent clickEvent;

    public Message() {

    }

    public Message(Message old) {
        this.bold = old.bold;
        this.italic = old.italic;
        this.underlined = old.underlined;
        this.strikethrough = old.strikethrough;
        this.color = old.color;
    }
}
