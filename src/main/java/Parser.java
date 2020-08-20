import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {

    protected static List<String> inputFormat = Arrays.asList("dd/MM/yyyy HHmm", "yyyy-mm-dd Haaa");
    protected static SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy h:mma");

    public static String DateParser(String str){
        Date date = null;
        for (String input : inputFormat) {
            try {
                date = new SimpleDateFormat(input).parse(str);
            } catch (ParseException ignored) { }
        }
        if (date == null) {
            return str;
        } else {
            return outputFormat.format(date);
        }
    }


}
