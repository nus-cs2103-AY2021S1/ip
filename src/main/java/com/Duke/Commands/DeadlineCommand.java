package com.Duke.Commands;

import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class acts as a model for the execution of the DeadlineCommand
 */
public class DeadlineCommand extends Command {
    private final String[] splitList;
    private final TaskList taskList;

    public DeadlineCommand(String[] splitList, TaskList taskList) {
        this.splitList = splitList;
        this.taskList = taskList;
    }

    /**
     * Simulates Duke executing the Deadline Command
     * @return The response to the command "deadline"
     */
    public String execute() {
        try {
            String[] splitList2 = splitList[1].split("/by ", 2);
            Deadline deadline = new Deadline(splitList2[0], LocalDate.parse(splitList2[1]), false);
            return UI.deadlineCalled(taskList, deadline);
        } catch (DateTimeParseException e) {
            return UI.printError("     \u2639 OOPS!!! The deadline is not of the proper format, make sure you enter it as YYYY-MM-dd");
        } catch (Exception e) {
            return UI.printError("     \u2639 OOPS!!! The description is empty or you have not entered a proper deadline.");
        }
    }
}
