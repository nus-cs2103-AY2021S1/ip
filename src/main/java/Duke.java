import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello I'm Duke, your favourite chatbot! \n"
                + "   What can I do for you? ";
        Print.formatPrint(greeting);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip().toLowerCase();
            String[] inputs = input.split("\\s");
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
                default:
                    Task.addTask(input);
                    break;
            }
        }

        sc.close();
    }
}
