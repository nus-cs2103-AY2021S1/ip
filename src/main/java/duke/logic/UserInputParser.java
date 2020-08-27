package duke.logic;

import duke.CommonMethod;
import duke.command.*;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidInstructionException;
import duke.exception.MissingFieldException;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.time.LocalDateTime;

/**
 * Represents a Parser of the user Commands.
 * It takes in the user's instructions and generates the corresponding command
 * if it exists.
 */
public class UserInputParser {

    // INSTRUCTIONS and related CONSTANTS
    // Kept as Strings because of certain constants (indicators) that are related to certain instructions
    // Convenient to use - final keeps them as constant values
    private static final String BYE = "bye";
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String BY_INDICATOR = "/by";
    private static final String EVENT = "event";
    private static final String AT_INDICATOR = "/at";
    private static final String UNKNOWN = "unknown";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

    /**
     * Parses the user instruction into a <code>Command</code>.
     * It breaks down the input <code>String</code> into an array and analyses the first word of it (tag).
     * Referring to the tag, it performs an input validation to ensure the required <code>Strings</code>
     * are present to generate the respective <code>Command</code>.
     * NOTE THAT IT JUST CHECKS FOR THE PRESENCE OF THE STRING, AND NOT WHETHER THE STRING PRODUCES A VALID COMMAND.
     * Finally, it returns a <code>Command</code> to the user.
     *
     * @param userInput <code>String</code> containing the user instruction.
     * @return Command object denoting the corresponding command.
     * @throws InvalidInstructionException If instruction does not exist.
     * @throws MissingFieldException       If the instruction has missing Strings
     * @throws InvalidFormatException      If the instruction has correct format and fields, but incorrect format.
     */
    public static Command parse(String userInput)
            throws InvalidInstructionException, MissingFieldException, InvalidFormatException {

        // Instruction Setup - split by whitespace to check
        String instruction = userInput.trim();
        String[] instructionArray = instruction.split(" ");
        int instrLen = instructionArray.length;
        String instructionTag = instruction.split(" ")[0]; // indicates if instruction or not

        switch (instructionTag) {
        case BYE:
            if (InputValidator.validateSizeOne(instrLen, instructionTag, true)) {
                return new ExitCommand();
            }
            break;
        case HELP:
            if (InputValidator.validateSizeOne(instrLen, instructionTag, true)) {
                return new HelpCommand();
            }
            break;
        case LIST:
            if (InputValidator.validateSizeOne(instrLen, instructionTag, true)) {
                return new ListCommand();
            }
        case TODO:
            if (InputValidator.validateSizeOne(instrLen, instructionTag, false)) {
                TodoTask todotask = new TodoTask(CommonMethod.mergeArray(instructionArray, 1, instrLen));
                return new AddCommand(todotask);
            }
            break;
        case DONE:
            if (InputValidator.validateSizeTwoAndInt(instructionArray, instructionTag)) {
                return new DoneCommand(Integer.parseInt(instruction.split(" ")[1]) - 1);
            }
            break;
        case DELETE:
            if (InputValidator.validateSizeTwoAndInt(instructionArray, instructionTag)) {
                return new DeleteCommand(Integer.parseInt(instruction.split(" ")[1]) - 1);
            }
            break;
        case DEADLINE:
            int byIndex = findIndex(instructionArray, BY_INDICATOR);
            if (InputValidator.validateDescriptionAndDateTime(instructionArray, DEADLINE, byIndex)
                    && (InputValidator.validateDateAndTime(instructionArray, DEADLINE, byIndex))) {
                return new AddCommand(generateTaskWithDate(DEADLINE, instructionArray, BY_INDICATOR));
            }
            break;
        case EVENT:
            int atIndex = findIndex(instructionArray, AT_INDICATOR);
            if (InputValidator.validateDescriptionAndDateTime(instructionArray, EVENT, atIndex)
                    && (InputValidator.validateDateAndTime(instructionArray, EVENT, atIndex))) {
                return new AddCommand(generateTaskWithDate(EVENT, instructionArray, AT_INDICATOR));
            }
            break;
        case FIND:
            if (InputValidator.validateSizeTwo(instructionArray, instructionTag)) {
                return new FindCommand(instructionArray[1]);
            }
            break;
        }
        throw new InvalidInstructionException(UNKNOWN);
    }

    /**
     * Generates <code>DukeTasks</code> that contain Date and Time.
     * It extracts the relevant Date and Time fields from the instructionArray,
     * parses them into a <code>LocalDateTime</code> object and
     * returns the <code>DukeTask</code> containing the required fields.
     *
     * @param taskType         <code>String</code> containing the type of <code>DukeTask</code> to generate.
     * @param instructionArray <code>Array</code> containing the processed user instruction
     * @param indicator        <code>String</code> to verify the instruction type.
     * @return DukeTask object denoting the corresponding DukeTask.
     * @throws InvalidFormatException If the instruction has correct format and fields, but incorrect format.
     */
    private static DukeTask generateTaskWithDate(String taskType, String[] instructionArray,
                                                 String indicator)
            throws InvalidFormatException {

        // find the index of the indicator
        int index = findIndex(instructionArray, indicator);

        String description = CommonMethod.mergeArray(instructionArray, 1, index);

        String date = instructionArray[index + 1];
        String time = instructionArray[index + 2];

        // parse date and time into LocalDateTime object
        LocalDateTime dateTime = parseDateAndTime(taskType, date, time);

        return taskType.equals(DEADLINE)
                ? new DeadlineTask(description, dateTime)
                : new EventTask(description, dateTime);
    }

    /**
     * Parses input variables into a <code>LocalDateTime</code> object.
     *
     * @param taskType <code>String</code> containing the type of <code>DukeTask</code> to generate.
     * @param date     <code>String</code> containing the Date of the Task.
     * @param time     <code>String</code> containing the Time of the Task.
     * @return LocalDateTime object denoting the corresponding Date and Time.
     * @throws InvalidFormatException If the instruction has correct format and fields, but incorrect format.
     */
    private static LocalDateTime parseDateAndTime(String taskType, String date, String time)
            throws InvalidFormatException {
        // INPUT DATE FORMAT: DD/MM/YYYY
        // INPUT TIME FORMAT: hh/mm/ss
        int year, month, day, hour, minute, second;

        String[] dateArray = date.split("/");
        String[] timeArray = time.split("/");

        try {
            day = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            year = Integer.parseInt(dateArray[2]);

            hour = Integer.parseInt(timeArray[0]);
            minute = Integer.parseInt(timeArray[1]);
            second = Integer.parseInt(timeArray[2]);
        } catch (NumberFormatException nfe) {
            throw new InvalidFormatException(taskType + " DATE AND TIME");
        }

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Finds the index of the given regex.
     *
     * @param array <code>Array</code> containing Strings of instructions.
     * @param regex <code>String</code> to be found.
     * @return Integer denoting the location of the regex.
     */
    private static int findIndex(String[] array, String regex) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(regex)) {
                return i;
            }
        }
        return -1;
    }
}

