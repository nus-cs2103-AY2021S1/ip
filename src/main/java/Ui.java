import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String LINE = "---------------------------------------------------------";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("hElLoOOoOOoO! Welcome to\n" + LOGO);
        System.out.println("How can I help you today? : D");
        System.out.println("Type \"help\" to view the list of commands you can use!");
    }

    public void showExit() {
        System.out.println("BYEEE!! SEE YOU AGAIN!!! >O<");
    }

    public void showHelp() {
        System.out.println("Here are the list of commands you can use! =D");
        System.out.println("help: displays the list of commands available\n");
        System.out.println("list: displays the list of tasks you have\n");
        System.out.println("find *keyword*: displays the tasks with that keyword\n"
                + "eg find book\n");
        System.out.println("todo *task description*: adds a task without " +
                "any\ndate/time attached to it\n" + "eg todo read book\n");
        System.out.println("deadline *task description* /by *date+time*: " +
                "adds a\ntask that needs to be done before a specific date and time\n" +
                "(date and time to be written in yyyy-mm-dd HHMM format)\n" +
                "eg deadline return book /by 2019-10-15 2359\n");
        System.out.println("event *task description* /at *date+time*: " +
                "adds a task that\nstarts at a specific time and ends at a specific time\n" +
                "(date and time to be written in yyyy-mm-dd HHMM format)\n" +
                "eg event meeting /at 2019-10-15 1200\n");
        System.out.println("done *task number*: marks the task with that number as\n" +
                "done eg done 1\n");
        System.out.println("delete *task number*: deletes the task with that number\n" +
                "from the list eg delete 1\n");
        System.out.println("bye: ends the session");
    }

    public void showList() {
        System.out.println("Here are your tasks! JIAYOU! =D");
    }

    public void showDone(Task t) {
        System.out.println("Nicee!! You've completed this task!");
        System.out.println(t);
    }

    public void showDelete(Task t) {
        System.out.println("Oki! I've removed this task!");
        System.out.println(t);
        System.out.println("Now you have " + Task.totalTasks
                + " tasks in your list!");
    }

    public void showAdd(Task t) {
        System.out.println("Oki! I have added this task:");
        System.out.println(t);
        System.out.println("Now you have " + Task.totalTasks
                + " tasks in your list!");
    }

    public void showFind() {
        System.out.println("Oki! I have found the matching tasks in your list:");
    }

    public void showError(String err) {
        System.out.println(err);
    }

    public void showLine() {
        System.out.println(LINE);
    }
}
