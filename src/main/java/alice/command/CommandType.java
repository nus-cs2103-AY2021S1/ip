package alice.command;

import java.util.function.Function;

import alice.util.CheckedFunction;

public enum CommandType {
    CMD_BYE(ByeCommand::hasCommandWord, (arg) -> ByeCommand.createCommand()),
    CMD_LIST(ListCommand::hasCommandWord, (arg) -> ListCommand.createCommand()),
    CMD_FIND(FindCommand::hasCommandWord, FindCommand::createCommand),
    CMD_CLEAR(ClearCommand::hasCommandWord, (arg) -> ClearCommand.createCommand()),
    CMD_HELP(HelpCommand::hasCommandWord, (arg) -> HelpCommand.createCommand()),
    CMD_DONE(DoneCommand::hasCommandWord, DoneCommand::createCommand),
    CMD_DELETE(DeleteCommand::hasCommandWord, DeleteCommand::createCommand),
    CMD_TODO(TodoCommand::hasCommandWord, TodoCommand::createCommand),
    CMD_DEADLINE(DeadlineCommand::hasCommandWord, DeadlineCommand::createCommand),
    CMD_EVENT(EventCommand::hasCommandWord, EventCommand::createCommand);


    private final Function<String, Boolean> wordChecker;
    private final CheckedFunction<String, Command> factoryMethod;

    CommandType(Function<String, Boolean> wordChecker, CheckedFunction<String, Command> factoryMethod) {
        this.wordChecker = wordChecker;
        this.factoryMethod = factoryMethod;
    }

    public boolean hasCommandWord(String name) {
        return wordChecker.apply(name);
    }

    public Command createCmd(String argument) throws InvalidCommandException {
        return factoryMethod.apply(argument);
    }
}
