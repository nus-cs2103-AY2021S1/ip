import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> lists = new ArrayList<>();

    public static void main(String[] args) {

        greeting();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while(true) {
            if(input.equals("bye")){
                break;
            } else {
                try{
                    executeCommand(input);
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = scanner.nextLine();
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void executeCommand(String command) throws DukeException {
        if(command.equals("list")) {
            displayList();
        } else if(command.length() >= 6 && command.substring(0, 5).equals("done ")) {
            System.out.println("Nice! I've marked this task as done:");
            int num = Integer.parseInt(command.split(" ")[1]);
            lists.get(num - 1).markAsDone();
            System.out.println(lists.get(num - 1));

        } else if(command.length() >= 8 && command.substring(0, 7).equals("delete ")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            System.out.println(" Noted. I've removed this task: ");
            System.out.println(lists.get(num - 1));
            lists.remove(num - 1);
            System.out.println("Now you have " + lists.size() + " tasks in the list.");
        } else if(command.split(" ")[0].equals("todo")) {
            if(command.length() < 5 || command.substring(5, command.length()).isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                System.out.println("Got it. I've added this task:");
                Task task = new ToDo(command.substring(5, command.length()));
                lists.add(task);
                System.out.println(task);
                System.out.println("Now you have " + lists.size() + " tasks in the list.");
            }

        } else if(command.split(" ")[0].equals("deadline")) {
            if(command.length() < 9 || command.substring(9, command.length()).isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                System.out.println("Got it. I've added this task:");
                String[] parts = command.substring(9, command.length()).split("/by");
                Task task = new Deadline(parts[0], parts[1]);
                lists.add(task);
                System.out.println(task);
                System.out.println("Now you have " + lists.size() + " tasks in the list.");
            }

        } else if(command.split(" ")[0].equals("event")) {
            if(command.length() < 6 || command.substring(6, command.length()).isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else {
                System.out.println("Got it. I've added this task:");
                String[] parts = command.substring(5, command.length()).split("/at");
                Task task = new Event(parts[0], parts[1]);
                lists.add(task);
                System.out.println(task);
                System.out.println("Now you have " + lists.size() + " tasks in the list.");
            }
        } else {
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }


    }

    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lists.size(); i++ ) {
            System.out.println(i+1 + "." + lists.get(i).toString());
        }
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }

}
