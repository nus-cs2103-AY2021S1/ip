import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String div = "\n____________________________________________________________\n\n";
        String logo = "                      █████████\n"
                    +  "  ███████          ███        ███\n"
                    +  "  █      █       ███             ███\n"
                    +  "   █      █    ██                   ██\n"
                    +  "    █     █   ██     ██      ██     ███          Hey! I'm Jimmy,\n"
                    +  "     █   █   █      ████    ████      ██     your personal assistant!\n"
                    +  "   █████████████                      ██\n"
                    +  "   █            █         █           ██   What can I do for you today?\n"
                    +  " ██             █   ██          ██    ██\n"
                    +  "██   ███████████     ██        ██     ██\n"
                    +  "█               █      ████████       ██\n"
                    +  "██              █                    ██\n"
                    +  " █   ███████████                   ██\n"
                    +  " ██          ████                 █\n"
                    +  "  ████████████   █████████████████";
        
        System.out.println(div + logo + div);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                System.out.println(div + "    Bye. Hope to see you again soon!" + div);
                break;
            }
            System.out.println(div + "\t" + msg + div);
        }
        sc.close();
    }
}
