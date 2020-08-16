import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);



        Scanner sc = new Scanner (System.in);
        ArrayList<Task> Todo = new ArrayList<>();

        //Greeting the user
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");

        String next = sc.nextLine();

        while (!next.equals("bye")){

            if (next.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Todo.size(); i++) {
                    System.out.printf("%d.%s \n", i + 1, Todo.get(i));
                }

            } else if (next.contains("done")) {
                int index = next.charAt(5) - 48;
                Todo.get(index - 1).markDone();
                System.out.println ("Nice! I've marked this task as done:");
                System.out.println (Todo.get(index - 1));

            } else {
                Task t = new Task(next);
                Todo.add(t);
                System.out.printf("added: %s \n", next);
            }

            next = sc.nextLine();
        }

        //Exit
        System.out.println ("Bye. Hope to see your again soon!");
    }
}
