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
            if (input.equals("bye")) {
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
}
