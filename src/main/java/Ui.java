import java.util.Scanner;

public class Ui {

    private static void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n" +
                "What duh hell du yu wan?";
        System.out.println(greeting);
    }

    private static void farewell() {
        String farewell = "Never come back,\n" +
                "dun wanna see yu ever agin";
        System.out.println(farewell);
    }

    public static void runUI() {
        greet();
        Scanner sc = new Scanner(System.in);
        boolean saidBye = false;
        while (!saidBye) {
            String nextLine = sc.nextLine();
            saidBye = Parser.parse(nextLine);
        }
        sc.close();
        farewell();
    }
}
