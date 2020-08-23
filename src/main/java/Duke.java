import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Task> arr =  new ArrayList<Task>();

    private static void markAsDone(String input) {
        String index = input.substring(5, input.length());
        int number = Integer.parseInt(index) - 1;
        arr.get(number).setDone();
        System.out.println(LINE);
        System.out.println(INDENT + "Task marked as done: ");
        System.out.println(String.format(INDENT + "%s", arr.get(number).getOutput()));
        System.out.println(LINE);
    }

    private static void printList() {
        System.out.println(LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("%s%d. %s",INDENT,  i + 1, arr.get(i).getOutput()));
        }
        System.out.println(LINE);
    }

    static void numTask() {
        System.out.println(String.format("%sNow you have %d tasks in the list.", INDENT, arr.size()));
    }

    static void addTodo(String input) {
        try {
            ToDo temp = new ToDo(input.substring(5, input.length()));
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Todo added: ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch (Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + " The description of a todo cannot be empty. Try again.");
            System.out.println(LINE);
        }
    }

    static void addDeadline(String input) {
        try {
            int dash = input.indexOf('/') + 1;
            String dateString = input.substring(dash, input.length());
            System.out.println(dateString);
            Deadline temp = new Deadline(input.substring(9, dash),
                    dateString);
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Deadline added:  ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch(Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + "The description of a deadline cannot be empty. Try again.");
            System.out.println(LINE);
        }
    }

    static void addEvent(String input) {
        try {
            int dash = input.indexOf('/') + 1;
            String date_String = input.substring(dash, input.length());
            Event temp = new Event(input.substring(6, dash), date_String
                    );
            arr.add(temp);
            System.out.println(LINE);
            System.out.println(INDENT + "Event added:  ");
            System.out.println(INDENT + temp.getOutput());
            numTask();
            System.out.println(LINE);
            System.out.println();
        } catch(Exception e) {
            System.out.println(LINE);
            System.out.println(INDENT + "The description of a event cannot be empty. Try again.");
            System.out.println(LINE);
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

    private static void removeEntry(String input)  {
        int temp = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        System.out.println(temp);
        Task removed = arr.remove(temp);
        System.out.println(LINE);
        System.out.println(INDENT + "Tasked removed: ");
        System.out.println(INDENT + removed.getOutput());
        numTask();
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        boolean on = true;
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! What can I do for you?");
        System.out.println(LINE);
        while(on) {
            try {
                String input = sc.nextLine();
                if (input.compareTo("bye") == 0) {
                    on = false;
                    System.out.println(LINE);
                    System.out.println(INDENT + "Bye. Hope to see you again soon!");
                    System.out.println(LINE);
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
                System.out.println(LINE);
                System.out.println(INDENT + "I'm sorry, but I don't know what that means");
                System.out.println(LINE);
            }
        }
    }
}
