import java.util.Scanner;

class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String takeInput() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }
    public void printListSize(int size) {
        System.out.println("You now have " + size + (size == 1
                ? " task in your list."
                : " tasks in your list."));
    }
    public void printList(TaskList tasks) {
        System.out.println(tasks);
    }
    public void printExitMessage() {
        System.out.println("Goodbye!");
    }
    public void printAddTask(String task) {
        System.out.println("Added new task: " + task);
    }
    public void printDoneTask() {
        System.out.println("Congrats, I've marked this task as finished!");
    }
    public void printDelTask(TaskList tasks, int idx) {
        System.out.println("The task " + tasks.getTasks().get(idx - 1) + " has been removed.");
    }
}
