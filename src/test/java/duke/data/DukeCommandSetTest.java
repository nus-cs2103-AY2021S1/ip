package duke.data;

import duke.exception.UnknownCommandException;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeCommandSetTest {

    private final DukeCommandSet commandSet = new DukeCommandSet();

    @Test
    public void getCommand_validCommandName_success() {
        String commandName01 = "list";
        String commandName02 = "todo";
        String commandName03 = "done";

        try {
            commandSet.getCommand(commandName01);
            commandSet.getCommand(commandName02);
            commandSet.getCommand(commandName03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void getCommand_invalidCommandName_exceptionThrown() {
        String invalidCommandName = "hahaha";

        Exception exception = assertThrows(UnknownCommandException.class,
                () -> commandSet.getCommand(invalidCommandName));

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }
}
