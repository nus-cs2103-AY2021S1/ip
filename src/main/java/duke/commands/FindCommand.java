package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that simulates the find command of the user.
 */
public class FindCommand extends Command {
    
    private static final String FIND_SUCCESS = "Here are the matching tasks in your list:";
    
    public FindCommand(String[] inputArr) {
        super(inputArr);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        findTasks(tasks, inputArr[1], ui);
    }

    /**
     * Find and displays the list of task based on the user's input
     * 
     * @param tasks Object that contains the list of tasks.
     * @param keyword The task that the user is looking for.
     */
    public void findTasks(TaskList tasks, String keyword, Ui ui) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword.toLowerCase())) {
                s.append(task.toString()).append("\n");
            }
        }
        // this means no available search
        if (s.toString().equals("")) {
            ui.messageFormatter(() -> System.out.printf("No available task matches %s\n", keyword));
        } else {
            ui.messageFormatter(() -> {
                System.out.println(FIND_SUCCESS);
                System.out.println(s.toString());
            });
        }
    }
}
