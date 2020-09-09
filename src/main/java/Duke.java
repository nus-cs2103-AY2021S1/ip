import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static String HOME = System.getProperty("user.home");
    static java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    static Scanner sc = new Scanner(System.in);
    static Ui ui = new Ui();
    static Parser parser = new Parser();

    static String s;
    static TaskList tasklist;

    String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return ui.exit();
            } else if (input.equals("list")) {
                return ui.list();
            } else if (input.startsWith("done")) {
                int n = parser.getTaskNumber(input);
                Task t = tasklist.complete(n);
                return ui.complete(t);
            } else if (input.startsWith("delete")) {
                int n = parser.getTaskNumber(input);
                Task t = tasklist.delete(n);
                return ui.delete(t, tasklist.getTotal());
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task t = tasklist.add(input);
                return ui.add(t, tasklist.getTotal());
            } else if (input.startsWith("find")) {
                String keyword = parser.getKeyword(input);
                return ui.find(keyword);
            } else { // unknown input
                throw new UnknownInputException();
            }
        } catch (DukeException | IOException e) {
            return ui.handleException(e);
        }
    }


    static void initialize() throws IOException {
        Scanner myReader = new Scanner(PATH);
        int total = myReader.nextInt();
        tasklist = new TaskList(total);
    }

    public static void main(String[] args) {
        // use launcher to launch
    }
}
