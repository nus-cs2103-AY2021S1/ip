package duke.command;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

/**
 * Represents a command that adds a deadline task.
 */
public class DeadlineCommand extends UserCommand {

    /**
     * @param userInput user's input.
     */
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {

        String[] deadlineArr = userInput.split("/", 2);
        String by = deadlineArr[1].substring(deadlineArr[1].indexOf("by") + 3);
        String deadlineString = deadlineArr[0].substring(9);
        LocalDate localDeadlineDate = TimeFormatter.localDate(by);
        Deadline deadline = new Deadline(deadlineString, localDeadlineDate);
        taskList.addTask(deadline);
        return ui.printResponse("Got it. I've added this task:") + "\n"
                + ui.printResponse(deadline.toString()) + "\n"
                + ui.printListCount(taskList);
    }
}
