package duke.commands;

import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatEventException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that simulates the add command of the user
 * Mainly: event, deadline, todo.
 */

public class AddCommand extends Command {
    
    private static final String ADDED_NOTIFICATION = "Got it. I've added this duke.task:";

    
    /**
     * Creates an AddCommand object
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public AddCommand(String[] inputArr) {
        super(inputArr);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException {
        addTask(inputArr[0], inputArr[1], ui, tasks);
    }

    /**
     * Adds the given task into the task list. Expected format for the date in message is YYYY-MM-DD HHMM or 
     * YYYY-MM-DD HHMM. If type is of todo, date can be omitted.
     * @param type Type of task that is being entered (todo, event, deadline).
     * @param message Details of the task that the user entered.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     * @throws InvalidFormatDeadlineException Throws an exception when the format of 'message' is wrong.
     * @throws InvalidFormatEventException Throws an exception when the format of 'message' is wrong.
     * @throws InvalidFormatDateException Throws an exception when the format of 'message' is wrong.
     */
    private void addTask(String type, String message, Ui ui, TaskList tasks)
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException {
        Task task;
        String[] dateTime;
        if (Parser.isTODO(type)) {
            task = new ToDo(message);
        } else if (Parser.isDeadline(type)) {
            dateTime = message.split(" /by ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatDeadlineException();
            }
            task = new Deadline(dateTime[0], Parser.formatDateTime(dateTime[1]));
        } else if (Parser.isEvent(type)){
            dateTime = message.split(" /at ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatEventException();
            }
            task = new Event(dateTime[0], Parser.formatDateTime(dateTime[1]));
        } else {
            return;
        }
        tasks.add(task);
        ui.messageFormatter(() -> {
            System.out.println(ADDED_NOTIFICATION);
            System.out.println(task);
            printNumTask(tasks);
        });
    }
}
