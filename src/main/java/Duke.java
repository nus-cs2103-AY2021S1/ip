import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String bye = "bye";
    private static final String list = "list";
    private ArrayList<String> input;

    private Duke() {
        input = new ArrayList<>();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals(bye)) {
            if (command.equals(list)) {
                display();
            } else {
                add(command);
            }
            command = sc.nextLine();
        }
        System.out.println(line + "Cya!!" + "\n" + line);
    }

    private void display() {
        System.out.println(line);
        for (int i = 0; i < input.size(); i++) {
            System.out.println(String.valueOf(i+1) + ". " + input.get(i) + "\n");
        }
        System.out.println(line);
    }

    private void add(String reminder) {
        input.add(reminder);
        System.out.println(line + "added: " + reminder + "\n" + line);
    }

    public static void main(String[] args) {
        String welcome = "Yo I'm Dood!!\nAnything I can do for you?\n";
        System.out.println(line + welcome + line);
        Duke bot = new Duke();
        bot.init();
    }
}
