import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();

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
        int counter = 1;

        while (1 == 1) {
            if (s.equals("bye")) {
                System.out.println("ok u can leave lmao");
                System.out.println("______________________");
                break;
            } else if (s.equals("list")) {
                arr.forEach(System.out::println);
                System.out.println("______________________");
                s = sc.nextLine();
            } else {
                System.out.println("added: " + s + "\nyoure welcome ;)");
                System.out.println("______________________");
                arr.add(Integer.toString(counter) + ". " + s);
                counter++;
                s = sc.nextLine();
            }
        }
    }
}
