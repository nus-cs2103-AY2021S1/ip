package main.java.command;


import main.java.exception.DescriptionException;
import main.java.exception.DukeDateTimeParserException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.DeadlineTask;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * DeadlineCommand class would execute the program when user specify
 * "deadline" in the command. It would parse the command and show
 * message to user, as well as updating the external file.
 */
public class DeadlineCommand extends Command {

    private String command;

    /**
     * Constructs a DeadlineCommand with the given
     * user command
     * @param command String user command
     */
    public DeadlineCommand(String command) {
        super();
        this.command =command;
    }

    /**
     * Executes parsed user command. The result is :
     * 1. A new Task of type DeadlineTask.
     * 2. Append the new task to the TaskList.
     * 3. Notify the user about the newly created task via Ui object.
     * 4. Updates the external file via Storage object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException This exception is thrown when the system failed to detect the external file.
     * @throws DescriptionException This is exception is thrown whenever Parsed failed to detect
     * valid description passed in the command.
     * @throws DukeDateTimeParserException This exception is thrown whenever when Parser to detect
     * valid date-time details on the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException, DukeDateTimeParserException {
          try {
            Map<String, String> taskDetails = Parser.findDescriptionParser(this.command);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DeadlineTask deadlineTask = new DeadlineTask(taskDetails.get("taskDescription"),
                    LocalDateTime.parse(taskDetails.get("taskTime"), formatter));

            tasks.add(deadlineTask);

            ui.getTaskMessage(deadlineTask, tasks.size());

            storage.updateFile(tasks);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParserException();
        }
    }
}
