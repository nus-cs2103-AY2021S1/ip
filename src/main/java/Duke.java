import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {

    private static String line = "________________________________________________";

    public static void display(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);

        String end = "bye";
        List<String> list = new ArrayList<>();
        String echo = scan.nextLine();
        String listing = "list";

        while (!echo.equals(end)) {
            String displayText = "";
            if (!echo.equals(listing)) {
                displayText = "added: " + echo;
                display(displayText);
                list.add(echo);
            } else {
                int i = 1;
                for (String item :list) {
                    displayText = displayText + i + ". " + item + "\n";
                    i++;
                }
                display(displayText);
            }
            echo = scan.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
