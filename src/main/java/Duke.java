import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        List<String> textList = new ArrayList<>();
        sayHello();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayList(textList);
            } else {
                echoAddCommand(command);
                textList.add(command);
            }
            command = scanner.nextLine();
        }
        sayBye();
    }

    private static void echoAddCommand(String command) {
        System.out.println(wrapDivider("     added: " + command + "\n"));
    }

    private static void sayHello() {
        System.out.println(wrapDivider(
                "      ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n" +
                "     Hello! I'm Thuya\n" +
                "     What may I do for you, sir/madam?\n")
        );
    }

    private static void sayBye() {
        System.out.println(wrapDivider("     Bye. Hope to see you again soon!\n"));
    }

    public static void displayList(List<String> textList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textList.size(); i++) {
            stringBuilder.append("     " + (i+1) + ". "+ textList.get(i) +"\n");
        }
        System.out.println(wrapDivider(stringBuilder.toString()));
    }

    private static String wrapDivider(String text) {
        return  "    ____________________________________________________________\n" +
                text +
                "    ____________________________________________________________\n" ;
    }
}
