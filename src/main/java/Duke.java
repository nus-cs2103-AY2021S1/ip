import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String lines = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(introduction);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] splitted = input.split(" ", 2);
            if (input.equals("list")) {
                System.out.println(lines);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(" " + i + ". " + list.get(i - 1));
                }
                System.out.println(lines);
            } else if (splitted[0].equals("done")){
                int index = Integer.parseInt(splitted[1]) - 1;
                list.set(index, list.get(index).completeTask());
                System.out.println(lines);
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + list.get(index));
                System.out.println(lines);
            }
            else {
                System.out.println(lines);
                System.out.println("added: " + input);
                System.out.println(lines);
                list.add(new Task(input, false));
            }
            input = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
