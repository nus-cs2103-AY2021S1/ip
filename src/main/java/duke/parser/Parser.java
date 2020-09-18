package duke.parser;

import duke.Duke;
import duke.DukeException;
import duke.command.*;
import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

public class Parser {

    /**
     * Parses a user input and returns the right command with other relevant arguments for the command.
     * Performs input validation and throws a DukeException if input is not valid.
     *
     * @param userInput
     * @return
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        assert !userInput.isEmpty() : "userInput cannot be empty";

        int space_idx = userInput.indexOf(' ');
        CMD cmd;

        try {
            cmd = CMD.valueOf((space_idx  == -1 ? userInput : userInput.substring(0, space_idx)).toUpperCase());
        } catch (IllegalArgumentException e) {
            cmd = CMD.DEFAULT;
        }

        String rest = space_idx == -1 ? "" : userInput.substring(space_idx + 1).trim();
        int dateStrIdx;
        String dateStr;
        String item;

        switch(cmd) {
        case BYE:
            return new ByeCommand();

        case LIST:
            dateStrIdx = rest.indexOf("/by");
            if (dateStrIdx == -1) {
                return new ListCommand();
            }

            dateStr = rest.substring(dateStrIdx + 3).trim();
            if (DateTimeUtility.checkDateTimeType(dateStr) != DateTimeFormat.String) {
                return new ListCommand(dateStr);
            } else {
                throw new DukeException("U NID 2 GIV CORRECT DATE FOMAT!");
            }

        case TODO:
            if (!rest.isEmpty()) {
                return new TodoCommand(rest);
            } else {
                throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR TODO ITEM LULZ");
            }

        case DEADLINE:
            if (rest.isEmpty()) {
                throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR DEDLINE ITEM LULZ");
            }

            if (rest.indexOf("/by") == -1) {
                throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA DEDLINE USIN /by");
            }

            dateStrIdx = rest.indexOf("/by");
            item = rest.substring(0, dateStrIdx).trim();
            dateStr = rest.substring(dateStrIdx + 3).trim();

            if (!item.isEmpty() && !dateStr.isEmpty()) {
                return new DeadlineCommand(item, dateStr);
            } else if (item.isEmpty()) {
                throw new DukeException("ME FINKZ U NED 2 GIV DA DEDLINE A NAEM");
            } else {
                throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
            }


        case EVENT:
            if (rest.isEmpty()) {
                throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR EVENT ITEM LULZ");
            }

            if (rest.indexOf("/at") == -1) {
                throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA EVENT USIN /at");
            }

            dateStrIdx = rest.indexOf("/at");
            item = rest.substring(0, dateStrIdx).trim();
            dateStr = rest.substring(dateStrIdx + 3).trim();

            if (!item.isEmpty() && !dateStr.isEmpty()) {
                return new EventCommand(item, dateStr);
            } else if (item.isEmpty()) {
                throw new DukeException("ME FINKZ U NED 2 GIV DA EVENT A NAEM");
            } else {
                throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
            }


        case DONE:
            try {
                int idx = Integer.parseInt(rest) - 1;
                return new DoneCommand(idx);
            } catch (NumberFormatException e) {
                throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
            }

        case DELETE:
            try {
                int idx = Integer.parseInt(rest) - 1;
                return new DeleteCommand(idx);
            } catch (NumberFormatException e) {
                throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
            }

        case FIND:
            if (!rest.isEmpty()) {
                return new FindCommand(rest);
            } else {
                throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR TODO ITEM LULZ");

            }

        case UPDATE:
            if (rest.isEmpty()) {
                throw new DukeException("U MUST PUT DA INDEX OV TASK AN' NEW NAME LULZ");
            }

            int taskIdx;
            String newInput;

            try {
                String parts[] = rest.split(" ", 2);
                taskIdx = Integer.parseInt(parts[0]) - 1;
                newInput = parts[1];
            } catch (NumberFormatException e) {
                throw new DukeException("U MUST PUT INDEX OV TASK LULS");
            }

            dateStrIdx = newInput.indexOf("/date");

            if (dateStrIdx == -1) {
                return new UpdateCommand(taskIdx, newInput, false);
            }

            dateStr = newInput.substring(dateStrIdx + 5).trim();
            if (!dateStr.isEmpty()) {
                return new UpdateCommand(taskIdx, dateStr, true);
            } else {
                throw new DukeException("U CANNOT GIV EMPTY DATE!");
            }

        case DEFAULT:
            return new Command();
        }
        return new Command();
    }
}
