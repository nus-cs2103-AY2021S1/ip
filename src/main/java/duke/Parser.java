package duke;
import java.util.Scanner;
public class Parser {
    private static Scanner scanner = new Scanner(System.in);
    
    
    public static Printable getUserInput() {
        return () -> scanner.nextLine();
    }
    
    public static String getUserCommand() {
        return getUserInput().print();
    }
    
    public static String[] splitString(String str) {
        return str.split("\\s+");
    }

    public  static Deadline parseDeadline(String deadlineInputString) {
        String[] arr = splitString(deadlineInputString);
        String description="";
        String deadline="";
        boolean flag = false;
        for (int i = 0; i<arr.length;i++) {
            if (arr[i].equals("/by")) {
                flag = true;
                continue;
            }
            if (!flag) {
                description += arr[i] + " ";
            } else {
                deadline += arr[i] + " ";
            }
        }

        return new Deadline (description.substring(0,description.length()-1),false,deadline.substring(0,deadline.length()-1));
    }

    public static Event parseEvent (String eventInputString) {
        String[] arr = splitString(eventInputString);
        String description="";
        String duration="";
        boolean flag = false;
        for (int i = 0; i<arr.length;i++) {
            if (arr[i].equals("/at")) {
                flag = true;
                continue;
            }
            if (!flag) {
                description += arr[i] + " ";
            } else {
                duration += arr[i] + " ";
            }
        }

        return new Event (description.substring(0,description.length()-1),false,duration.substring(0,duration.length()-1));
    }
}
