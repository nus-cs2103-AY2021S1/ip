package dukechatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dukechatbot.enums.StringMatchEnum;

public class StringMatchParserTest {
    @Test
    void contains_no_match() {
        String actual = "lol 123 name book";
        String subStr = "z";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.NO_MATCH, result);
    }

    @Test
    void contains_partial_match() {
        String actual = "lol 123 name book";
        String subStr = "3 n";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.PARTIAL_MATCH, result);
    }

    @Test
    void contains_word_match() {
        String actual = "lol 123 name book";
        String subStr = "name";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.WORD_MATCH, result);
    }

    @Test
    void contains_full_match() {
        String actual = "lol 123 name book";
        String subStr = "lol 123 name book";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.FULL_MATCH, result);
    }

    @Test
    void emptyspace_no_match() {
        String actual = "lol 123 name book";
        String subStr = "   ";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.NO_MATCH, result);
    }

    @Test
    void blankinput_no_match() {
        String actual = "lol 123 name book";
        String subStr = "";
        StringMatchEnum result = StringMatchParser.match(actual, subStr);
        assertEquals(StringMatchEnum.NO_MATCH, result);
    }
}
