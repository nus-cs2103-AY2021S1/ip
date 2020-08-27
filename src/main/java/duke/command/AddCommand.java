package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.tasks.Task;

public class AddCommand extends Command {
    private Task task;
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String MESSAGE_ADD_ACKNOWLEDGEMENT = "*Gobble gobble* the following has been eated OwO:";
    public static final String MESSAGE_ADD_UPDATE = "Number of thing(s) in my belly has now become ";

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveTaskList(task.saveToString());
        ui.printMessage(String.format("%s\n%s\n%s%d!", MESSAGE_ADD_ACKNOWLEDGEMENT, task.toString(),
                MESSAGE_ADD_UPDATE, tasks.getTaskCount()));
    }


}
