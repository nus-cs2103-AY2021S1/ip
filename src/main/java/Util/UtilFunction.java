package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilFunction {

    public static Boolean matchPattern(String patternStr, String string){
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
