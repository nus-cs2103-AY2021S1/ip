package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UtilsTest {
    private static Stream<Arguments> getConcatenateArguments() {
        String[] arr = new String[]{"This", "is", "," , "a", "test", "case", "."};
        return Stream.of(
                Arguments.of(arr, 0, 7, "This is , a test case ."),
                Arguments.of(arr, 1, 6, "is , a test case"),
                Arguments.of(arr, 2, 3, ","),
                Arguments.of(arr, 1, 1, "")
        );
    }

    @ParameterizedTest
    @MethodSource("getConcatenateArguments")
    public void testConcatenate(String[] arr, int start, int end, String actual) {
        assertEquals(Utils.concatenate(arr, start, end), actual);
    }

    private static Stream<Arguments> getIndexOfArguments() {
        String[] arr = new String[]{"This", "is", "/some", "*special*", "test", "."};
        return Stream.of(
                Arguments.of(arr, "This", 0),
                Arguments.of(arr, "/some", 2),
                Arguments.of(arr, "*special*", 3),
                Arguments.of(arr, ".", 5),
                Arguments.of(arr, "does not exist", Utils.INDEX_NOT_FOUND)
        );
    }

    @ParameterizedTest
    @MethodSource("getIndexOfArguments")
    public void testGetIndexOf(String[] arr, String target, int actual) {
        assertEquals(Utils.getIndexOf(arr, target), actual);
    }

    private static Stream<Arguments> getHasIntegerArguments() {
        return Stream.of(
                Arguments.of(new String[]{"done", "2"}, 1, true),
                Arguments.of(new String[]{"delete", "2", "extra"}, 1, true),
                Arguments.of(new String[]{"done", "extra", "2"}, 2, true),
                Arguments.of(new String[]{"done", "2.2"}, 1, false),
                Arguments.of(new String[]{"delete", "2.2"}, 0, false),
                Arguments.of(new String[]{"delete"}, 1, false),
                Arguments.of(new String[]{"done", "-"}, 1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("getHasIntegerArguments")
    public void testHasInteger(String[] arr, int index, boolean actual) {
        assertEquals(Utils.hasInteger(arr, index), actual);
    }

    private static Stream<Arguments> isMoneyArguments() {
        return Stream.of(
                Arguments.of("$0.01", true),
                Arguments.of("$5.05", true),
                Arguments.of("$300", true),
                Arguments.of("$3", true),
                Arguments.of("$300.00", true),
                Arguments.of("\u00a5500.50", true),
                Arguments.of("100", false),
                Arguments.of("100.00", false),
                Arguments.of("$30.0", false)
        );
    }

    @ParameterizedTest
    @MethodSource("isMoneyArguments")
    public void testIsMoney(String money, boolean expected) {
        assertEquals(Utils.isMoney(money), expected);
    }

    private static Stream<Arguments> convertMoneyToValueArguments() {
        return Stream.of(
                Arguments.of("$0.01", 0.01),
                Arguments.of("$5.05", 5.05),
                Arguments.of("$300", 300.00),
                Arguments.of("$300.00", 300.00),
                Arguments.of("\u00a5500.50", 500.50)
        );
    }

    @ParameterizedTest
    @MethodSource("convertMoneyToValueArguments")
    public void testConvertMoneyToValue(String money, double expected) {
        assertEquals(Utils.convertMoneyToValue(money), expected);
    }
}
