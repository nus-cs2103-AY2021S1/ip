package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import exception.DescriptionException;
import exception.DukeDateTimeParserException;
import parser.Parser;
import storage.Storage;
import task.EventTask;
import task.TaskList;
import ui.Ui;


/**
 * EventCommand would execute the program when user specify
 * "event" as the command. This would create a Task of type
 * EventTask, show the created task to user and update the
 * external file.
 */
public class EventCommand extends Command {

    private String command;


    /**
     * Constructs a EventCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public EventCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is :
     * 1. Creates a Task of type EventTask.
     * 2. Adds the newly created to the list of task via TaskList.
     * 3. Notifies the new created task to user via Ui object.
     * 4. Updates the external file via Storage object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException This exception would be thrown when
     * the system failed to detect the external file.
     * @throws DescriptionException This exception would be thrown
     * when user failed to specify the task details in the command.
     * @throws DukeDateTimeParserException This exception would be thrown when
     * user failed to enter the specified format of date-time in the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException,
            DukeDateTimeParserException {
        try {
            Map<String, String> taskDetails = Parser.findDescriptionParser(this.command);

            assert !taskDetails.isEmpty() : "TaskDetails is empty";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String eventTaskDescription = taskDetails.get("taskDescription");
            LocalDateTime eventTaskDateTime = LocalDateTime.parse(taskDetails.get("taskTime"), formatter);

            EventTask eventTask = new EventTask(eventTaskDescription, eventTaskDateTime);

            tasks.add(eventTask);

            storage.updateFile(tasks);

            return ui.getTaskMessage(eventTask, tasks.size());

        } catch (DescriptionException e) {
            throw new DescriptionException();
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
