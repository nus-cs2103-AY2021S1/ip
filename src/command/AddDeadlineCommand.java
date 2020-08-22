package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exception.DukeException;
import exception.EmptyDeadlineException;

import task.Deadline;
import task.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String argument = splitCommand[1];
            String description = argument.split(" /by ", 2)[0];
            String deadline = argument.split(" /by ", 2)[1];
            Task toAdd = new Deadline(description, LocalDateTime.parse(deadline,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));

            tasks.add(toAdd);
            ui.sayAddedTask(toAdd, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyDeadlineException();
        } catch (DateTimeParseException e) {
            ui.say("The date and time format must be: [dd/MM/yyyy HHmm]\nFor example, 02/12/2019 1800");
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddDeadlineCommand) {
            AddDeadlineCommand other = (AddDeadlineCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
