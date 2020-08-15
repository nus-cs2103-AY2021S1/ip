import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();
        startGreet();
        startChat();
    }
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void startGreet() {
        String greeting = "Hello! I'm Duke the chatbot! \n" +
                "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void startChat() {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Listens for input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Encounters exit command
                System.out.println();
                indent();
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // Prints the given list
                printList(list);
            } else if (input.startsWith("done")) {
                // Mark given task as done
                if (!input.contains(" "))  {
                    // checking if done command is given correctly else asks the user again
                    System.out.println("pls input done followed by valid task number");
                    continue;
                }
                // Splits the input command by space
                String[] stringArr = input.split(" ");
                if (stringArr.length != 2) {
                    // Checking if done command is given correctly else asks the user again
                    indent();
                    System.out.println("pls input done followed by valid task number");
                    continue;
                }
                try {
                    // Checking if valid integer given using parseInt() method
                    Integer.parseInt(stringArr[1]);
                    Task dummyTask = list.get(Integer.parseInt(stringArr[1]) - 1);
                } catch (NumberFormatException e) {
                    indent();
                    System.out.println('"' + stringArr[1] + '"' +" is not a valid integer number");
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    indent();
                    System.out.println("pls input done followed by valid task num");
                    continue;
                }
                // Finding and marking the actual task as done
                int indexOfDone =  Integer.parseInt(stringArr[1]) - 1;
                Task currentTask = list.get(indexOfDone);
                if (currentTask.isDone) {
                    continue;
                }
                currentTask.markAsDone();
                indent();
                System.out.println("Good job! You completed:");
                indent();
                indent();
                System.out.println(currentTask);
            } else if (input.startsWith("todo")) {
                // Create and store todos given in list
                String taskDesc = input.substring(5);
                Task newTodo = new Todo(taskDesc);
                list.add(newTodo);
                printAddSuccess(list.size(), newTodo);
            } else if (input.startsWith("deadline")) {
                // Create and store deadlines given in list
                String taskDesc = input.substring(9, input.indexOf("/by"));
                String by = input.substring(input.indexOf("/by") + 4);
                Task newDeadline = new Deadline(taskDesc, by);
                list.add(newDeadline);
                printAddSuccess(list.size(), newDeadline);
            } else if (input.startsWith("event")) {
                // Create and store events given in list
                String taskDesc = input.substring(6, input.indexOf("/at"));
                String at = input.substring(input.indexOf("/at") + 4);
                Task newEvent = new Event(taskDesc, at);
                list.add(newEvent);
                printAddSuccess(list.size(), newEvent);
            } else {
                // Returns blank line
                System.out.println();
            }
        }
    }

    public static void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            // Asks user for tasks when printing empty list
            indent();
            System.out.println("Empty list, pls add tasks to list first");
            return;
        }
        int listPos = 1;
        indent();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++,listPos++) {
            indent();
            indent();
            System.out.println(listPos + ". " + list.get(i));
        }
        indent();
        System.out.println("You have " + list.size() + " task(s) in the list");
        System.out.println();
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void printAddSuccess(int listSize, Task task) {
        // Prints success message after an item is added to list
        indent();
        System.out.print("Successfully added:\n");
        indent();
        indent();
        System.out.println(task);
        indent();
        System.out.println("You have " + listSize + " task(s) in the list");
        System.out.println();
    }
}
