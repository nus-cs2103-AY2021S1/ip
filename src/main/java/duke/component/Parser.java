package duke.component;

import duke.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] userInputArr = fullCommand.split(" ");
        String instructionCommand = userInputArr[0];

        // ENUM generation
        try {
            EnumUserInstruction.userInstruction userInstructionEnum = EnumUserInstruction.userInstruction.
                    valueOf(instructionCommand.toUpperCase());

            switch (userInstructionEnum) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case DONE:
                    return new DoneCommand(fullCommand);
                case DELETE:
                    return new DeleteCommand(fullCommand);
                case TODO:
                case DEADLINE:
                case EVENT:
                    return new AddCommand(fullCommand, userInstructionEnum);
                default:
                    throw new DukeException("Please enter a valid instruction (eg. todo, list, done...)");
            }
        } catch (IllegalArgumentException i) {
            throw new DukeException("Please enter a valid instruction (eg. todo, list, done...)");
        }

    }
}


