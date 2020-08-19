import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy pardner!! I'm\n" + logo);

        System.out.println("What can I get yer for?");

        while (true) {
            String input = userInput.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.println("Well I'll see you around, pardner!!");
                break;
            } else if (input.toLowerCase().equals("list")) {
                System.out.println("Here's yer current list of thingymajigs");
                for(int i = 0; i < toDoList.size();i++ ) {
                    String index = Integer.toString(i + 1);
                    System.out.println(i + 1 + ". " + toDoList.get(i));
                }
            } else {
                System.out.println("Alright, I'll put it on yer tab: " + input);
                toDoList.add(input);
            }
        }
    }
}
