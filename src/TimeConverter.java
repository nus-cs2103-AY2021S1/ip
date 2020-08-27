import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeConverter {

    public String convertTime(String time) throws Exception {
        if(isValidTime(time)){

            LocalDate localDate = LocalDate.parse(time);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

            String formattedTime = localDate.format(dateTimeFormatter);

            return formattedTime;
        }else{

            throw new InvalidTimeException("I don't understand the due date mentioned in the message. " +"/n" +
                    "Please tell me the time strictly in YYYY-MM-DD format");

        }

    }

    public boolean isValidTime(String time){
        String[] timeArray = time.split("-", 3);
        if (timeArray.length == 3) {
            int year = Integer.parseInt(timeArray[0]);
            int month = Integer.parseInt(timeArray[1]);
            int date = Integer.parseInt(timeArray[2]);
            return month <= 12 && date <= 31;
        } else {
            return false;
        }
    }

}


