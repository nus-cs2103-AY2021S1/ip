package duke;

class DukeGotNoArgumentsException extends IllegalArgumentException {
    public DukeGotNoArgumentsException(String message) {
        super("Please reenter your tasks in the format " + message + "\nFill in all blanks.");
    }
}

class DukeCannotUnderstandException extends IllegalArgumentException {
    public DukeCannotUnderstandException() {
        super("Hmm I did not understand what you meant.\n" +
                "Could you try again?");
    }
}