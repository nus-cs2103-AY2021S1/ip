import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parses input by the user and identifies the commands.
 */
public class Parser {

    private static final int LIST_COMMAND = 1;
    private static final int DONE_COMMAND = 2;
    private static final int DELETE_COMMAND = 3;
    private static final int FIND_COMMAND = 4;
    private static final int UPDATE_COMMAND = 5;
    private static final int TASK_COMMAND = 6;


    /**
     * Parses the input by the user in the user interface.
     * @param currInput the current input of the user.
     * @param sizeOfList The current size of the task list.
     * @return int A number that represents the status of the input.
     * @throws InvalidCommandException If command is of invalid format.
     */
    public int parse(String currInput, int sizeOfList) throws InvalidCommandException {
        assert(sizeOfList >= 0);
        //Cuts white space before and after the command
        String input = currInput.trim();

        if (isListCommand(input)) {
            return LIST_COMMAND;
        } else if (isDoneCommand(input)) {
            verifyDoneOrDeleteCommand(input, sizeOfList);
            return DONE_COMMAND;
        } else if (isDeleteCommand(input)) {
            verifyDoneOrDeleteCommand(input, sizeOfList);
            return DELETE_COMMAND;
        } else if (isFindCommand(input)) {
            verifyFindCommand(input);
            return FIND_COMMAND;
        } else if (isUpdateCommand(input)) {
            verifyUpdateCommand(input, sizeOfList);
            return UPDATE_COMMAND;
        } else {
            return TASK_COMMAND;
        }
    }

