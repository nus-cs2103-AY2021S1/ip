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

            //listing out all the tasks
            if (next.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s \n", i + 1, tasks.get(i));
                }

            //when a task is done
            } else if (next.contains("done") && next.startsWith("done")) {
                int index = Integer.parseInt(next.replaceAll("[^0-9]", ""));
                tasks.get(index - 1).markDone();
                System.out.println (tasks.get(index - 1));

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
