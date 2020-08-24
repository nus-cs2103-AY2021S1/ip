package duke.command;

import java.util.Optional;
import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CommandTest {
    private void testCommandRejectsBadInputs(Command command) {
        assertEquals(Optional.empty(), command.matcher("thisshouldnotmatchever"));
    }
    private void testCommandMatchesCorrectly(Command command, String input, String[] argsToMatch) {
        Optional<Matcher> maybeMatcher = command.matcher(input);
        Matcher matcher = maybeMatcher.get();
        matcher.find();
        int count = matcher.groupCount();
        String[] args = new String[count];
        for (int i=1;i<=count;i++) {
            args[i-1] = matcher.group(i);
        }
        assertArrayEquals(argsToMatch, args);
    }

    @Test
    void testTodoCommandRejectsBadInputs() {
        testCommandRejectsBadInputs(Command.TODO);
    }

    @Test
    void testTodoCommandMatchesCorrectly() {
        testCommandMatchesCorrectly(Command.TODO, "todo test", new String[] {"test"});
    }

}
