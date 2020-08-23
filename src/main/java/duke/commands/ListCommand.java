package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that simulates the add command of the user
 */

public class ListCommand extends Command {
    
    private static final String EMPTY_MSG = "Your list is empty!!!";
    private static final String SHOW_TASK = "Here are the tasks in your list:";


    /**
     * Creates an ListCommand object
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public ListCommand(String[] inputArr) {
        super(inputArr);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        showListTasks(tasks, ui);
    }

    /**
     * Printing out all the task in the list
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     */
    private void showListTasks(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            ui.messageFormatter(() -> System.out.println(EMPTY_MSG));
        } else {
            ui.messageFormatter(() -> {
                System.out.println(SHOW_TASK);
                for(int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            });
        }
    }
}
