import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            String toEcho = sc.next();
            if (toEcho.equals("bye")) {
                System.out.println("\n    Bye. Hope to see you again!");
                break;
            }
            System.out.println("\n    " + toEcho + "\n");
        }
    }
}
