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

            //get first word of String
            String[] instruction = query.split(" ", 2);


            System.out.println("____________________________________");

            //handle and print based on query

            //list items
            if (instruction[0].equals("list")) {
                for (int i = 0; i <lst.size(); i++) {
                    Task targetTask = lst.get(i);
                    System.out.println((i + 1) + "." + targetTask.toString());
                }

            }

            //handle done items
            else if (instruction[0].equals("done")) {
                Integer doneIndex = Integer.valueOf(instruction[1]);
                if (doneIndex > lst.size()) {
                    return; //handle item does not exist exception; for now do nothing
                } else {
                    Task doneItem = lst.get(doneIndex - 1);
                    doneItem.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneItem.toString());
                }

            } else {
                switch (instruction[0]) {
                    case "todo" :
                        Task todo = new Todo(instruction[1]);
                        lst.add(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.toString());
                        System.out.println("Now you have " + lst.size() + " tasks in the list.");
                        break;
                    case "deadline":

                        Task deadline = new Deadline(instruction[1].split(" /by ")[0], instruction[1].split(" /by ")[1]);
                        lst.add(deadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        System.out.println("Now you have " + lst.size() + " tasks in the list.");
                        break;

                    case "event":
                        Task event = new Event(instruction[1].split(" /at ")[0], instruction[1].split(" /at ")[1]);
                        lst.add(event);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        System.out.println("Now you have " + lst.size() + " tasks in the list.");
                        break;


                    default:
                        Task tsk = new Task(query);
                        System.out.println("added: " + query);
                        lst.add(tsk);
                }

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
