package logic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidInstructionLengthException;
import duke.exception.MissingFieldException;
import duke.logic.InputValidator;

public class InputValidatorTest {

    @Test
    public void testValidateSizeOne() throws InvalidInstructionLengthException {
        assertTrue(InputValidator.validateSizeOne(1, true));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeOne(1, false));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeOne(2, true));
        assertTrue(InputValidator.validateSizeOne(2, false));
    }

    @Test
    public void testValidateSizeTwo() throws InvalidInstructionLengthException {
        String[] twoArray = {"Hello", "World"};
        String[] threeArray = {"Hello", "World", "!"};
        assertTrue(InputValidator.validateSizeTwo(twoArray));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeTwo(threeArray));
    }

    @Test
    public void testValidateSizeTwoAndInt() throws InvalidInstructionLengthException,
            InvalidInstructionFormatException {
        String[] twoAndIntArray = {"Hello", "1"};
        String[] twoArray = {"Hello", "World"};
        String[] threeArray = {"Hello", "World", "!"};
        assertTrue(InputValidator.validateSizeTwoAndInt(twoAndIntArray));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateSizeTwoAndInt(twoArray));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeTwoAndInt(threeArray));
    }

    @Test
    public void testValidateDescription() throws
            InvalidInstructionFormatException, MissingFieldException {
        String[] emptyArray = new String[]{"tag", "", "/regex"};
        String[] array = new String[]{"tag", "description", "/regex"};
        String[] moreDescription = new String[]{"tag", "more", "description", "/regex"};
        assertTrue(InputValidator.validateDescription(array, 2));
        assertTrue(InputValidator.validateDescription(moreDescription, 3));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDescription(emptyArray, -1));
        assertThrows(MissingFieldException.class, (
        ) -> InputValidator.validateDescription(emptyArray, 2));
    }

    @Test
    public void testValidateDateFormat() throws
            InvalidInstructionFormatException, MissingFieldException {
        String[] dateTime = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/12"};
        String[] missingDateTime = new String[]{"tag", "description", "/regex", "12/12/2020"};
        String[] missingDay = new String[]{"tag", "description", "/regex", "/12/2020", "12/12/12"};
        String[] missingMonth = new String[]{"tag", "description", "/regex", "12//2020", "12/12/12"};
        String[] missingYear = new String[]{"tag", "description", "/regex", "12/12/", "12/12/12"};

        assertTrue(InputValidator.validateDateAndTime(dateTime, 2));
        assertThrows(MissingFieldException.class, () -> InputValidator.validateDateAndTime(missingDateTime, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingDay, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingMonth, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingYear, 2));

        String[] notIntDay = new String[]{"tag", "description", "/regex", "a/12/2020", "12/12/12"};
        String[] notIntMonth = new String[]{"tag", "description", "/regex", "12/b/2020", "12/12/12"};
        String[] notIntYear = new String[]{"tag", "description", "/regex", "12/12/c", "12/12/12"};
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntDay, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntMonth, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntYear, 2));
    }

    @Test
    public void testValidateTimeFormat() throws
            InvalidInstructionFormatException, MissingFieldException {
        String[] dateTime = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/12"};
        String[] missingDateTime = new String[]{"tag", "description", "/regex", "12/12/2020"};
        String[] missingHour = new String[]{"tag", "description", "/regex", "12/12/2020", "/12/12"};
        String[] missingMinute = new String[]{"tag", "description", "/regex", "12/12/2020", "12//12"};
        String[] missingSecond = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/"};

        assertTrue(InputValidator.validateDateAndTime(dateTime, 2));
        assertThrows(MissingFieldException.class, () -> InputValidator.validateDateAndTime(missingDateTime, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingHour, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingMinute, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(missingSecond, 2));
        String[] notIntHour = new String[]{"tag", "description", "/regex", "12/12/2020", "d/12/12"};
        String[] notIntMin = new String[]{"tag", "description", "/regex", "12/12/2020", "12/e/12"};
        String[] notIntSec = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/f"};
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntHour, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntMin, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(notIntSec, 2));
    }

    @Test
    public void testValidateDateAccuracy() throws
            InvalidInstructionFormatException, MissingFieldException {
        String[] dateTime = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/12"};
        assertTrue(InputValidator.validateDateAndTime(dateTime, 2));

        // NEGATIVE DATE
        String[] negativeDay = new String[]{"tag", "description", "/regex", "-12/12/2020", "12/12/12"};
        String[] negativeMonth = new String[]{"tag", "description", "/regex", "12/-12/2020", "12/12/12"};
        String[] negativeYear = new String[]{"tag", "description", "/regex", "-12/12/-2020", "12/12/12"};
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeDay, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeMonth, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeYear, 2));
        // FEBRUARY
        String[] normalFeb = new String[]{"tag", "description", "/regex", "12/2/2020", "12/12/12"};
        String[] wrongFeb = new String[]{"tag", "description", "/regex", "30/2/2020", "12/12/12"};
        String[] leapYearFeb = new String[]{"tag", "description", "/regex", "29/2/2016", "12/12/12"};
        String[] nonLeapYearFeb = new String[]{"tag", "description", "/regex", "29/2/2001", "12/12/12"};
        assertTrue(InputValidator.validateDateAndTime(normalFeb, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(wrongFeb, 2));
        assertTrue(InputValidator.validateDateAndTime(leapYearFeb, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(nonLeapYearFeb, 2));
        // 30 Days
        for (int i = 1; i < 13; i++) {
            if (i == 4 | i == 6 | i == 9 | i == 11) {
                String date = "30/" + i + "/2020";
                dateTime[3] = date;
                assertTrue(InputValidator.validateDateAndTime(dateTime, 2));

                date = "31/" + i + "/2020";
                dateTime[3] = date;
                assertThrows(InvalidInstructionFormatException.class, (
                ) -> InputValidator.validateDateAndTime(dateTime, 2));
            } else if (i == 1 | i == 3 | i == 5 | i == 7 | i == 8 | i == 10 | i == 12) {
                String date = "30/" + i + "/2020";
                dateTime[3] = date;
                assertTrue(InputValidator.validateDateAndTime(dateTime, 2));

                date = "31/" + i + "/2020";
                dateTime[3] = date;
                assertTrue(InputValidator.validateDateAndTime(dateTime, 2));
            }

            String date = "32/" + i + "/2020";
            dateTime[3] = date;
            assertThrows(InvalidInstructionFormatException.class, (
            ) -> InputValidator.validateDateAndTime(dateTime, 2));
        }
    }

    @Test
    public void testValidateTimeAccuracy() throws
            InvalidInstructionFormatException, MissingFieldException {
        String[] dateTime = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/12"};
        assertTrue(InputValidator.validateDateAndTime(dateTime, 2));
        String[] negativeHour = new String[]{"tag", "description", "/regex", "12/12/2020", "-12/12/12"};
        String[] negativeMin = new String[]{"tag", "description", "/regex", "12/12/2020", "12/-12/12"};
        String[] negativeSec = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/-12"};
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeHour, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeMin, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(negativeSec, 2));
        String[] outOfBoundMin = new String[]{"tag", "description", "/regex", "12/12/2020", "12/60/12"};
        String[] outOfBoundSec = new String[]{"tag", "description", "/regex", "12/12/2020", "12/12/60"};
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(outOfBoundMin, 2));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateDateAndTime(outOfBoundSec, 2));
    }
}
