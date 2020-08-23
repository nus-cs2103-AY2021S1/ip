import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Represents the user input Parser handler.
 */
public class Parser {
    private static final Ui ui = new Ui();

    /**
     * Processes the user input date and returns it in the correct format.
     * @param s The user input date
     * @return The formatted date for use.
     */
    public static String parseDateTime(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy h:mma");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HHmm");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (s.contains(" ")) {
                return formatter.format((parser.parse(s)));
            } else {
                return dateFormatter.format(dateParser.parse(s));
            }
        } catch (ParseException e) {
            return ui.getBorder() + "Please input the time and date in\n"
                            + dateParser.toPattern() + " or " + parser.toPattern() + "\n" + ui.getBorder();
        }
    }

    public static boolean checkBye(String s) {
        return s.equals("bye");
    }

    public static boolean checkList(String s) {
        return s.equals("list");
    }

    public static boolean checkDone(String s) {
        return s.equals("done");
    }

    public static boolean checkDel(String s) {
        return s.equals("delete");
    }

    /**
     * Parses the user input and calls the appropriate functions.
     * @param taskList The list of tasks handler
     * @param storage The storage call handler
     */
    public static void parseInput(TaskList taskList, Storage storage) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String test = scan.next().toLowerCase();
            if (checkBye(test)) {
                ui.exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (checkList(test)) {
                    taskList.displayList();
                } else if (checkDone(test)) {
                    try {
                        taskList.doneTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                } else if (checkDel(test)) {
                    try {
                        taskList.delTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                } else {
                    try {
                        taskList.addTask(test, next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                }
            }
        }
        scan.close();
    }
}
