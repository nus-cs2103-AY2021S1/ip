import java.util.Scanner;

public class Ui {
    private static String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greeting = "Hello human, I'm Duke the supporting chatbot\n"
            + "What can I do for you?";
    private static String horizontalLine = "________________________________________";
    private static String cmdReq = "Your command:";

    private static String exitMsg = "Bye human. See you again soon!";
    
    public static void showWelcome() {
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting);
    }
    
    public static String requestCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println(horizontalLine);
        System.out.println(cmdReq);
        String input = sc.nextLine();
        System.out.println(horizontalLine);
        return input;
    }
    
    public static void showLine() {
        System.out.println(horizontalLine);
    }
    
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
    public static void showGoodbye() {
        System.out.println(exitMsg);
    }
}
