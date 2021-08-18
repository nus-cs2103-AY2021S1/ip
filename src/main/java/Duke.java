import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.function.Function;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String sectionBreak = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello buddy! I am");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(sectionBreak);

        Scanner sc=new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTaskList();
            } else if (input.startsWith("done")) {
                MarkTask(input);
            } else if (input.startsWith("event")) {
                AddTask(input, Event::create);
            } else if (input.startsWith("deadline")) {
                AddTask(input, Deadline::create);
            } else if (input.startsWith("todo")) {
                AddTask(input, ToDo::create);
            } else if (input.startsWith("delete")) {
                DeleteTask(input);
            } else {
                System.out.println(new DukeException("I'm not sure what you mean"));
            }


            System.out.println(sectionBreak);
        }

        System.out.println("Bye. Hope to see you soon!");
        System.out.println(sectionBreak);
    }

    private static void AddTask(String formattedString, Function<String, ? extends Task> create) {
        try {
            Task e = create.apply(formattedString);
            taskList.add(e);
            System.out.printf("added: %s\n", e);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private static void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    private static int getId(String input, String prefix) throws DukeException {
        String idString = "";
        try {
            if (input.indexOf(prefix) == -1 || input.length() <= prefix.length())
                throw new DukeException("you did not specify a task id");

            idString = input.substring(prefix.length());
            return Integer.parseInt(idString);

        } catch (NumberFormatException e) {
            String msg = String.format("\"%s\" is not a valid integer", idString);
            throw new DukeException(msg);
        }
    }

    private static void MarkTask(String input) {
        int taskId = -1;
        try {
            taskId = getId(input, "done ");
            Task t = taskList.get(taskId - 1);
            t.markAsDone();
            System.out.println("Cool, I've marked this task as done\n" + t);
        } catch (DukeException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Oops, Task #%d doesn't exist\n", taskId);
        }
    }

    private static void DeleteTask(String input) {
        int taskId = -1;
        try {
            taskId = getId(input, "delete ");
            Task t = taskList.get(taskId - 1);
            taskList.remove(taskId);
            System.out.println("Okay, I've removed this task\n" + t);
        } catch (DukeException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Oops, Task #%d doesn't exist\n", taskId);
        }
    }
}
