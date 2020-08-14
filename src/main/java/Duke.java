import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create scanner object
        String welcomeMessage = "―――― Serina here, what would you like to do?";
        System.out.println(welcomeMessage); // print welcome message
        int index = 0;
        String[] tasks = new String[100];

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) { // terminate on "bye"
                System.out.println("――――  Goodbye, call me when you need me."); // send goodbye message
                break;
            } if (userInput.equals("list")) { // list task
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    } else {
                        int num = i + 1;
                        System.out.println("――――  " + num + ". " + tasks[i]);
                    }
                }
            } else {
                tasks[index] = userInput; // add task
                System.out.println("――――  added: " + userInput); // print added task
                index += 1; // increment index
            }
        }
    }
}
