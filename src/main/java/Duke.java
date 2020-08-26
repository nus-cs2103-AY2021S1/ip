import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        initialize();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        serveClient();
    }

    private static void initialize() {
        File dir = new File("./data");
        dir.mkdir();
        File file = new File("./data/duke.txt");
        try {
            if (!file.createNewFile()) {        // createNewFile() returns true if the file path
                Scanner sc = new Scanner(file); // does not exist and a new file is created
                while (sc.hasNext()) {
                    String str = sc.nextLine();
                    Task task;
                    if (str.startsWith("T")) {
                        task = ToDo.load(str);
                    } else if (str.startsWith("D")) {
                        task = Deadline.load(str);
                    } else if (str.startsWith("E")) {
                        task = Event.load(str);
                    } else {
                        break;
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveTasks() {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                fw.write(task.store() + "\n"); // write the task onto txt with the format
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serveClient() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] arr = input.split(" ", 2); // limit is the result threshold; return 2 strings
                switch (arr[0]) { // use switch case for easy scalability
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println(i + "." + tasks.get(i - 1));
                        }
                        break;
                    case "done":
                        int index = Integer.parseInt(arr[1]) - 1;
                        Task task = tasks.get(index); // returns the specific task from the list
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + task);
                        break;
                    case "delete":
                        index = Integer.parseInt(arr[1]) - 1;
                        task = tasks.get(index);
                        tasks.remove(index);
                        int size = tasks.size();
                        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.");
                        break;
                    case "todo":
                        if (arr.length == 1 || arr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDo(arr[1]);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        if (arr.length == 1 || arr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] strings = arr[1].split("/by");
                        task = new Deadline(strings[0].strip(), strings[1].strip());
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    case "event":
                        if (arr.length == 1 || arr[1].strip().equals("")) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                        }
                        strings = arr[1].split("/at");
                        task = new Event(strings[0].strip(), strings[1].strip());
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                saveTasks();
                if (arr[0].equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}