package Duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 * The Best2013TBot program that implements a chatbot.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Duke {

    private static String indent = "   ";
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static String line = indent + "----------------------------";

    /**
     * This method greets the user.
     */
    public static void greet() {
        System.out.println(line);
        System.out.println(indent + "Hello! I'm Best2103/TBot\n" + indent + "What can I do for you?");
        System.out.println(line);
    }

    /**
     * This method adds todoTask for the bot.
     *
     * @param taskName name of the task
     * @return taskName name of the task
     */
    public String addTodo(String taskName) {
        System.out.println(indent + "Got it. I've added this task:");
        taskList.add(new Todo(taskName));
        System.out.println(indent + "  " + taskList.get(taskList.size() - 1));
        displayNoOfTasks();
        return taskName;
    }

    /**
     * This method adds deadlineTask for the bot.
     *
     * @param input string to be cleaned and parsed
     * @return boolean true or false depending on the success
     */
    public boolean addDeadline(String input) {
        input.replace("deadline", "").trim();
        String[] splittedInput = input.split("/");
        System.out.println(indent + "Got it. I've added this task:");
        taskList.add(new Deadline(splittedInput[0], splittedInput[1].replace("by", "").trim()));
        System.out.println(indent + "  " + taskList.get(taskList.size()-1));
        displayNoOfTasks();
        return true;
    }

    /**
     * This method adds eventTask for the bot.
     *
     * @param input string to be cleaned and parsed
     * @return boolean true or false depending on the success
     */
    public boolean addEvent(String input) {
        input.replace("event", "").trim();
        String[] splittedInput = input.split("/");
        System.out.println(indent + "Got it. I've added this task:");
        taskList.add(new Event(splittedInput[0], splittedInput[1].replace("at", "").trim()));
        System.out.println(indent + "  " + taskList.get(taskList.size()-1));
        displayNoOfTasks();
        return true;
    }

    public void displayNoOfTasks() {
        System.out.println(
                indent + "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * This method adds task for the bot.
     *
     * @param taskName name of the task
     * @return taskName name of the task
     */
    public String addTask(String taskName) {
        taskList.add(new Task(taskName));
        System.out.println(indent + "Added: " + taskName);
        return taskName;
    }

    /**
     * This method displays the task list.
     */
    public void displayList() {
        if (taskList.size() == 0) {
            System.out.println("There's nothing in your list");
            return ;
        }
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(indent + (i+1) + "." + taskList.get(i));
        }
    }

    /**
     * This method does verification for description.
     * @param type Type of task
     * @throws DukeException Customized exception
     */
    public void verificationDescription(String type) throws DukeException {
        throw new DukeException("The description of a " + type + " task cannot be empty.");
    }


    /**
     * This method responds to the user's input.
     * @throws DukeException Customized exception
     */
    public void respond() throws DukeException {
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNext()) {
            try {
                String input = userInput.nextLine();
                System.out.println(line);
                // Split the string
                String[] inputList = input.split(" ");
                // Use switch case

                // If input does not contain any of the actions
                if (!(input.contains("bye") || input.contains("done") || input.contains("todo")
                        || input.contains("deadline") || input.contains("list") || input.contains("event") || input.contains("delete"))) {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

                if (inputList[0].equals("bye")) {
                    System.out.println(indent + "Bye. Hope to see you again soon!");
                    System.out.println(line);
                    continue;
                }
                if (inputList[0].equals("done")) {
                    int number = Integer.parseInt(inputList[1]) - 1;
                    if (number >= 0 && number < taskList.size()) {
                        System.out.println(indent+"Nice! I've marked this task as done:");
                        taskList.get(number).markAsDone();
                        System.out.println(line);
                    }
                    continue;
                }

                if (inputList[0].equals("delete")) {
                    int number = Integer.parseInt(inputList[1]) - 1;
                    if (number >= 0 && number < taskList.size()) {
                        System.out.println(indent+"Noted. I've removed this task:");
                        System.out.println(indent+taskList.get(number));
                        taskList.remove(number);
                        displayNoOfTasks();
                    }
                    continue;
                }
                if (inputList[0].equals("list")) {
                    displayList();
                    System.out.println(line);
                    continue;
                }

                // Verification for description
                if (inputList.length < 2) {
                    verificationDescription(inputList[0]);
                }

                if (inputList[0].equals("todo")) {
                    addTodo(input.replace("todo", "").trim());
                    System.out.println(line);
                    continue;
                }

                if (inputList[0].equals("deadline")) {
                    // Check whether the string is correct
                    if (!Pattern.matches("^(deadline)+\\s+a*([a-z])+.+(\\/by)+\\s.*", input)) {
                        throw new DukeException("Incorrect format");
                    }
                    addDeadline(input);
                    System.out.println(line);
                    continue;
                }

                if (inputList[0].equals("event")) {
                    // Check whether the string is correct
                    if (!Pattern.matches("^(event)+\\s+a*([a-z])+.+(\\/at)+\\s.*", input)) {
                        throw new DukeException("Incorrect format");
                    }
                    addEvent(input);
                    System.out.println(line);
                    continue;
                }

                throw new DukeException("I don't know what to do :-(");
            } catch (Exception m) {
                System.out.println(indent + m);
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.greet();
        duke.respond();
    }
}
