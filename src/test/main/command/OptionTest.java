package main.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;

public class OptionTest {
    @Nested
    @DisplayName("recurring daily")
    class RecurringDaily {
        @Test
        @DisplayName("should generate RECURRING_DAILY instance of Option from short alias")
        public void getOptionFromShortAlias_shortAlias_recurringDailyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_DAILY, Option.getOptionFromShortAlias("rd"));
        }

        @Test
        @DisplayName("should generate RECURRING_DAILY instance of Option from full alias")
        public void getOptionFromFullAlias_fullAlias_recurringDailyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_DAILY,
                    Option.getOptionFromFullAlias("recurring-daily"));
        }
    }

    @Nested
    @DisplayName("recurring weekly")
    class RecurringWeekly {
        @Test
        @DisplayName("should generate RECURRING_WEEKLY instance of Option from short alias")
        public void getOptionFromShortAlias_shortAlias_recurringWeeklyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_WEEKLY, Option.getOptionFromShortAlias("rw"));
        }

        @Test
        @DisplayName("should generate RECURRING_WEEKLY instance of Option from full alias")
        public void getOptionFromFullAlias_fullAlias_recurringWeeklyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_WEEKLY,
                    Option.getOptionFromFullAlias("recurring-weekly"));
        }
    }

    @Nested
    @DisplayName("recurring monthly")
    class RecurringMonthly {
        @Test
        @DisplayName("should generate RECURRING_MONTHLY instance of Option from short alias")
        public void getOptionFromShortAlias_shortAlias_recurringMonthlyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_MONTHLY, Option.getOptionFromShortAlias("rm"));
        }

        @Test
        @DisplayName("should generate RECURRING_MONTHLY instance of Option from full alias")
        public void getOptionFromFullAlias_fullAlias_recurringWeeklyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_MONTHLY,
                    Option.getOptionFromFullAlias("recurring-monthly"));
        }
    }


    @Nested
    @DisplayName("recurring yearly")
    class RecurringYearly {
        @Test
        @DisplayName("should generate RECURRING_YEARLY instance of Option from short alias")
        public void getOptionFromShortAlias_shortAlias_recurringYearlyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_YEARLY, Option.getOptionFromShortAlias("ry"));
        }

        @Test
        @DisplayName("should generate RECURRING_WEEKLY instance of Option from full alias")
        public void getOptionFromFullAlias_fullAlias_recurringYearlyOption()
                throws InvalidOptionException {
            assertEquals(Option.RECURRING_YEARLY,
                    Option.getOptionFromFullAlias("recurring-yearly"));
        }
    }

    @Nested
    @DisplayName("invalid option")
    class InvalidOption {
        @Test
        @DisplayName("should throw InvalidOptionException if option not found from short alias")
        public void getOptionFromShortAlias_notAlias_throwException() {
            InvalidOptionException e = assertThrows(InvalidOptionException.class, () ->
                    Option.getOptionFromShortAlias("test"));

            assertEquals("Your option of test does not exist!", e.getMessage());
        }


        @Test
        @DisplayName("should throw InvalidOptionException if option not found from full alias")
        public void getOptionFromFullAlias_notAlias_throwException() {
            InvalidOptionException e = assertThrows(InvalidOptionException.class, () ->
                    Option.getOptionFromFullAlias("test"));

            assertEquals("Your option of test does not exist!", e.getMessage());
        }
    }
}
