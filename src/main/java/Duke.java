import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "I can be your friend who manages your task!");
        boolean exited = false;
        ArrayList<Task> arr = new ArrayList<>();

        while (!exited) {
            int counter = 1;
            Scanner sc = new Scanner(System.in);

            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Please come back soon :(");
                exited = true;
            } else if (command.equals("list")) {
                for (Task s: arr) {
                    System.out.println(counter + ". " + s.getIcon() + s.name);
                    counter++;
                }
            } else if (command.substring(0, 4).equals("done")) {
                int taskNumber = Integer.parseInt(command.substring(5, command.length()));

                Task t = arr.get(taskNumber - 1);
                t.taskIsDone();
                System.out.println("Nice! Duke has marked this task as done: " + "\n" + t.getIcon() + t.name);
            }
            else {
                arr.add(new Task(command));
                System.out.print("added: "+ command + "\n");
            }
        }

    }
}
