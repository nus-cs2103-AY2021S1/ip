import java.util.Scanner;

// The bot that handles inputs and give responses
public class Bot {
    private static final String divider = "\t____________________________________________________________\n";

    //start the bot
    public void start() {
        String greeting = "Hello! I'm Duke\n" +
                "\t What can I do for you?";
        giveResponse(greeting);
        String input = getInput();
        while (!input.equals("bye")) {
            giveResponse(input);
            input = getInput();
        }
        giveResponse("Bye. Hope to see you again soon!");
    }

    //print out the response
    private void giveResponse(String response) {
        System.out.println(divider + "\t " + response + "\n" + divider);
    }

    //get the next input
    private String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

