import javax.xml.parsers.ParserConfigurationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {
    private static final Ui ui = new Ui();

    public static String parseDateTime(String s) {
        SimpleDateFormat formater = new SimpleDateFormat("dd MMM yyyy h:mma");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HHmm");

        SimpleDateFormat dateFormater = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (s.contains(" ")) {
                return formater.format((parser.parse(s)));
            } else {
                return dateFormater.format(dateParser.parse(s));
            }
        } catch (ParseException e) {
            return ui.getBorder() + "Please input the time and date in\n"
                            + dateParser.toPattern() + " or " + parser.toPattern() + "\n" + ui.getBorder();
        }
    }
}
