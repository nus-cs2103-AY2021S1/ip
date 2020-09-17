package duke.command;

import java.time.LocalDate;

import duke.duplicatechecker.DuplicateChecker;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

/**
 * Represents a command that creates an Event task.
 */
public class EventCommand extends UserCommand {

    private static String eventType = "Event";
    private static final String DEFAULT_MESSAGE = "Got it. I've added this task:";

    /**
     * @param userInput User's input.
     */
    public EventCommand(String userInput) {
        super(userInput);
    }

    /**
     * Adds an Event task while checking for duplicates in TaskList.
     *
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] eventArr = userInput.split("/", 2);
        String at = eventArr[1].substring(eventArr[1].indexOf("at") + 3);
        String eventString = eventArr[0].substring(6);
        LocalDate localEventDate = TimeFormatter.localDate(at);
        DuplicateChecker duplicateChecker = new DuplicateChecker(eventString, localEventDate, taskList,
                Task.TaskType.Event);
        if (duplicateChecker.checkForDuplicates()) {
            throw new DuplicateException();
        } else {
            Event event = new Event(eventString, localEventDate);
            taskList.addTask(event);
            return ui.printResponse(DEFAULT_MESSAGE) + "\n"
                    + ui.printResponse(event.toString()) + "\n"
                    + ui.printListCount(taskList);
        }

    }
}
