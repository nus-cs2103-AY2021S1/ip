package parser.commands;

import java.util.Scanner;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;




public class DeleteParser {


    /**
     * parses user input
     * @param input Scanner format of user input
     * @param tl duke's tasklist
     * @param stores duke's storage
     * @return confirmation message of marking task as deleted
     */
    public static String parse(Scanner input, TaskList tl, Storage stores) {
        assert input != null;
        String index = input.next();
        int intIndex = Integer.parseInt(index);
        try {
            boolean isValidIndex = intIndex <= tl.getStorage().size() && intIndex > 0;
            if (isValidIndex) {
                String returnString = tl.getDeleteString(intIndex - 1);
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
