package alice.command;

import java.util.function.Function;

public enum CommandType {
    CMD_BYE((w) -> ByeCommand.hasCommandWord(w), (arg) -> new ByeCommand()),
    CMD_LIST((w) -> ListCommand.hasCommandWord(w), (arg) -> new ListCommand()),
    CMD_FIND((w) -> FindCommand.hasCommandWord(w), (arg) -> new FindCommand(arg)),
    CMD_CLEAR((w) -> ClearCommand.hasCommandWord(w), (arg) -> new ClearCommand()),
    CMD_HELP((w) -> HelpCommand.hasCommandWord(w), (arg) -> new HelpCommand()),
    CMD_DONE((w) -> DoneCommand.hasCommandWord(w), (arg) -> new DoneCommand(arg)),
    CMD_DELETE((w) -> DeleteCommand.hasCommandWord(w), (arg) -> new DeleteCommand(arg)),
    CMD_TODO((w) -> TodoCommand.hasCommandWord(w), (arg) -> new TodoCommand(arg)),
    CMD_DEADLINE((w) -> DeadlineCommand.hasCommandWord(w), (arg) -> new DeadlineCommand(arg)),
    CMD_EVENT((w) -> EventCommand.hasCommandWord(w), (arg) -> new EventCommand(arg));


    private final Function<String, Boolean> wordChecker;
    private final Function<String, Command> factoryMethod;

    CommandType(Function<String, Boolean> wordChecker, Function<String, Command> factoryMethod) {
        this.wordChecker = wordChecker;
        this.factoryMethod = factoryMethod;
    }

    public boolean hasCommandWord(String name) {
        return wordChecker.apply(name);
    }

    public Command createCmd(String argument) {
        return factoryMethod.apply(argument);
    }
}
