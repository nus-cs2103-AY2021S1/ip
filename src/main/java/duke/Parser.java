package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.UnknownCommandException;
import duke.task.Task;

public class Parser {

    public boolean parse(TaskList tasks, String nextLine) {
        switch (nextLine) {
            case "bye":
                return true;
            case "list":
                System.out.println("Here yur tasks faggit: \n" + tasks.toString());
                return false;
            default:
                try {
                    parseSplit(tasks, nextLine);
                } catch (UnknownCommandException | EmptyDescriptionException e) {
                    System.out.println(e.toString());
                }
                return false;
        }
    }

    public void parseSplit(TaskList tasks, String nextLine) throws EmptyDescriptionException, UnknownCommandException {
        String[] slashSplit = nextLine.split("/", 2);
        if (slashSplit.length == 2) {
            String[] spaceSplitFront = slashSplit[0].split(" ", 2);
            String[] spaceSplitBack = slashSplit[1].split(" ", 2);
            // todo = spaceSplitFront[0], description = spaceSplitFront[1], deadline = spaceSplitBack[1]
            switch (spaceSplitFront[0]) {
                case "todo":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(tasks.addTask(Task.TaskType.TODOS, spaceSplitFront[1], spaceSplitBack[1]));
                    break;
                case "deadline":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(tasks.addTask(Task.TaskType.DEADLINE, spaceSplitFront[1], spaceSplitBack[1]));
                    break;
                case "event":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(tasks.addTask(Task.TaskType.EVENT, spaceSplitFront[1], spaceSplitBack[1]));
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
                    System.out.println(tasks.addTask(Task.TaskType.TODOS, spaceSplitFront[1]));
                    break;
                case "deadline":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(tasks.addTask(Task.TaskType.DEADLINE, spaceSplitFront[1]));
                    break;
                case "event":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Description empty la oi");
                    System.out.println(tasks.addTask(Task.TaskType.EVENT, spaceSplitFront[1]));
                    break;
                case "done":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Specify index la oi");
                    try {
                        parseDone(tasks, spaceSplitFront[1]);
                    } catch (InvalidIndexException e) {
                        System.out.println(e.toString());
                    }
                    break;
                case "delete":
                    if (spaceSplitFront.length < 2) throw new EmptyDescriptionException("Specify index la oi");
                    try {
                        parseDelete(tasks, spaceSplitFront[1]);
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

    public void parseDone(TaskList tasks, String str) throws InvalidIndexException {
        try {
            int num = Integer.parseInt(str) - 1;
            if (num < 0 || num > tasks.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                System.out.println(tasks.getTask(Integer.parseInt(str) - 1).markAsDone());
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not number ley...");
        }
    }

    public void parseDelete(TaskList tasks, String str) throws InvalidIndexException {
        try {
            int num = Integer.parseInt(str) - 1;
            if (num < 0 || num > tasks.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            } else {
                System.out.println("okcan done:");
                System.out.println(tasks.removeTask(Integer.parseInt(str) - 1));
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            }
        } catch (InvalidIndexException e) {
            System.out.println("Input is not number ley...");
        }
    }
}
