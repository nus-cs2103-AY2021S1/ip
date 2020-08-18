import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "    ____________________________________________________________";
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> arr =  new ArrayList<Task>();

    private static void markAsDone(String input) {
        String index = input.substring(5, input.length());
        int number = Integer.parseInt(index) - 1;
        arr.get(number).setDone();
        System.out.println(line);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("       %s", arr.get(number).getOutput()));
        System.out.println(line);
    }

    private static void printList() {
        System.out.println(line);
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("     %d. %s", i + 1, arr.get(i).getOutput()));
        }
        System.out.println(line);
    }

    static void numTask() {
        System.out.println(String.format("     Now you have %d tasks in the list.", arr.size()));
    }

    static void addTodo(String input) {
        try {
            ToDo temp = new ToDo(input.substring(5, input.length()));
            arr.add(temp);
            System.out.println(line);
            System.out.println("     Got it. I've added this task:");
            System.out.println("      " + temp.getOutput());
            numTask();
            System.out.println(line);
            System.out.println();
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(line);
        }
    }

    static void addDeadline(String input) {
        try {
            int dash = input.indexOf('/');
            Deadline temp = new Deadline(input.substring(9, dash),
                    input.substring(dash, input.length()));
            arr.add(temp);
            System.out.println(line);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("      " + temp.getOutput());
            numTask();
            System.out.println(line);
            System.out.println();
        } catch(Exception e) {
            System.out.println(line);
            System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(line);
        }
    }

    static void addEvent(String input) {
        try {
            int dash = input.indexOf('/');
            Deadline temp = new Deadline(input.substring(6, dash),
                    input.substring(dash, input.length()));
            arr.add(temp);
            System.out.println(line);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("      " + temp.getOutput());
            numTask();
            System.out.println(line);
            System.out.println();
        } catch(Exception e) {
            System.out.println(line);
            System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
            System.out.println(line);
        }
    }

    private static void addNewTask(String input) {
        if(input.indexOf("todo") == 0) {
            addTodo(input);
        } else if (input.indexOf("deadline") == 0) {
            addDeadline(input);
        } else {
            addEvent(input);
        }
    }

    private static void removeEntry(String input) {
        int temp = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        System.out.println(temp);
        Task removed = arr.remove(temp);
        System.out.println(line);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("     " + removed.getOutput());
         numTask();
        System.out.println(line);
    }

    public static void main(String[] args) {
        boolean on = true;
        System.out.println(line);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(line);

        while(on) {
            try {
                String input = sc.nextLine();
                if (input.compareTo("bye") == 0) {
                    on = false;
                    System.out.println(line);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(line);
                } else if (input.indexOf("done") == 0) {
                    markAsDone(input);
                } else if (input.compareTo("list") == 0) {
                    printList();
                } else if(input.indexOf("delete") == 0) {
                    removeEntry(input);
                } else {
                    addNewTask(input);
                }
            } catch (Exception e) {
                System.out.println(line);
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
    }
}
