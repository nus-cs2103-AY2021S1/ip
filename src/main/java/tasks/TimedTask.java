package tasks;

import exceptions.DukeDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

abstract class TimedTask extends Task {
    protected static final DateTimeFormatter FMAT;
    protected static final LocalDate NOW = LocalDateTime.now().toLocalDate();

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendPattern("dd-MM-yyyy");
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.HOUR_OF_DAY, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear());
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue());
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth());
        FMAT = dateTimeFormatterBuilder
                .toFormatter();
    }

    protected final LocalDateTime dateby;

    protected TimedTask(String desc, String date, Boolean done) throws DukeDateTimeException {
        super(desc, done);
        try {
            if (NOW.format(FMAT).length() > date.length()) {
                date = date + NOW.format(FMAT).substring(date.length());
            }
            this.dateby = LocalDateTime.parse(date, FMAT);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("The String you entered does not meet the required format of 'yyyy-MM-dd' ");
        }

    }

    protected TimedTask(String desc, String date) throws DukeDateTimeException {
        this(desc, date, false);
    }

    protected int timeLeft() {
        return Period.between(NOW, LocalDate.from(dateby)).getDays();
    }

    /**
     * Get the dateby for the set task
     *
     * @return dateby for the registered task
     */
    public String getDateby() {
        return dateby.toLocalDate().format(FMAT);
    }

    @Override
    public String saveTask() {
        return super.saveTask() + SEPERATOR + getDateby();
    }
}
