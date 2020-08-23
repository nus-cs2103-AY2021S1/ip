package datetime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import datetimeconverter.DateTimeConverter;

public class DateTimeTest {
    @Test
    public void correctDateTimeFormatTest(){
        assertEquals("Monday, August 24, 2020 06:00 PM", DateTimeConverter.formatDateTime("24/08/2020 1800"));
        assertEquals("Saturday, August 01, 2020 06:00 PM", DateTimeConverter.formatDateTime("1/08/2020 1800"));
        assertEquals("Monday, August 24, 2020 06:00 PM", DateTimeConverter.formatDateTime("24-08-2020 1800"));
        assertEquals("Saturday, August 01, 2020 06:00 PM", DateTimeConverter.formatDateTime("1-08-2020 1800"));
        assertEquals("Monday, August 24, 2020 06:00 PM", DateTimeConverter.formatDateTime("24/8/2020 1800"));
        assertEquals("Saturday, August 01, 2020 06:00 PM", DateTimeConverter.formatDateTime("1/8/2020 1800"));
        assertEquals("Monday, August 24, 2020 06:00 PM", DateTimeConverter.formatDateTime("24-8-2020 1800"));
        assertEquals("Saturday, August 01, 2020 06:00 PM", DateTimeConverter.formatDateTime("1-8-2020 1800"));
    }

    @Test
    public void wrongDateTimeFormatTest(){
        assertEquals("1800 24/08/2020", DateTimeConverter.formatDateTime("1800 24/08/2020"));
        assertEquals("1800", DateTimeConverter.formatDateTime("1800"));
        assertEquals("24/08/2020", DateTimeConverter.formatDateTime("24/08/2020"));
        assertEquals("Date and Time Format Test", DateTimeConverter.formatDateTime("Date and Time Format Test"));
    }

}
