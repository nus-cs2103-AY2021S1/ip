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

    private static void add(String toAdd, int type) {
        Task task = null;
        String[] splitDeadline = toAdd.split("/", 2 );
        String[] content;
        if (splitDeadline.length == 2) content = splitDeadline[1].split(" ", 2);
        else content = null;
        switch (type) {
            case 1:
                if (splitDeadline.length == 2) task = new ToDos(splitDeadline[0].trim(), content[1]);
                else task = new ToDos(splitDeadline[0]);
                break;
            case 2:
                if (splitDeadline.length == 2) task = new Deadline(splitDeadline[0].trim(), content[1]);
                else task = new Deadline(splitDeadline[0]);
                break;
            case 3:
                if (splitDeadline.length == 2) task = new Event(splitDeadline[0].trim(), content[1]);
                else task = new Event(splitDeadline[0]);
                break;
        }
        texts.add(task);
        System.out.println("Got it, here yur task bij");
        System.out.println(task.toString());
        System.out.println("Now you have " + texts.size() + " tasks in the list.");
    }


    private static void processDone(String s) {
        System.out.println("okcan done:");
        System.out.println(texts.get(Integer.parseInt(s) - 1).markAsDone());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        boolean cont = true;
        String nextLine = sc.nextLine();
        String[] nextLineSplit = nextLine.split(" ", 1);
        while (cont) {
            nextLineSplit = nextLine.split(" ", 2);
            System.out.println(nextLineSplit[0]);
            switch (nextLine) {
                case "bye":
                    farewell();
                    cont = false;
                    break;
                case "list":
                    int num = 1;
                    System.out.println("Here yur tasks faggit: ");
                    for (Task i : texts) {
                        System.out.println(num + "." + i.toString());
                        num++;
                    }
                    nextLine = sc.nextLine();
                    break;
                default:
                    switch (nextLineSplit[0]) {
                        case "todo":
                            add(nextLineSplit[1], 1);
                            nextLine = sc.nextLine();
                            break;
                        case "deadline":
                            add(nextLineSplit[1], 2);
                            nextLine = sc.nextLine();
                            break;
                        case "event":
                            add(nextLineSplit[1], 3);
                            nextLine = sc.nextLine();
                            break;
                        case "done":
                            processDone(nextLineSplit[1]);
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
