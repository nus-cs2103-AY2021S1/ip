import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> userInputs = new ArrayList<>();
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            if (inputMsg.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputMsg.equals("list")) {
                if (userInputs.size() == 0) {
                    System.out.println("Nothing has been added to the list yet!");
                } else {
                    for (int i = 0; i < userInputs.size(); i++) {
                        String output = (i + 1) + ". " + userInputs.get(i);
                        System.out.println(output);
                    }
                }
            } else {
                userInputs.add(inputMsg);
                System.out.println("added: " + inputMsg);
            }
        }
        sc.close();
    }
}
