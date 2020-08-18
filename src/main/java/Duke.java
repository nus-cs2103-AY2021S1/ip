import java.util.*;

public class Duke {

    public void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you mateeee?");
    }

    public void echo(String input) {
        System.out.println("---------------------------");
        System.out.println(input);
        System.out.println("---------------------------");
    }

    public void byeMessage() {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("Bye. Hope to see you again soon!!!");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }
            echo(str);
        }
        byeMessage();
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.sayHi();
        bot.run();
    }
}
