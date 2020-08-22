package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;
import main.java.Deadline;
import main.java.Task;


import Exception.DukeException;
import Exception.DeadlineException;
import Exception.WrongFormatException;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] str = command[1].split("/by ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse(str[1], dateFormatter);
            Task temp = new Deadline(str[0], date);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineException();
        } catch (DateTimeParseException e) {
            throw new WrongFormatException();
        }
    }

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
            if (Arrays.equals(this.command, cur.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
