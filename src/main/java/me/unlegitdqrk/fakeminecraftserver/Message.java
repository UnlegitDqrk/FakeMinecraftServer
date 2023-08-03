/*
 * Copyright (C) 2023 UnlegitDqrk. - All Rights Reserved
 *
 * You are unauthorized to remove this copyright.
 * You have to give Credits to the Author in your project and link this GitHub site: https://github.com/UnlegitDqrk
 */

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

    public Color color;

    public ClickEvent clickEvent;

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
