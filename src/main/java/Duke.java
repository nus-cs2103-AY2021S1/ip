import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String lines = "____________________________________________________________";

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        int counter = 0;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");

                for (Task task : list) {
                    String stringedIndex = Integer.toString(list.indexOf(task) + 1);
                    String outputLine = stringedIndex + ". " + task;
                    System.out.println(outputLine);
                }
                System.out.println(lines);
                input = sc.nextLine();
            } else if (input.contains("done")) {
                String stringIndex = input.substring(5, input.length());
                int index = Integer.parseInt(stringIndex);
                Task chosen = list.get(index -1);
                chosen.finish();
                System.out.println(lines);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(chosen);
                System.out.println(lines);
                input = sc.nextLine();
            } else {
                System.out.println(lines);
                list.add(new Task(input));
                String outputLine = "added: " + input;
                System.out.println(outputLine);
                input = sc.nextLine();
            }
        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);


    }

}
