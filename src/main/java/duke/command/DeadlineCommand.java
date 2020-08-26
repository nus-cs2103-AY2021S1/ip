package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

import java.time.LocalDate;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {

        String[] deadlineArr = userInput.split("/", 2);
        String by = deadlineArr[1].substring(deadlineArr[1].indexOf("by") + 3);
        String deadlineString = deadlineArr[0].substring(9);
        LocalDate localDeadlineDate = TimeFormatter.localDate(by);
        Deadline deadline = new Deadline(deadlineString, localDeadlineDate);
        taskList.addTask(deadline);
        ui.printResponse("Got it. I've added this task:");
        ui.printResponse(deadline.toString());
        ui.printListCount(taskList);
    }
}
