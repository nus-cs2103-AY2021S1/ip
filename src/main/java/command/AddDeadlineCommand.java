package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.Deadline;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import exception.DeadlineException;
import exception.DukeException;
import exception.WrongFormatException;



/**
 * Represents a command to add deadline to the tasklist.
 */
public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String[] command) {
        super(command);
    }

    /**
     * Adds Duke.Duke.Deadline to the Duke.TaskList and save it to storage.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException if they use the wrong format or no description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] str = commands[1].split("/by ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse(str[1], dateFormatter);
            Task temp = new Deadline(str[0], date);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineException();
        } catch (DateTimeParseException e) {
            throw new WrongFormatException();
        }
    }

    /**
     * Indicates not to exit the loop.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddDeadlineCommand) {
            AddDeadlineCommand cur = (AddDeadlineCommand) o;
            if (Arrays.equals(this.commands, cur.commands)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
