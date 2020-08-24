import main.java.Task;
import main.java.TaskDoneException;
import main.java.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for interpreting the input and interacting with the User.
 */
public class Duke {
    /**
     * Print on screen the message wrapped with dotted lines.
     * @param message Message to be printed out.
     */
    public static void echo(String message) {
        String line = "____________________________________________________________\n";
        System.out.println(line + message + "\n" + line);
    }

    /**
     * Print on the screen the new task created and the task count
     * @param task The newly created task to be printed.
     * @param taskCount The new count of the tasks.
     */
    public static void echoNewTask(Task task, int taskCount){
        String first = "Got it. I've added this task:\n";
        String second = "    " + task.toString() + "\n";
        String third = String.format("Now you have %d tasks in the list", taskCount);
        echo(first + second + third);
    }

    /**
     * Take in the String input and split into the 3 parts, namely
     * the command, the title and extra_descriptions.
     * @param input The input from the users.
     * @return a String array that contains different components of the input.
     */
    public static String[] interpretInput(String input) {
        ArrayList<String> list = new ArrayList<>();
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
        File file =  new File("src/data/duke.txt");
        try {
            if (!file.createNewFile()){
                taskManager.readFile(file);
            }
        } catch (IOException err) {
            echo("IOException when trying to check if file exists");
        }

        echo("Duke at your service. How may I help?");
        outerLoop:
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = interpretInput(input);
            String command = words[0];
            switch (command) {

            //Common functions
            case "bye":
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(taskManager.toSaveFormat());
                    fw.close();
                    echo("Bye. See you again, bro!");
                    break outerLoop;
                } catch (IOException err) {
                    System.out.println("Error saving data to the file");
                }
            case "done":
                try {
                    int index = Integer.parseInt(words[1]);
                    taskManager.doTask(index);
                    echo("Nice! I have marked this task as done:\n" +
                            taskManager.getTaskStatus(index));
                } catch (NumberFormatException err) {
                    echo("Error. Please key in an integer after \"done\"");
                } catch (IndexOutOfBoundsException err) {
                    echo("Error. You don't have task #" + words[1] +
                            ".\nKey in \"list\" to find out the tasks on hand");
                } catch (TaskDoneException err) {
                    echo("The task is already done. No need to mark it as done again.");
                }
                break;
            case "list":
                if (taskManager.getTotalTask() == 0) {
                    echo("Currently, you have no tasks on hand");
                } else {
                    echo("Here are the tasks in your list\n" + taskManager.toString());
                }
                break;
            //3 different types of task
            case "event":
                try {
                    Task addedEvent = taskManager.addEvent(words[1], words[2]);
                    echoNewTask(addedEvent, taskManager.getTotalTask());
                } catch (IndexOutOfBoundsException err) {
                    echo("Error: Please key in the date & time as yyyy-mm-dd hh:mm hh:mm" +
                            "(Time in 24 hour format)");
                } catch (DateTimeParseException err) {
                    echo("Error: Please key in the date & time as yyyy-mm-dd hh:mm hh:mm" +
                            "(Time in 24 hour format)");
                }
                break;
            case "todo":
                try {
                    if (words.length > 2) {
                        throw new IllegalArgumentException();
                    }
                    Task addedToDo = taskManager.addToDo(words[1]);
                    echoNewTask(addedToDo, taskManager.getTotalTask());
                } catch (IndexOutOfBoundsException err) {
                    echo("Error: The description for ToDo can't be empty");
                } catch (IllegalArgumentException err) {
                    echo("Error. Don't include / in the title of todo task");
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

            //Delete Task
            case "delete":
                try {
                    int index = Integer.parseInt(words[1]);
                    Task deletedTask = taskManager.deleteTask(index);
                    echo("Nice! I have deleted this task:\n" +
                            deletedTask);
                } catch (NumberFormatException err) {
                    echo("Error. Please key in an integer after \"done\"");
                } catch (IndexOutOfBoundsException err) {
                    echo("Error. You don't have task #" + words[1] +
                            ".\nKey in \"list\" to find out the tasks on hand");
                }
                break;

            //When command does not match any of those above
            default:
                echo("OOPS!!! I don't know what does it mean by: \"" + input + "\"" );
                break;
            }
        }
    }
}