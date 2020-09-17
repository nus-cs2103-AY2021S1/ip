package sparrow.commands;

import sparrow.data.exceptions.IncorrectCommandException;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String TASK_MESSAGE_SUCCESS = "Here are the tasks in your list: \n%s";
    public static final String VOCAB_MESSAGE_SUCCESS = "Here are the words in your list: \n%s";
    public static final String HELP_MESSAGE = "Ye ought to specify either 'tasks' or 'vocab' after the 'list' command.";

    private final String data;

    public ListCommand(String data) {
        this.data = data;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        if (data.equals("tasks")) {
            String tasksAsString = ui.taskListToString(tasks.getTasks());
            return String.format(TASK_MESSAGE_SUCCESS, tasksAsString);
        } else if (data.equals("vocab")) {
            String vocabsAsString = ui.vocabListToString(vocabList.getVocabList());
            return String.format(VOCAB_MESSAGE_SUCCESS, vocabsAsString);
        } else {
            return HELP_MESSAGE;
        }
    }
}
