import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void print(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.time + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.time + ")";
        }
        System.out.println("Got it. I've added this task: " + "\n" +
                t.getIndicator() + t.getIcon() + t.name + toPrint + "\n" +
                "Now you have " + arr.size() + " tasks in the list."
                );
    }

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
            int indexOfSlash = command.indexOf('/');

            if (command.equals("bye")) {
                System.out.println("Bye. Please come back soon :(");
                exited = true;
            } else if (command.length() >= 4) {
                if (command.equals("list")) {
                    for (Task s: arr) {
                        System.out.println("Here are the tasks in the list: " + "\n" + counter + ". " + s.getIndicator() + s.getIcon() + " " + s.name);
                        counter++;
                    }
                } else if (command.substring(0, 4).equals("done")) {
                    int taskNumber = Integer.parseInt(command.substring(5, command.length()));
                    Task t = arr.get(taskNumber - 1);
                    t.taskIsDone();
                    System.out.println("Nice! Duke has marked this task as done: " + "\n" + t.getIcon() + t.name);
                } else if (command.substring(0, 5).equals("event") && indexOfSlash != -1) {
                    if (command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("at")) {
                        Event e = new Event(command.substring(6, indexOfSlash - 1), command.substring(indexOfSlash + 4));
                        arr.add(e);
                        print(arr, e);
                    }
                } else if (command.substring(0, 4).equals("todo")) {
                    Todo t = new Todo(command.substring(5));
                    arr.add(t);
                    print(arr, t);
                } else if (command.substring(0, 8).equals("deadline") && indexOfSlash != -1) {
                    if (command.substring(indexOfSlash + 1, indexOfSlash + 3).equals("by")) {
                        Deadline d = new Deadline(command.substring(9, indexOfSlash - 1), command.substring(indexOfSlash + 4));
                        arr.add(d);
                        print(arr, d);
                    }
                } else {
                    System.out.println("Wrong Command" + "\n");
                }
            } else {
                System.out.println("Wrong Command" + "\n");
            }
        }

    }
}
