import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args){
//        LocalDateTime x = LocalDateTime.parse("12-12-2020 1200", DateTimeFormatter.ofPattern("d-MM-uuuu HHmm"));
        LocalDate y = LocalDate.parse("12-12-2020", DateTimeFormatter.ofPattern("d-MM-uuuu"));
        LocalDate x = LocalDate.parse("12-12-2020", DateTimeFormatter.ofPattern("d-MM-uuuu"));
        System.out.println(x.equals(y));
    }
}
