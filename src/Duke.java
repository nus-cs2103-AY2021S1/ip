import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String horizontal = "________________________" + "\n";
    public static ArrayList<Task> list = new ArrayList<>();
    public static int counter = 1;

    public static void action() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if  (input.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!" + "\n";
                System.out.println(horizontal + bye + horizontal);
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontal + "Here are the tasks in your list:" + "\n");
                for (Task task : list) {
                    System.out.println(task.toString());
                }
                System.out.println(horizontal);
            } else if (input.startsWith("done")) {
                String[] number = input.split("done ");
                int num = Integer.parseInt(number[1]);
                Task task = list.get(num - 1);
                task.markAsDone();
                System.out.println(horizontal + "Nice! I've marked this task as done:" + "\n" +
                        task.toString() + "\n" + horizontal);
            } else {
                list.add(new Task(input, counter));
                counter++;
                System.out.println(horizontal + "added: " + input + "\n" + horizontal);
            }
        }
    }

    public static void greet() {
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }


    public static void main(String[] args) {
        greet();
        action();

        //String logo = " ____        _        \n"
        //      + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
    }
}

