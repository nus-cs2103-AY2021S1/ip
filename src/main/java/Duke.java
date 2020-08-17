import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    public static void main(String[] args) {
        String logo = "   ___      _      _ __   _              \n" +
                "  /   \\    | |    | '_ \\ | |_     __ _   \n" +
                "  | - |    | |    | .__/ | ' \\   / _` |  \n" +
                "  |_|_|   _|_|_   |_|__  |_||_|  \\__,_|  \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
        System.out.println("Hello from\n" + logo);

        String greeting = "    ____________________________________________________________\n" +
                "     Hello, Alpha here... welcome to my help centre... again :/\n" +
                "     Would you like to explain what you want?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);

        List<String> itemList = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            respondToInput(input, itemList);
            System.out.println("    ____________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Try not to come again please... let me live.");
        System.out.println("    ____________________________________________________________");
    }

    private static void respondToInput(String input, List<String> itemList) {
        if (!input.equals("list"))
        {
            itemList.add(input);
            System.out.println("    added: " + input);
        }
        else {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.print("    " + (i+1) + ". ");
                System.out.println(itemList.get(i));
            }
        }
    }
}
