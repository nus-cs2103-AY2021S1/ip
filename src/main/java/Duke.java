import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String divider = "************************************************\n";
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
        boolean carryOn = true;
        ArrayList<String> stringArray = new ArrayList<>();
        int numberOfItems = 0;
        while(carryOn) {
            String inputString = input.nextLine();
            if (inputString.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < stringArray.size(); i++) {
                    int numbering = i + 1;
                    System.out.println(numbering + ". " + stringArray.get(i));
                }
                System.out.println(divider);
            }
            else if (inputString.equals("bye")) {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
            } else {
                if (numberOfItems < 100) {
                    stringArray.add(inputString);
                    System.out.println(divider + "added: " + inputString + "\n" + divider);
                    numberOfItems += 1;
                } else {
                    System.out.println(divider + "Sorry, the list is full!\n" + divider);
                }
            }
        }
    }
}
