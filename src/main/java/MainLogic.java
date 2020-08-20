import java.util.Scanner;

public class MainLogic {

    Storage storage = new Storage();
    boolean bye = false;
    String current;
    Scanner sc = new Scanner(System.in);

    public void main() {

        Text.printStartMessage();

        while (!bye) {

            current = sc.nextLine();

            if (!commandCheck(current.split(" ", 2)[0])) {
                Text.normalPrint("Added: " + current);
                storage.addTodo(current);
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
                doneLogic();
                return true;
            case "todo":
                toDoLogic();
                return true;
            case "deadline":
                deadlineLogic();
                return true;
            case "event":
                eventLogic();
                return true;
            default:
//                Text.printCommandNotFoundError();
                return false;
        }
    }

    private void doneLogic() {
        storage.markDone(Integer.parseInt(current.split(" ", 2)[1]));
    }

    private void toDoLogic() {
        storage.addTodo(sc.next());
    }

    private void deadlineLogic() {
    }

    private void eventLogic() {

    }
}