    /**
     * Retrieves the index for a delete, done or update command in the input string.
     * @param input The user input.
     * @return int The index of the task which the user wants to delete/mark as done.
     */
    public int getIndex(String input) {
        String[] words = getStringTokens(input);

        if (isUpdateCommand(input.trim())) {
            return Integer.parseInt(words[2]) - 1;
        }
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * Retrieves the keywords for a find command in the input string.
     * @param input The user input.
     * @return String The keyword that the user is looking for.
     */
    public String[] getKeyword(String input) {
        String[] tokens = getStringTokens(input);
        return Arrays.copyOfRange(tokens, 1, tokens.length);
    }

    /**
     * Gets the type of update that the user requests based on their update command.
     * @param input The user's input.
     * @return String The update type the user is requesting for.
     */
    public String getUpdateType(String input) {
        return getStringTokens(input)[1];
    }

    /**
     * Checks if the following task type can be updated based on the user's update command.
     * @param updateType The type of update the user is initiating.
     * @param taskType The type of the task that the user wishes to update.
     * @throws InvalidCommandException
     */
    public void verifyTaskCanUpdate(String updateType, String taskType) throws InvalidCommandException {
        if (updateType.equals("time") || updateType.equals("date")) {
            if (taskType.equals("Task")) {
                throw new InvalidCommandException("Sorry, can't update the task this way.");
            }
        }
    }

    /**
     * Translates a task command to a Task object.
     * @param input The user input.
     * @return Task The task to be put in the schedule.
     * @throws InvalidInputException If input is not a valid command.
     * @throws InvalidCommandException If command is of invalid format.
     */
    public Task getTask(String input) throws InvalidInputException, InvalidCommandException {
        String[] words = getStringTokens(input);
        String taskType = words[0];

        if (taskType.isBlank()) {
            throw new InvalidInputException("Please input something!");
        }

        try {
            if (taskType.equals("todo")) {
                String description = getDescription(words);

                return new Task(description);
            }

            if (taskType.equals("deadline")) {
                String keyword = "/by";

                String description = getDescription(words, keyword);
                LocalDate date = getDate(words, keyword);
                LocalTime time = getTime(words, keyword);

                return new Deadline(description, date, time);
            }

            if (taskType.equals("event")) {
                String keyword = "/at";

                String description = getDescription(words, keyword);
                LocalDate date = getDate(words, keyword);
                LocalTime time = getTime(words, keyword);

                return new Event(description, date, time);
            } else {
                throw new InvalidInputException("Sorry, I don't know what " + "\"" + input + "\"" + " means.");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (InvalidInputException e) {
            throw e;
        }
    }

    /**
     * Checks if the user has correctly inputted the update that they have requested for.
     * @param input The user's input.
     * @param updateType The update type that the user is initiating.
     * @return int The status of the update.
     * @throws InvalidCommandException If the input does not match the update type that the user is initiating.
     */
    public int checkInputMatchesUpdate(String input, String updateType) throws InvalidCommandException {
        String trimmedInput = input.trim();
        int changeDate = 1;
        int changeTime = 2;
        int changeTask = 3;
        int changeDesc = 4;

        if (updateType.equals("time")) {
            try {
                LocalTime.parse(trimmedInput);
                return changeTime;
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException("Please give your time in hh:mm format!");
            }
        }
        if (updateType.equals("date")) {
            try {
                LocalDate.parse(trimmedInput);
                return changeDate;
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException("Please give your date in yyyy-mm-dd format!");
            }
        }
        if (updateType.equals("task")) {
            return changeTask;
        }

        return changeDesc;
    }

    private static void verifyDoneOrDeleteCommand(String input, int numOfTasks) throws InvalidCommandException {
        String[] words = getStringTokens(input);

        if (words.length == 1) {
            throw new InvalidCommandException("Please give an index.");
        }

        if (words.length > 2) {
            throw new InvalidCommandException("Sorry wrong format!");
        }

        try {
            int index = Integer.parseInt(words[1]) - 1;

            boolean numInRange = index > -1 && index < numOfTasks;

            if (!numInRange) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(("Please input number after the command!"));
        }
    }

    private static void verifyFindCommand(String input) throws InvalidCommandException {
        String[] tokens = getStringTokens(input);
        if (tokens.length < 2) {
            throw new InvalidCommandException("Find what?");
        }
    }

    private static void verifyUpdateCommand(String input, int numOfTasks) throws InvalidCommandException {
        String[] words = getStringTokens(input);

        //Should have 3 parts, the "update" command, the update type and the index.
        if (words.length != 3) {
            throw new InvalidCommandException("Sorry wrong format.");
        }

        String updateType = words[1];
        if (!updateType.equals("time") && !updateType.equals("date") && !updateType.equals("task")
                && !updateType.equals("desc")) {
            throw new InvalidCommandException("Sorry I can't update that.");
        }

        try {
            int index = Integer.parseInt(words[2]) - 1;
            boolean numInRange = index > -1 && index < numOfTasks;

            if (!numInRange) {
                throw new InvalidCommandException("Number is invalid!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Please input a number!");
        }

    }

    private static boolean isListCommand(String input) {
        return input.equals("list");
    }

    private static boolean isDoneCommand(String input) {
        return input.startsWith("done");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    private static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    private static boolean isUpdateCommand(String input) {
        return input.startsWith("update");
    }

    /**
     * Checks if input is "bye" which is the terminating command.
     * @param input The user's input.
     * @return boolean True if command is equals to "bye", returns false otherwise.
     */
    public boolean isTerminateCommand(String input) {
        return input.trim().equals("bye");
    }

    /**
     * Checks if input is "abort" which is terminates the update.
     * @param input The user's input.
     * @return boolean True if command equals to "abort", returns false otherwise.
     */
    public boolean isAbortUpdate(String input) {
        return input.trim().equals("abort");
    }

    private static String[] getStringTokens(String input) {
        return input.trim().split("\\s+");
    }

    private static String getDescription (String[] inputWords, String keyword) throws InvalidCommandException {
        int keywordIndex = -1;
        boolean hasKeyword = false;

        for (int i = 0; i < inputWords.length; i++) {
            if (inputWords[i].equals(keyword)) {
                keywordIndex = i;
                hasKeyword = true;
                break;
            }
        }

        if (!hasKeyword) {
            throw new InvalidCommandException("Missing " + keyword + " keyword!");
        }

        String description = "";
        for (int i = 1; i < keywordIndex; i++) {
            description += inputWords[i] + " ";
        }

        if (description.isBlank()) {
            throw new InvalidCommandException("Missing task description!");
        }

        return description;
    }

    private static String getDescription(String[] inputWords) throws InvalidCommandException {
        if (inputWords.length <= 1 || inputWords[1].isBlank()) {
            throw new InvalidCommandException("Missing task description!");
        }

        String description = "";
        for (int i = 1; i < inputWords.length; i++) {
            description += inputWords[i] + " ";
        }
        return description;
    }

    private static LocalDate getDate(String[] inputWords, String keyword) throws InvalidCommandException {
        int keywordIndex = -1;
        boolean hasKeyword = false;

        for (int i = 0; i < inputWords.length; i++) {
            if (inputWords[i].equals(keyword)) {
                keywordIndex = i;
                hasKeyword = true;
                break;
            }
        }

        if (!hasKeyword) {
            throw new InvalidCommandException("Missing " + keyword + " keyword!");
        }

        try {
            String taskDate = inputWords[keywordIndex + 1];
            LocalDate date = LocalDate.parse(taskDate);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Wrong date format!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Missing date!");
        }
    }

    private static LocalTime getTime(String[] inputWords, String keyword) throws InvalidCommandException {
        int keywordIndex = -1;
        boolean hasKeyword = false;

        for (int i = 0; i < inputWords.length; i++) {
            if (inputWords[i].equals(keyword)) {
                keywordIndex = i;
                hasKeyword = true;
                break;
            }
        }

        if (!hasKeyword) {
            throw new InvalidCommandException("Missing " + keyword + " keyword!");
        }

        try {
            String taskTime = inputWords[keywordIndex + 2];
            LocalTime time = LocalTime.parse(taskTime);
            return time;
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Wrong time format!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Missing time!");
        }
    }
}
