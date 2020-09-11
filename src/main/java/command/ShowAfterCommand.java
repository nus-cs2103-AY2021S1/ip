package command;


import java.time.LocalDate;

import exception.DukeDateTimeParserException;
import parser.Parser;
import storage.Storage;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import ui.Ui;


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


    private String printTask(TaskList tasks, LocalDate localDate) {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                boolean isAfterSpecifiedDate = deadlineTask.getDateTime().toLocalDate().isAfter(localDate);
                if (isAfterSpecifiedDate) {
                    sb.append(index).append(". ").append(deadlineTask).append("\n");
                    index++;
                }

            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                boolean isAfterSpecifiedDate = eventTask.getDateTime().toLocalDate().isAfter(localDate);
                if (isAfterSpecifiedDate) {
                    sb.append(index).append(". ").append(eventTask).append("\n");
                    index++;
                }
            }
        }
        return sb.toString();
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeDateTimeParserException {
        LocalDate localDate = Parser.findDateParser(this.command);
        int index = 1;

        String printTask = printTask(tasks, localDate);
        return ui.getMessageTemplate(printTask);
    }
}
