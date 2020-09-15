package duke.io;

import duke.task.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a class to format user input.
 */
public class Parser {

    /**
     * Return date and description of task from user input.
     * 
     * @param arr Array of words in user input.
     * @return Array of date and description.
     */
    public static String [] getDateAndDescription(String [] arr) {
        boolean reached = false;
        StringBuilder date = new StringBuilder();
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < arr.length; i ++) {
            if (reached) {
                if (i != arr.length - 1) {
                    date.append(arr[i] + " ");
                } else {
                    date.append(arr[i]);
                }
            } else if (arr[i].equals("/by") || arr[i].equals("/at")) {
                reached = true;
            } else {
                if (i != 1) {
                    description.append(" " + arr[i]);
                } else {
                    description.append(arr[i]);
                }
            }
        }
        return new String[]{date.toString(), description.toString()};
    }

    /**
     * Return the word to filter tasks by.
     * @param arr Array of words in user input.
     * @return single word.
     * @throws DukeException
     */
    public static String getFilterWord(String [] arr) throws DukeException{
        if (arr.length < 2) {
            throw new DukeException("Please specify a filter word");
        } 
        if (arr.length > 2) {
            throw new DukeException("Please only specify 1 filter word");
        }
        return arr[1];
    }

    /**
     * Return date from user input.
     * @param arr Array of words in user input.
     * @return date of task.
     * @throws DukeException
     */
    public static String getDate(String [] arr) throws DukeException {
        if (arr.length < 2) {
            throw new DukeException("Please specify a filter date");
        }
        if (arr.length > 2) {
            throw new DukeException("Please only specify 1 filter date");
        }
        return arr[1];
    }

    /**
     * Return specified date and time in a different format 
     * if valid date and time are given.
     * 
     * @param date Date to format.
     * @return ArrayList of LocalDate object and String that represents 12 hour time.
     */
    public static ArrayList<Object> dateAndTimeFormatter(String date) {
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

    /**
     * Check if parameter is a valid date.
     * @param str user input date.
     * @return LocalDate object with user input date.
     */
    public static LocalDate checkDate(String str) {
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

    private static String checkTime(String str) {
        
        if (str.length() < 3) {
            return null;
        }
        
        int len = str.length();
        String lastTwoChars = str.substring(len - 2);
        String formattedTime = "";

        if (len == 4 && tryStringToInteger(str) != null) {
            int time = tryStringToInteger(str);
            String period = "am";
            if (time > 2359 || tryStringToInteger(str.substring(2)) > 59 ) {
                //Case: 2400 || 1360
                return null;
            } 
            else if (time >= 1200) {
                period = "pm";
                formattedTime += (time - 1200);
            } else if (str.substring(0, 2).equals("00")) {
                //Case: 0012
                formattedTime += "12" + str.substring(2);
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
                int hour = tryStringToInteger(arr[0]);
                int minute = tryStringToInteger(arr[1].substring(0, arr[1].length() - 2));

                if (0 <= hour && hour <= 12 && 0 <= minute && minute < 60) {
                    return str;
                } else {
                    //Not a valid time so will not be stored
                    return "";
                }
            }
        }
        return null;
    }

    private static Integer tryStringToInteger(String str) {
        Integer i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            i = null;
        }
        return i;
    }

    private static String formatTime(String t) {
        String [] splitArr = t.split("\\.");

        if (splitArr.length == 1) {
            
            if (t.length() == 3) {
                //Case: 7pm
                return t.charAt(0) + ".00" + t.substring(1);
            } else if(t.length() == 4 && Integer.valueOf(t.substring(0, 2)) < 13) {
                //Case: 12am
                return t.substring(0, 2) + ".00" + t.substring(2);
            } else if (t.length() == 5) {
                //Case: 732pm 
                return t.charAt(0) + "." + t.substring(1);
            } else if (t.length() == 6) {
                //Case: 1159pm 
                return t.substring(0, 2) + "." + t.substring(2);
            } else {
                return null;
            }
        } else {
            if (splitArr[1].length() == 3) {
                //Case: 8.2pm
                return splitArr[0] + "." + splitArr[1].substring(0, 1) + "0" + splitArr[1].substring(1,3);
            } else {
                //Case: 8.20pm
                return t;
            }
        }
    }


}
