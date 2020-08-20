public abstract class Command {
    String tag;
    String input;
    public Command(String tag) {
        this.tag = tag;
        this.input = "";
    }

    public Command(String tag, String input) {
        this.tag = tag;
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException,
            DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException,
            DukeUnknownInputException,
            DukeLoadingErrorException;

}
