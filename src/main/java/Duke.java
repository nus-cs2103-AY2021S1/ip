import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //prints Greeting message
        printGreeting();

        //Initialize Task list
        List<Task> lst = new ArrayList<>();


        //Take in Input
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();

        //main loop
        while (!query.equals("bye")) {

            System.out.println("_______________________________________________________________");

            String[] instruction;


            //checks for exception
            try {
                instruction = readInstruction(lst, query);
            } catch (DukeException e) {
                System.out.println(e);
                System.out.println("_______________________________________________________________");
                System.out.println();
                query = sc.nextLine();
                continue;
            }



            //list items
            if (instruction[0].equals("list")) {
                printList(lst);
            }

            //handle done items
            else if (instruction[0].equals("done")) {
                int doneIndex = Integer.parseInt(instruction[1]);
                if (doneIndex > lst.size()) {
                    return; //handle item does not exist exception; for now do nothing
                } else {
                    doneItem(lst, doneIndex);
                }

            } else {
                switch (instruction[0]) {
                    case "todo":
                        addTodo(lst, instruction[1]);
                        break;
                    case "deadline":
                        addDeadline(lst, instruction[1].split(" /by ")[0], instruction[1].split(" /by ")[1]);
                        break;

                    case "event":
                        addEvent(lst, instruction[1].split(" /at ")[0], instruction[1].split(" /at ")[1]);
                        break;

                }

            }


            System.out.println("_______________________________________________________________");
            System.out.println();


            query = sc.nextLine();
        }

        //exit Dukenizer
        printExit();
        sc.close();

    }

    private static String[] readInstruction(List<Task> lst, String query) throws DukeException {
        String[] instruction = query.split(" ", 2);
        switch (instruction[0]) {
            case "list":
                if (instruction.length == 1) {
                    return instruction;
                }
                break;
            case "done":
                if (instruction.length == 1) {
                    throw new DukeException("Please specify item number!");
                }
                if (Integer.parseInt(instruction[1])> lst.size()) {
                    throw new DukeException("There is no such item in the list!");
                }
                break;
            case "todo":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;
            case "event":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
        }
        return instruction;
    }

    private static void printExit() {
        System.out.println("_______________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________________________________");
    }

    private static void printGreeting() {
        String logo =
                " ____        _                    \n"
                        + "|  _ \\ _   _| | _____  ______ ______ ______  ___  _____\n"
                        + "| | | | | | | |/ / _ \\|  __  |__  __|___   |/ _ \\|  _  \\\n"
                        + "| |_| | |_| |   <  __/| |  | |__||__ /   /_<  __/|     /\n"
                        + "|____/ \\__,_|_|\\_\\___|| |  | |______|______|\\___||_|\\__\\ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_______________________________________________________________");
        System.out.println("Hello! I'm Dukenizer\nWhat can I do for you?");
        System.out.println("_______________________________________________________________");
        System.out.println();
    }

    private static void printList(List<Task> lst) {
        for (int i = 0; i < lst.size(); i++) {
            Task targetTask = lst.get(i);
            System.out.println((i + 1) + "." + targetTask.toString());
        }
    }

    private static void addTodo(List<Task> lst, String description) {
        Task todo = new Todo(description);
        lst.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    private static void addDeadline(List<Task> lst, String description, String by) {
        Task deadline = new Deadline(description, by);
        lst.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }


    private static void addEvent(List<Task> lst, String description, String at) {
        Task event = new Event(description, at);
        lst.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    private static void doneItem(List<Task> lst, int itemNumber) {
        Task doneItem = lst.get(itemNumber - 1);
        doneItem.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneItem.toString());
    }


}
