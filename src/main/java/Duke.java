import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String s;

    static Ui ui = new Ui(sc);
    static TaskList tasklist;
    static Parser parser = new Parser();

    static String HOME = System.getProperty("user.home");
    static java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    static void initialize() throws IOException {
        Scanner myReader = new Scanner(PATH);
        int total = myReader.nextInt();
        tasklist = new TaskList(total);

        s = ui.greet();
    }

    static void talk() {
        while (1 == 1) {
            try {
                if (s.equals("bye")) {
                    ui.exit();
                    break;
                } else if (s.equals("list")) {
                    s = ui.list();
                } else if (s.startsWith("done")) {
                    int n = parser.getTaskNumber(s);
                    Task t = tasklist.complete(n);
                    s = ui.complete(t);
                } else if (s.startsWith("delete")) {
                    int n = parser.getTaskNumber(s);
                    Task t = tasklist.delete(n);
                    s = ui.delete(t, tasklist.getTotal());
                } else if (s.startsWith("todo") || s.startsWith("deadline") || s.startsWith("event")) {
                    Task t = tasklist.add(s);
                    s = ui.add(t, tasklist.getTotal());
                } else { // unknown input
                    throw new UnknownInputException();
                }
            } catch (DukeException | IOException e) {
                ui.handleException(e);
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        initialize();
        talk();
    }
}
