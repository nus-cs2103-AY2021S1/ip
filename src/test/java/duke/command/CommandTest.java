package duke.command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

public class CommandTest {
    private void testCommandUnmatchedInput_ReturnsEmpty(Command command) {
        assertEquals(Optional.empty(), command.matcher("thisshouldnotmatchever"));
    }

    private void testCommandCorrectInput_ReturnsCorrectOutput(Command command, String input, String[] argsToMatch) {
        Optional<Matcher> maybeMatcher = command.matcher(input);
        Matcher matcher = maybeMatcher.get();
        matcher.find();
        int count = matcher.groupCount();
        String[] args = new String[count];
        for (int i = 1; i <= count; i++) {
            args[i - 1] = matcher.group(i);
        }
        assertArrayEquals(argsToMatch, args);
    }

    @Test
    void testTodoCommandUnmatchedInput_ReturnsEmpty() {
        testCommandUnmatchedInput_ReturnsEmpty(Command.TODO);
    }

    @Test
    void testTodoCommandCorrectInput_ReturnsCorrectOutput() {
        testCommandCorrectInput_ReturnsCorrectOutput(Command.TODO, "todo test", new String[] {"test"});
    }

}
