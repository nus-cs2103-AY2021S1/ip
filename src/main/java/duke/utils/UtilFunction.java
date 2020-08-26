package duke.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilFunction {
    /**
     * util function used to match pattern of the string
     * @param patternStr the pattern you want to check
     * @param string the string to check
     * @return true if the string matches the string pattern
     */
    public static Boolean matchPattern(String patternStr, String string){
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
