import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String str = "";
        System.out.println("    _________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________");

        ArrayList<Task> lst = new ArrayList<>();

        while (!str.equals("bye")) {
            str = sc.nextLine();
            Task newTask = new Task(str);

            System.out.println("    _________________________________");


            if (newTask.getDescription().equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < lst.size(); i++) {
                    String item = (i + 1) + ". [" + lst.get(i).getStatusIcon() + "] " + lst.get(i);
                    System.out.println("    " + item);
                }

            } else if (newTask.getFirstWord().equals("done")) {
                int taskNumber = newTask.getNumber();
                lst.get(taskNumber - 1).markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    [" + lst.get(taskNumber - 1).getStatusIcon() + "] " + lst.get(taskNumber - 1));

            } else if (!str.equals("bye")) {
                System.out.println("    added: " + newTask.getDescription());
                //System.out.println("    _________________________________");
                lst.add(newTask);
            }
            System.out.println("    _________________________________");
        }

        System.out.println("    _________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________");

    }
}
