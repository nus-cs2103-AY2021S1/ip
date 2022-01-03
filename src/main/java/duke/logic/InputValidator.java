package duke.logic;

import duke.CommonMethod;
import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidInstructionLengthException;
import duke.exception.MissingFieldException;

/**
 * Contains input validation methods used by <code>UserInputParser</code>
 */
public class InputValidator {

    /**
     * Validates the instruction <code>String</code> is of size 1.
     *
     * @param instrLen <code>String</code> containing length of user instruction.
     * @param isLenOne <code>boolean</code> to note if the output should equate to 1.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionLengthException If len==1 is not equal to isLenOne.
     */
    public static boolean validateSizeOne(int instrLen, boolean isLenOne)
            throws InvalidInstructionLengthException {
        if ((instrLen == 1) == isLenOne) { // i want instrLen == 1 to be isOne
            return true;
        }
        throw new InvalidInstructionLengthException();
    }

    /**
     * Validates the instruction <code>String Array</code> is of size 2
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionLengthException If validation fails.
     */
    public static boolean validateSizeTwo(String[] instructionArray)
            throws InvalidInstructionLengthException {
        assert instructionArray != null : "validateSizeTwo array cannot be null";
        if (instructionArray.length != 2) {
            throw new InvalidInstructionLengthException();
        } // if len != 2 or the input is not an integer
        return true;
    }

    /**
     * Validates the instruction <code>String Array</code> is of size 2
     * and the second <code>String</code> can be parsed into <code>Integer</code>.
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionLengthException If validation fails.
     */
    public static boolean validateSizeTwoAndInt(String[] instructionArray)
            throws InvalidInstructionLengthException, InvalidInstructionFormatException {
        assert instructionArray != null : "validateSizeTwoAndInt array cannot be null";
        if (instructionArray.length != 2) {
            throw new InvalidInstructionLengthException();
        }
        if (!isNumeric(instructionArray[1])) {
            throw new InvalidInstructionFormatException();
        }
        return true;
    }


    /**
     * Validates that the instructions are not empty <code>String</code>.
     * Using the instructionArray, the <code>Strings</code> are merged for indices
     * before and after the separator.
     * Location of separator is given by <code>Integer</code> index.
     * The <code>Strings</code> before and after the index at separator then have their lengths compared.
     * If the separator does not exist, the index is denoted with -1
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @param index            <code>Integer</code> containing location of separator.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionFormatException If index does not exist.
     * @throws MissingFieldException             If validation fails.
     */
    public static boolean validateDescription(String[] instructionArray, int index)
            throws MissingFieldException, InvalidInstructionFormatException {
        assert instructionArray != null : "validateDescriptionAndDateTime array cannot be null";

        if (index == -1) { // does not exist
            throw new InvalidInstructionFormatException();
        }

        String description = CommonMethod.mergeArray(instructionArray, 1, index);
        if (description.equals("")) {
            throw new MissingFieldException();
        }
        return true;
    }

    /**
     * Validates that the instructions have correct Date and Time formats.
     * Date and Time exists as <code>Strings</code> in the array.
     * They are extracted and split into their constituent <code>Integer</code> values and parsed.
     * If the parse fails, an error is raised.
     * Else, they are put through validation to ensure it is proper Date and Time values.
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @param index            <code>Integer</code> containing location of separator.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionFormatException If validation fails
     */
    public static boolean validateDateAndTime(String[] instructionArray, int index)
            throws InvalidInstructionFormatException, MissingFieldException {
        assert instructionArray != null : "validateDateAndTime array cannot be null";
        if (instructionArray.length < index + 3) { // account for index + date + time
            throw new MissingFieldException();
        }
        String regex = "/"; // regex to split DateTime format
        String date = instructionArray[index + 1];
        String time = instructionArray[index + 2];
        String[] dateArray = date.split(regex);
        String[] timeArray = time.split(regex);

        if (dateArray.length != 3 || timeArray.length != 3) {
            throw new InvalidInstructionFormatException();
        }

        try {
            // INPUT DATE FORMAT: DD/MM/YYYY
            // INPUT TIME FORMAT: hh/mm/ss
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);

            int hour = Integer.parseInt(timeArray[0]);
            int minute = Integer.parseInt(timeArray[1]);
            int second = Integer.parseInt(timeArray[2]);

            // input validation for time
            validateTimeFormat(hour, minute, second);
            // input validation for date
            validateDateFormat(year, month, day);
        } catch (NumberFormatException nfe) {
            throw new InvalidInstructionFormatException();
        }
        return true;
    }

    /**
     * Validates that time format used is correct
     *
     * @param hour   Integer denoting the hour
     * @param minute Integer denoting the minute
     * @param second Integer denoting the second
     * @throws InvalidInstructionFormatException if format is wrong
     */
    private static void validateTimeFormat(int hour, int minute, int second)
            throws InvalidInstructionFormatException {
        // input validation for time
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
            throw new InvalidInstructionFormatException();
        }
    }

    /**
     * Validates that date format used is correct
     *
     * @param year  Integer denoting the year
     * @param month Integer denoting the month
     * @param day   Integer denoting the day
     * @throws InvalidInstructionFormatException if format is wrong
     */
    private static void validateDateFormat(int year, int month, int day) throws InvalidInstructionFormatException {
        if (year <= 0 || month <= 0 || month > 12 || day <= 0) {
            throw new InvalidInstructionFormatException();
        }

        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (day > 31) {
                throw new InvalidInstructionFormatException();
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            if (day > 30) {
                throw new InvalidInstructionFormatException();
            }
            break;
        default:
            if ((CommonMethod.isLeapYear(year) && day > 29) || (!CommonMethod.isLeapYear(year) && day > 28)) {
                throw new InvalidInstructionFormatException();
            }
        }
    }

    /**
     * Validates if the input <code>String</code> can be parsed into an integer.
     *
     * @param instruction <code>String</code> containing the integer.
     * @return boolean denoting the validation results
     */
    private static boolean isNumeric(String instruction) {
        assert instruction != null : "isNumeric String cannot be null";
        try {
            Integer.parseInt(instruction);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

