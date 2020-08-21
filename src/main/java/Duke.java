import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    // all valid commands
    public enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE
    }

    // validate command format
    public static void validateCommandDesc(String desc, Command type) throws DukeException {
        // trim whitespaces
        String result = desc.trim();
        if (result.isEmpty()) {
            throw new DukeException("Command description cannot be empty.");
        } 
        if (type == Command.DEADLINE) {
            if (!result.contains("/by") || result.split("/by").length <= 1 || result.split("/by")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Command.EVENT) {
            if (!result.contains("/at") || result.split("/at").length <= 1 || result.split("/at")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
    }

    public static void validateCommandDesc(int taskNum, Command type, List<Task> lst) throws DukeException {
        if (taskNum > lst.size()) {
            throw new DukeException("You have no such task. Please check your task number.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMessage = "----- Serina here, what would you like to do?";
        System.out.println(welcomeMessage); // print welcome message
        List<Task> tasks = new ArrayList<>(); // create list of tasks

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split(" ", 2);

            try {
                Command command = Command.valueOf(splitInput[0].toUpperCase());
                String value;
                value = splitInput.length > 1 ? splitInput[1] : "";

                // exit the application
                if (command == Command.BYE) {
                    System.out.print("----- Goodbye, call me when you need me.");
                    break;
                }

                switch (command) {
                    // list task
                    case LIST:
                        System.out.println("----- Your Tasks: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            int num = i + 1;
                            System.out.println(num + ". " + tasks.get(i));
                        }
                        break;
                    // mark task as done and print it
                    case DONE:
                        validateCommandDesc(Integer.parseInt(value), Command.DONE, tasks);
                        int taskNum = Integer.parseInt(value) - 1;
                        System.out.println(tasks.get(taskNum).markAsDone());
                        break;
                    // add toDoTask
                    case TODO:
                        Duke.validateCommandDesc(value, Command.TODO);
                        tasks.add(new ToDoTask(value));
                        System.out.println("----- Received, added the following task:\n" + tasks.get(tasks.size() - 1));
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                        break;
                    // add deadlineTask
                    // add eventTask
                    case DEADLINE:
                    case EVENT:
                        String[] splitValue;
                        if (command == Command.DEADLINE) {
                            Duke.validateCommandDesc(value, Command.DEADLINE);
                            splitValue = value.split("/by ");
                            tasks.add(new DeadlineTask(splitValue[0], splitValue[1]));
                        } else {
                            Duke.validateCommandDesc(value, Command.EVENT);
                            splitValue = value.split("/at ");
                            tasks.add(new EventTask(splitValue[0], splitValue[1]));
                        }
                        System.out.println("----- Received, added the following task:\n" + tasks.get(tasks.size() - 1));
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                        break;
                    // delete task
                    case DELETE:
                        validateCommandDesc(Integer.parseInt(value), Command.DELETE, tasks);
                        int taskIndex = Integer.parseInt(value) - 1;
                        Task removedTask = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        System.out.println("----- Received, deleted the following task:\n" + removedTask);
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                }
            }
            // catch error when command is invalid
            catch (IllegalArgumentException ex) {
                System.out.println("----- I can't help you with that request, try something else.");
            }
            // catch invalid command description errors
            catch (DukeException ex) {
                String result = "----- You entered a valid command but I can't carry it out. " + ex.getMessage();
                System.out.println(result);
            }
        }
    }
}
