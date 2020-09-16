import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String HOME = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");
    private static boolean pathExists = java.nio.file.Files.exists(PATH);



    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static TaskList tasklist;
    private static Statistics statistics;

    String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return ui.exit();
            } else if (input.equals("list")) {
                return ui.list();
            } else if (input.startsWith("done")) {
                statistics = statistics.addCompletedTask();
                tasklist.updateStatistics(statistics);

                int n = parser.getTaskNumber(input);
                Task t = tasklist.complete(n);
                return ui.complete(t);
            } else if (input.startsWith("delete")) {
                statistics = statistics.addDeletedTask();
                tasklist.updateStatistics(statistics);

                int n = parser.getTaskNumber(input);
                Task t = tasklist.delete(n);
                return ui.delete(t, tasklist.getTotal());
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                statistics = statistics.addTask();
                tasklist.updateStatistics(statistics);

                Task t = tasklist.add(input);
                return ui.add(t, tasklist.getTotal());
            } else if (input.startsWith("find")) {
                String keyword = parser.getKeyword(input);
                return ui.find(keyword);
            } else if (input.startsWith("statistics")) {
                return statistics.toString();
            } else {
                // unknown input
                throw new UnknownInputException();
            }
        } catch (DukeException | IOException e) {
            return ui.handleException(e);
        }
    }


    static void initialize() throws IOException {
        if (!pathExists) {
            // do smth lmao
        }

        Scanner myReader = new Scanner(PATH);
        int total = myReader.nextInt();
        tasklist = new TaskList(total);
        myReader.nextLine();

        String data = myReader.nextLine();
        statistics = new Statistics(data);
        statistics = statistics.reset();
    }

    public static void main(String[] args) {
        // use launcher to launch
    }
}
