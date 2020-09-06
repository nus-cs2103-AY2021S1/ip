package duke;

public class FindTaskStringChecker extends StringChecker {

    public FindTaskStringChecker(String[] userInput) {
        super(userInput);
    }

    public void checkTaskName() throws DukeException {
        checkTaskNamePresent();
    }

    private void checkTaskNamePresent() throws DukeException {
        if (super.checkEmptyString(getStringArray(),2)) {
            throw new DukeException("Please enter a keyword to match against task names!");
        };
    }
}
