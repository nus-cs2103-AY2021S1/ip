import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Parser {

    public static String[] getSplit(String query){
        return query.split("\\s+");
    }

    public static String getCommand(String[] splitQuery){
        return splitQuery[0].toLowerCase();
    }

    public static String[] removeCommandString(String[] splitQuery){
        splitQuery[0] = "";
        return splitQuery;
    }

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

    public static String getTitle(String[] splitQuery){
        StringBuilder accTaskTitle = new StringBuilder();
        int i = 1;
        while(!splitQuery[i].startsWith("/")){
            accTaskTitle.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskTitle.toString();
    }

    public static String getPreposition(String[] splitQuery){
        for (String s:splitQuery) {
            if(s.startsWith("/")){
                return s.substring(1);
            }
        }
        return "";
    }

    //TODO: Place WrongUsageException here instead to accommodate for no dateTime being passed in
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
