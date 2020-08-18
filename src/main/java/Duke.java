import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // all valid commands
    public enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE
    }

    // validate command format
    public static void validateCommandDesc(String desc) throws DukeException {
        if (desc.isEmpty()) {
            throw new DukeException("Command description cannot be empty.");
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
                        int taskNum = Integer.parseInt(value) - 1;
                        System.out.println(tasks.get(taskNum).markAsDone());
                        break;
                    // add toDoTask
                    case TODO:
                        Duke.validateCommandDesc(value);
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
                            splitValue = value.split("/by ");
                            tasks.add(new DeadlineTask(splitValue[0], splitValue[1]));
                        } else {
                            splitValue = value.split("/at ");
                            tasks.add(new EventTask(splitValue[0], splitValue[1]));
                        }
                        System.out.println("----- Received, added the following task:\n" + tasks.get(tasks.size() - 1));
                        System.out.println("You now have " + tasks.size() + " pending tasks.");
                        break;
                }
            }
            // catch error when command is invalid
            catch (IllegalArgumentException ex) {
                System.err.println("----- I can't help you with that request, try something else.");
            }
            // catch invalid command description errors
            catch (DukeException ex) {
                String result = "----- You entered a valid command but I can't carry it out. " + ex.getMessage();
                System.err.println(result);
            }
        }
    }
}
