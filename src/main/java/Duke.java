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

    private static void testNextLineSplit(String nextLine) throws EmptyDescriptionException, UnknownCommandException {
        String[] nextLineSplit = nextLine.split(" ", 2);
        switch (nextLineSplit[0]) {
            case "todo":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], 1);
                break;
            case "deadline":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], 2);
                break;
            case "event":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], 3);
                break;
            case "done":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                processDone(nextLineSplit[1]);
                break;
            default:
                throw new UnknownCommandException("Don't understand...");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        boolean cont = true;
        String nextLine = sc.nextLine();
        while (cont) {
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
                    try {
                        testNextLineSplit(nextLine);
                    } catch (UnknownCommandException e) {
                        System.out.println(e.toString());
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    }
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
