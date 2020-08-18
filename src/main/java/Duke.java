import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        ArrayList<String> ls = new ArrayList<>();
        int counter = 1;
        String horizontalDiv = "____________________________________________________________";

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalDiv);
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        System.out.println(horizontalDiv);
        while(in.hasNextLine()) {
            String str = in.nextLine();

            if(str.equals("bye")) {
                System.out.println(horizontalDiv);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horizontalDiv);
                break;
            } else if (str.equals("list")) {
                for (String l : ls) {
                    System.out.println(l);
                }
            } else {
                ls.add(counter + ". " + str);
                System.out.println(horizontalDiv);
                System.out.println("added: " + str);
                System.out.println(horizontalDiv);

                counter++;
            }
        }
    }
}
