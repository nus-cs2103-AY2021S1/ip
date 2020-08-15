import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[101];
        String divider = "    _________________________________________\n";
        int listIndex = 1;
        String MrCamel = "                  ,,__\n"
                + "        ..  ..   / o._)\n"
                + "       /--'/--\\  \\-'|| \n"
                + "      /        \\_/ / |\n"
                + "    .'\\  \\__\\  __.'.'\n"
                + "      )\\ |  )\\ |\n"
                + "     // \\\\ // \\\\\n"
                + "    ||_  \\\\|_  \\\\_\n"
                + "    '--' '--'' '--'\n";

        System.out.println("Mr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);

        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println(divider);
                for (int i = 1; i < listIndex; i++) {
                    System.out.println("      " + i + ". " + list[i]);
                }
                System.out.println(divider);
            } else if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("      Bye. Hope to see you again!");
                System.out.println(divider);
                break;
            } else {
                list[listIndex] = command;
                listIndex++;
                System.out.println(divider);
                System.out.println("      added: " + command);
                System.out.println(divider);
            }
        }
    }
}
