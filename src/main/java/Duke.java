import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create scanner object
        String welcomeMessage = "―――― Serina here, what would you like to do?";
        System.out.println(welcomeMessage); // print welcome message

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) { // terminate on "bye"
                System.out.println("――――  Goodbye, call me when you need me."); // send goodbye message
                break;
            } else {
                System.out.println("――――  " + userInput); // else echo user input
            }
        }
    }
}
