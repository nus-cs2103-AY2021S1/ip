package duke.logic;
// INPUT PARSER JUST CHECKS IF THE FIELDS ARE MISSING OR NOT
// OR ARE IN THEIR CORRECT FORMATS
// DOES NOT CHECK IF THE VALUES ARE VALID OR NOT
// IN THE SENSE IT ASKS: IS YOUR COMMAND SOMETHING DUKE CAN UNDERSTAND?
// THIS DOES NOT MEAN DUKE CAN EXECUTE IT BECAUSE THE DETAILS MAY NOT BE RIGHT

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

// PARSER JUST CHECKS: HEY IS THIS FORMAT CORRECT FOR DUKE INSTRUCTIONS?
// ARE THERE MISSING FIELDS, OR INCORRECT FORMAT FOR THE COMMANDS?
// ARE THESE COMMANDS EXISTING?
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

    // TAKES IN USER COMMAND
    // PERFORMS INPUT VERIFICATION
    // AND RETURNS EITHER A COMMAND, OR THROWS A DUKE_EXCEPTION
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

    // GENERATOR FUNCTIONS FOR TASKS (DEADLINE AND EVENT)
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

        return taskType.equals(DEADLINE) ? new DeadlineTask(description, dateTime) : new EventTask(description, dateTime);
    }

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


    // HELPER FUNCTIONS
    private static int findIndex(String[] array, String regex) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(regex)) {
                return i;
            }
        }
        return -1;
    }
}

