import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
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










