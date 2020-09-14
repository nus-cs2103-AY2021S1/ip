package duke.commands;

import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.errors.ShortCutException;
import duke.helpers.ShortCuts;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Handles case when short is the input to add short cuts
 */
public class ShortCutCommand extends Command {

    /**
     * Assigns string to a value and integer to lengthOfKeyword
     *
     * @param commandDescription assigns this.string to string
     * @param lengthOfKeyword assigns length of keyword to this var
     */
    public ShortCutCommand(String commandDescription, int lengthOfKeyword) {
        super(commandDescription, lengthOfKeyword);
    }

    /**
     * Duke gives String depending on input, in this case that short cut has been added
     *
     * @param tasks used to access tasks in its list and change if necessary
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the input there if necessary
     * @return String that short cut is added
     * @throws DukeException when the user input is wrong such as
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return process(storage);
            //function processes the given input and gives message that short cut is added or else exception thrown
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Judges whether the String given is correct, else it throws an error
     *
     * @param storage which contains information of the various short forms and checks for short forms in them
     * @return String value if the String given by user is correct
     * @throws DukeException thrown if there is an error in user input
     */
    private String process(Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            throw new ShortCutException(true, false, false, false, ""); //if description is absent
        }
        String[] splitData = splitDescription(commandDescription);
        if (shortCutPresent(splitData[1])) {
            throw new ShortCutException(false, false, true, false, splitData[1]);
            //if short cut is already present
        } else if (containsUselessShortCut(splitData[0])) {
            throw new ShortCutException(false, false, false, true, splitData[1]);
            //if short cut exception is useless
        } else if (!shortCutPresent(splitData[0])) {
            ShortCuts.addShortCut(splitData[0], splitData[1]);
            updateShortCutFile(splitData[1], splitData[0], storage);
            return shortCutMessage(); //when it is correct
        } else {
            return null;
        }
    }
    /**
     * splits input into original form and short form
     *
     * @param input by user
     * @return String array containing original form index 0 and short form index 1
     * @throws DukeException when there are errors in user input
     */
    private String[] splitDescription(String input) throws DukeException {
        boolean originalOfShortFormPresent = false;
        int index = -1;
        String originalKeyWord = "";

        for (int i = lengthOfKeyword + 1; i < input.length(); i++) {
            if (input.charAt(i) == ' ') { //after " " comes short form
                index = i;
                originalOfShortFormPresent = true;
                break;
            }
            originalKeyWord = originalKeyWord + input.charAt(i);
        }
        if (!originalOfShortFormPresent || commandDescription.substring(index + 1).length() == 0) {
            throw new ShortCutException(false, true, false, false, ""); //happens when there is nothing after keyword
        }
        String[] splitData = new String[]{originalKeyWord, commandDescription.substring(index + 1)};
        return splitData;
    }

    /**
     * returns boolean value of whether input is already present
     *
     * @param input short cut added by user
     * @return true if hashMap contains input and false otherwise.
     */
    private boolean shortCutPresent(String input) {
        return ShortCuts.getShortCuts().containsKey(input); //if present is true
    }

    /**
     * Informs user that short cut has been added
     *
     * @return String that short cut is added
     */
    private String shortCutMessage() {
        return "  short cut successfully added";
    }

    /**
     * adds new short cuts by user into file
     *
     * @param shortCut by user
     * @param original duke function name
     * @param storage where the file is contained in
     * @throws DukeException thrown when file doesn't exist
     */
    private void updateShortCutFile(String shortCut, String original, Storage storage) throws DukeException {
        try {
            FileWriter fw = new FileWriter(storage.getShortFormsFilePath(), true);
            //updates the file in storage as new task is added
            String newShortCutToAdd = shortCut + " " + original;
            fw.write(newShortCutToAdd + "\n");
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getShortFormsFilePath());
        }
    }

    /**
     * Checks whether original form given is uselss
     *
     * @param originalForm given by user
     * @return true if useless and false otherwise
     */
    private boolean containsUselessShortCut(String originalForm) {
        return !ShortCuts.getShortCuts().containsValue(originalForm);
        //since the short cut in ShorCut contains value of all tasks recognised by user
    }
}
