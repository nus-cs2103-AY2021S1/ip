import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Duke {

    private static void response() {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        boolean exit = false;

        while (!exit) {
            if (line.isEmpty()) {
                line = scanner.nextLine();
                continue;
            }
            Command command = new Command(line);
            if (command.isExit()) {
                Ui.exit();
                exit = true;
            }
            else {
                command.execute();
                line = scanner.nextLine();
            }
        }
    }

    public static void main(String[] args){
        response();
    }
}
