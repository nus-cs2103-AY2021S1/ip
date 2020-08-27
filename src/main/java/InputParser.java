import java.util.Scanner;

public class InputParser {

    private Scanner myObj;

    public InputParser() {
        // Initialise the Scanner object to get input from user
        this.myObj = new Scanner(System.in);
    }

    public String getInput() {
        return myObj.nextLine().trim();
    }

    public boolean isEmptyInput(String input) {
        return input.isEmpty();
    }

    public boolean isValidCommand(String input) {
        return input.toLowerCase().startsWith("todo")
                || input.toLowerCase().startsWith("deadline")
                || input.toLowerCase().startsWith("event");
    }

    public boolean isEmptyDescription(String input) {
        return input.split(" ").length == 1;
    }

    public boolean hasDeadlineBy(String input) {
        return input.contains("/by")
                && input.split(" /by ").length == 2;
    }

    public boolean hasEventStartEndTime(String input) {
        return input.contains("/at")
                && input.split(" /at ").length == 2
                && input.split(" /at ")[1].split(" ").length == 2
                && input.split(" /at ")[1].split(" ")[1]
                .split("-").length == 2;
    }
}
