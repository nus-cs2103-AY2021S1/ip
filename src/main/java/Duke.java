import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, homie?");

        ArrayList<String> arraylst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (!cmd.equals("bye")) {
                //System.out.println("_________________________________________\n" + cmd + "\n" + "_________________________________________");
                if (cmd.equals("list")) {
                    System.out.println("_________________________________________");
                    for (int i = 0; i < arraylst.size(); i++) {
                        int index = i+1;
                        System.out.println(index + ". " + arraylst.get(i));
                    }
                    System.out.println("_________________________________________");
                } else {
                    arraylst.add(cmd);
                    System.out.println("_________________________________________\n" + "added: " + cmd + "\n" + "_________________________________________");
                }
            } else {
                System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
                break;
            }
        }
    }
}
