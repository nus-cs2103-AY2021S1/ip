package duke.commands;

import static duke.util.FormatChecker.checkListFormat;
import static duke.util.Keyword.KEYWORD_LIST_EMPTY_MSG;
import static duke.util.Keyword.KEYWORD_LIST_SHOW_TASK;

import java.util.stream.IntStream;

import duke.exception.InvalidFormatListException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the add command of the user.
 */
public class ListCommand extends Command {

    /**
     * Initialize an ListCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     * Index 0 contains the type of command while Index 1 contains the message of the command.
     */
    public ListCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatListException {
        checkListFormat(inputArr);
        return showListTasks(tasks, ui);
    }

    /**
     * Print out all the task in the list.
     *
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @return A string representing the information of the list of tasks.
     */
    private String showListTasks(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            return ui.messageFormatter(KEYWORD_LIST_EMPTY_MSG);
        } else {
            assert tasks.size() > 0 : "Invalid task list, giving a negative size";
            StringBuffer finalMessage = new StringBuffer(KEYWORD_LIST_SHOW_TASK).append("\n");
            IntStream.range(1, tasks.size() + 1).forEach(num -> finalMessage.append(num).append(". ")
                    .append(tasks.get(num - 1)).append("\n"));
            return ui.messageFormatter(finalMessage.toString());
        }
    }
}
