import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) {
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
                    assert i >= 0;
                    assert i < tasks.size();
                    Task task = tasks.get(i);
                    task.done = true;
                    print("Nice! I've marked this task as done: \n" + task);
                    break;
                case "todo":
                    String text = input.substring(5);
                    newTask = new Todo(text);
                    addTask(newTask, tasks);
                    break;
                case "deadline":
                    subst = input.substring(9).split(" /by ");
                    newTask = new Deadline(subst[0], subst[1]);
                    addTask(newTask, tasks);
                    break;
                case "event":
                    subst = input.substring(6).split(" /at ");
                    newTask = new Event(subst[0], subst[1]);
                    addTask(newTask, tasks);
                    break;
                default:
                    break;
            }

//            System.out.println(LINE + input + "\n" + LINE);
            input = scanner.nextLine();
        }
        print(BYE);
    }

    public static void print_tasks(ArrayList<Task> tasks){
        System.out.print(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i+1) + "." + tasks.get(i));
        }
        System.out.print(LINE);
    }

    public static void print(String str){
        System.out.print(LINE + str + LINE);
    }

    public static void addTask(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        int size = tasks.size();
        print("added: " + task.toString() + "You now have " + size + " task" + (size > 1 ? "s" : "") +" in the list.\n");
    }
}
