package duke.ui;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private <T> void assertListEquality(List<T> expected, List<T> actual) {
        assertEquals(expected, actual);
    }

    @Test
    public void splitIntoLines_exactLineLength() {
        List<String> result = new Ui().splitIntoLines("abc d f s", 3);
        assertListEquality(Arrays.asList("abc", "d f", "s"), result); // using list equality
    }

    @Test
    public void splitIntoLines_overLineLength() {
        List<String> result = new Ui().splitIntoLines("abc def s", 5);
        assertListEquality(Arrays.asList("abc", "def s"), result);
    }

    @Test
    public void splitIntoLines_singleLongWord() {
        List<String> result = new Ui().splitIntoLines("abcdefgh s abcdefghijklmn", 5);
        assertListEquality(Arrays.asList("abcdefgh", "s", "abcdefghijklmn"), result);
    }

    @Test
    public void splitIntoLines_newlines() {
        List<String> result = new Ui().splitIntoLines("abc\ndefg zd\nxy", 3);
        assertListEquality(Arrays.asList("abc", "defg", "zd", "xy"), result);
    }

    @Test
    public void newSay() {
        
    }
}
