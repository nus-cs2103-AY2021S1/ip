package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.tasks.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_DONE_ACKNOWLEDGEMENT = "Nice I've digested the following: ";
    public static final String MESSAGE_DONE_END = "Now I'm hungry again! FEED ME MORE :3";
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        Task task = tasks.doneTask(taskNum);
        storage.editTaskList(task.saveToString(), taskNum, false);
        ui.printMessage(String.format("%s\n%s\n%s", MESSAGE_DONE_ACKNOWLEDGEMENT,
                task.toString(), MESSAGE_DONE_END));

    }
}
