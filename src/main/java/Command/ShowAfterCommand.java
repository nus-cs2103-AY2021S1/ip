package main.java.Command;


import main.java.Exception.DukeDateTimeParserException;
import main.java.Parser.Parser;
import main.java.Storage.Storage;
import main.java.Task.DeadlineTask;
import main.java.Task.EventTask;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.time.LocalDate;

/**
 * ShowAfterCommand would execute the program when user specify
 * "show after" as the command. This would show all the task that exist
 * after the specified date that was passed in the user command.
 */
public class ShowAfterCommand extends Command {

    private String command;

    /**
     * Constructs a ShowAfterCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public ShowAfterCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Shows user all the task that exist after the specified date.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws DukeDateTimeParserException This exception would be thrown
     * when the user failed to specify the date in the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeDateTimeParserException {
        LocalDate localDate = Parser.findDateParser(this.command);
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for(Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getDateTime().toLocalDate().isAfter(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + deadlineTask + "\n"));
                    i++;
                }

            } else if(task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getDateTime().toLocalDate().isAfter(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + eventTask + "\n"));
                    i++;
                }
            }

        }

        ui.getMessageTemplate(sb.toString());
    }
}
