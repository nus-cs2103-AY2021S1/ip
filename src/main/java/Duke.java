import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("______________________");
        System.out.println(logo);
        System.out.println("welcome to my crib");
        System.out.println("______________________");

        String s = sc.nextLine();

        while (1 == 1) {
            if (s.equals("bye")) {
                System.out.println("ok u can leave lmao");
                System.out.println("______________________");
                break;
            } else if (s.equals("list")) {
                for (int i = 0; i < arr.size(); i ++) {
                    System.out.println(Integer.toString(i + 1) + ". " + arr.get(i).toString());
                }
                System.out.println("______________________");
                s = sc.nextLine();
            } else if (s.startsWith("done")) {
                System.out.println("gfy youve managed to finish the following...");
                Task t = arr.get(Integer.parseInt(s.substring(5)) - 1);
                t = t.completeTask();
                arr.set(Integer.parseInt(s.substring(5)) - 1, t);
                System.out.println(t);
                System.out.println("______________________");
                s = sc.nextLine();
            } else {
                System.out.println("added: " + s + "\nyoure welcome ;)");
                System.out.println("______________________");
                arr.add(new Task(s));
                s = sc.nextLine();
            }
        }
    }
}
