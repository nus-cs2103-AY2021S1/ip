package Tasks;

import Exceptions.DukeDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

abstract class TimedTask extends Task {
    protected final LocalDateTime dateby;
    protected static final DateTimeFormatter FMAT;
    
    
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
    protected static final LocalDate NOW = LocalDateTime.now().toLocalDate();
    
    protected TimedTask(String desc, String date) throws DukeDateTimeException {
        super(desc, false);
        try {
            if (NOW.format(FMAT).length() > date.length()){
                date = date+NOW.format(FMAT).substring(date.length());
            }
            this.dateby = LocalDateTime.parse(date,FMAT);    
        }catch (DateTimeParseException e){
            throw new DukeDateTimeException("The String you entered does not meet the required format of 'yyyy-MM-dd' ");
        }
        
    }
    
    protected int timeLeft(){
        return Period.between(NOW,LocalDate.from(dateby)).getDays();
    }

    /**
     * Get the dateby for the set task
     * @return dateby for the registered task
     */
    public String getDateby(){
        return dateby.toLocalDate().format(FMAT);
    }
}
