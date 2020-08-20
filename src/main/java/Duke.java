import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> texts = new ArrayList<>();

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
        texts.add(new Task(toAdd));
        System.out.println("added: " + toAdd);
    }

    private static void processDone(String s) {
        System.out.println("okcan done:");
        System.out.println(texts.get(Integer.parseInt(s) - 1).markAsDone());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String nextLine = sc.nextLine();
        boolean cont = true;
        while (cont) {
            if (nextLine.length() >= 6) {
                if (nextLine.substring(0, 5).equals("done ")) {
                    processDone(nextLine.substring(5));
                    nextLine = sc.nextLine();
                } else {
                    switch (nextLine) {
                        case "bye":
                            farewell();
                            cont = false;
                            break;
                        case "list":
                            int num = 1;
                            System.out.println("Here yur tasks faggit: ");
                            for (Task i : texts) {
                                System.out.println(num + "." + i.getStatus());
                                num++;
                            }
                            nextLine = sc.nextLine();
                            break;
                        default:
                            add(nextLine);
                            nextLine = sc.nextLine();
                    }
                }
            } else {
                switch (nextLine) {
                    case "bye":
                        farewell();
                        cont = false;
                        break;
                    case "list":
                        int num = 1;
                        System.out.println("Here yur tasks faggit: ");
                        for (Task i : texts) {
                            System.out.println(num + "." + i.getStatus());
                            num++;
                        }
                        nextLine = sc.nextLine();
                        break;
                    default:
                        add(nextLine);
                        nextLine = sc.nextLine();
                }
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
