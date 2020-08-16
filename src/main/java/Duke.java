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
        ArrayList<Task> tasks = new ArrayList<>();

        //Greeting the user
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");

        String next = sc.nextLine();


        while (!next.equals("bye")){

            //Listing out all the tasks
            if (next.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s \n", i + 1, tasks.get(i));
                }

            //When a task is done
            } else if (next.startsWith("done ")) {
                int index = Integer.parseInt(next.replaceAll("[^0-9]", ""));
                tasks.get(index - 1).markDone();
                System.out.println(tasks.get(index - 1));


            //Adding a to do, deadline, event
            } else if (next.startsWith("todo ") || next.startsWith("deadline ") || next.startsWith("event ")) {
                System.out.println ("Got it. I've added this task:");
                Task task;

                if (next.contains("todo")) {
                    String s = next.substring(5);
                    task = new Todo(s);

                } else if (next.contains("deadline")) {
                    String[] s = next.split("/");
                    task = new Deadline(s[0].substring(9), s[1].substring(3));

                } else {
                    String[] s = next.split ("/");
                    task = new Event (s[0].substring(6), s[1].substring(3));
                }

                tasks.add(task);
                System.out.println(task);
                System.out.printf ("Now you have %d tasks in the list.\n", tasks.size());


            //adding a new task
            } else {
                Task t = new Task(next);
                tasks.add(t);
                System.out.printf("added: %s \n", next);
            }

            next = sc.nextLine();
        }

        //Exit
        System.out.println ("Bye. Hope to see your again soon!");
    }
}
