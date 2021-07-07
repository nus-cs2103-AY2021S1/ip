package dukechatbot.parser;

import static dukechatbot.enums.StringMatchEnum.FULL_MATCH;
import static dukechatbot.enums.StringMatchEnum.NO_MATCH;
import static dukechatbot.enums.StringMatchEnum.PARTIAL_MATCH;
import static dukechatbot.enums.StringMatchEnum.WORD_MATCH;

import java.util.Arrays;

import dukechatbot.enums.StringMatchEnum;

public class StringMatchParser {
    public static StringMatchEnum match(String actual, String substring) {
        String lowerCaseActual = actual.toLowerCase();
        String lowerCaseSubstr = substring.toLowerCase().trim();
        
        if (lowerCaseSubstr.isBlank()) {
            return NO_MATCH;
        }
        
        if (lowerCaseActual.equals(lowerCaseSubstr)) {
            return FULL_MATCH;
        }

        if (Arrays.asList(lowerCaseActual.split("\\s+")).contains(lowerCaseSubstr)) {
            return WORD_MATCH;
        }

        if (lowerCaseActual.contains(lowerCaseSubstr)) {
            return PARTIAL_MATCH;
        }

        return NO_MATCH;
    }
}
