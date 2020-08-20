import java.util.Scanner;

public class MainLogic {

    Storage storage = new Storage();
    boolean bye = false;
    String[] current;
    Scanner sc = new Scanner(System.in);

    public void main() {

        Text.printStartMessage();

        while (!bye) {

            current = sc.nextLine().split(" ", 2);

            commandCheck(current[0]);

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
                Text.printCommandNotFoundError();
                return false;
        }
    }

    private void doneLogic() {
        storage.markDone(Integer.parseInt(current[1]));
    }

    private void toDoLogic() {
        storage.addTask(new TodoTask(current[1]));
    }

    private void deadlineLogic() {
        String[] details = current[1].split("/by", 2);
        if (details.length == 1) {
            storage.addTask(new DeadLineTask(details[0], "non specified date/time"));
        } else {
            storage.addTask(new DeadLineTask(details[0], details[1]));
        }
    }

    private void eventLogic() {
        String[] details = current[1].split("/at", 2);
        if (details.length == 1) {
            storage.addTask(new EventTask(details[0], "non specified date/time"));
        } else {
            storage.addTask(new EventTask(details[0], details[1]));
        }
    }
}
