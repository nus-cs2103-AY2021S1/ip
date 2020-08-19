import java.util.Scanner;

public class Duke {

    private static void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n" +
                "What duh hell du yu wan?";
        System.out.println(greeting);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String nextLine = sc.nextLine();
        while (!nextLine.equals("bye")) {
            System.out.println(nextLine);
            nextLine = sc.nextLine();
        }
        System.out.println("Never come back,\n" +
                "dun wanna see yu ever agin");
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        sc.close();
    }
}
