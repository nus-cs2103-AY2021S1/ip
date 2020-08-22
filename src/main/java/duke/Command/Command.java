package duke.Command;

import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.TaskList;

import duke.Ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public static ArrayList<Task> listArray = new ArrayList<>();

>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
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
=======
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
>>>>>>> master
    }
}
