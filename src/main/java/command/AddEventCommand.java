package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EventException;
import exception.WrongFormatException;



/**
 * Represents a command to add event to Duke.TaskList.
 */
public class AddEventCommand extends Command {

    public AddEventCommand(String[] command) {
        super(command);
    }

    /**
     * Adds Duke.Duke.Event to the Duke.TaskList and save it to storage.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException if they use the wrong format or no description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] str = commands[1].split("/at ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse(str[1], dateFormatter);
            Task temp = new Event(str[0], date);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EventException();
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
        } else if (o instanceof AddEventCommand) {
            AddEventCommand cur = (AddEventCommand) o;
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
