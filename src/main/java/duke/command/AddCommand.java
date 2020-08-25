package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.task.TaskParser;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    protected static final String ADDED_MESSAGE = "Got it. I've added this task: ";

    private Task task;

    private AddCommand(Task task) {
        this.task = task;
    }

    public static AddCommand parse(String taskCommand) {
        return new AddCommand(TaskParser.parse(taskCommand));
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        ui.outputMessage(createAddMessage(task, taskList));
        storage.updateFile(taskList);
    }

    private String createAddMessage(Task task, TaskList taskList) {
        return ADDED_MESSAGE + '\n'
                + "   " + task + '\n'
                + taskList.createTaskNumberCountMessage();
    }

}
