import main.java.Task;
import main.java.TaskManager;

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
        String[] result = {"","",""};
        String[] words = input.split("\\s+");
        result[0] = words[0];
        if (words.length > 1) {
            int index = 1;
            for (int i = 1; i < words.length; i++){
                String current = words[i];
                Character cha = current.charAt(0);
                if (current.charAt(0) == '/' && index < 2){
                    index++;
                } else {
                    result[index] += result[index] == "" ? current : " " + current;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        echo("Duke at your service. How may I help?");
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = interpretInput(input);
            String command = words[0];
            switch (command) {
                case "bye" :
                    echo("Bye. Hope to see you again, bro!");
                    break;
                case "list":
                    echo("Here are the tasks in your list\n" + taskManager.toString());
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(words[1]);
                        taskManager.doTask(index);
                        String returnMessage = "Nice! I've marked this task as done: \n";
                        returnMessage += taskManager.getTaskStatus(index);
                        echo(returnMessage);
                    } catch (NumberFormatException err){
                        echo("Error. Please key in an integer after \"done\"");
                    } catch (IndexOutOfBoundsException err) {
                        echo("Error. You don't have task # " + words[1] +
                                "Key in \"list\" to find out the tasks");
                    }
                    break;
                case "deadline":
                    Task addedDeadline = taskManager.addDeadLine(words[1], words[2]);
                    echoNewTask(addedDeadline, taskManager.getTotalTask());
                    break;
                case "event":
                    Task addedEvent = taskManager.addEvent(words[1], words[2]);
                    echoNewTask(addedEvent, taskManager.getTotalTask());
                    break;
                case "todo":
                    Task addedToDo = taskManager.addToDo(words[1]);
                    echoNewTask(addedToDo, taskManager.getTotalTask());
                    break;
                default:
                    echo(input);
                    break;
            }
        }
    }
}