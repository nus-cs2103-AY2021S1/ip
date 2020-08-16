import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> todo = new ArrayList<String>();
        System.out.println("What's new scooby doo?\n" + "How can I help you today?");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    System.out.println("See you later alligator ");
                    System.exit(0);
                    break;
                case "list":
                    int index = 1;
                    for (String str :
                            todo) {
                        System.out.println(index + ". " + str);
                        index++;
                    }
                    if (todo.size() == 0) {
                        System.out.println("Your life is empty now bruh");
                    }
                    break;
                default:
                    todo.add(command);
                    System.out.println("okay you need to: " + command);
            }
        }
    }
}
