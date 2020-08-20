import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan1 = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        int count = 1;


        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");



        while (scan1.hasNext()) {
            String command = scan1.next();
            if (command.equals("list")) {
                for (Task item: storage) {
                    System.out.println(count + "." + item);
                    count++;
                }
            } else if (command.equals("done")) {
                int number = scan1.nextInt();
                Task current = storage.get(number - 1);
                current.setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(current.getStatusIcon() + " " + current.getDescription());
            } else if (command.equals("todo")) {
                String desc = scan1.nextLine();
                Todo todo = new Todo(desc);
                storage.add(todo);
                int size = storage.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + size +  " tasks in the list.");
            } else if (command.equals("deadline")) {
                String desc = scan1.nextLine();
                String[] string = desc.split("/by");
                Deadline deadline = new Deadline(string[0], string[1]);
                storage.add(deadline);
                int size = storage.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + size +  " tasks in the list.");
            } else if (command.equals("event")) {
                String desc = scan1.nextLine();
                String[] string = desc.split("/at");
                Events event = new Events(string[0], string[1]);
                storage.add(event);
                int size = storage.size();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + size +  " tasks in the list.");
            }
            else {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                scan1.close();
                break;
            }
        }


    }
}
