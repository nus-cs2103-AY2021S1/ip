import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();
        startGreet();
        startChat();
        Task t = new Task("read book");
        t.markAsDone();
        System.out.println(t);
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
                "What can I do for you? \n";
        System.out.println(greeting);
    }

    public static void startChat() {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Listens for input
            System.out.print("Enter command: ");
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
            } else if (input.contains("done")) {
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
                currentTask.markAsDone();
                indent();
                System.out.println("Good job! You completed: ");
                indent();
                indent();
                System.out.println(currentTask);
            } else if (!input.isBlank()) {
                // Create and store task given in list
                list.add(new Task(input));
                indent();
                System.out.print("Successfully added: " + input + "\n\n");
            } else {
                // Returns blank line
                System.out.println();
            }
        }
    }

    public static void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
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
        System.out.println();
    }

    public static void indent() {
        System.out.print("    ");
    }
}
