import java.util.Scanner;

public class Duke {

    private final Task task;

    public Duke() {
        task = new Task();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    public void store() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (! exit) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit = true;
                sc.close();
            } else if (command.equals("list")) {
                System.out.println(task);
            } else {
                task.addTasks(command);
                System.out.println("added: " + command + "\n");
            }
        }
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.store();
        duke.exit();
    }
}
