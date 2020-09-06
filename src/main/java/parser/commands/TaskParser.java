package parser.commands;

import java.util.Scanner;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;



public class TaskParser {


    /**
     * parses user input
     * @param input Scanner format of user input
     * @param tl duke's tasklist
     * @param stores duke's storage
     * @return confirmation message of adding task
     */
    public static String parse(String firstWord, Scanner input, TaskList tl, Storage stores) {
        try {
            // whitespace in front of nextLine
            if (input.hasNextLine()) {
                String remainingWords = input.nextLine().trim();
                String returnString = tl.getAddTaskString(firstWord, remainingWords);
                // save to save file
                stores.save(tl.getStorage());
                return returnString;
            } else {
                throw Ui.DukeException.empty(firstWord);
            }
        } catch (Ui.DukeException e) {
            return e.getMessage();
        }
    }
}
