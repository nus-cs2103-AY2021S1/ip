/**
 * Duke is a chatbot that takes in tasks, which includes todos, deadlines and events, and compiles them
 * in a list. Tasks can be removed from the list and be marked as completed.
 *
 * @author Dominic Siew Zhen Yu
 *
 */

import java.util.Scanner;

public class Duke {

    public enum Tasks {
        DEADLINE,
        EVENT,
        TODO
    }

    public static void main(String[] args) {
        String logo = " ____        _lis\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");

        boolean isEnded = false;
        List taskList = new List();

        Scanner sc= new Scanner(System.in);

        while(!isEnded) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");
            String[] parsedInput = userInput.split(" ", 2);

            if (parsedInput[0].equals("todo")) {
                if (parsedInput.length == 1 ) {
                    throw new DukeException("empty");
                }
                taskList.addTodo(parsedInput[1]);

            } else if (parsedInput[0].equals("deadline")) {
                String[] furtherParsed = parsedInput[1].split(" /by ", 2);
                taskList.addDeadline(furtherParsed[0], furtherParsed[1]);
            } else if (parsedInput[0].equals("event")){
                String[] furtherParsed = parsedInput[1].split(" /at ",2);
                taskList.addEvent(furtherParsed[0],furtherParsed[1]);
            } else if(userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                isEnded = true;
            } else if (userInput.equals("list")){
                taskList.printList();
            } else if (parsedInput[0].equals("done")) {
                int itemRank = Integer.parseInt(parsedInput[1]);
                taskList.updateTaskStatus(itemRank);
            } else if (parsedInput[0].equals("remove")){
                int itemRank = Integer.parseInt(parsedInput[1]);
                taskList.removeTask(itemRank - 1 );
            } else {
                System.out.println("Please give an appropriate response.");
                throw new DukeException("generic");
            }
            System.out.println("____________________________________________________________");
        }


    }
}
