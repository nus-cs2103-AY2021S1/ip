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
            processInput(input).execute(bot, list);
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

    private Function processInput(String input) {
        input = input.stripTrailing();
        String[] split = input.split("\\s+", 2); // guranteed to contain at least ""
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        if (input.equals("bye")) {
            return new ByeFunction();
        } else if (input.equals("list")) {
            return new ListFunction();
        } else if (command.equals("done")) {
            int index = Integer.valueOf(args) - 1;
            return new DoneFunction(index);
        } else if (command.equals("todo")) {
            return new AddFunction(new Task(args));
        } else if (command.equals("deadline")) {
            String[] argsSplit = args.split("/by", 2);
            return new AddFunction(new Deadline(argsSplit[0].stripTrailing(), argsSplit[1].stripLeading()));
        } else { // if (command.equals("event")) {
            String[] argsSplit = args.split("/at", 2);
            return new AddFunction(new Event(argsSplit[0].stripTrailing(), argsSplit[1].stripLeading()));
        }
    }
}
