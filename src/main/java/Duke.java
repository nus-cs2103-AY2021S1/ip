import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static String line = "____________________________________________________________";
    static String userInput = "";
    static ArrayList<Task> lst = new ArrayList<>();

    public static void main(String[] args) {
        DukeBot();
    }

    public static void DukeBot() {
        Scanner input = new Scanner(System.in);

        format("Hello! I'm Duke\nWhat can I do for you?");

        userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                formatLst();
            } else if (userInput.startsWith("done")) {
                int num = Integer.parseInt(userInput.substring(5));
                formatMarkAsDone(num - 1);
            } else if (userInput.startsWith("todo")) {
                formatAddTask(new ToDo(userInput.substring(5)));
            } else if (userInput.startsWith("deadline")) {
                int pos = userInput.indexOf("/by");
                formatAddTask(new Deadline(userInput.substring(9, pos - 1), userInput.substring(pos + 4)));
            } else if (userInput.startsWith("event")) {
                int pos = userInput.indexOf("/at");
                formatAddTask(new Event(userInput.substring(6, pos - 1), userInput.substring(pos + 4)));
            } else {
                format("added: " + userInput);
                lst.add(new Task(userInput));
            }
            userInput = input.nextLine();
        }

        format("Bye. Hope to see you again soon!");
    }

    public static void format(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void formatLst() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println(i + "." + lst.get(i-1));
        }
        System.out.println(line);
    }

    public static void formatMarkAsDone(int num) {
        lst.get(num).markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(lst.get(num));
        System.out.println(line);
    }

    public static void formatAddTask(Task task) {
        lst.add(task);
        int size = lst.size();
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks"));
        System.out.println(line);
    }
}
