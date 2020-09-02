import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean shouldIgnore(String line) {
        return line.trim().isEmpty();
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public String readCommand() {
        String commandLine = in.nextLine();
        while(shouldIgnore(commandLine)) {
            commandLine = in.nextLine();
        }
        return commandLine;
    }

    public void showWelcome() {
        showLine();
        out.println((
                " ____        _\n" +
                "|  _ \\ _   _| | _____\n" +
                "| | | | | | | |/ / _\\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "Hello! I'm Thuya\n" +
                "What may I do for you, sir/madam?")
        );
        showLine();
    }

    public void showDelete(Task task, TaskList taskList) {
        out.println(
            "Noted. I've removed this task:\n" +
            task.toString() + "\n" +
            "Now you have "+ taskList.size() +" tasks in the list."
        );
    }

    public void showAdd(Task task, TaskList taskList) {
        out.println(
            "Got it. I've added this task:\n" +
            task.toString() + "\n" +
            "Now you have "+ taskList.size() + " tasks in the list."
        );
    }

    public void showBye() {
        out.println("Bye. Hope to see you again soon!");
    }

    public void showException(DukeException dukeException) {
        out.println(dukeException.getMessage());
    }

    public void showDone(Task task) {
        out.println(
            "Nice! I've marked this task as done:\n" +
             task.toString()
        );
    }


    public void showTaskList(TaskList taskList) {
        out.println((taskList.toString()));
    }
}
