package luke;

import luke.task.Task;

public class Ui {

    public Ui() {

    }

    public String showWelcome() {
        return "Hey there! I'm Luke.\n"
                + "Please tell me what to do.\n";
    }

    public String showError(String msg) {
        return msg;
    }

    public String showLoadingError() {
        return "An unexpected error was encountered while loading data file.\nPlease try again.";
    }

    public String showAddResult(Task newTask, int numOfTasks) {
        return String.format("The following task has been successfully added.\n"
                + "-> %s\n"
                + "Now you have %d tasks in your list.\n", newTask, numOfTasks);
    }

    public String showDeleteResult(Task newTask, int numOfTasks) {
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
            return "You don't have any tasks in your list :(\n";
        } else {
            String taskSummary = "Here are the tasks in your list.";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task current = tasks.getTask(i);
                taskSummary += String.format("\n\t%d.%s", i + 1, current);
            }
            return String.format("%s\n", taskSummary);
        }
    }

    public String showExitResult() {
        return "See you next time.";
    }
}
