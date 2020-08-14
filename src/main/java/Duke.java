import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        while (!s.equals("bye")) {
            System.out.println(s);
            System.out.println("______________________");
            s = sc.nextLine();
        }

        System.out.println("ok u can leave lmao");
        System.out.println("______________________");
    }
}
