import java.util.Scanner;

public class Duke {

    public enum TaskType {
        TODOS,
        DEADLINE,
        EVENT
    }

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

    private static void processDone(String s) throws InvalidIndexException {
        try {
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num > TaskList.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                String i = TaskList.getTask(Integer.parseInt(s) - 1).markAsDone();
                System.out.println(i);
                System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    private static void processDelete(String s) {
        try {
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num > TaskList.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                String i = TaskList.removeTask(Integer.parseInt(s) - 1).toString();
                System.out.println(i);
                System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
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
                System.out.println(TaskList.addTask(TaskType.TODOS, nextLineSplit[1]));
                break;
            case "deadline":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                System.out.println(TaskList.addTask(TaskType.DEADLINE, nextLineSplit[1]));
                break;
            case "event":
                if (nextLineSplit.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                System.out.println(TaskList.addTask(TaskType.EVENT, nextLineSplit[1]));
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
                System.out.println("Here yur tasks faggit: ");
                System.out.println(TaskList.toStr());
                return false;
            default:
                try {
                    testNextLineSplit(nextLine);
                } catch (UnknownCommandException | EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
                return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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