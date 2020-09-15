package duke.command;

import java.time.LocalDate;

import duke.duplicatechecker.DuplicateDetector;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

/**
 * Represents a command that adds a deadline task.
 */
public class DeadlineCommand extends UserCommand {

    private static String eventType = "Deadline";
    private static final String DEFAULT_MESSAGE = "Got it. I've added this task:";

    /**
     * @param userInput User's input.
     */
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Adds an Deadline task while checking for duplicates in TaskList.
     *
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] deadlineArr = userInput.split("/", 2);
        String by = deadlineArr[1].substring(deadlineArr[1].indexOf("by") + 3);
        String deadlineString = deadlineArr[0].substring(9);
        LocalDate localDeadlineDate = TimeFormatter.localDate(by);
        DuplicateDetector duplicateDetector = new DuplicateDetector(deadlineString, localDeadlineDate, taskList,
                eventType);
        if (duplicateDetector.checkForDuplicates()) {
            throw new DuplicateException();
        } else {
            Deadline deadline = new Deadline(deadlineString, localDeadlineDate);
            taskList.addTask(deadline);
            return ui.printResponse(DEFAULT_MESSAGE) + "\n"
                    + ui.printResponse(deadline.toString()) + "\n"
                    + ui.printListCount(taskList);
        }
    }
}
