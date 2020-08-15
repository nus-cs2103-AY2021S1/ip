/*
 * Duke is a retired old uncle who likes to speak in Singlish.
 */
import java.util.Scanner;

public class Duke {

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish, come talk to me now lah!";
        System.out.println(logo);
        System.out.println(intro);
    }

    public void exit() {
        String bye = "Alamak why so fast end the convo with me? Ok lah I also need to sleep now Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }

    public void echoDuke() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.toLowerCase().equals("bye")) {
            System.out.println(cmd);
            cmd = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.echoDuke();
        duke.exit();
    }
}
