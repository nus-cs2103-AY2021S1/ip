package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyToDoException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends UserCommand {
    public ToDoCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        if (userInput.trim().length() <= 4) {
            throw new EmptyToDoException();
        } else {
            String todoString = userInput.substring(5);
            ToDo todo = new ToDo(todoString);
            taskList.addTask(todo);
            ui.printResponse("Got it. I've added this task:");
            ui.printResponse(todo.toString());
            ui.printListCount(taskList);
        }
    }
}
