import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String bye = "bye";


    public void init() {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        while (!command.equals(bye)) {
            System.out.println(line + command + "\n" + line);
            command = sc.next();
        }
        System.out.println(line + "Cya!!" + "\n" + line);
    }

    public static void main(String[] args) {
        String welcome = "Yo I'm Dood!!\nAnything I can do for you?\n";
        System.out.println(line + welcome + line);
        Duke bot = new Duke();
        bot.init();
    }
}
