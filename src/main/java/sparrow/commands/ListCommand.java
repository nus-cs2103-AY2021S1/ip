package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list: \n%s";

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        String tasksAsString = ui.taskListToString(tasks.getTasks());
        return String.format(MESSAGE_SUCCESS, tasksAsString);
    }
}
