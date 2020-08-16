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
        List<Task> list = new ArrayList<>();
        while (!stop) {
            String input = sc.nextLine();
            BotClass bot = new BotClass();
            processArgs(input).execute(bot, list);
            say(bot.getLines());
            if (bot.stopped()) {
                stop = true;
            }
        }
        sc.close();
    }

    private void say(List<String> strings) {
        System.out.println(SEPARATOR);
        for (String s : strings) {
            System.out.println(INDENT + s);
        }
        System.out.println(SEPARATOR);
        System.out.println();
    }

    private void say(String string) {
        say(Arrays.asList(string));
    }

    private Function processArgs(String input) {
        if (input.equals("bye")) {
            return new ByeFunction();
        } else if (input.equals("list")) {
            return new ListFunction();
        } else if (input.startsWith("done")) {
            String indexString = input.split(" ", 2)[1];
            int index = Integer.valueOf(indexString) - 1;
            return new DoneFunction(index);
        } else {
            return new AddFunction(new Task(input));
        }
    }
}
