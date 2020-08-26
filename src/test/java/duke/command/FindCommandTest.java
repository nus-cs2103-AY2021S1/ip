package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.TextUi;
import duke.ui.Ui;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    
    public static TaskList testTaskList = new TaskList();
    public static Ui ui = new TextUi();
    
    public static void initTest() throws DukeException {
        String[] parsedInput1 = {"T", "kill me now"};
        String[] parsedInput2 = {"T", "i want me ice cream"};
        String[] parsedIntput3 = {"T", "i want more"};
        String[] parsedInput4 = {"D", "i want", "19/02/2020 1400"};
        String commandTag = "T";
        testTaskList.addEntry(parsedInput1, commandTag);
        testTaskList.addEntry(parsedInput2, commandTag);
        testTaskList.addEntry(parsedIntput3, commandTag);
        testTaskList.addEntry(parsedInput4, "D");
        testTaskList.completeTask(2);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"find want", "find i", "find me", "find xxx"})
    public void findSuccessfully_queryTaskList_returnsTrue(String s) throws DukeException {
        initTest();
        FindCommand cmd = new FindCommand(s);
        cmd.execute(testTaskList, ui);
        testTaskList = new TaskList();
        
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"i", "world i", "world ", "hi"})
    public void testHelperContains_matchExactSearchTerm_returnsTrue(String query) {
        String text = "hello world i am ritesh hohohehe hi";
        FindCommand command = new FindCommand("hello");
        assertEquals((command.containsExactWord(text, query)),
                     true);
    }
}
