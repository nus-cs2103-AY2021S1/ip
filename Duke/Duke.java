package Duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

/**
 * The Best2013TBot program that implements a chatbot. (Week 3)
 *
 * @author Zeng Yu Ting
 * @version 2.0
 * @since 2020-15-08
 */
public class Duke {

    private static String indent = "   ";
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static String LINE_INDENTATION = indent + "----------------------------";
    private static String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/data";
    private static String TEXT_FILE_NAME = "/duke.txt";



    /**
     * This method greets the user.
     */
    public static void greet() {
        System.out.println(LINE_INDENTATION);
        System.out.println(indent + "Hello! I'm Best2103/TBot\n" + indent + "What can I do for you?");
        System.out.println(LINE_INDENTATION);
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
     * This method read the current existing text file and process it into a todolist.
     */
    public void readFiles() {
        try {
            FileInputStream inputStream = new FileInputStream(STORAGE_DIRECTORY + TEXT_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String input = "";
            while ((input = reader.readLine()) != null) {
                try {
                    // Split the string
                    String[] inputList = input.split("\\|");
                    // If  does not matches input throw exceptions
                    if (!(Pattern.matches("(T)+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+", input.trim())
                            | Pattern.matches("([DE])+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+\\s+([|])+\\s+\\w+.+", input.trim()))) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    if (inputList[0].trim().equals("D")) {
                        taskList.add(new Deadline(inputList[2].trim(),inputList[3].trim()));
                    }

                    if (inputList[0].trim().equals("T")) {
                        taskList.add(new Todo(inputList[2].trim()));
                    }

                    if (inputList[0].trim().equals("E")) {
                        taskList.add(new Event(inputList[2].trim(),inputList[3].trim()));
                    }

                    if (inputList[1].trim().equals("1")) {
                        taskList.get(taskList.size()-1).markAsDoneWithoutPrint();
                    }
                } catch (Exception m) {
                    System.out.println(indent + m);
                    System.out.println(LINE_INDENTATION);
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("There's no duke text");
        }
    }

    /**
     * This method writes out the existing todolist to a text file.
     */
    public void writeFiles() {
        try {
            FileWriter writer = new FileWriter(STORAGE_DIRECTORY + TEXT_FILE_NAME, false);
            for (int i = 0; i < taskList.size(); i++){
                writer.write(taskList.get(i).toWriteString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                System.out.println(LINE_INDENTATION);

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
                    System.out.println(LINE_INDENTATION);
                    break;
                }
                if (inputList[0].equals("done")) {
                    int number = Integer.parseInt(inputList[1]) - 1;
                    if (number >= 0 && number < taskList.size()) {
                        System.out.println(indent+"Nice! I've marked this task as done:");
                        taskList.get(number).markAsDone();
                        System.out.println(LINE_INDENTATION);
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
                    System.out.println(LINE_INDENTATION);
                    continue;
                }

                // Verification for description
                if (inputList.length < 2) {
                    verificationDescription(inputList[0]);
                }

                if (inputList[0].equals("todo")) {
                    addTodo(input.replace("todo", "").trim());
                    System.out.println(LINE_INDENTATION);
                    continue;
                }

                if (inputList[0].equals("deadline")) {
                    // Check whether the string is correct
                    if (!Pattern.matches("(deadline)+\\s+([a-z])\\w+\\s+.+(\\/by)+\\s+.+", input)) {
                        throw new DukeException("Incorrect format");
                    }
                    addDeadline(input);
                    System.out.println(LINE_INDENTATION);
                    continue;
                }

                if (inputList[0].equals("event")) {
                    // Check whether the string is correct
                    if (!Pattern.matches("(event)+\\s+([a-z])\\w+\\s+.+(\\/at)+\\s+.+", input)) {
                        throw new DukeException("Incorrect format");
                    }
                    addEvent(input);
                    System.out.println(LINE_INDENTATION);
                    continue;
                }

                throw new DukeException("I don't know what to do :-(");
            } catch (Exception m) {
                System.out.println(indent + m);
                System.out.println(LINE_INDENTATION);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();

        // Retrieve existing file and parse it
        duke.readFiles();
        duke.greet();
        duke.respond();

        // Write out the file
        duke.writeFiles();
    }
}
