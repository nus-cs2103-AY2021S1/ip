package parser.commands;

import static ui.Ui.getFilterListString;

import java.util.Scanner;

import tasklist.TaskList;
import ui.Ui;




public class FindParser {


    /**
     * parses user input
     * @param input Scanner format of user input
     * @param tl duke's tasklist
     * @return lists all found tasks
     */
    public static String parse(Scanner input, TaskList tl) {
        assert input != null;
        try {
            if (input.hasNextLine()) {
                String remainingWords = input.nextLine().trim();
                return getFilterListString(remainingWords, tl.getStorage());
            } else {
                throw Ui.DukeException.empty("find");
            }
        } catch (Ui.DukeException e) {
            return "Error: " + e.getMessage();
        }
    }
}
