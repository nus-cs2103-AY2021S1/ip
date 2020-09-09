package duke;

import java.util.Map;
import java.util.stream.Stream;

import javafx.util.Pair;

/**
 * Parses user inputs or saved strings.
 */
public class Parser {
    private Map<String, ParserResult> stringParserResultMap = Map.of(
        "bye", ParserResult.BYE,
        "list", ParserResult.LIST,
        "done", ParserResult.DONE,
        "delete", ParserResult.DELETE,
        "find", ParserResult.FIND,
        "todo", ParserResult.ADD,
        "deadline", ParserResult.ADD,
        "event", ParserResult.ADD
    );

    /**
     * Returns the index of the task to be marked done.
     *
     * @param s The input.
     * @return An integer index.
     */
    public int parseDoneIndex(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @param s The input.
     * @return An integer index.
     */
    public int parseDeleteIndex(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    /**
     * Returns the keyword to be searched.
     *
     * @param s The input.
     * @return A string keyword.
     */
    public String parseFindKeyword(String s) {
        return s.split(" ")[1];
    }

    // Returns an array of the input string split by whitespace.
    private String[] inputToArray(String s) {
        return s.trim().split(" +");
    }

    /**
     * Returns a task represented by the input.
     *
     * @param s The input.
     * @return A task.
     * @throws IllegalArgumentException Unrecognizable task command.
     * @throws IndexOutOfBoundsException Task formatted wrongly.
     */
    public Task parseAddedTask(String s) throws IndexOutOfBoundsException {
        s = s.trim();
        String[] processedArray = inputToArray(s);
        String taskType = processedArray[0].toLowerCase();
        assert("todo".startsWith(taskType) || "deadline".startsWith(taskType) || "event".startsWith(taskType));
        Task processedTask = null;
        try {
            String[] stringsAfterType = s.split(String.format("%s +", processedArray[0]));
            if (stringsAfterType.length <= 1) {
                throw new IndexOutOfBoundsException("Task formatted incorrectly.");
            }
            String stringAfterType = stringsAfterType[1];
            String[] arguments;
            if ("todo".startsWith(taskType)) {
                processedTask = ToDoTask.of(stringAfterType);
            } else if ("deadline".startsWith(taskType)) {
                arguments = stringAfterType.split(" +/by +");
                if (arguments.length != 2) {
                    throw new IndexOutOfBoundsException("Deadline task formatted incorrectly.");
                }
                processedTask = DeadlineTask.of(arguments);
            } else if ("event".startsWith(taskType)) {
                arguments = stringAfterType.split(" +/at +");
                if (arguments.length != 2) {
                    throw new IndexOutOfBoundsException("Event task formatted incorrectly.");
                }
                processedTask = EventTask.of(arguments);
            } else {
                assert(false);
            }
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        assert(processedTask != null);
        return processedTask;
    }

    /**
     * Determines the type of command.
     *
     * @param input The input.
     * @return A parserResult value.
     * @throws IllegalArgumentException Unrecognizable command.
     */
    public ParserResult parseInput(String input) throws IllegalArgumentException {
        input = input.trim();
        final String inputCommand = inputToArray(input)[0].toLowerCase();
        Stream<String> commandStream = stringParserResultMap.keySet().stream();
        Pair<Integer, ParserResult> countCommandPair =
            commandStream.reduce(
                new Pair<>(0, null),
                (pair, command) ->
                    command.startsWith(inputCommand)
                        ? new Pair<>(pair.getKey() + 1, stringParserResultMap.get(command))
                        : pair,
                (p1, p2) ->
                    new Pair<>(p1.getKey() + p2.getKey(), p1.getValue() != null ? p1.getValue() : p2.getValue()));
        if (countCommandPair.getKey() == 1) {
            return countCommandPair.getValue();
        } else if (countCommandPair.getKey() > 1) {
            throw new IllegalArgumentException("Ambiguous command.");
        } else {
            throw new IllegalArgumentException("No matching commands.");
        }
    }

    /**
     * Parse a saved string to a task.
     *
     * @param s The saved string.
     * @return The saved task.
     */
    public static Task parseSavedTask(String s) {
        String[] arg = s.split(" @@ ");
        Task task;
        switch (arg[0]) {
        case "T":
            task = new ToDoTask(arg[2]);
            break;
        case "D":
            task = new DeadlineTask(arg[2], arg[3]);
            break;
        case "E":
            task = new EventTask(arg[2], arg[3]);
            break;
        default:
            throw new IllegalArgumentException("Save file corrupted.");
        }
        if (arg[1].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
