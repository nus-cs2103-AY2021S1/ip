package duke.parser;

import duke.DukeException;

/**
 * Ensures that input strings to find tasks is formatted correctly.
 */
public class FindTaskStringChecker extends StringChecker {

    public FindTaskStringChecker(String[] userInput) {
        super(userInput);
    }

    /**
     * Ensures that the task name / keyword to match is present.
     *
     * @throws DukeException If no keyword is specified to match against task names.
     */
    public void checkTaskName() throws DukeException {
        checkTaskNamePresent();
    }

    private void checkTaskNamePresent() throws DukeException {
        if (super.checkEmptyString(getStringArray(), 2)) {
            throw new DukeException("Please enter a keyword to match against task names!");
        }
    }
}
