import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import command.Command;
import exceptions.DukeException;
import task.Task;

public class DukeTest {
    @Test
    public void toDoTest() throws DukeException {
        Duke duke = new Duke("duke.txt");
        Parser parser = new Parser(duke);
        Command command = parser.parse("todo Project", duke.taskList);
        command.execute();

        Task taskAdded = duke.taskList.tasks.get(0);
        assertEquals(taskAdded.toString(), "[T][✗] Project");
    }

    @Test
    public void deadlineTest() throws DukeException {
        Duke duke = new Duke("duke.txt");
        Parser parser = new Parser(duke);
        Command command = parser.parse("deadline Project Research /by 2020-09-01", duke.taskList);
        command.execute();

        Task taskAdded = duke.taskList.tasks.get(0);
        assertEquals(taskAdded.toString(), "[D][✗] Project Research (by: Sep 01 2020)");

        command = parser.parse("done 1", duke.taskList);
        command.execute();

        Task taskDone = duke.taskList.tasks.get(0);
        assertEquals(taskDone.toString(), "[D][✓] Project Research (by: Sep 01 2020)");

    }

    @Test
    public void eventTest() throws DukeException {
        Duke duke = new Duke("duke.txt");
        Parser parser = new Parser(duke);
        Command command1 = parser.parse("event Project Meeting /at Zoom", duke.taskList);
        command1.execute();

        Task taskAdded = duke.taskList.tasks.get(0);
        assertEquals(taskAdded.toString(), "[E][✗] Project Meeting (at: Zoom)");
    }
}
