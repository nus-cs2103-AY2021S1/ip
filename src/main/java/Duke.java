import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static void readAndEcho(List<Task> list) {

        //Formatting of greeting
        String intro1 = "Hello! I'm Duke \n";
        String intro2 = "What can I do for you? \n";

        String greeting = addDividers(formatString(intro1) + formatString(intro2));
        System.out.println(greeting);

        //Reading in user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        //Stop when user inputs "bye"
        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");

            //Print list when user inputs "list"
            if (input.equals("list")) {
                printList(list);
            } else if (inputArr[0].equals("done")) { // Mark task as done when user inputs "done"
                String taskNumber = inputArr[1];
                try {
                    markTaskDoneInList(list, Integer.parseInt(taskNumber) - 1);
                } catch (NumberFormatException e) {
                    System.out.println(addDividers(formatString("Please enter out a valid number\n")));
                }
            } else { //Add a new task to the list
                if (inputArr[0].equals("todo")) { //Add a new to-do task
                    String todoName = input.substring(5);
                    ToDo todo = new ToDo(todoName);
                    list.add(todo);
                    String s = formatString("Got it. I've added this task: \n") +
                            formatString(todo.toString() + '\n') +
                            formatString("Now you have " + list.size() + " tasks in the list. \n");
                    System.out.println(addDividers(s));
                } else {
                    list.add(new Task(input));
                    String inputText = "added: " + input + '\n';
                    String echo = addDividers(formatString(inputText));
                    System.out.println(echo);
                }
            }
            input = sc.nextLine();
        }

        //Formatting and printing of goodbye message
        String goodbye = "Bye. Hope to see you again soon! \n";
        System.out.println(addDividers(formatString(goodbye)));
    }


    private static void markTaskDoneInList(List<Task> list, Integer taskNumber) {
        if (taskNumber < 0 || taskNumber > list.size() - 1) {
            System.out.println(addDividers(formatString("Please enter a valid task number\n")));
        } else {
            list.get(taskNumber).markDone();
            String success = formatString("Nice! I've marked this task as done: \n") +
                    formatString(list.get(taskNumber).toString() + "\n");
            System.out.println(addDividers(success));
        }

    }

    private static void printList(List<Task> list) {
        String printedList = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String lineItem = (i + 1) + ". " + list.get(i) + "\n";
                printedList = printedList + formatString(lineItem);
            }
        } else {
            String emptyString = "List is empty \n";
            printedList = formatString(emptyString);
        }
        System.out.println(addDividers(printedList));
    }

    private static String addDividers(String s) {
        String divider = "___________________________\n";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        String added = dividerFormatted + s + dividerFormatted;
        return added;
    }

    private static String formatString(String s) {
        return String.format("%" + (6 + s.length()) + "s", s);
    }

    public static void main(String[] args) {
        ArrayList<Task> arrayList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        readAndEcho(arrayList);
    }
}
