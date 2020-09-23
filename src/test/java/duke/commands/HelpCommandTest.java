package duke.commands;

import static duke.util.Keyword.KEYWORD_HELP_BYE;
import static duke.util.Keyword.KEYWORD_HELP_DEADLINE;
import static duke.util.Keyword.KEYWORD_HELP_DELETE;
import static duke.util.Keyword.KEYWORD_HELP_DISPLAY_MESSAGE;
import static duke.util.Keyword.KEYWORD_HELP_DONE;
import static duke.util.Keyword.KEYWORD_HELP_EVENT;
import static duke.util.Keyword.KEYWORD_HELP_FIND;
import static duke.util.Keyword.KEYWORD_HELP_LIST;
import static duke.util.Keyword.KEYWORD_HELP_REMIND;
import static duke.util.Keyword.KEYWORD_HELP_TODO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class HelpCommandTest {

    @Test
    public void getListOfCommands_userRequestingCommandsAvailable_success() {
        try {
            Method method = HelpCommand.class.getDeclaredMethod("getListOfCommands");
            method.setAccessible(true);
            HelpCommand command = new HelpCommand(new String[]{"help"});
            String dukeReply = (String) method.invoke(command);
            String expected = KEYWORD_HELP_DISPLAY_MESSAGE + KEYWORD_HELP_LIST + KEYWORD_HELP_BYE
                    + KEYWORD_HELP_TODO + KEYWORD_HELP_DELETE + KEYWORD_HELP_DONE + KEYWORD_HELP_FIND
                    + KEYWORD_HELP_REMIND + KEYWORD_HELP_DEADLINE + KEYWORD_HELP_EVENT;
            assertEquals(dukeReply, expected);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
