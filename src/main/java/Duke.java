import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String INDENT = "  ";
    private final String SEPARATOR = INDENT + "_".repeat(68);

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        say("Hello, I'm Duke. What can I do for you?");

        boolean stop = false;
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (!stop) {
            String input = sc.nextLine();
            BotClass bot = new BotClass();
            if (input.equals("bye")) {
                new ByeFunction().execute("", bot, list);
            } else if (input.equals("list")) {
                new ListFunction().execute("", bot, list);
            } else {
                new AddFunction().execute(input, bot, list);
            }

            say(bot.getLines());
            if (bot.stopped()) {
                stop = true;
            }
        }
        sc.close();
    }

    private void say(String string) {
        say(Arrays.asList(string));
    }

    private void say(List<String> strings) {
        System.out.println(SEPARATOR);
        for (String s : strings) {
            System.out.println(INDENT + s);
        }
        System.out.println(SEPARATOR);
        System.out.println();
    }
}
