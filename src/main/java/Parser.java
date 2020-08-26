public class Parser {
    public static boolean parse(String nextLine) {
        switch (nextLine) {
            case "bye":
                return true;
            case "list":
                System.out.println("Here yur tasks faggit: \n" + TaskList.toStr());
                return false;
            default:
                try {
                    parseSplit(nextLine);
                } catch (UnknownCommandException | EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
                return false;
        }
    }

    public static void parseSplit(String nextLine) throws EmptyDescriptionException, UnknownCommandException {
        String[] slashSplit = nextLine.split("/", 2);
        if (slashSplit.length == 2) {
            String[] spaceSplitFront = slashSplit[0].split(" ", 2);
            String[] spaceSplitBack = slashSplit[1].split(" ", 2);
            // todo = spaceSplitFront[0], description = spaceSplitFront[1], deadline = spaceSplitBack[1]
            switch (spaceSplitFront[0]) {
                case "todo":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.TODOS, spaceSplitFront[1], spaceSplitBack[1]));
                    break;
                case "deadline":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.DEADLINE, spaceSplitFront[1], spaceSplitBack[1]));
                    break;
                case "event":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.EVENT, spaceSplitFront[1], spaceSplitBack[1]));
                    break;
                default:
                    throw new UnknownCommandException("Don't understand...");
            }
        } else if (slashSplit.length == 1) {
            String[] spaceSplitFront = slashSplit[0].split(" ", 2);
            // todo = spaceSplitFront[0], description = spaceSplitFront[1]
            switch (spaceSplitFront[0]) {
                case "todo":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.TODOS, spaceSplitFront[1]));
                    break;
                case "deadline":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.DEADLINE, spaceSplitFront[1]));
                    break;
                case "event":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(TaskList.addTask(Task.TaskType.EVENT, spaceSplitFront[1]));
                    break;
                case "done":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Specify index la oi");
                    try {
                        parseDone(spaceSplitFront[1]);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "delete":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Specify index la oi");
                    try {
                        parseDelete(spaceSplitFront[1]);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                default:
                    throw new UnknownCommandException("Don't understand...");
            }
        } else {
            throw new Error();
        }
    }

    public static void parseDone(String str) {
        try {
            int num = Integer.parseInt(str) - 1;
            if (num < 0 || num > TaskList.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                System.out.println(TaskList.getTask(Integer.parseInt(str) - 1).markAsDone());
                System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    public static void parseDelete(String str) {
        try {
            int num = Integer.parseInt(str) - 1;
            if (num < 0 || num > TaskList.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                System.out.println(TaskList.removeTask(Integer.parseInt(str) - 1));
                System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }
}
