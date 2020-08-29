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
    public void testSizeOne() throws InvalidInstructionLengthException {

        assertTrue(InputValidator.validateSizeOne(1, true));
        assertTrue(InputValidator.validateSizeOne(2, false));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeOne(2, true));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeOne(1, false));
    }

    @Test
    public void testSizeTwoAndString() throws InvalidInstructionLengthException, InvalidInstructionFormatException {

        assertTrue(InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "1"}));
        assertThrows(InvalidInstructionFormatException.class, (
        ) -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "hello"}));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "1", "hello"}));
        assertThrows(InvalidInstructionLengthException.class, (
        ) -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello"}));
    }

    @Test
    public void testDescriptionAndDateTime() throws MissingFieldException, InvalidInstructionFormatException {

        assertTrue(InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date", "time"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date", "time"}, -1));
        assertThrows(MissingFieldException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "", "index", "date", "time"}, 2));
        assertThrows(MissingFieldException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date or time"}, 2));
    }

    @Test
    public void testDateAndTime() throws InvalidInstructionFormatException {

        assertTrue(InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/00"}, 2));

        // missing date/time
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "/06/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06//2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12//00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/"}, 2));

        // negative values
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "-6/06/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/-6/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/-2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "-12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/-30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/-10"}, 2));

        // illogical times
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "25/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/90/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/90"}, 2));

        // illogical dates
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "30/02/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/13/2020", "12/30/00"}, 2));

        // months w 31 days
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/01/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/03/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/05/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/07/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/08/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/10/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/12/2020", "12/30/00"}, 2));

        // months with 30 days
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/04/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/06/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/09/2020", "12/30/00"}, 2));
        assertThrows(InvalidInstructionFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/11/2020", "12/30/00"}, 2));
    }
}
