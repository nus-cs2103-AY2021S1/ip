import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String div = "\n______________________________________________________________\n\n";
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

        Planner lst = new Planner();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                System.out.println(div + "\tBye. Hope to see you again soon!" + div);
                break;
            } else if (!msg.equals("list")) {
                System.out.println(div + "\tadded:\t" + msg + div);
                lst.addItem(msg);
            } else {
                System.out.println(div + lst + div);
            }
            
        }
        sc.close();
    }
}
