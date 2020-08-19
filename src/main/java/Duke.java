import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke" + "\nWhat can I do for you?");

        List<String> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean boo = true;
        while (boo){
            String str = sc.nextLine();
            if (str.equals("bye")) {
                boo = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (str.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i));
                }
            } else {
                lst.add(str);
                System.out.println("added: " + str);
            }
        }
        sc.close();
    }
}
