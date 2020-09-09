package luke;

import luke.exception.LukeException;
import luke.task.Task;

public class Ui {

    public Ui() {

    }

    public String showWelcome() {
        return "Hey there! I'm Luke.\n"
                + "Please tell me what to do.\n";
    }

    public String showError(LukeException e) {
        return e.getMessage();
    }

    public void showLoadingError() {
//        return "An unexpected error was encountered while loading data file.\nPlease try again.";
        System.out.println("loading error");
    }

    public String showAddResult(Task newTask, int numOfTasks) {
        assert numOfTasks >= 0 : "Number of tasks should not be negative";
        return String.format("The following task has been successfully added.\n"
                + "-> %s\n"
                + "Now you have %d tasks in your list.\n", newTask, numOfTasks);
    }

    public String showDeleteResult(Task newTask, int numOfTasks) {
        assert numOfTasks >= 0 : "Number of tasks should not be negative";
        return String.format("The following task has been successfully deleted.\n"
                + "-> %s\n"
                + "Now you have %d tasks in your list.\n", newTask, numOfTasks);
    }

    public String showDoneResult(Task newTask) {
        return String.format("The following task has been successfully marked as done.\n"
                + "-> %s\n", newTask);
    }

    public String showListResult(TaskList tasks) {
        if (tasks.getSize() < 1) {
            return "You don't have any tasks in your list :(";
        } else {
            String taskSummary = "Here are the tasks in your list.\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task current = tasks.getTask(i);
                taskSummary += String.format("-> %d.%s\n", i + 1, current);
            }
            return String.format("%s", taskSummary);
        }
    }

    public String showExitResult() {
        return "";
    }
}
