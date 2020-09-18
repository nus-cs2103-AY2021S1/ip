package sparrow.commands;

import sparrow.data.exceptions.FileErrorException;
import sparrow.data.exceptions.SparrowException;
import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.data.trivia.Vocabulary;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Jolly riddance! I've deleted this task: \n\t%s";

    public static final String MESSAGE_DELETE_VOCAB_SUCCESS = "Jolly riddance! I've deleted this word: \n\t%s";

    public static final String HELP_MESSAGE = "Ye ought to specify 'tasks'/'vocab' after the 'delete' command.";

    public static final String REQUIREMENT_MESSAGE =
            "The delete command should include which data to delete and the item number";

    private final String data;

    /**
     * Creates a DeleteCommand.
     * @param data Type of data list to delete from (i.e. TaskList or VocabList).
     * @param indexToDelete Number corresponding to task/vocab to be deleted.
     */
    public DeleteCommand(String data, int indexToDelete) {
        super(indexToDelete);
        this.data = data;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        try {
            if (data.equals("tasks")) {
                Task deletedTask = tasks.deleteTask(targetIndex);
                storage.saveTaskListToFile(tasks);
                storage.saveVocabListToFile(vocabList);
                return String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask);
            } else if (data.equals("vocab")) {
                Vocabulary deletedVocab = vocabList.deleteVocab(targetIndex);
                storage.saveTaskListToFile(tasks);
                storage.saveVocabListToFile(vocabList);
                return String.format(MESSAGE_DELETE_VOCAB_SUCCESS, deletedVocab);
            } else {
                return HELP_MESSAGE;
            }
        } catch (IndexOutOfBoundsException e) {
            return String.format(SparrowException.STANDARD_EXCEPTION_MESSAGE,
                    "No task found at the index specified.");
        } catch (FileErrorException fee) {
            return fee.getMessage();
        }
    }
}
