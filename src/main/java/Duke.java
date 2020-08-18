import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Duke {

    public static String divider = "_________________________________________________________________";
    public static String logo = "             *\n"
            + "      o  o  / \\  o  o\n"
            + "      |\\/ \\/   \\/ \\/|\n"
            + "      |             |\n"
            + "      |ooooooooooooo|\n"
            + " __  _  ____  ____    ____      ____    ___   ____  \n"
            + "|  |/ ||    ||    \\  /    |    |    \\  /   \\ |    \\ \n"
            + "|  ' /  |  | |  _  ||   __|    |  o  )|     ||  o  )\n"
            + "|    \\  |  | |  |  ||  |  |    |     ||  O  ||     |\n"
            + "|     | |  | |  |  ||  |_ |    |  O  ||     ||  O  |\n"
            + "|  .  | |  | |  |  ||     |    |     ||     ||     |\n"
            + "|__|\\_||____||__|__||___,_|    |_____| \\___/ |_____|\n";

    public static List<String> list = new ArrayList<>();

    public static void byeMessage() {
        System.out.println(divider);
        System.out.println("   Banana! King Bob is sad to see you go. Farewell my friend!");
        System.out.println(divider);
    }

    public static void inputMessage(String input) {
        System.out.println(divider);
        System.out.println("   added: " + input);
        System.out.println(divider + "\n");
    }

    public static void addList(String input) {
        list.add(input);
    }

    public static void showList() {
        System.out.println(divider);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(divider + "\n");
    }

    public static void main(String[] args) {

        System.out.println("Bello from the Majestic\n" + logo);
        System.out.println("Banana! What can King Bob do for you?\n" + divider + "\n");

        Scanner scanner = new Scanner(System.in);
        String nextLine = "";

        while (scanner.hasNext()) {
            nextLine = scanner.nextLine();
            if (nextLine.equals("bye")) {
                byeMessage();
                break;
            } else if (nextLine.equals("list")) {
                showList();
            } else {
                inputMessage(nextLine);
                addList(nextLine);
            }
        }
        scanner.close();

    }
}
