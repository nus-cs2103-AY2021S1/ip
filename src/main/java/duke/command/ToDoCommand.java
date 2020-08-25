package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyToDoException;
import duke.storage.ArrayListToTextConverter;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

import java.util.List;

public class ToDoCommand extends UserCommand{
    public ToDoCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTaskList();
        if (userInput.trim().length() <= 4) {
            throw new EmptyToDoException();
        } else {
            String todoString = userInput.substring(5);
            ToDo todo = new ToDo(todoString);
            ls.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println(todo.toString());
            System.out.format("Now you have %d tasks in the list\n", ls.size());
//            ArrayListToTextConverter.convertArrayListToText(ls);
        }
    }
}
