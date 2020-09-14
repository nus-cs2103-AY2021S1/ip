package duke.helpers;


import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.RandomCommand;
import duke.commands.ShortCutCommand;
import duke.commands.TodoCommand;

/**
 * This is a Parser class that determines which command operation to choose, which in turn determines
 * the action to be taken.
 */
public class Parser {

    /**
     * Returns a Command, depending on the string being input
     *
     * @param input where the first words determines command to be returned
     * @return Command is returned based on the first word of param string
     */
    public static Command parse(String input) {
        assert !input.contains(","); //to ensure that no , appears when multiple inputs are given
        if (ShortCuts.containsShortCut(input)) {
            String properInput = shortCutStringUpdate(input);
            return parse(properInput);
        } else if (isShortCutDefined(input)) {
            return shortCutCommand(input);
        } else if (isBye(input)) { //represents ExitCommand
            return exitCommand(input);
        } else if (isList(input)) { //represents ListCommand
            return listCommand(input);
        } else if (isDelete(input)) { //represents deleteCommand
            return deleteCommand(input);
        } else if (isDone(input)) { //represents doneCommand
            return doneCommand(input);
        } else if (isTodo(input)) { //represents ToDoCommand
            return todoCommand(input);
        } else if (isEvent(input)) { //represents EventCommand
            return eventCommand(input);
        } else if (isDeadline(input)) { //represents DeadlineCommand
            return deadlineCommand(input);
        } else if (isFind(input)) { //represents FindCommand
            return findCommand(input);
        } else { //rest are RandomCommand
            return randomCommand(input);
        }
    }

    /**
     * checks whether short cut is attempted to be defined
     *
     * @param input String by user
     * @return boolean whether short cut is attempted to be defined
     */
    private static boolean isShortCutDefined(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("short");
    }

    /**
     * checks whether bye is said by user
     *
     * @param input String by user
     * @return boolean whether bye is said, true if bye is said and false otherwise
     */
    private static boolean isBye(String input) {
        return input.length() >= 3 && input.equals("bye");
    }

    /**
     * checks whether list is said by user
     *
     * @param input String by user
     * @return boolean whether list is said, true if list is said and false otherwise
     */
    private static boolean isList(String input) {
        return input.length() >= 4 && input.equals("list");
    }

    /**
     * checks whether delete is said by user
     *
     * @param input String by user
     * @return boolean whether delete is said, true if delete is said and false otherwise
     */
    private static boolean isDelete(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("delete");
    }

    /**
     * checks whether done is said by user
     *
     * @param input String by user
     * @return boolean whether done is said, true done is said and false otherwise
     */
    private static boolean isDone(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("done");
    }

    /**
     * checks whether todo is said by user
     *
     * @param input String by user
     * @return boolean whether todo is said, true if bye is said and false otherwise
     */
    private static boolean isTodo(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("todo");
    }

    /**
     * checks whether event is said by user
     *
     * @param input String by user
     * @return boolean whether event is said, true if event is said and false otherwise
     */
    private static boolean isEvent(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("event");
    }

    /**
     * checks whether deadline is said by user
     *
     * @param input String by user
     * @return boolean whether deadline is said, true if deadline is said and false otherwise
     */
    private static boolean isDeadline(String input) {
        return input.length() >= 8 && input.substring(0, 8).equals("deadline");
    }

    /**
     *checks whether find is said by user
     *
     * @param input String by user
     * @return boolean whether find is said, true if find is said and false otherwise
     */
    private static boolean isFind(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("find");
    }

    private static ShortCutCommand shortCutCommand(String input) {
        int lengthOfKeyword = "short".length();
        return new ShortCutCommand(input, lengthOfKeyword);
    }
    /**
     * Gives exit command
     *
     * @param input String given by user
     * @return ExitCommand
     */
    private static ExitCommand exitCommand(String input) {
        int lengthOfKeyWord = "list".length();
        return new ExitCommand(input, lengthOfKeyWord); //when in short form
    }

    /**
     * Gives list command
     *
     * @param input String given by user
     * @return ListCommand
     */
    private static ListCommand listCommand(String input) {
        int lengthOfKeyWord = "list".length();
        return new ListCommand(input, lengthOfKeyWord); //when in short form
    }

    /**
     * Gives delete command
     *
     * @param input String given by user
     * @return DeleteCommand
     */
    private static DeleteCommand deleteCommand(String input) {
        int lengthOfKeyWord = "delete".length();
        return new DeleteCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * Gives done command
     *
     * @param input String given by user
     * @return DoneCommand
     */
    private static DoneCommand doneCommand(String input) {
        int lengthOfKeyWord = "done".length();
        return new DoneCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * Gives todo command
     *
     * @param input String given by user
     * @return ToDoCommand
     */
    private static TodoCommand todoCommand(String input) {
        int lengthOfKeyWord = "todo".length();
        return new TodoCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * Gives event command
     *
     * @param input String given by user
     * @return EventCommand
     */
    private static EventCommand eventCommand(String input) {
        int lengthOfKeyWord = "event".length();
        return new EventCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * Gives deadline command
     *
     * @param input String given by user
     * @return DeadlineCommand
     */
    private static DeadlineCommand deadlineCommand(String input) {
        int lengthOfKeyWord = "deadline".length();
        return new DeadlineCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * Gives exit command
     *
     * @param input String given by user
     * @return ExitCommand
     */
    private static FindCommand findCommand(String input) {
        int lengthOfKeyWord = "find".length();
        return new FindCommand(input, lengthOfKeyWord); //when not in short form
    }

    /**
     * If short cuts is present it gives the original string, converts it to proper form.
     *
     * @param input String given by user
     * @return gives proper String input without the short form
     */
    public static String shortCutStringUpdate(String input) {
        String[] strings = input.split(" ");
        String command = ShortCuts.getShortCuts().get(strings[0]);
        String properInput = command;

        for (int i = 1; i < strings.length; i++) {
            properInput = properInput + " " + strings[i];
        }
        return properInput;
    }

    /**
     * Gives random command when user doesn't make sense
     *
     * @param input String given by user
     * @return RandomCommand
     */
    private static RandomCommand randomCommand(String input) {
        return new RandomCommand(input, -1);
    }



}
