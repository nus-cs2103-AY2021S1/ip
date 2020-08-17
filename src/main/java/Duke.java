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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        echo("    Hello! I'm Duke\n    How can I help you?");
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split("\\s+");
            String command = words[0];
            switch (command) {
                case "bye" :
                    echo("Bye. Hope to see you again, bro!");
                    break;
                case "list":
                    echo(taskManager.toString());
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
                        echo("Error. You don't have task # " + words[1]);
                    }
                    break;
                default:
                    taskManager.addTask(input);
                    echo("added: " + input);
                    break;
            }
        }
    }
}