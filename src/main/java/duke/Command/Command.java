package duke.Command;

import duke.DukeException;
import duke.Task;

import java.util.ArrayList;

public abstract class Command {

    public abstract String execute() throws DukeException;

    public static ArrayList<Task> listArray = new ArrayList<>();

    public static String parse(String str) throws DukeException {
        if (str.equals("bye")) {
            return new ExitCommand().execute();
        } else if (str.equals("list")) {
            return new ListCommand().execute();
        } else if (str.contains("done")) {
            int taskIndex = Integer.parseInt(str.split("\\s+")[1]);
            return new CompleteCommand(taskIndex).execute();
        } else if (str.contains("delete")) {
            int taskIndex = Integer.parseInt(str.split("\\s+")[1]);
            return new DeleteCommand(taskIndex).execute();
        } else {
            return new AddCommand(str).execute();
        }
    }
}
