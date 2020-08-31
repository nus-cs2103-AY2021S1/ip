import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            return "Please input the time and date in\n"
                + dateParser.toPattern() + " or " + parser.toPattern() + "\n";
        }
    }

    public static boolean checkBye(String s) {
        return s.equals("bye");
    }

    public static boolean checkList(String s) {
        return s.equals("list");
    }

    private static boolean checkFind(String test) {
        return test.equals("find");
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
     * @param text The user input
     * @return String for command executed
     */
    public static String parse(TaskList taskList, Storage storage, String text) {
        String output;
        if (text.contains(" ")) {
            int i = text.indexOf(' ');
            String test = text.substring(0, i).toLowerCase();
            String next = text.substring(i + 1);
            if (checkFind(test)) {
                return taskList.findTask(next);
            } else if (checkDone(test)) {
                try {
                    output = taskList.doneTask(next);
                    storage.updateFile();
                    return output;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            } else if (checkDel(test)) {
                try {
                    output = taskList.delTask(next);
                    storage.updateFile();
                    return output;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            } else {
                try {
                    output = taskList.addTask(test, next);
                    storage.updateFile();
                    return output;
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
        } else {
            if (checkBye(text.toLowerCase())) {
                return ui.exitLine();
            } else if (checkList(text)) {
                return taskList.displayList();
            } else {
                return ui.unknownInputLine();
            }
        }
    }
}
