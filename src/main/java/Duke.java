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
            if (input.equals("list")) {
                System.out.println(lines);
                list.forEach(x -> {
                    int i = 1;
                    System.out.println(i + ". " + x);
                    i = i + 1;
                });
                System.out.println(lines);
            } else if (input.split(" ")[0].equals("done")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).setDone();
                System.out.println(lines);
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(list.get(index));
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
