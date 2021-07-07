package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {
    public String execute(Ui ui, TaskList tl, Storage store) throws IOException, DukeException {
        String result = "I don't understand, did you type an invalid command?";
        return result;
    }
}
