import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> tasks;

    Duke() {
        this.tasks = new ArrayList<>();
    }

    boolean handleInput(String input) {
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            System.out.println("    ________________________________________________________");
            for (Task task : tasks) {
                System.out.println("    " + (tasks.indexOf(task) + 1) + "." + task.toString()
                );
            }
            if (tasks.size() == 0) {
                System.out.println("    None");
            }
            System.out.println("    ________________________________________________________");
            return false;
        } else if (input.startsWith("done ")) {
            int index = Integer.parseInt(input.substring(5));
            Task task = tasks.get(index - 1);
            task.setDone();
            System.out.println(
                    "    ________________________________________________________\n"
                    + "    The following task has been marked as done:\n    " + task.toString()
                    + "\n    ________________________________________________________");
            return false;
        } else {
            System.out.println(
                    "    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n    "
                    + "added: " + input
                    + "\n    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
            );
            tasks.add(new Task(input));
            return false;
        }
        return true;
    }

    void exit() {
        System.out.println(
            "    ========================================================\n"
            + "    Goodbye\n"
            + "    ========================================================"
        );
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            boolean exit = duke.handleInput(input);
            if (exit) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
