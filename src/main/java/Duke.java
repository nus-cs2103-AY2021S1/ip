import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static String LINE1 = "_____________________________________________________DUKE___";
    private final static String LINE2 = "------------------------------------------------------------";

    private final static String BYE = "bye";
    private final static String LIST = "list";
    private final static String DONE = "done";

    public static void main(String[] args) {
        looper();
    }

    private static void reply(String text) {
        System.out.printf("%s\n%s\n%s\n", LINE1, text.trim(), LINE2);
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
            switch(parsedInput[0]) {
                case BYE:
                    exit = true;
                    break;
                case LIST:
                    String result = "";
                    for (int i = 0; i < list.size(); i++) {
                        result += String.format("%d. %s\n", i+1, list.get(i));
                    }
                    reply(result);
                    break;
                case DONE:
                    int index = Integer.parseInt(parsedInput[1]) - 1;
                    list.get(index).setCompleted();
                    reply(String.format("Task marked as completed: \n%s", list.get(index).toString()));
                    break;
                default:
                    Task task = new Task(input);
                    list.add(task);
                    reply(String.format("Added %s", input));
                    break;
            }

        }
        reply("Goodbye.");
    }



}
