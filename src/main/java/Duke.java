import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static class Task {
        private boolean completion;
        private String name;

        Task(String name) {
            this.name = name;
            this.completion = false;
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
    public static void store(String item) {
        storage.add(new Task(item));
    }

    // lists items in storage with numbers
    public static void listOut() {
        line();
        System.out.println("Here's your tasks");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + ". " + currentTask.getCompletion() + " " + currentTask.getName());
        }
        line();
    }

    public static void markComplete(int taskNumber) {
        storage.get(taskNumber).complete();
        line();
        System.out.println("Marked task " + (taskNumber + 1) + " as complete.");
        System.out.println("[✓] " + storage.get(taskNumber).getName());
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
            // bye exits while
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            }
            // list of items in storage
            else if (output.equals("list")) {
                listOut();
            }
            else if (output.length() >= 6 && output.substring(0, 4).equals("done")) {
                String index = output.substring(5);
                int intIndex = Integer.parseInt(index);
                markComplete(intIndex - 1);
            }
            // no special order, adds user input to storage
            else {
                store(output);
                echo("added: " + output);
            }
        }

    }
}
