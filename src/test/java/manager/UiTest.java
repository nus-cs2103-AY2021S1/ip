package manager;

import main.java.manager.Commands;
import main.java.manager.Ui;
import main.java.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testConvertTask() {
        Ui converter = new Ui();
        Task todo = converter.convertTask(Commands.TODO, "todo buy a hat");
        Task deadline = converter.convertTask(Commands.DEADLINE,
                "deadline undo cronenberg world /by 2020-12-07");
        Task event = converter.convertTask(Commands.EVENT,
                "event destroy the galactic government /at 2020-11-08 1430");
        assertEquals("[T][✘] buy a hat", todo.toString());
        assertEquals("[D][✘] undo cronenberg world (by: Dec 7 2020)", deadline.toString());
        assertEquals(
                "[E][✘] destroy the galactic government " +
                        "(at: Nov 8 2020, 02:30 PM)", event.toString());
    }

    @Test
    public void testConvertAction() {
        Ui converter = new Ui();
        Task todo = converter.convertTask(Commands.TODO, "todo grab a shovel");
        Task deadline = converter.convertTask(Commands.DEADLINE,
                "deadline clean up meeseeks' mess /by 2024-08-24 2000");
        Task event = converter.convertTask(Commands.EVENT,
                "event invent snake time travel /at 2051-11-07");
        converter.passTask(todo);
        converter.passTask(deadline);
        converter.passTask(event);
        converter.convertAction(Commands.LIST, 0, "");
        converter.convertAction(Commands.DONE, 2, "");
        converter.convertAction(Commands.DELETE, 1, "");
        converter.convertAction(Commands.LIST, 0, "");
        assertEquals(2, converter.totalTasks());
    }
}
