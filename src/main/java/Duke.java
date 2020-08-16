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
        input = input.strip();
        String[] split = input.split("\\s+", 2); // guranteed to contain at least ""
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        if (command.equals("bye")) {
            if (args.isEmpty()) {
                return new ByeFunction();
            } else {
                return new ErrorFunction(Arrays.asList("Didn't understand " + input + ".", "Did you mean bye?"));
            }
        } else if (command.equals("list")) {
            if (args.isEmpty()) {
                return new ListFunction();
            } else {
                return new ErrorFunction(Arrays.asList("Didn't understand " + input + ".", "Did you mean list?"));
            }
        } else if (command.equals("done")) {
            int index;
            try {
                index = Integer.valueOf(args) - 1;
            } catch (NumberFormatException e) {
                return new ErrorFunction(Arrays.asList("You need to tell me the number of the task you have completed.",
                                                       "Eg. done 1"));
            }
            return new DoneFunction(index);
        } else if (command.equals("delete")) {
            int index;
            try {
                index = Integer.valueOf(args) - 1;
            } catch (NumberFormatException e) {
                return new ErrorFunction(Arrays.asList("You need to tell me the number of the task you want to remove.",
                                                       "Eg. delete 1"));
            }
            return new DeleteFunction(index);
        } else if (command.equals("todo")) {
            if (args.isEmpty()) {
                return new ErrorFunction("Couldn't add todo! The description of a todo cannot be empty.");
            }
            return new AddFunction(new Task(args));
        } else if (command.equals("deadline")) {
            String[] argsSplit = args.split("\\s+/by\\s+", 2);
            if (argsSplit.length != 2 || argsSplit[0].isBlank() || argsSplit[1].isBlank()) {
                return new ErrorFunction(Arrays.asList("Couldn't add deadline! To add a deadline, talk to me using the",
                                                       "format: deadline <description> /by <date>"));
            }
            return new AddFunction(new Deadline(argsSplit[0], argsSplit[1]));
        } else if (command.equals("event")) {
            String[] argsSplit = args.split("\\s+/at\\s+", 2);
            if (argsSplit.length != 2 || argsSplit[0].isBlank() || argsSplit[1].isBlank()) {
                return new ErrorFunction(Arrays.asList("Couldn't add event! To add an event, talk to me using the format:",
                                                       "event <description> /at <date>"));
            }
            return new AddFunction(new Event(argsSplit[0], argsSplit[1]));
        } else if (input.isBlank()) {
            return new ErrorFunction("Please tell me what you want me to do!");
        } else {
            return new ErrorFunction("Sorry, I don't understand that!");
        }
    }
}
