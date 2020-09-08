package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.ShortCutException;
import Duke.Helpers.ShortCuts;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

public class ShortCutCommand extends Command{

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
     *
     *
     * @param tasks used to access tasks in its list and change if necessary
     * @param ui
     * @param storage to change the input there if necessary
     * @return
     * @throws DukeException when the user input is wrong such as
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (isDescriptionAbsent()) {
                throw new ShortCutException(true, false, false, "");
            }

            String[] splitData = splitDescription(commandDescription);

            if (shortCutPresent(splitData[1])) {
                throw new ShortCutException(false, false, true, splitData[1]);
            } else if (!shortCutPresent(splitData[1])) {
                ShortCuts.addShortCut(splitData[0], splitData[1]);
                return shortCutMessage();
            } else {
                return null;
            }
        }catch (DukeException dukeException){
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }
    private boolean isDescriptionAbsent(){
        return commandDescription.length() == lengthOfKeyword || commandDescription.length() == lengthOfKeyword + 1; // since description can only appear after length of keyword
    }
    private String[] splitDescription(String input) throws ShortCutException {
        boolean originalOfShortFormPresent = false;
        int index = -1;
        String originalKeyWord = "";

        for(int i = lengthOfKeyword + 1; i < input.length(); i++){
            if(input.charAt(i) == ' '){
                index = i;
                originalOfShortFormPresent = true;
                break;
            }
            originalKeyWord = originalKeyWord + input.charAt(i);
        }
        if(!originalOfShortFormPresent || commandDescription.substring(index + 1).length() == 0){
            throw new ShortCutException(false, true, false, "");
        }
        String[] splitData = new String[]{originalKeyWord, commandDescription.substring(index + 1)};
        return splitData;
    }
    private boolean shortCutPresent(String input) {
        return ShortCuts.getShortCuts().containsKey(input);
    }

    private String shortCutMessage(){
        return "  short cut successfully added";
    }
}
