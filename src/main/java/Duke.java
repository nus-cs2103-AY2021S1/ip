import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        print(WELCOME);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.isEmpty()) {
                    continue;
                }
                String[] words = input.split(" ");
                String command = words[0];
                Task newTask;
                String[] subst;
                switch (command) {
                    case "list":
                        print_tasks(tasks);
                        break;
                    case "done":
                        int i = Integer.parseInt(words[1]) - 1;
                        if(i <= 0 || i > tasks.size()){
                            throw new DukeException("invalid task number");
                        }
                        Task task = tasks.get(i);
                        task.done = true;
                        print("Nice! I've marked this task as done: \n" + task);
                        break;
                    case "todo":
                        if (input.length() < 6) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        String text = input.substring(5);
                        newTask = new Todo(text);
                        addTask(newTask, tasks);
                        break;
                    case "deadline":
                        if (input.length() < 10) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        subst = input.substring(9).split(" /by ");
                        if (subst.length < 2) {
                            throw new DukeException("The due date must be specified.");
                        }
                        newTask = new Deadline(subst[0], subst[1]);
                        addTask(newTask, tasks);
                        break;
                    case "event":
                        if (input.length() < 7) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        subst = input.substring(6).split(" /at ");
                        if (subst.length < 2) {
                            throw new DukeException("The event date must be specified.");
                        }
                        newTask = new Event(subst[0], subst[1]);
                        addTask(newTask, tasks);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
//                        break;
                }
            } catch (DukeException e) {
                print(e.getMessage() + "\n");
            }

//            System.out.println(LINE + input + "\n" + LINE);
            input = scanner.nextLine();
        }
        print(BYE);
    }

    public static void print_tasks(ArrayList<Task> tasks) {
        System.out.print(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + "." + tasks.get(i));
        }
        System.out.print(LINE);
    }

    public static void print(String str) {
        System.out.print(LINE + str + LINE);
    }

    public static void addTask(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        int size = tasks.size();
        print("added: " + task.toString() + "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n");
    }
}
