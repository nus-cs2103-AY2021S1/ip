import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE_BREAK_BOT = "____________________________________________________________";
    private static final String LINE_BREAK_USER = "************************************************************";
    private static final String SPACE_BEFORE_LINE = "   ";
    private static final String SPACE_BEFORE_TEXT = "    ";

    private static boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);
        System.out.println(SPACE_BEFORE_TEXT + "Hey, I'm Emilia!" + "\n" +
                SPACE_BEFORE_TEXT + "What can I do for you ♥ ?");
        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("bye")) {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT + "\n" + SPACE_BEFORE_TEXT +
                        "Welcome back, master ♥ !" + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);
                break;

            }

            if (input.equals("list")) {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_USER);
                System.out.println(SPACE_BEFORE_LINE + "Please take a look at the tasks:");
                int count = 1;
                for (Task task : tasks) {
                    System.out.println(SPACE_BEFORE_TEXT + count + ". " + task);
                    count++;
                }
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);
                continue;
            }

            String[] process = input.split(" ");
            if (process[0].equals("done") && isInteger(process[1])) {
                int index = Integer.parseInt(process[1]);
                try {
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println(SPACE_BEFORE_LINE + "Understood, I've marked this task as done:" +
                            "\n" + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry master, I can't seem to find the task...");
                }
                continue;
            }

            Task current = new Task(input);
            tasks.add(current);
            System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_USER + "\n" + SPACE_BEFORE_TEXT +
                    "added: " + input + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);

        }
    }
}
