import java.util.Scanner;

public class Duke {
    private static String logo =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";

    private static void greet() {
        System.out.println(logo);
        System.out.println("Hello! This is Duke.\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Duke is always there for you!");
        System.out.println(logo);
    }

    private static void response() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (true) {
            if (Parser.isExit(line)) {
                exit();
                break;
            }
            else if (line.isEmpty()) {
                line = scanner.nextLine();
                continue;
            }
            else if (Parser.isList(line)) {
                ListFunction.printList();
            }
            else if (Parser.isDone(line)) {
                ListFunction.setDone(Integer.parseInt(line.substring(5)));
            }
            else {
                int type = Parser.taskType(line);
                if (type == -1)
                    System.out.println("Sorry, can not figure out your meaning :(");
                else if (type == 1)
                    ListFunction.add(type, Parser.getName(line));
                else
                    ListFunction.add(type, Parser.getName(line), Parser.getTime(line));
            }
            line = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        response();
    }
}
