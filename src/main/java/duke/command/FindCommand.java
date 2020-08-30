package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.FindException;
import duke.task.Task;

public class FindCommand extends Command {

    private String[] keywords;

    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processFind(this.keywords, taskList, ui, storage);
        } catch (FindException find) {
            System.out.println(find.getMessage());
        }
    }

    public void processFind(String[] toFind, TaskList taskList, Ui ui, Storage storage) throws FindException {
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

    private void findTask(String[] toFind, TaskList taskList) {
        //try {
        List<Task> tasks = taskList.getTasks();
        List<Task> tasksFound = new ArrayList<>();
        int num = 1;
        boolean containsKeyword;

        System.out.println("Here are the matching task(s) in your list : ");

        for (Task task : tasks) {
            containsKeyword = false;

            for (int i = 0; i < toFind.length; i++) {

                if (task.getDescription().contains(toFind[i])) {
                    containsKeyword = true;
                    break;
                }
            }

            if (containsKeyword) {
                tasksFound.add(task);
                System.out.println(num + ". " + task.toString());
            }
        }

        if (tasksFound.size() == 0) {
            System.out.println("Nothing match this keyword. \n"
                + "Please try again with another keyword.");
        }

    }
}
