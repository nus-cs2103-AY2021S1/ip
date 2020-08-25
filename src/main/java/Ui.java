public class Ui {

    private String border = "----------------------------------------------------------------------------\n";

    public void printGreeting() {
        String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Sorry :( Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for Duke, how may I assist you ?\n";
        System.out.println(this.border + greeting + this.border);
    }

    public void printFarewell() {
        String farewell = "Adios, pleasure to serve you!\n";
        System.out.println(this.border + farewell + this.border);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(this.border + taskList.toString() + this.border);
    }

    public void printDoneTask(Task task) {
        System.out.println(this.border
                + "Making great progress master.\n"
                + task.toString() + "\n"
                + this.border
        );
    }

    public void printAddedNewTask(Task task, int noTask) {
        System.out.println(this.border
                + "Yes master. I've added the task to the list: \n"
                + task.toString() + "\n"
                + "You now have " + noTask + " task in the list master.\n"
                + this.border
        );
    }

    public void printDeleteTask(Task task, int noTask) {
        System.out.println(this.border
                + "Yes master. I've deleted the task from the list: \n"
                + task.toString() + "\n"
                + "You now have " + noTask + " task in the list master.\n"
                + this.border
        );
    }

    public void printGetTaskOnDThisDate(String dueDate, TaskList tasks) {
        System.out.println(
            this.border
            + "Master here are the tasks due on " + dueDate.strip() + " :\n"
            + tasks.getTaskDueOn(dueDate)
            + this.border
        );
    }

    public void printLoadingError() {
        System.out.println(
            this.border
            + "Master i am unable to retrieve the file, initializing new one!"
            + this.border
        );
    }

    public void printErrorInSaving() {
        System.out.println(
            this.border
            + "Master i am unable to save the file!"
            + this.border
        );
    }
}
