package logic;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidInstructionException;
import duke.exception.MissingFieldException;
import duke.logic.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {
    @Test
    public void testSizeOne() throws InvalidInstructionException {
        String instrType = "TEST";

        assertTrue(InputValidator.validateSizeOne(1, instrType, true));
        assertTrue(InputValidator.validateSizeOne(2, instrType, false));
        assertThrows(InvalidInstructionException.class,
                () -> InputValidator.validateSizeOne(2, instrType, true));
        assertThrows(InvalidInstructionException.class,
                () -> InputValidator.validateSizeOne(1, instrType, false));
    }

    @Test
    public void testSizeTwoAndString() throws InvalidInstructionException {
        String instrType = "TEST";

        assertTrue(InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "1"}, instrType));
        assertThrows(InvalidInstructionException.class,
                () -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "hello"}, instrType));
        assertThrows(InvalidInstructionException.class,
                () -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello", "1", "hello"}, instrType));
        assertThrows(InvalidInstructionException.class,
                () -> InputValidator.validateSizeTwoAndInt(new String[]{"Hello"}, instrType));
    }

    @Test
    public void testDescriptionAndDateTime() throws MissingFieldException, InvalidFormatException {
        String instrType = "TEST";

        assertTrue(InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date", "time"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date", "time"}, instrType, -1));
        assertThrows(MissingFieldException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "", "index", "date", "time"}, instrType, 2));
        assertThrows(MissingFieldException.class, () -> InputValidator
                .validateDescriptionAndDateTime(new String[]{"type", "desc", "index", "date or time"}, instrType, 2));
    }

    @Test
    public void testDateAndTime() throws InvalidFormatException {
        String instrType = "TEST";

        assertTrue(InputValidator
            .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/00"}, instrType, 2));

        // missing date/time
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "/06/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06//2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12//00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/"}, instrType, 2));

        // negative values
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "-6/06/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/-6/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/-2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "-12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/-30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/-10"}, instrType, 2));

        // illogical times
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "25/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/90/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/06/2020", "12/30/90"}, instrType, 2));

        // illogical dates
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "30/02/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "06/13/2020", "12/30/00"}, instrType, 2));

        // months w 31 days
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/01/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/03/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/05/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/07/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/08/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/10/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "32/12/2020", "12/30/00"}, instrType, 2));

        // months with 30 days
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/04/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/06/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/09/2020", "12/30/00"}, instrType, 2));
        assertThrows(InvalidFormatException.class, () -> InputValidator
                .validateDateAndTime(new String[]{"type", "desc", "index", "31/11/2020", "12/30/00"}, instrType, 2));
    }
}
