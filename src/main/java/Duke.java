import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> lst= new ArrayList<>();

        // start of Greeting
        String logo =
                  " ____        _                    \n"
                + "|  _ \\ _   _| | _____  ______ ______ ______  ___  _____\n"
                + "| | | | | | | |/ / _ \\|  __  |__  __|___   |/ _ \\|  _  \\\n"
                + "| |_| | |_| |   <  __/| |  | |__||__ /   /_<  __/|     /\n"
                + "|____/ \\__,_|_|\\_\\___|| |  | |______|______|\\___||_|\\__\\ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________");
        System.out.println("Hello! I'm Dukenizer\nWhat can I do for you?");
        System.out.println("____________________________________");
        System.out.println();


        //Take in Input
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        //main loop
        while (!query.equals("bye")) {

            System.out.println("____________________________________");

            //handle and print based on query
            if (query.equals("list")) {
                for (int i = 0; i <lst.size(); i++) {
                    Task targetTask = lst.get(i);
                    System.out.println((i + 1) + ".[" + targetTask.getStatusIcon() + "] " + targetTask.getDescription());
                }
            } else if (query.startsWith("done")) {   //assuming correct inputs
                Integer doneIndex = Integer.valueOf(query.substring(5));
                if (doneIndex > lst.size()) {
                    return; //handle item does not exist exception; for now do nothing
                } else {
                    Task doneItem = lst.get(doneIndex - 1);
                    doneItem.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + doneItem.getStatusIcon() + "] " + doneItem.getDescription());
                }

            } else {
                Task tsk = new Task(query);
                System.out.println("added: " + query);
                lst.add(tsk);
            }


            System.out.println("____________________________________");
            System.out.println();


            query = sc.nextLine();
        }


        //exit Dukenizer
        System.out.println("____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");

    }


}
