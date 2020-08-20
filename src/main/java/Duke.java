import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<String> texts = new ArrayList<>();

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

    private static void add(String toAdd) {
        texts.add(toAdd);
        System.out.println("added: " + toAdd);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String nextLine = sc.nextLine();
        boolean cont = true;
        while (cont) {
            switch (nextLine) {
                case "bye":
                    farewell();
                    cont = false;
                    break;
                case "list":
                    int num = 1;
                    for (String i: texts) {
                        System.out.println(num + ": " + i);
                        num++;
                    }
                    nextLine = sc.nextLine();
                    break;
//                case "bye":
//                    //someth
//                    break;
                default:
                    add(nextLine);
                    nextLine = sc.nextLine();
            }
        }
        sc.close();
    }
}

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
