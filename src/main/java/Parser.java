import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public Parser(){}

    // TODO: 20/8/20 CHANGE ALL MATCHES TO matcher.find 
    public Task parseAdd(String echo) throws DukeException, ParseException {
        if (echo.matches("(?i)^todo.*")) {
            if (echo.matches("(?i)^todo\\s+\\S+.*")) {
                String text = echo.replaceFirst("(?i)^todo\\s*", "");
                return new ToDo(text);
            } else if (echo.matches("(?i)^todo\\s*")) {
                throw new DukeException("Please put a description to your task.");
            } else {
                throw new DukeException("Please put a space between the command and description.");
            }
        } else if (echo.matches("(?i)^deadline.*")) {
            if (echo.matches("(?i)^deadline\\s+\\S+.*\\s+\\/by\\s+\\S+.*")) {
                String[] res = echo.replaceFirst("(?i)deadline\\s+", "").strip().split("(?i)/by", 2);
                return new Deadline(res[0], res[1]);
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
        } else if (echo.matches("(?i)^event.*")) {
            if (echo.matches("(?i)^event\\s+\\S+.*\\s+\\/at\\s+\\S+.*")) {
                String[] res = echo.replaceFirst("(?i)event\\s+", "").strip().split("(?i)/at", 2);
                return new Event(res[0], res[1]);
            } else {
                String[] badText = echo.split("\\s+", 2);
                if (badText.length == 1) {
                    throw new DukeException("bruh you don't have spaces");
                }
                String badCommand = badText[1];
                if (badCommand.matches("(?i).*\\/at.*")) { // The command contains by.
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
        if (echo.matches("(?i)done\\s+[0-9]+")) {
            res = echo.replaceFirst("done\\s+", "");
        } else {
            throw new DukeException("Done must be followed by a space and an integer!");
        }

        int toBeRemoved = Integer.parseInt(res); // No error: regex above guarantees that.

        if (toBeRemoved > listLength || toBeRemoved <= 0) {
            throw new DukeException("The task does not exist! (Index out of bounds)");
        }

        return toBeRemoved;
    }

    public int parseDelete(String echo, int listLength) throws DukeException {
        String res = "";
        if (echo.matches("(?i)delete\\s+[0-9]+")) {
            res = echo.replaceFirst("(?i)delete\\s+", "");
        } else {
            throw new DukeException("Delete must be followed by a space and an integer!");
        }

        int toBeRemoved = Integer.parseInt(res); // No error: regex above guarantees that.

        if (toBeRemoved > listLength || toBeRemoved <= 0) {
            throw new DukeException("The task does not exist! (Index out of bounds)");
        }

        return toBeRemoved;
    }
}
