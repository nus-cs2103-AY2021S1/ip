import java.util.Scanner;

public class MainLogic {

    Storage storage = new Storage();
    boolean bye = false;

    public void main() {
        Scanner sc = new Scanner(System.in);

        Text.printStartMessage();

        while (!bye) {

            String next = sc.next();

            if (isCommand(next)) {
                // do nothing, is command handles command logic
            } else {
                Text.normalPrint("Added: " + next);
                storage.addItem(next);
            }
        }
        sc.close();
    }

    private boolean isCommand(String string) {
        switch (string) {
            case "bye":
                bye = true;
                Text.printEndMessage();
                return true;
            case "list":
                storage.printOut();
                return true;
            default:
                return false;
        }
    }
}
