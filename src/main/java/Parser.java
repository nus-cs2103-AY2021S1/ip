import java.util.Scanner;

public class Parser {


    public Command parse(String input) throws IllegalArgumentException{
        Scanner sc = new Scanner(input);
        String command = sc.next().toLowerCase();
        switch (command) {
            case "list":
                return new ListCommand();
            case "date":
                return new DateCommand(sc.nextLine().strip());
            case "done":
                return new DoneCommand(sc.nextLine().strip());
            case "delete":
                return new DelCommand(sc.nextLine().strip());
            case "event":
                return new EventCommand(sc.nextLine().split("/at"));
            case "deadline" :
                return new DeadLineCommand(sc.nextLine().split("/by"));
            case "todo":
                return new TodoCommand(sc.nextLine());
            case "bye":
                return new ByeCommand();
            default:
                throw new IllegalArgumentException();
        }
    }

}
