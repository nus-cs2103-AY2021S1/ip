public class Ui {
    protected String LINE = "____________________________________________________________";

    protected String GREETING = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";



    protected void greet() {
        System.out.println(GREETING);
    }

    protected void farewell() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    protected void printLines() {
        System.out.println(LINE);
    }

    protected void provideListMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    protected void markAsDoneMsg(Task chosen) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(chosen);
        System.out.println(LINE);
    }

    protected void deleteMsg(int i, Task chosen) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(chosen);
        System.out.println(" Now you have " + i + " tasks in the list.");
        System.out.println(LINE);
    }

    protected void addTaskToTasklistMsg(Task task, int i) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + i + " tasks in the list.");
        System.out.println(LINE);
    }
}
