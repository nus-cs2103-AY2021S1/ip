import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        try {
            readListFromFile();
        } catch (DukeException e) {
            Print.formatPrint(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        String greeting = "Hello I'm Duke, your favourite chatbot! \n\n"
                + Task.printList()
                + "\n\n"
                + "What can I do for you? ";
        Print.formatPrint(greeting);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip();
            String[] inputs = input.split("\\s", 2);

            try {
                CommandType command = convertInputToEnum(inputs[0].toLowerCase());
                switch (command) {
                case BYE:
                    Print.formatPrint("Bye! Hope to see you again soon! ");
                    return;
                case LIST:
                    Print.formatPrint(Task.printList());
                    break;
                case DONE:
                    if (inputs.length <= 1) {
                        throw new DukeException("OOPS! Task number cannot be empty for done action!");
                    }
                    try {
                        Task.markTaskAsDone(Integer.parseInt(inputs[1]));
                    } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                        throw new DukeException("OOPS! Task number is invalid!");
                    }
                    break;
                case DELETE:
                    if (inputs.length <= 1) {
                        throw new DukeException("OOPS! Task number cannot be empty for delete action!");
                    }
                    try {
                        Task.deleteTask(Integer.parseInt(inputs[1]));
                    } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                        throw new DukeException("OOPS! Task number is invalid!");
                    }
                    break;
                case TODO:
                    if (inputs.length <= 1) {
                        throw new DukeException("OOPS! The description of a todo cannot be empty!");
                    }
                    Task.addTask(new ToDo(inputs[1]));
                    break;
                case DEADLINE:
                    if (inputs.length <= 1) {
                        throw new DukeException("OOPS! The description of a deadline cannot be empty!");
                    }
                    String[] deadlineDetails = inputs[1].split(" /by ", 2);
                    if (deadlineDetails.length <= 1) {
                        throw new DukeException("OOPS! The date of a deadline cannot be empty!");
                    }
                    Task.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                    break;
                case EVENT:
                    if (inputs.length <= 1) {
                        throw new DukeException("OOPS! The description of an event cannot be empty!");
                    }
                    String[] eventDetails = inputs[1].split(" /at ", 2);
                    if (eventDetails.length <= 1) {
                        throw new DukeException("OOPS! The time of an event cannot be empty!");
                    }
                    Task.addTask(new Event(eventDetails[0], eventDetails[1]));
                    break;
                default:
                    throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Print.formatPrint(e.getMessage());
            }
        }

        sc.close();
    }

    private static CommandType convertInputToEnum(String input) throws DukeException {
        switch (input) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "done":
            return CommandType.DONE;
        case "delete":
            return CommandType.DELETE;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void readListFromFile() throws DukeException {
        try {

            FileReader fr = new FileReader("data/TaskList.txt");
            BufferedReader br = new BufferedReader(fr);

            String task;
            List<String> tasks = new ArrayList<>();
            while ((task = br.readLine()) != null) {
                tasks.add(task.strip());
            }
            br.close();
            fr.close();

            Task.generateTaskList(tasks);

        } catch (FileNotFoundException e) {
            new File("data/").mkdirs();
            try {
                new File("data/TaskList.txt").createNewFile();
            } catch (IOException ex) {
                throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
            }
        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }
}
