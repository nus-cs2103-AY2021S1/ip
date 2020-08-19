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

            switch (command) {
                case "bye":
                    Print.formatPrint("Bye! Hope to see you again soon! ");
                    return;
                case "list":
                    Task.printList();
                    break;
                case "done":
                    Task.markTaskAsDone(Integer.parseInt(inputs[1]));
                    break;
                case "todo":
                    Task.addTask(new ToDo(inputs[1]));
                    break;
                case "deadline":
                    String[] deadlineDetails = inputs[1].split(" /by ", 2);
                    Task.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                    break;
                case "event":
                    String[] eventDetails = inputs[1].split(" /at ", 2);
                    Task.addTask(new Event(eventDetails[0], eventDetails[1]));
                    break;
                default:
                    break;
            }
        }

        sc.close();
    }
}
