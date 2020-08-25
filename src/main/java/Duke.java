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

            //execute instruction & checks for exception
            try {
                parseInstruction(lst, query);
            } catch (DukeException e) {
                System.out.println(e);
                System.out.println("_______________________________________________________________");
                System.out.println();
                query = sc.nextLine();
                continue;
            }


            System.out.println("_______________________________________________________________");
            System.out.println();


            query = sc.nextLine();
        }

        //exit Dukenizer
        printExit();
        sc.close();

    }

    private static void parseInstruction(List<Task> lst, String query) throws DukeException {

        //take away leading and trailing white spaces
        String[] instruction = query.strip().split(" ", 2);

        switch (instruction[0]) {
            case "list":
                if (instruction.length == 1) {
                    printList(lst);
                } else {
                    throw new DukeException("Extra inputs detected! Please only input 'list'.");
                }
                break;

            case "delete":
                //done with no other arguments
                if (instruction.length == 1) {
                    throw new DukeException("Please specify item number!");
                }
                //done with exactly 2 inputs
                else if (instruction.length == 2) {

                    //check if second argument is integer
                    try {
                        if ((Integer.parseInt(instruction[1]) < 1) || (Integer.parseInt(instruction[1]) > lst.size())) {
                            throw new DukeException("Please enter a valid item number from the list!");
                        }
                    }

                    //second argument wrong format
                    catch (NumberFormatException e) {
                        throw new DukeException("Please only input 'delete <item number>' with no other inputs!");
                    }

                    int itemNum = Integer.parseInt(instruction[1]);
                    deleteItem(lst, itemNum);
                }
                break;

            case "done":

                //done with no other arguments
                if (instruction.length == 1) {
                    throw new DukeException("Please specify item number!");
                }

                //done with exactly 2 inputs
                else if (instruction.length == 2) {

                    //check if second argument is integer
                    try {
                        if ((Integer.parseInt(instruction[1]) < 1) || (Integer.parseInt(instruction[1]) > lst.size())) {
                            throw new DukeException("Please enter a valid item number from the list!");
                        }
                    }
                    //second argument wrong format
                    catch (NumberFormatException e) {
                        throw new DukeException("Please only input 'done <item number>' with no other inputs!");
                    }
                    int doneIndex = Integer.parseInt(instruction[1]);
                    doneItem(lst, doneIndex);
                }
                break;

            case "todo":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTodo(lst, instruction[1]);
                }
                break;

            case "deadline":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else {

                    //check if contains deadline " /by yyyy-mm-dd"
                    if ((instruction[1].contains(" /by ")) &&
                            (instruction[1].split(" /by ")[1].matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
                        addDeadline(lst, instruction[1].split(" /by ")[0], instruction[1].split(" /by ")[1]);

                    } else {
                        throw new DukeException("Please input in the following format 'deadline <description> /by <yyyy-MM-dd HH:mm>' with a valid date & time");
                    }
                }
                break;


            case "event":
                if (instruction.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else {
                    //check if contains event " /at yyyy-mm-dd"
                    if ((instruction[1].contains(" /at ")) &&
                            (instruction[1].split(" /at ")[1].matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
                        addEvent(lst, instruction[1].split(" /at ")[0], instruction[1].split(" /at ")[1]);
                    } else {
                        throw new DukeException("Please input in the following format 'event <description> /at <yyyy-MM-dd HH:mm>' with a valid date & time");
                    }
                }
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

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

    private static void deleteItem(List<Task> lst, int itemNumber) {
        Task item = lst.get(itemNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(item.toString());
        lst.remove(item);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }


}
