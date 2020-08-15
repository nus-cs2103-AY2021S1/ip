import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static String LINE1 = "_____________________________________________________DUKE___";
    private final static String LINE2 = "------------------------------------------------------------";

    private final static String BYE = "bye";
    private final static String LIST = "list";
    private final static String DONE = "done";

    private final static String TODO = "todo";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";

    public static void main(String[] args) {
        looper();
    }

    private static void reply(String text) {
        System.out.printf("%s\n%s\n%s\n", LINE1, text.trim(), LINE2);
    }

    private static void invalid() {
        reply("Invalid format");
    }

    private static void addTaskNotification(Task t) {
        reply(String.format("Added %s", t.toString()));
    }

    private static String[] readCommand(String text) {
        String[] split = text.split(" ", 2);
        return split;
    }


    private static void looper() {
        reply("Hello");
        ArrayList<Task> list = new ArrayList<>();
        Boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            String input = sc.nextLine().trim();
            String[] parsedInput = readCommand(input);
            if (parsedInput.length != 2 && !(parsedInput[0].equals(BYE) || parsedInput[0].equals(LIST))) {
                invalid();

            } else {
                switch (parsedInput[0]) {
                    case BYE:
                        exit = true;
                        break;
                    case LIST:
                        String result = "";
                        for (int i = 0; i < list.size(); i++) {
                            result += String.format("%d. %s\n", i + 1, list.get(i));
                        }
                        reply(result);
                        break;
                    case DONE:
                        int index = Integer.parseInt(parsedInput[1]) - 1;
                        list.get(index).setCompleted();
                        reply(String.format("Task marked as completed: \n%s", list.get(index).toString()));
                        break;
                    case TODO:
                        Todo todo = new Todo(parsedInput[1]);
                        list.add(todo);
                        addTaskNotification(todo);
                        break;
                    case EVENT:
                        String[] evInput = parsedInput[1].split("/at ", 2);
                        if (evInput.length != 2) {
                            invalid();
                        } else {
                            Event event = new Event(evInput[0].trim(), evInput[1].trim());
                            list.add(event);
                            addTaskNotification(event);
                        }
                        break;
                    case DEADLINE:
                        String[] dlInput = parsedInput[1].split("/by ", 2);
                        if (dlInput.length != 2) {
                            invalid();
                        } else {
                            Deadline deadline = new Deadline(dlInput[0].trim(), dlInput[1].trim());
                            list.add(deadline);
                            addTaskNotification(deadline);
                        }
                        break;
                    default:
                        invalid();
                        break;
                }
            }
        }
        reply("Goodbye.");
    }


}
