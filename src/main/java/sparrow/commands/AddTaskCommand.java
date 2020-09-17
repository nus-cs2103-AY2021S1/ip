package sparrow.commands;

import sparrow.data.exceptions.FileErrorException;
import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class AddTaskCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Aye Aye Captain! I've added this task: \n\t%s";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand with the Task specified.
     * @param toAdd Task to add to the TaskList.
     */
    public AddTaskCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        try {
            tasks.addTask(toAdd);
            storage.saveTaskListToFile(tasks);
            storage.saveVocabListToFile(vocabList);
            return String.format(MESSAGE_SUCCESS, toAdd);
        } catch (FileErrorException fee) {
            return fee.getMessage();
        }
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof AddTaskCommand
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
