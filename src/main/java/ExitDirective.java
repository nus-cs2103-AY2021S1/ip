public class ExitDirective implements Executable {
    private final String MESSAGE_EXIT_SUCCESS = "hOpE To sEe yOu aGaIn. NoT.";

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        return new Report(MESSAGE_EXIT_SUCCESS, true);
    }
}
