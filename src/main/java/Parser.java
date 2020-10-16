import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Represents a parser that deals with making sense of the user input.
 */
public class Parser {

    /**
     * How the parser will respond to users' input.
     *
     * @param input  Full input by the user.
    // * @param ui       Ui used in responding.
     * @param taskList Task list referred to in the interaction.
     * @param filePath The relative path to assigned file for reading
     *                 and writing of data.
     * @return String Response to be returned
     */
    public static String respond(String input, TaskList taskList, String filePath) {
        Command command = Parser.parseIntoCommand(input, taskList, filePath);
        String response = command.execute();
        return response;
    }


    public static Command parseIntoCommand(String input,TaskList taskList, String filePath) {
        String[] pieces = input.split(" ", 2);
        assert (pieces[0] != null) : "Incorrect splitting.";

        // 2 or more word input
        if (pieces.length == 2) {
            String firstWord = pieces[0];
            switch (firstWord) {
                case "find":
                    return new FindCommand(taskList, pieces[1]);

                case "done":
                    return new DoneCommand(taskList, filePath, pieces[1]);


                case "delete":
                    return new DeleteCommand(taskList, filePath, pieces[1]);


                case "tag":
                    return new TagCommand(taskList, filePath, pieces[1]);


                case "todo":
                    return new AddCommand(taskList, filePath, "todo", pieces[1]);


                case "deadline":
                    return new AddCommand(taskList, filePath, "deadline", pieces[1]);


                case "event":
                    return new AddCommand(taskList, filePath, "event", pieces[1]);


                default:
                    return new InvalidCommand(Ui.unknownCommand());

            }
        }

        // single word input
        switch (input) {
            case "bye" :
                return new ByeCommand();

            case "help" :
                return new HelpCommand();

            case "list" :
                return new ListCommand(taskList, filePath);

            case "find" :
                return new InvalidCommand(Ui.missingFindKeyword());

            case "done" :
                return new InvalidCommand(Ui.missingDoneIndex());

            case "delete" :
                return new InvalidCommand(Ui.incompleteDeleteCommand());

            case "tag" :
                return new InvalidCommand(Ui.incompleteTagCommand());

            case "todo":
                return new InvalidCommand(Ui.missingDescription("todo"));

            case "deadline":
                return new InvalidCommand(Ui.missingDescription("deadline"));

            case "event":
                return new InvalidCommand(Ui.missingDescription("event"));

            default:
                return new InvalidCommand(Ui.unknownCommand());
        }
    }
}




