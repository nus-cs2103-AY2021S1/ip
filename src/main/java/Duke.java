import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello I'm Duke, your favourite chatbot! \n"
                + "What can I do for you? ";
        Print.formatPrint(greeting);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip().toLowerCase();
            String[] inputs = input.split("\\s", 2);
            String command = inputs[0];

            try {
                switch (command) {
                    case "bye":
                        Print.formatPrint("Bye! Hope to see you again soon! ");
                        return;
                    case "list":
                        Task.printList();
                        break;
                    case "done":
                        if (inputs.length <= 1) {
                            throw new DukeException("OOPS! Task number cannot be empty!");
                        }
                        try {
                            Task.markTaskAsDone(Integer.parseInt(inputs[1]));
                        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                            throw new DukeException("OOPS! Task number format is invalid!");
                        }
                        break;
                    case "todo":
                        if (inputs.length <= 1) {
                            throw new DukeException("OOPS! The description of a todo cannot be empty!");
                        }
                        Task.addTask(new ToDo(inputs[1]));
                        break;
                    case "deadline":
                        if (inputs.length <= 1) {
                            throw new DukeException("OOPS! The description of a deadline cannot be empty!");
                        }
                        String[] deadlineDetails = inputs[1].split(" /by ", 2);
                        if (deadlineDetails.length <= 1) {
                            throw new DukeException("OOPS! The date of a deadline cannot be empty!");
                        }
                        Task.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                        break;
                    case "event":
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
}
