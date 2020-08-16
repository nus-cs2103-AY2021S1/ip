package duke.Command;

import duke.Task;

import java.util.ArrayList;

public abstract class Command {

    public abstract String execute();

    public static ArrayList<Task> listArray = new ArrayList<>();

    public static String parse(String str) throws Exception {
        if (str.equals("bye")) {
            return new ExitCommand().execute();
        } else if (str.equals("list")) {
            return new ListCommand().execute();
        } else if (str.equals("blah")) {
            return new RandomCommand().execute();
        } else if (str.contains("done")) {
            int taskIndex = Integer.parseInt(str.split("\\s+")[1]);
            return new CompleteCommand(taskIndex).execute();
        } else {
            try {
                return new AddCommand(str).execute();
            } catch (Exception e) {
                throw new Exception();
            }
        }
    }
}
