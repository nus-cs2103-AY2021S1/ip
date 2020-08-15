import java.util.ArrayList;
import java.util.List;
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

        List<String> tasks = new ArrayList<>();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();

            switch(command) {
            case "list":
                printInWindow(formatTaskListToString(tasks));
                break;
            case "bye":
                printInWindow("Bye. Hope to see you again soon!");
                return;
            default:
                tasks.add(command);
                printInWindow("Added: " + command);
            }
        }
    }

    public static void printInWindow(String text) {
        String divider = "---------------------------------------------";
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider);
    }

    public static String formatTaskListToString(List<String> tasks) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(i)
                    .append(". ")
                    .append(tasks.get(i));
            if(i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
