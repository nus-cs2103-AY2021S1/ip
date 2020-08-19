package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilFunction {

    public static Boolean matchPattern(Pattern pattern, String string){
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
