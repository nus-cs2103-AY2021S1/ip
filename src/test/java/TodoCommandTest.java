import duke.TaskList;
import duke.Ui;
import duke.command.TodoCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void addTodoTest() {

        TodoCommand todoCommand = new TodoCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        String input = "todo Laundry";
        try {
            todoCommand.execute(taskList, ui, input);
        } catch (InvalidTaskIdException e) {
            e.printStackTrace();
        } catch (EmptyDescriptionException e) {
            e.printStackTrace();
        }
        Task lastTaskAdded = taskList.getTasks().get(taskList.taskSize() - 1);
        assertEquals(lastTaskAdded.toString(), "[✗][T] Laundry");
    }
}
