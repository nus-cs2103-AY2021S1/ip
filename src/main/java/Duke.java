import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int listSize = list.size();
                if (listSize == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listSize; i++) {
                        int taskNum = i + 1;
                        System.out.print(taskNum + ".");
                        System.out.println(list.get(i));
                    }
                }
            } else if (input.length() > 5 && input.substring(0,4).equals("done")) {
                int index = Integer.parseInt(input.substring(input.length()-1)) - 1;
                if (index > list.size() - 1) {
                    System.out.println("Task number out of range.");
                    input = sc.nextLine();
                    continue;
                }
                Task task = list.get(index);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.done());
            } else {
                list.add(new Task(input));
                System.out.println("I have added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
