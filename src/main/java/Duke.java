import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while(!command.equals("bye")) {
            if(command.equals("list")){
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                Integer index = 1;
                for(Task task: list) {
                    System.out.println("    " + index.toString() + ". " + task);
                    index++;
                }
                System.out.println("    ____________________________________________________________");
            } else if (command.substring(0, 4).equals("done")){
                Integer index = Integer.parseInt(command.substring(5));
                Task task = list.get(index - 1).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + task);
                System.out.println("    ____________________________________________________________");

            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                list.add(new Task(command));
                System.out.println("    ____________________________________________________________");
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
