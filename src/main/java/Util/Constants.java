package Util;

import java.util.regex.Pattern;

public class Constants {
    public static final String GREETING =
            "Hello/您好/こんにちは I'm Duke\n" +
            "What can I do for you?";
    public static final String DIVIDER = "____________________________";

    public static final Pattern EXITPATTERN = Pattern.compile("^(b|B)(y|Y)(e|E)$");

}
