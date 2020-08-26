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
 * ShowBeforeCommand would execute the program when user specify
 * "show before" as the command. This would show all the task that exist
 * before the specified date that was passed in the user command.
 */
public class ShowBeforeCommand extends Command {

    private String  command;

    /**
     * Constructs a ShowBeforeCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public ShowBeforeCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Shows user all the task that exist before the specified date.
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
                if (deadlineTask.getDateTime().toLocalDate().isBefore(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + deadlineTask + "\n"));
                    i++;
                }
            } else if(task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getDateTime().toLocalDate().isBefore(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + eventTask + "\n"));
                    i++;
                }
            }
        }

        ui.getMessageTemplate(sb.toString());
    }
}
