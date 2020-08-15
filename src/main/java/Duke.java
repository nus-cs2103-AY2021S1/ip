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
                store.addItem(input);
                bot.repeat(input);
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
