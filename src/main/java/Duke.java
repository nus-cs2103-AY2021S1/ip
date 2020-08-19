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
            switch (command) {
                case "list":
                    print_tasks(tasks);
                    break;
                default:
                    Task newTask = new Task(input, false);
                    print("added: " + newTask.toString());
                    tasks.add(newTask);
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
}
