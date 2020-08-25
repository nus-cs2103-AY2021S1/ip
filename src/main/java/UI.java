import java.util.Scanner;

public class UI {

    public static final String line = "____________________________________________________________";
    public Scanner sc;

    UI() {
        this.sc = new Scanner(System.in);
    }


    public static void addLines(String content) {
        System.out.print(line);
        System.out.print(content);
        System.out.println(line);
    }

    public static void introduction() {

        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);
    }
}
