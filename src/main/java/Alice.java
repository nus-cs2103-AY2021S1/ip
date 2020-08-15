import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private final ArrayList<Task> ls;

    public Alice() {
        ls = new ArrayList<>();
        greet();
        prompt();
    }

    public boolean reply(String s) {
        if (s.equals("bye")) {
            display("Goodbye. Hope to see you again soon!");
            return false;
        }

        String[] inputs = s.split(" ");
        if (inputs[0].equals("list")) {
            // Display task list
            print_list();
            prompt();
        } else if (inputs[0].equals("done")) {
            // Mark as done
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                Task completedTask = ls.get(index).markAsDone();
                ls.set(index, completedTask);
                display("Great work! I've marked this task as done: \n    " + completedTask);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                display("Sorry! I could not register that. Please use a valid number");
            } finally {
                prompt();
            }
        } else {
            // Add task
            Task t = new Task(s);
            ls.add(t);

            display("added: " + s);
            prompt();
        }
        return true;
    }

    private void display(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }

    private void print_list() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i + 1 + ". " + ls.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void greet() {
        String logo = "\n _____  _     _____ _____  _____ \n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__  \n" +
                "|  _  || |     | | | |    |  __| \n" +
                "| | | || |_____| |_| \\__/\\| |___ \n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/ \n";

        System.out.println(logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?\n" +
                "____________________________________________________________");
    }

    private void prompt() {
        System.out.print(" > ");
    }

    public static void main(String[] args) {
        Alice alice = new Alice();
        Scanner sc = new Scanner(System.in);

        String cmd = sc.nextLine();
        while (alice.reply(cmd)) {
            cmd = sc.nextLine();
        }
    }
}
