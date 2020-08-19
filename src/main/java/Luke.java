import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey there! I'm Luke. How may I help you?");
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Oh, are you leaving? Hope to see you soon!");
                break;
            } else {
                System.out.println(echo);
            }
        }
    }
}
