import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("hi! im conundrum boy :)");
        Scanner input = new Scanner(System.in);

        String inn = "";
        while (!inn.equals("bye")) {
            System.out.println(inn);
            inn = input.nextLine();
        }
        System.out.println("bye bye!");

    }
}
