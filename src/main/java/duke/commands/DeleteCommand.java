package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import static java.lang.Integer.parseInt;

public class DeleteCommand extends Command {
    
    private static final String INVALID_INPUT = "Invalid input for delete";
    private static final String DELETE_NOTIFICATION = "Noted. I've removed this duke.task:";
    
    public DeleteCommand(String[] inputArr) {
        super(inputArr);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTask(parseInt(inputArr[1]),ui,tasks);
    }
    
    private void deleteTask(int pos, Ui ui, TaskList tasks) {
        if (pos <= 0 || pos > tasks.size()) {
            ui.messageFormatter(() -> System.out.println(INVALID_INPUT));
        } else {
            Task task = tasks.get(pos - 1);
            tasks.remove(pos - 1);
            ui.messageFormatter(() -> {
                System.out.println(DELETE_NOTIFICATION);
                System.out.println(task);
                printNumTask(tasks);
            });
        }
    }
    

}
