package duke.command;

import java.time.LocalDate;

import duke.duplicatechecker.DuplicateDetector;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

/**
 * Represents a command that creates an event task.
 */
public class EventCommand extends UserCommand {

    private static String eventType = "Event";
    private static final String DEFAULT_MESSAGE = "Got it. I've added this task:";

    /**
     * @param userInput user's input.
     */
    public EventCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] eventArr = userInput.split("/", 2);
        String at = eventArr[1].substring(eventArr[1].indexOf("at") + 3);
        String eventString = eventArr[0].substring(6);
        LocalDate localEventDate = TimeFormatter.localDate(at);
        DuplicateDetector duplicateDetector = new DuplicateDetector(eventString, localEventDate, taskList, eventType);
        if (duplicateDetector.checkForDuplicates()) {
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
