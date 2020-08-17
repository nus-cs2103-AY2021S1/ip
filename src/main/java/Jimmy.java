import java.util.Scanner;

public class Jimmy {
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
            String[] arr = msg.split(" ");
            if (msg.equals("bye")) {
                System.out.println(div + "\tBye. Hope to see you again soon!" + div);
                break;
            } else if (!msg.equals("list") && !arr[0].equals("done")) {
                System.out.println(div + "\tadded:\t" + msg + div);
                lst.addTask(new Task(msg));
            } else if (arr[0].equals("done")) {
                int i = Integer.parseInt(arr[1]);
                lst.completeTask(i);
                Task t = lst.getTask(i);
                System.out.println(div + "\tNice! I've marked this task as done:" + "\n\t  " + t + "\n");
            } else {
                System.out.println(div + lst + div);
            }
            
        }
        sc.close();
    }
}