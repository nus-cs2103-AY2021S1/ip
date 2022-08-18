import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String line = "_______________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void handle(String word) {
        if (word.startsWith("bye") || word.startsWith("Bye")) {
            bye();
        } else if (word.startsWith("list") || word.startsWith("List")) {
            showList();
        } else if (word.startsWith("done") || word.startsWith("Done")) {
            handleDone(word);
        } else if (word.startsWith("todo")) {
            String[] splitted = word.split(" ", 2);
            addTask(splitted[1], null, 'T');
        } else if (word.startsWith("deadline")) {
            String[] splitted = word.split(" ", 2);
            String[] stringAndDate = splitted[1].split("/by");
            addTask(stringAndDate[0], stringAndDate[1], 'D');
        } else if (word.startsWith("event")) {
            String[] splitted = word.split(" ", 2);
            String[] stringAndDate = splitted[1].split("/at");
            addTask(stringAndDate[0], stringAndDate[1], 'E');
        } else {
            System.out.println("Please give proper input");
        }
    }

    public static void addTask(String word, String date, char tag) {
        System.out.println(line);
        Task newTask;
        if (tag == 'D') {
            newTask = new Deadline(word, date);
        } else if (tag == 'E') {
            newTask = new Event(word, date);
        } else {
            newTask = new Todo(word);
        }
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in your list");
        System.out.println(line);
    }

    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    public static void showList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int counter = i + 1;
            System.out.println(counter + ". " + tasks.get(i));
        }
        System.out.println(line);
    }

    public static void handleDone(String word) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        String[] doneTasks = word.split(" ");
        for (int i = 1; i < doneTasks.length; i++) {
            int taskNo = Integer.parseInt(doneTasks[i]);
            if (taskNo - 1 < tasks.size()) {
                tasks.get(taskNo - 1).markAsDone();
                System.out.println(tasks.get(taskNo - 1));
            }
        }
        System.out.println(line);
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String nextWord = sc.nextLine();
            if (!nextWord.equals("")) {
                handle((nextWord));
            }
        }
    }
}
