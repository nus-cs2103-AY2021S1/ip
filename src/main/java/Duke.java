import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello I'm Duke, your favourite chatbot! \n"
                + "What can I do for you? \n\n"
                + "Type 'help' to see the list of command I support. ";
        String supportedCommands = "I support these commands: \n"
                + "todo: \n"
                + "    add a todo item with a description. \n    format: todo {description} \n"
                + "deadline: \n"
                + "    add a deadline with a description and date. \n"
                + "    format: deadline {description} /by {yyyy-mm-dd} \n"
                + "event: \n"
                + "    add an event with a description, date, start time and end time. \n"
                + "    format: event {description} /at {yyyy-mm-dd} {hh-mm} {hh-mm}\n"
                + "done: \n"
                + "    mark an item as done. \n    format: done {taskNumber} \n"
                + "delete: \n"
                + "    delete an item. \n    format: delete {taskNumber} \n"
                + "list: \n"
                + "    see all the tasks you have now. \n"
                + "bye: \n"
                + "    say goodbye to me :<";
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
                    case HELP:
                        Print.formatPrint(supportedCommands);
                        break;
                    case LIST:
                        Task.printList();
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
            case "help":
                return CommandType.HELP;
            default:
                throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }
}
