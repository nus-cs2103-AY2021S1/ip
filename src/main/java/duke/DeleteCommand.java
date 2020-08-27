package main.java.duke;

import main.java.duke.tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_ACKNOWLEDGEMENT =
            "Nooo you can't take away what you've already given me...\n"
                    + "Okay fine. It's in my stomach tho... ASDFGUUVHHH!!\n"
                    + "The following has been removed: ";
    public static final String MESSAGE_DELETE_CONTINUED = "Now I'm feeling sick :( there's ";
    public static final String MESSAGE_DELETE_END = " thing(s) in my belly now...HUNGRY!";
    private int taskNum;

    DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        Task task = tasks.removeTask(taskNum);
        storage.editTaskList("", taskNum, true);
        ui.printMessage(String.format("%s\n%s\n%s%d%s", MESSAGE_DELETE_ACKNOWLEDGEMENT,
                task.toString(), MESSAGE_DELETE_CONTINUED, tasks.getTaskCount(), MESSAGE_DELETE_END));

    }
}
