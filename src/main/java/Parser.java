import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public Parser(){}

    // TODO: 20/8/20 CHANGE ALL MATCHES TO matcher.find 
    public Pair<TaskType, ArrayList<String>> parseAdd(String echo) throws DukeException {
        if (echo.matches("^todo.*")) {
            if (echo.matches("^todo\\s+\\S+.*")) {
                String text = echo.replaceFirst("^todo\\s*", "");
                ArrayList<String> boxed = new ArrayList<>();
                boxed.add(text);
                return new Pair<TaskType, ArrayList<String>>(TaskType.TODO, boxed);
            } else if (echo.matches("^todo\\s*")) {
                throw new DukeException("Please put a description to your task.");
            } else {
                throw new DukeException("Please put a space between the command and description.");
            }
        } else if (echo.matches("^deadline.*")) {
            if (echo.matches("^deadline\\s+\\S+.*\\s+\\/by\\s+\\S+.*")) {
                String[] res = echo.replaceFirst("deadline\\s+", "").strip().split("/by", 2);
                ArrayList<String> boxed = new ArrayList<String>(Arrays.asList(res));
                return new Pair<TaskType, ArrayList<String>>(TaskType.DEADLINE, boxed);
            } else {
                String[] badText = echo.split("\\s+", 2);
                if (badText.length == 1) {
                    throw new DukeException("bruh you don't have spaces");
                }
                String badCommand = badText[1];
                if (badCommand.matches(".*\\/by.*")) { // The command contains by.
                    throw new DukeException("Did you miss the description or date? Remember to put spaces.");
                } else {
                    throw new DukeException("Please put /by followed by the date.");
                }
            }
            // TODO: 20/8/20 errors: no by, no space inbetween, no desc, no date
        } else if (echo.matches("^event.*")) {
            if (echo.matches("^event\\s+\\S+.*\\s+\\/at\\s+\\S+.*")) {
                String[] res = echo.replaceFirst("event\\s+", "").strip().split("/at", 2);
                ArrayList<String> boxed = new ArrayList<String>(Arrays.asList(res));
                return new Pair<TaskType, ArrayList<String>>(TaskType.EVENT, boxed);
            } else {
                String[] badText = echo.split("\\s+", 2);
                if (badText.length == 1) {
                    throw new DukeException("bruh you don't have spaces");
                }
                String badCommand = badText[1];
                if (badCommand.matches(".*\\/at.*")) { // The command contains by.
                    throw new DukeException("Did you miss the description or date? Remember to put spaces.");
                } else {
                    throw new DukeException("Please put /at followed by the date.");
                }
            }
        }
        throw new DukeException("Invalid command.");
    }

    public int parseDone(String echo, int listLength) throws DukeException {
        String res = "";
        if (echo.matches("done\\s+[0-9]+")) {
            res = echo.replaceFirst("done\\s+", "");
        } else {
            throw new DukeException("Done must be followed by a space and an integer!");
        }

        int toBeRemoved = Integer.parseInt(res); // No error: regex above guarantees that.

        if (toBeRemoved >= listLength) {
            throw new DukeException("The task does not exist! (Index out of bounds)");
        }

        return toBeRemoved;
    }
}
