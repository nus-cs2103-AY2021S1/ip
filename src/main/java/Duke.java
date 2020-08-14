import java.util.ArrayList;
import java.util.Scanner;
// I'm taking a break
public class Duke {

    static class Task {
        private boolean completion;
        private String name;
        private Type type;

        enum Type {
            todo,
            deadline,
            event
        }

        Task(String type, String name) {
            this.name = name;
            this.completion = false;
            this.type = Type.valueOf(type);
        }

        void complete() {
            completion = true;
        }

        public String getName() {
            return name;
        }

        public String getCompletion() {
            if (completion) {
                return "[✓]";
            } else {
                return "[✗]";
            }
        }

        public String getType() {
            switch (type) {
                case todo:
                    return "[T]";
                case event:
                    return "[E]";
                case deadline:
                    return "[D]";
                default:
                    // this should not happen?
                    return "something went wrong";
            }
        }
    }

    // Task storage
    static ArrayList<Task> storage = new ArrayList<>();

    // border line
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    // output sandwiched by border lines
    public static void echo(String output) {
        line();
        System.out.println(output);
        line();
    }

    // stores user inputs in storage
    public static void store(String type, String name) {
        Task taskToAdd = new Task(type, name);
        storage.add(taskToAdd);
        taskAdded(taskToAdd);
    }

    public static void taskAdded(Task task) {
        String toEcho = "Task added: \n"
                + task.getType() + task.getCompletion() + " " + task.getName() + "\n"
                + "You now have " + storage.size() + " task(s).";
        echo(toEcho);
    }

    // lists items in storage with numbers
    public static void listOut() {
        line();
        System.out.println("Here's your tasks");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + ". "
                    + currentTask.getType()
                    + currentTask.getCompletion() + " "
                    + currentTask.getName());
        }
        line();
    }

    public static void markComplete(int taskNumber) {
        Task currentTask = storage.get(taskNumber);
        currentTask.complete();
        line();
        System.out.println("Marked task " + (taskNumber + 1) + " as complete.");
        System.out.println(currentTask.getType() + "[✓] " + storage.get(taskNumber).getName());
        line();
    }

    public static void main(String[] args) {
        String logo =
                          " ____             _     \n"
                        + "|  _ \\           | |    \n"
                        + "| |_) |_ __ _   _| |__\n"
                        + "|  _ <| '__| | | | '_ \\ \n"
                        + "| |_) | |  | |_| | | | |\n"
                        + "|____/|_|   \\__,_|_| |_|\n";

        echo(logo + "What's up?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String output = scanner.nextLine();
            // special 1 word cases
            // bye exits while
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            }
            // list of items in storage
            else if (output.equals("list")) {
                listOut();
            }
            // multiple words
            else {
                Scanner multiWord = new Scanner(output);
                String firstWord = multiWord.next();
                if (firstWord.equals("done")) {
                    String index = multiWord.next();
                    int intIndex = Integer.parseInt(index);
                    markComplete(intIndex - 1);
                }
                // it's a task
                else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    // whitespace in front of nextLine
                    String remainingWords = multiWord.nextLine().trim();
                    System.out.println(remainingWords);
                    store(firstWord, remainingWords);
                }
                // no special order, this shouldn't happen anymore
                else {
                    System.out.println("invalid order");
                }
            }
        }
    }
}
