package duke.logic;

import duke.CommonMethod;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidInstructionException;
import duke.exception.MissingFieldException;

/**
 * Contains input validation methods used by <code>UserInputParser</code>
 */
public class InputValidator {

    /**
     * Validates the instruction <code>String</code> is of size 1.
     *
     * @param instrLen  <code>String</code> containing length of user instruction.
     * @param instrType <code>String</code> containing type of instruction.
     * @param isLenOne  <code>boolean</code> to note if the output should equate to 1.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionException If len==1 is not equal to isLenOne.
     */
    public static boolean validateSizeOne(int instrLen, String instrType, boolean isLenOne)
            throws InvalidInstructionException {
        if ((instrLen == 1) == isLenOne) { // i want instrLen == 1 to be isOne
            return true;
        } else {
            throw new InvalidInstructionException(instrType);
        }
    }

    /**
     * Validates the instruction <code>String Array</code> is of size 2
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @param instrType        <code>String</code> containing type of instruction.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionException If validation fails.
     */
    public static boolean validateSizeTwo(String[] instructionArray, String instrType)
            throws InvalidInstructionException {
        if (instructionArray.length == 2) {
            return true;
        } // if len != 2 or the input is not an integer
        throw new InvalidInstructionException(instrType);
    }

    /**
     * Validates the instruction <code>String Array</code> is of size 2
     * and the second <code>String</code> can be parsed into <code>Integer</code>.
     *
     * @param instructionArray <code>String</code> containing length of user instruction.
     * @param instrType        <code>String</code> containing type of instruction.
     * @return boolean denoting the validation results
     * @throws InvalidInstructionException If validation fails.
     */
    public static boolean validateSizeTwoAndInt(String[] instructionArray, String instrType)
            throws InvalidInstructionException {
        if (instructionArray.length == 2 && (isNumeric(instructionArray[1]))) {
            return true;
        } // if len != 2 or the input is not an integer
        throw new InvalidInstructionException(instrType);
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
     * @param instrType        <code>String</code> containing type of instruction.
     * @param index            <code>Integer</code> containing location of separator.
     * @return boolean denoting the validation results
     * @throws InvalidFormatException If index does not exist.
     * @throws MissingFieldException  If validation fails.
     */
    public static boolean validateDescriptionAndDateTime(String[] instructionArray, String instrType, int index)
            throws InvalidFormatException, MissingFieldException {

        if (index == -1) { // does not exist
            throw new InvalidFormatException(instrType);
        }

        String atDescription = CommonMethod.mergeArray(instructionArray, 1, index);
        if (atDescription.equals("")) {
            throw new MissingFieldException(instrType + ": Description");
        }

        if (instructionArray.length < index + 3) { // account for index + date + time
            throw new MissingFieldException(instrType + ": Date and Time");
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
     * @param instrType        <code>String</code> containing type of instruction.
     * @param index            <code>Integer</code> containing location of separator.
     * @return boolean denoting the validation results
     * @throws InvalidFormatException If index does not exist.
     */
    public static boolean validateDateAndTime(String[] instructionArray, String instrType, int index)
            throws InvalidFormatException {

        String date = instructionArray[index + 1];
        String time = instructionArray[index + 2];

        // INPUT DATE FORMAT: DD/MM/YYYY
        // INPUT TIME FORMAT: hh/mm/ss
        int year, month, day, hour, minute, second;

        String[] dateArray = date.split("/");
        String[] timeArray = time.split("/");

        if (dateArray.length != 3 || timeArray.length != 3) {
            throw new InvalidFormatException(instrType + " DATE AND TIME");
        }

        try {
            day = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            year = Integer.parseInt(dateArray[2]);

            hour = Integer.parseInt(timeArray[0]);
            minute = Integer.parseInt(timeArray[1]);
            second = Integer.parseInt(timeArray[2]);
        } catch (NumberFormatException nfe) {
            throw new InvalidFormatException(instrType + " DATE AND TIME");
        }

        // input validation for time
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
            throw new InvalidFormatException(instrType + " TIME FORMAT");
        }

        // input validation for date
        if (year <= 0 || month <= 0 || month > 12 || day <= 0) {
            throw new InvalidFormatException(instrType + " DATE FORMAT");
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
                throw new InvalidFormatException(instrType + " DATE FORMAT");
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            if (day > 30) {
                throw new InvalidFormatException(instrType + " DATE FORMAT");
            }
            break;
        default:
            if ((CommonMethod.isLeapYear(year) && day > 29) || (!CommonMethod.isLeapYear(year) && day > 28)) {
                throw new InvalidFormatException(instrType + " DATE FORMAT");
            }
        }
        return true;
    }

    /**
     * Validates if the input <code>String</code> can be parsed into an integer.
     *
     * @param instruction <code>String</code> containing the integer.
     * @return boolean denoting the validation results
     */
    private static boolean isNumeric(String instruction) {
        try {
            Integer.parseInt(instruction);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

