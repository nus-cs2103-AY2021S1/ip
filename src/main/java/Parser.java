import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 * Parses all the Strings received from User.
 */
public class Parser {

    /**
     * Splits the User query String into String Array.
     *
     * @param query The String passed in by the User.
     * @return String Array containing all the tokens passed in by User.
     */
    public static String[] getSplit(String query){
        return query.split("\\s+");
    }

    /**
     * Gets the Command passed in by the User.
     *
     * @param splitQuery Query received from the User.
     * @return String Command in lower case.
     */
    public static String getCommand(String[] splitQuery){
        return splitQuery[0].toLowerCase();
    }

    /**
     * Removes Command term from the query passed in by User.
     *
     * @param splitQuery Query received from the User.
     * @return String Array without the Command term.
     */
    public static String[] removeCommandString(String[] splitQuery){
        splitQuery[0] = "";
        return splitQuery;
    }

    /**
     * Concatenates a String array with a Command term removed.
     *
     * @param strArr Any String Array that needs to be concatenated.
     * @return Concatenated String.
     */
    public static String concatenateStrArr(String[] strArr){
        StringBuilder acc = new StringBuilder();
        for (int i = 0;i< strArr.length;i++) {
            if(!strArr[i].equals("")) {
                if (i == 1) {
                    acc.append(strArr[i]);
                } else {
                    acc.append(" ").append(strArr[i]);
                }
            }
        }
        return acc.toString();
    }

    /**
     * Returns the title of the task queried by the User.
     *
     * @param splitQuery Query received from the User.
     * @return Title of the Task queried by User.
     */
    public static String getTitle(String[] splitQuery){
        StringBuilder accTaskTitle = new StringBuilder();
        int i = 1;
        while(!splitQuery[i].startsWith("/")){
            accTaskTitle.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskTitle.toString();
    }

    /**
     * Returns the preposition queried by the User.
     *
     * @param splitQuery Query received from the User.
     * @return Preposition queried by User.
     */
    public static String getPreposition(String[] splitQuery){
        for (String s:splitQuery) {
            if(s.startsWith("/")){
                return s.substring(1);
            }
        }
        return "";
    }

    /**
     * Returns the Date of the task queried by the User.
     *
     * @param splitQuery Query received from the User.
     * @return Date as a LocalDate Object queried by User.
     * @throws CustomException If Date is input wrongly.
     */
    public static LocalDate getDate(String[] splitQuery) throws CustomException{
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i++;
        String[] splitDate = splitQuery[i].split(Pattern.quote("/"));
        if(splitDate.length!=3){
            throw new CustomException("Date is wrongly formatted!");
        }
        //Format required is DD/MM/YYYY
        LocalDate date = LocalDate.parse(splitDate[2]+"-"+splitDate[1]+"-"+splitDate[0]);
        return date;
    }

    /**
     * Returns the Time of the task queried by the User.
     *
     * @param splitQuery Query received from the User.
     * @return Time as a LocalTime Object queried by User.
     */
    public static LocalTime getTime(String[] splitQuery){
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i+=2;
        //Format required is HH:MM
        LocalTime time = LocalTime.parse(splitQuery[i]+":00");
        return time;
    }
}
