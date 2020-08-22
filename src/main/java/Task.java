package ip.src.main.java;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private boolean done;
    protected String description;

    public Task(String task) {
        this.description = task;
        done = false;
    }

    public void markDone() {
        done = true;
    }

    private String getStatusSymbol() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    public String toString() {
        return getStatusSymbol() + " " + description;
    }
    
    protected ArrayList<Object> dateAndTimeFormatter(String date) {
        String [] arr = date.split(" ");
        LocalDate localDate = null;
        String time = null;
        for(String str: arr) {
            if (checkDate(str) != null) {
                localDate = checkDate(str);
            } 
            if (checkTime(str) != null) {
                String t = checkTime(str);
                time = formatTime(t);
            }
        }
        ArrayList<Object> arrList = new ArrayList<>();
        arrList.add(localDate);
        arrList.add(time);
        return arrList;
    }
    
    private LocalDate checkDate(String str) {
        String [] arr = str.split("/");
        String year;
        String month;
        String day;
        LocalDate localDate;
        if (arr.length == 1) {
            arr = str.split("-");
        }
        if (arr.length == 3) {
            if (arr[0].length() == 4) {
                year = arr[0];
                day = arr[2];
            } else {
                year = arr[2];
                day = arr[0];
            }
            month = arr[1];
            try {
                localDate = LocalDate.of(Integer.parseInt(year),
                        Integer.parseInt(month), Integer.parseInt(day));
            } catch (Exception e) {
                localDate = null;
            }
            return localDate;
        }
        return null;
    }
    
    private String checkTime(String str) {
        int len = str.length();
        String lastTwoChars = str.substring(len - 2);
        String formattedTime = "";
        
        if (len == 4 && stringToInteger(str) != null) {
            int time = stringToInteger(str);
            String period = "am";
            if (time >= 1200) {
                period = "pm";
                formattedTime += (time - 1200);
            } else {
                formattedTime += time;
            }
            formattedTime = formattedTime + period;
            return formattedTime;
        } else if (lastTwoChars.equals("am") || lastTwoChars.equals("pm")) {
            String [] arr = str.split("\\.");
            if (arr.length == 1) {
                return arr[0];
            } else if (arr.length == 2) {
                int hour = stringToInteger(arr[0]);
                int minute = stringToInteger(arr[1].substring(0, arr[1].length() - 2));
              
                if (0 <= hour && hour <= 12 && 0 <= minute && minute < 60) {
                    return str;
                } else {
                    //Not a valid time so will not be stored
                    return "";
                }
            }
            return null;
        } 
        return null;
    }
    
    private Integer stringToInteger(String str) {
        Integer i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            i = null;
        }
        return i;
    }
    
    private String formatTime(String t) {
        String [] splitArr = t.split("\\.");

        if (splitArr.length == 1) {
            if (t.length() == 3) {
                return t.charAt(0) + ".00" + t.substring(1);
            } else if (t.length() == 5) {
                return t.charAt(0) + "." + t.substring(1);
            } else if (t.length() == 6) {
                return t.substring(0, 2) + "." + t.substring(2);
            } else {
                return t;
            }
        } else {
            if (splitArr[1].length() == 3) {
                return splitArr[0] + "." + splitArr[1].substring(0, 1) + "0" + splitArr[1].substring(1,3);
            } else {
                return t;
            }
        }
    }
    
}
