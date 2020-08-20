import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public enum TaskType {
        TODOS,
        DEADLINE,
        EVENT
    }

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

    private static void add(String toAdd, TaskType taskType) {
        //need to improve regex
        Task task = null;
        String[] splitDeadline = toAdd.split("/", 2 );
        String[] content;
        if (splitDeadline.length == 2) content = splitDeadline[1].split(" ", 2);
        else content = null;
        switch (taskType) {
            case TODOS:
                if (splitDeadline.length == 2) task = new ToDos(splitDeadline[0].trim(), content[1]);
                else task = new ToDos(splitDeadline[0]);
                break;
            case DEADLINE:
                if (splitDeadline.length == 2) task = new Deadline(splitDeadline[0].trim(), content[1]);
                else task = new Deadline(splitDeadline[0]);
                break;
            case EVENT:
                if (splitDeadline.length == 2) task = new Event(splitDeadline[0].trim(), content[1]);
                else task = new Event(splitDeadline[0]);
                break;
        }
        texts.add(task);
        System.out.println("Got it, here yur task bij");
        System.out.println(task.toString());
        System.out.println("Now you have " + texts.size() + " tasks in the list.");
    }


    private static void processDone(String s) throws InvalidIndexException {
        try {
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num > texts.size() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                String i = texts.get(Integer.parseInt(s) - 1).markAsDone();
                System.out.println(i);
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    private static void processDelete(String s) {
        try {
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num > texts.size() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                String i = texts.remove(Integer.parseInt(s) - 1).toString();
                System.out.println(i);
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    private static void testNextLineSplit(String nextLine) throws EmptyDescriptionException, UnknownCommandException {
        String[] nextLineSplit = nextLine.split(" ", 2);
        switch (nextLineSplit[0]) {
            case "todo":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], TaskType.TODOS);
                break;
            case "deadline":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], TaskType.DEADLINE);
                break;
            case "event":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                add(nextLineSplit[1], TaskType.EVENT);
                break;
            case "done":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                try {
                    processDone(nextLineSplit[1]);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            case "delete":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                try {
                    processDelete(nextLineSplit[1]);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            default:
                throw new UnknownCommandException("Don't understand...");
        }
    }

    private static boolean testNextLine(String nextLine) {
        switch (nextLine) {
            case "bye":
                farewell();
                return true;
            case "list":
                int num = 1;
                System.out.println("Here yur tasks faggit: ");
                for (Task i : texts) {
                    System.out.println(num + "." + i.toString());
//                    WriteFile.writeFinally(num + "." + i.toString());
                    num++;
                }
                return false;
            default:
                try {
                    testNextLineSplit(nextLine);
                } catch (UnknownCommandException | EmptyDescriptionException e) {
                    System.out.println(e.toString());
//                    WriteFile.writeFinally(e.toString());
                }
                return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        WriteFile.reset();
        greet();
        boolean saidBye = false;
        while (!saidBye) {
            String nextLine = "";
            if (!saidBye) nextLine = sc.nextLine();
            saidBye = testNextLine(nextLine);
        }
        sc.close();
    }
}