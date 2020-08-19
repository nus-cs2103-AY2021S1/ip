import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while(!(s = scanner.nextLine()).equals("bye")) {
            if(s.equals("list")) {
                String temp = "";
                for(int i = 0; i < list.size(); i++) {
                    temp += "     " + (i+1) + ". " + list.get(i) + "\n";
                }
                System.out.println("    ____________________________________________________________\n" +
                        temp +
                        "    ____________________________________________________________\n");
            } else {
                list.add(s);
                System.out.println("    ____________________________________________________________\n" +
                        "    added: " + s + "\n" +
                        "    ____________________________________________________________\n");
            }
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
