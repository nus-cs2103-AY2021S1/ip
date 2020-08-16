import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm DukeBot");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Store store = new Store();
        Bot bot = new Bot();
        while (flag) {
            String input = scanner.nextLine();

            if (isDone(input)) {
                int index = Integer.parseInt(input.substring(5, 6)) - 1;
                bot.markDone(index, store);
            } else if (input.equals("bye")) {
                bot.sayBye();
                flag = false;
            } else if (input.equals("list")) {
                bot.listItems(store);
            } else {
                try {
                    String taskType = input.substring(0, input.indexOf(" "));
                    String task = input.substring(input.indexOf(" ") + 1);
                    String date = input.substring(input.indexOf("/") + 1, input.length());
                    if (taskType.equals("todo")) {
                        store.addItem(new ToDos(task));
                    } else if (taskType.equals("deadline")) {
                        // date = 'by Sunday'
                        store.addItem(new Deadlines(task));
                    } else if (taskType.equals("event")) {
                        store.addItem(new Events(task));
                    }
                    bot.repeat(store);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "    ____________________________________________________________");
                }
            }
        }
    }

    static boolean isDone(String input) {
        return input.length() == 6
                && input.substring(0, 4).equals("done")
                && isNumber(input.substring(5,6));
    }

    static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
