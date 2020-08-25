import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public String line = "____________________________________________________________";
    private Parser parser;
    private Scanner scanner;

    public Ui() {
        parser = new Parser();
        scanner = new Scanner(System.in);
    }

    public void interact() throws IOException, IncorrectInputException {
        while (scanner.hasNext()) {
            parser.parse(scanner.nextLine());
        }
        scanner.close();
    }

    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

//    public void exit() {
//        System.out.println(line);
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println(line);
//    }
}
