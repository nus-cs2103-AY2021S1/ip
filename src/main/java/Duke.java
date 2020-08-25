import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * main place where the actions take place
 */
public class Duke {
    /**
     * 1. asks for user input with greeting message
     * 2. categorise the user input into different tasks
     * 3. print necessary commands/ outputs based on the task
     * 4. store the inputs in Duke.txt file
     *
     * @param args Unused
     * @throws IOException            if ObjectInputStream unable to read inputs
     * @throws ClassNotFoundException if ObjectInputStream unable to read Object
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TextUi.printHello();
        Scanner sc = new Scanner(System.in);
        String input;
        TaskList taskList = Storage.load();

        while (true) {
            try {
                input = sc.nextLine();
                Command command = Parser.decideCategory(input);
                TextUi.printTaskStatements(input);
                command.execute(taskList);
                Storage.store(taskList);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (DateTimeParseException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}










