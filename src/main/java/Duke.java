import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                "//\\\n" +
                "V  \\\n" +
                " \\  \\_\n" +
                "  \\,'.`-.\n" +
                "   |\\ `. `.       \n" +
                "   ( \\  `. `-.                        _,.-:\\\n" +
                "    \\ \\   `.  `-._             __..--' ,-';/\n" +
                "     \\ `.   `-.   `-..___..---'   _.--' ,'/\n" +
                "      `. `.    `-._        __..--'    ,' /\n" +
                "        `. `-_     ``--..''       _.-' ,'\n" +
                "          `-_ `-.___        __,--'   ,'\n" +
                "             `-.__  `----\"\"\"    __.-'\n" +
                "                   `--..____..--'";

        System.out.println(logo);
        printInWindow("Hello, I'm a banana.\nWhat can I do for you?");

        while(sc.hasNextLine()) {
            String command = sc.nextLine();

            switch(command) {
            case "bye":
                printInWindow("Bye. Hope to see you again soon!");
                return;
            }

            printInWindow(command);

        }
    }

    public static void printInWindow(String text) {
        String divider = "---------------------------------------------";
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider);
    }
}
