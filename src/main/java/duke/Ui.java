package duke;

import task.Task;

public class Ui {

    private String border = "";

    /**
     * prints the greeting when user first boots duke
     */
    public String printGreeting() {
        String greeting = "Sorry :( duke.Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for duke.Duke, how may I assist you ?\n";
        return (this.border + greeting + this.border);
    }

    /**
     * prints the farewell message when the user closes duke
     */
    public String printFarewell() {
        String farewell = "Adios, pleasure to serve you!\n";
        return (this.border + farewell + this.border);
    }

    /**
     * prints the current list of tasks
     * @param taskList the current list of task
     */
    public String printTaskList(TaskList taskList) {
        return (this.border + taskList.toString() + this.border);
    }

    /**
     * prints the task that was just marked complete
     * @param task the task that was just marked completed
     */
    public String printDoneTask(Task task) {
        return (this.border
                + "Making great progress master.\n"
                + task.toString() + "\n"
                + this.border
                );
    }

    /**
     * prints the new task that was added and prints the updated total number of tasks for the user to see
     * @param task the new task that was created by the user
     * @param noTask the number of task currently in the task list
     */
    public String addedNewTaskMessage(Task task, int noTask) {
        return (this.border
                + "Yes master. I've added the task to the list: \n"
                + task.toString() + "\n"
                + "You now have " + noTask + " task in the list master.\n"
                + this.border
                );
    }

    /**
     * prints the deleted task and prints the updated total number of tasks forthe user to see
     * @param task the task that was deleted by the user
     * @param noTask the number of task currently in the task list
     */
    public String printDeleteTask(Task task, int noTask) {
        return (this.border
                + "Yes master. I've deleted the task from the list: \n"
                + task.toString() + "\n"
                + "You now have " + noTask + " task in the list master.\n"
                + this.border
                );
    }


    /**
     * prints the tasks that falls on the date input by the user
     * @param dueDate the date in string
     * @param tasks the current list of task
     */
    public String printGetTaskOnDThisDate(String dueDate, TaskList tasks) {
        return (this.border
                + "Master here are the tasks due on " + dueDate.strip() + " :\n"
                + tasks.getTaskDueOn(dueDate)
                + this.border
                );
    }

    /**
     * alerts the user that it has trouble loading the tasks that was saved on the hard disk and hence creating a new
     * file
     */
    public String printLoadingError() {
        return (this.border
                + "Master i am unable to retrieve the file, initializing new one!"
                + this.border
                );
    }

    /**
     * alerts the user that it has trouble saving the tasks onto the hard disk
     */
    public String printErrorInSaving() {
        return (this.border
                + "Master i am unable to save the file!"
                + this.border
                );
    }

    /**
     * prints the tasks that contains the keywords that is given by the user
     * @param parameter the keyword that the user inputs
     * @param taskList the current list of task
     */
    public String printFindKeyword(String parameter, TaskList taskList) {
        return (this.border
                + "Master here are the tasks with keyword " + parameter.strip() + " :\n"
                + taskList.getTaskWithKeyword(parameter)
                + this.border
                );
    }
}
