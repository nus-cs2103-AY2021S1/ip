package tasks;

import exceptions.DukeDateTimeException;

public class TextParser {
    public TextParser() {
    }

    /**
     * Extracts the time from the command.
     * Slightly lenient on wording of datetime marker for Deadlines and Events
     * @param cmd
     * @return String array of size 2 where [0] is the description and [1] the datetime string
     * @throws DukeDateTimeException
     */
    String[] extractTime(String cmd) throws DukeDateTimeException {
        cmd = cmd.strip();
        int i;
        if (cmd.contains("/at")) {
            i = cmd.lastIndexOf("/at");
        } else if (cmd.contains("/by")) {
            i = cmd.lastIndexOf("/by");
        } else {
            //else throw an error here
            throw new DukeDateTimeException(cmd);
        }
        String[] c = new String[2];
        c[0] = cmd.substring(0, i).strip();
        c[1] = cmd.substring(i + 3).strip();
        return c;
    }

    /**
     * Parse String Input into the Command Parser to return a Enum of the command encoded.
     * @param cmd
     * @return
     */

    public Command parseCommand(String cmd) {
        String cleaned = cmd.toLowerCase();
        Command given = null;
        Command[] commandlst = Command.values();
        for (Command c : Command.values()) {
            if (c.getCode().equals(cleaned)) {
                given = c;
                // if there is a match, there is no other command that would match
                break;
            }
        }
        given = given == null ? Command.ERROR : given;
        return given;
    }

}
