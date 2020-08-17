import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Editable Strings for system messages
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        String parting = "Bye. Hope to see you again soon!";
        String error_msg = "Please enter an appropriate command!";

        //Initialisation Message
        System.out.println(greeting);

        //To control various states of Duke
        boolean isChatting = true;

        //Initialise scanner to prompt user
        Scanner sc = new Scanner(System.in);

        while (isChatting) {
            //Obtain user input
            String user_input = sc.nextLine();

            //Conditionals for user inputs
            if (user_input.equals("list")) {
                System.out.println(user_input);
            } else if (user_input.equals("blah")) {
                System.out.println(user_input);
            } else if (user_input.equals("bye")) {
                System.out.println(parting);
                isChatting = false;
            } else {
                System.out.println(error_msg);
            }
        }
    }
}
