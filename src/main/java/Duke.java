import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<String> tasks = new ArrayList<>();

    private static void formatResponse(ArrayList<String> response) {
        System.out.println(INDENT + LINE);
        for (String resp: response) {
            System.out.println(INDENT + " " + resp);
        }
        System.out.println(INDENT + LINE);
        System.out.println();
    }

    private static void formatResponse(String ...response) {
        ArrayList<String> lst = new ArrayList<>();
        for (String resp: response) {
            lst.add(resp);
        }
        formatResponse(lst);
    }

    private static void formatList(ArrayList<String> response) {
        ArrayList<String> lst = new ArrayList<>();
        for (int i = 0; i < response.size(); i++) {
            lst.add((i + 1) + ". " + response.get(i));
        }
        formatResponse(lst);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        formatResponse("Hello! I'm Duke", "What can I do for you?");
        String display = "";
        while (!display.equals("bye")) {
            display = sc.nextLine();
            if (display.equals("list")) {
                formatList(tasks);
            } else if (!display.equals("bye")) {
                formatResponse("added: " + display);
                tasks.add(display);
            }
        }
       formatResponse("Bye. Hope to see you again soon!");
       sc.close();
    }
}
