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


public class AddCommand extends Command {
    
    private static final String ADDED_NOTIFICATION = "Got it. I've added this duke.task:";
    
    public AddCommand(String[] inputArr) {
        super(inputArr);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException {
        addTask(inputArr[0], inputArr[1], ui, tasks);
    }

    // adding the duke.task into the list
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
