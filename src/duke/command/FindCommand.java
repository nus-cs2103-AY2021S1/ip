package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.FindException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processFind(this.task, taskList, ui, storage);
        } catch (FindException find) {
            System.out.println(find.getMessage());
        }
    }

    public void processFind(String toFind, TaskList taskList, Ui ui, Storage storage) throws FindException {
        findTask(toFind, taskList);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof FindCommand) {
            FindCommand findCommand = (FindCommand) other;
            return this.task.equals(findCommand.getTask());
        }
        return false;
    }

    private List<Task> findTask(String toFind, TaskList taskList) {
        //try {
        List<Task> tasks = taskList.getTasks();
        List<Task> tasksFound = new ArrayList<>();
        int num = 1;


        System.out.println("Here are the matching task(s) in your list : ");
        for (Task task : tasks) {
            if (task.getDescription().contains(toFind)) {
                tasksFound.add(task);
                System.out.println(num + ". " + task.toString());
            }
        }

        if (tasksFound.size() == 0) {
            System.out.println("Nothing match this keyword. \n"
                    + "Please try again with another keyword.");
        }

        return tasksFound;
    }
}
