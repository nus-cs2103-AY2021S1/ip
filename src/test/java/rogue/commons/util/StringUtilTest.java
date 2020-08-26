package rogue.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    @Test
    public void findIndex_strInArray_success() {
        String[] strings = { "first", "second", "third" };

        assertEquals(StringUtil.findIndex(strings, "first"), 0);
        assertEquals(StringUtil.findIndex(strings, "second"), 1);
        assertEquals(StringUtil.findIndex(strings, "third"), 2);
    }

    @Test
    public void findIndex_strNotInArray_success() {
        String[] strings = { "first", "second", "third" };

        assertEquals(StringUtil.findIndex(strings, "fourth"), -1);
        assertEquals(StringUtil.findIndex(strings, "123"), -1);
    }
}
