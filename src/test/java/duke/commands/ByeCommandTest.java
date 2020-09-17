package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class ByeCommandTest {

    @Test
    public void checkValidByeCommandTest() {
        try {
            Method method = ByeCommand.class.getDeclaredMethod("endProgram", TaskList.class, Ui.class,
                    Storage.class);
            method.setAccessible(true);
            Ui ui = new Ui();
            TaskList tasks = new TaskList();
            Storage storage = new Storage();
            ByeCommand command = new ByeCommand(new String[]{"bye"});
            String dukeReply = (String) method.invoke(command, tasks, ui, storage);
            String expected = "Bye ^.^, Hope to see you again soon!!!" + "\n";
            assertEquals(dukeReply, expected);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
