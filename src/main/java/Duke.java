import main.java.Task;
import main.java.TaskDoneException;
import main.java.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Print on screen the message wrapped with dotted lines.
     * @param message Message to be printed out
     */
    public static void echo(String message) {
        String line = "____________________________________________________________\n";
        System.out.println(line + message + "\n" + line);
    }

    public static void echoNewTask(Task task, int taskCount){
        String first = String.format("Got it. I've added this task:\n");
        String second = "    " + task.toString() + "\n";
        String third = String.format("Now you have %d tasks in the list", taskCount);
        echo(first + second + third);
    }

    public static String[] interpretInput(String input) {
        ArrayList<String> list = new ArrayList<String>();
        int spaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/");
        int infoIndex = input.indexOf(" ", slashIndex);
        if (spaceIndex == -1) {
            list.add(input);
        } else if (slashIndex == -1) {
            list.add(input.substring(0,spaceIndex));
            list.add(input.substring(spaceIndex+1));
        } else {
            list.add(input.substring(0,spaceIndex));
            list.add(input.substring(spaceIndex+1,slashIndex));
            list.add(input.substring(infoIndex+1));
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        echo("Duke at your service. How may I help?");
        outerLoop:
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = interpretInput(input);
            String command = words[0];
            switch (command) {
                case "bye" :
                    echo("Bye. See you again, bro!");
                    break outerLoop;
                case "list":
                    echo("Here are the tasks in your list\n" + taskManager.toString());
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(words[1]);
                        taskManager.doTask(index);
                        String returnMessage = "Nice! I've marked this task as done:\n";
                        returnMessage += taskManager.getTaskStatus(index);
                        echo(returnMessage);
                    } catch (NumberFormatException err){
                        echo("Error. Please key in an integer after \"done\"");
                    } catch (IndexOutOfBoundsException err) {
                        echo("Error. You don't have task #" + words[1] +
                                ".\nKey in \"list\" to find out the tasks on hand");
                    } catch (TaskDoneException err) {
                        echo("The task is already done. No need to mark it as done again.");
                    }
                    break;
                case "deadline":
                    try {
                        Task addedDeadline = taskManager.addDeadLine(words[1], words[2]);
                        echoNewTask(addedDeadline, taskManager.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        echo("Error: The description for deadline can't be empty");
                    }
                    break;
                case "event":
                    try {
                        Task addedEvent = taskManager.addEvent(words[1], words[2]);
                        echoNewTask(addedEvent, taskManager.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        echo("Error: The description for Event can't be empty");
                    }
                    break;
                case "todo":
                    try {
                        Task addedToDo = taskManager.addToDo(words[1]);
                        echoNewTask(addedToDo, taskManager.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        echo("Error: The description for ToDo can't be empty");
                    }
                    break;
                default:
                    echo("OOPS!!! I don't know what does it mean by: \"" + input + "\"" );
                    break;
            }
        }
    }
}