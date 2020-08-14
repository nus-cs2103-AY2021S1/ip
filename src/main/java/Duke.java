import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "     What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private static ArrayList<Task> list = new ArrayList<>();

    private static String makeWrappedString(String txt) {
        return LINE + "\n     " + txt + "\n" + LINE;
    }

    public static void main(String[] args) {
        System.out.println(makeWrappedString(WELCOME_MESSAGE));

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            switch (input.split(" ")[0]) {
            case "list":
                System.out.println(LINE);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + ": " + list.get(i));
                }
                System.out.println(LINE);
                break;
            case "done":
                Task doneTask = list.get(Integer.parseInt(input.split(" ")[1]) - 1);
                doneTask.setDone(true);
                System.out.println(
                    makeWrappedString("Great Job! I've marked this task as done:\n       " + doneTask));
                break;
            default:
                list.add(new Task(input));
                System.out.println(makeWrappedString("added: " + input));
                break;
            }
            input = sc.nextLine();
        }

        System.out.println(makeWrappedString(GOODBYE_MESSAGE));
    }
}
