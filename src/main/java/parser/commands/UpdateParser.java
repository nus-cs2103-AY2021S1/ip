package parser.commands;

import java.util.Scanner;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


public class UpdateParser {
    /**
     * parses user input
     * @param input Scanner format of user input
     * @param tl duke's tasklist
     * @param stores duke's storage
     * @return confirmation message of updating task
     */
    public static String parse(Scanner input, TaskList tl, Storage stores) {
        assert input != null;
        String index;
        try {
            if (input.hasNext()) {
                index = input.next();
            } else {
                throw Ui.DukeException.empty("update");
            }
        } catch (Ui.DukeException e) {
            return e.getMessage();
        }
        String changeField;
        try {
            if (input.hasNext()) {
                changeField = input.next();
            } else {
                throw Ui.DukeException.empty("update");
            }
        } catch (Ui.DukeException e) {
            return e.getMessage();
        }
        String changeTo;
        try {
            if (input.hasNext()) {
                changeTo = input.next();
            } else {
                throw Ui.DukeException.empty("update");
            }
        } catch (Ui.DukeException e) {
            return e.getMessage();
        }
        int intIndex = Integer.parseInt(index);
        try {
            boolean isValidIndex = intIndex <= tl.getStorage().size() && intIndex > 0;
            if (isValidIndex) {
                assert changeTo != null;
                String returnString = tl.getUpdateString(intIndex - 1, changeField, changeTo);
                // save to save file
                stores.save(tl.getStorage());
                return returnString;
            } else {
                throw Ui.DukeException.outOfBounds(intIndex);
            }
        } catch (Ui.DukeException e) {
            return e.getMessage();
        }
    }
}
