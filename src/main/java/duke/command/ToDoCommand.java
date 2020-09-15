package duke.command;

import duke.duplicatechecker.DuplicateChecker;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;
import duke.exceptions.EmptyToDoException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends UserCommand {

    private static String eventType = "ToDo";
    private static final String DEFAULT_MESSAGE = "Got it. I've added this task:";

    /**
     * @param userInput User's input.
     */
    public ToDoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Adds an ToDo task while checking for duplicates in TaskList.
     *
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        if (userInput.trim().length() <= 4) {
            throw new EmptyToDoException();
        } else {
            String todoString = userInput.substring(5);
            DuplicateChecker duplicateChecker = new DuplicateChecker(todoString, taskList
                    , Task.TaskType.ToDo);
            if (duplicateChecker.checkForDuplicates()) {
                throw new DuplicateException();
            } else {
                ToDo todo = new ToDo(todoString);
                taskList.addTask(todo);
                return ui.printResponse(DEFAULT_MESSAGE) + "\n"
                        + ui.printResponse(todo.toString()) + "\n"
                        + ui.printListCount(taskList);
            }
        }
    }
}
