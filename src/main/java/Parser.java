package main.java;
import java.util.Scanner;

public class Parser {
    private static final Scanner sc = new Scanner(System.in);

    public static String nextLine() {
        return sc.nextLine();
    }

    public static boolean hasNextLine() {
        return sc.hasNext();
    }

    private static String getCommand(String userInput) {
        String[] arr = userInput.split(" ", 2);
        return arr[0];
    }

    public static String getArgs(String userInput) {
        String[] arr = userInput.split(" ", 2);
        return arr[1];
    }

    static String findDescription(String userInput) throws EmptyTaskException {
        String[] arr = userInput.split("/");
        if (arr[0].length() == 0) {
            throw new EmptyTaskException();
        } else {
            return arr[0];
        }
    }

    static String findTime(String userInput, String keyword) throws EmptyDateException {
        String[] arr = userInput.split("/" + keyword + " ");
        if (arr.length < 2) {
            throw new EmptyDateException();
        } else {
            return arr[1];
        }
    }

    public static boolean isValidDate(String time) throws IllegalArgumentException {
        String[] stringArr = time.split("-");
        if (stringArr.length != 3) {
            throw new IllegalArgumentException("Entered date is in the wrong format. Please " +
                    "specify in this format YYYY-MM-DD");
        }

        return true;
    }

    public static boolean hasTime(String time) {
        String[] stringArr = time.split(" ");
        return stringArr.length == 2;
    }

}
