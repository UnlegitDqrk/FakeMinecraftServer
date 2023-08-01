/*
Copyright (c) 2012, md_5. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

The name of the author may not be used to endorse or promote products derived
from this software without specific prior written permission.

You may not use the software for commercial software hosting services without
written permission from the author.

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

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ColorConverter {
    private static final char COLOR_CHAR = '\u00A7';
    private static final Pattern url = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$");
    public static final char ESCAPE = '\u00A7';

    public static String replaceColors(String text) {
        char[] charArray = text.toCharArray();

        for (int index = 0; index < charArray.length; index++) {
            char chr = charArray[index];
            if (chr != '&') continue;
            if ((index + 1) == charArray.length) break;

            char forward = charArray[index + 1];

            if ((forward >= '0' && forward <= '9') || (forward >= 'a' && forward <= 'f')
                    || (forward >= 'k' && forward <= 'r')) charArray[index] = ESCAPE;
        }
        return new String(charArray);
    }

    public class ClickEvent {
        public String action;
        public String value;
    }

    public enum Color {
        @SerializedName("black")
        BLACK("0"),
        @SerializedName("dark_blue")
        DARK_BLUE("1"),
        @SerializedName("dark_green")
        DARK_GREEN("2"),
        @SerializedName("dark_aqua")
        DARK_AQUA("3"),
        @SerializedName("dark_red")
        DARK_RED("4"),
        @SerializedName("purple")
        DARK_PURPLE("5"),
        @SerializedName("gold")
        GOLD("6"),
        @SerializedName("gray")
        GRAY("7"),
        @SerializedName("dark_gray")
        DARK_GRAY("8"),
        @SerializedName("blue")
        BLUE("9"),
        @SerializedName("green")
        GREEN("a"),
        @SerializedName("aqua")
        AQUA("b"),
        @SerializedName("red")
        RED("c"),
        @SerializedName("light_purple")
        LIGHT_PURPLE("d"),
        @SerializedName("yellow")
        YELLOW("e"),
        @SerializedName("white")
        WHITE("f");

        public String code;

        Color(String code) {
            this.code = code;
        }


        private static HashMap<String, Color> codeMap = new HashMap<>();

        public static Color fromCode(String code) {
            return codeMap.get(code);
        }

        static {
            for (Color color : values()) {
                codeMap.put(color.code, color);
            }
        }
    }
}

