package duke.commands;

import duke.exceptions.DeadlineException;
import duke.exceptions.DukeException;
import duke.exceptions.EventException;
import duke.exceptions.IncompleteCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Adds a new task into the taskList
 */
public class AddCommand extends Command {

    public AddCommand(String description){
        super(description);
    }

    /**
     * Adds a new task to the taskList
     * @param taskList
     * @param ui
     * @param storage
     * @return String based on the add command
     * @throws DukeException
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Task("Do Something");
        String[] inputList = description.trim().split(" ", 2);

        //asserts the command to have the proper format
        assert(inputList.length > 2);
        if (inputList.length != 2) {
            throw new IncompleteCommandException();
        }

        String commandLine = inputList[0].trim();

        if (Parser.isToDo(commandLine)){
            newTask = new ToDo(inputList[1].trim());

        }

        if (Parser.isDeadline(commandLine)){
            String[] task_deadline = inputList[1].trim().split("/by", 2);
            if (task_deadline.length != 2){
                throw new DeadlineException();
            }
            // check for date time format
            try {
                newTask = new Deadline(task_deadline[0].trim(), task_deadline[1].trim());

            } catch (Exception e){
                throw new DeadlineException();
            }
        }

        if (Parser.isEvent(commandLine)){
            String[] task_event = inputList[1].trim().split("/at", 2);
            if (task_event.length != 2){
                throw new EventException();
            }
            // check for date time format
            try {
                newTask = new Event(task_event[0].trim(), task_event[1].trim());

            } catch (Exception e){
                throw new EventException();
            }
        }

        taskList.addTask(newTask);
        return ui.newTaskItem(newTask);
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
