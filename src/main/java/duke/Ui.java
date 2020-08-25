package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import duke.task.*;

public class Ui {

    public static final String LINE = "    ____________________________________________________________\n";
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        String greet = LINE + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + LINE;
        System.out.println(greet);
    }

    public void bye() {
        String exit = LINE + "     Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(exit);
    }

    public void done(Task task) {
        System.out.println(LINE +
                "    Nice! I've marked this task as done:" + "\n" +
                "    " + task.toString() + "\n" +
                LINE);
    }

    public String readCommand() {
        return sc.next();
    }

    public int readTaskNumber() {
        return sc.nextInt();
    }

    public void showError(String message) {
        System.out.println(LINE + "    " + message + "\n" + LINE);
    }

    public ToDo getToDo() throws DukeException {
        String detail = sc.nextLine().trim();
        if (detail.equals("")) {
            throw new DukeException("Oops! Todo cannot be empty");
        }
        return new ToDo(detail);
    }

    public Event getEvent() throws DukeException {
        String s = sc.nextLine();
        if (s.trim().equals("")) {
            throw new DukeException("Oops! Event cannot be empty");
        }
        String[] arr = s.split("/at");
        if (arr.length == 1) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
        return new Event(detail, date);
    }

    public Deadline getDeadline() throws DukeException {
        String s = sc.nextLine();
        if (s.trim().equals("")) {
            throw new DukeException("Oops! Deadline cannot be empty");
        }
        String[] arr = s.split("/by");
        if (arr.length == 1) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = LocalDateTime.parse(arr[1].trim(), df);
        return new Deadline(detail, date);
    }

    public void deleteTask(Task task, TaskList taskList) {
        System.out.println(LINE +
                "    Noted. I've removed this task:" + "\n" +
                "      " + task.toString() + "\n" +
                String.format("    Now you have %d tasks in the list.\n", taskList.size()) +
                LINE);
    }

    public void addTask(Task task, TaskList taskList) {
        System.out.println(LINE +
                "    Got it! I have added this task to the list!" + "\n" +
                "      " + task + "\n" +
                String.format("    Now you have %d tasks in the list.", taskList.size()) + "\n" +
                LINE);
    }

    public String getKeyword() {
        return sc.nextLine().trim();
    }
}
