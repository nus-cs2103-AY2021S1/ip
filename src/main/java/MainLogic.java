import java.util.Scanner;

public class MainLogic {

    Storage storage = new Storage();
    boolean bye = false;
    String current;
    Scanner sc = new Scanner(System.in);

    public void main() {

        Text.printStartMessage();

        while (!bye) {

            current = sc.next();

            if (commandCheck(current)) {
                // do nothing, isCommand handles command logic
            } else {
                Text.normalPrint("Added: " + current);
                storage.addItem(current);
            }
        }
        sc.close();
    }

    private boolean commandCheck(String string) {
        switch (string) {
            case "bye":
                bye = true;
                Text.printEndMessage();
                return true;
            case "list":
                storage.printOut();
                return true;
            case "done":
                doneCommandLogic();
                return true;
            default:
                return false;
        }
    }

    private void doneCommandLogic() {
        storage.markDone(Integer.parseInt(sc.next()));
    }
}
