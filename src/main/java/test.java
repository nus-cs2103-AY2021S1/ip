import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class test {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String echo = sc.nextLine();
//        task t = new Todo(echo);
//        Deadline d = new Deadline(echo);
//        try {
//            d.addDate("12-12-2020 1800");
//            d.getDT();
//        } catch(ErrorExceptions e){
//            System.out.println(e);
//        }
//        String date = "by: Monday";
//        d.addDate(date);
//        System.out.println(d.read());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm");
//        LocalDateTime dt = DateTimeManager.setDateTime("2019-12-01T18:00");
        LocalDateTime dt = LocalDateTime.parse("12-12-2020 1800",format);
        System.out.println(dt.format(DateTimeFormatter.ofPattern("MMM dd uuuu HHmm")));
    }
}
