import java.util.Scanner;

public class Parser {
    public static void parse(Ui ui, TaskList taskList) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toLowerCase();
        while (!input.equals("bye")) {
            ui.printDivider();
            try {
                if (taskList.isEmpty()) {
                    throw new EmptyListException("There are no tasks on your list");
                }
                switch (input) {
                    case "help":
                        ui.printHelp();
                        break;
                    case "add":
                        Parser.processAdd(taskList, sc, ui);
                        Storage.saveTaskChanges(taskList);
                        break;
                    case "list":
                        ui.printList(taskList);
                        break;
                    case "done":
                        Parser.processDone(taskList, sc, ui);
                        Storage.saveTaskChanges(taskList);
                        break;
                    case "delete":
                        Parser.processDelete(taskList, sc, ui);
                        Storage.saveTaskChanges(taskList);
                        break;
                    case "find":
                        Parser.processFind(taskList, sc, ui);
                        break;
                    default:
                        throw new UnknownCommandException("Sorry I didn't understand that :(");

                }
            } catch (UnknownCommandException e1) {
                ui.showUnknownCommandException(e1);
            } catch (EmptyListException e2) {
                ui.showEmptyListException(e2);
            }
            ui.printAdditionActionMessage();
            input = sc.nextLine().toLowerCase();
        }
        ui.printGoodbyeMessage();
    }

    public static void processAdd(TaskList taskList, Scanner sc, Ui ui) {
        ui.printTaskTypes();
        try {
            String type = sc.nextLine().toLowerCase();
            TaskType taskType;
            switch (type.toLowerCase()) {
                case "todo":
                    taskType = TaskType.TODO;
                    ui.printEnterTaskPrompt();
                    break;
                case "deadline":
                    taskType = TaskType.DEADLINE;
                    ui.printDeadlineExample();
                    break;
                case "event":
                    taskType = TaskType.EVENT;
                    ui.printEventExample();
                    break;
                default:
                    throw new InvalidTaskTypeException("Oops that wasn't a valid task type!");
            }
            processTaskType(taskList, taskType, sc, ui);
        } catch (InvalidTaskTypeException e) {
            ui.showInvalidTaskTypeException(e);
        }
    }

    public static void processTaskType(TaskList taskList, TaskType taskType, Scanner sc, Ui ui)
            throws ArrayIndexOutOfBoundsException {
        String inputToAdd = sc.nextLine();
        try {
            if (inputToAdd.isEmpty()) {
                throw new EmptyDescriptionException("Oops, the description can't be empty!");
            }
            switch (taskType) {
                case TODO:
                    taskList.addTask(new Todo(inputToAdd, taskType));
                    break;
                case DEADLINE:
                    String[] deadlineSplit = inputToAdd.split(",");
                    taskList.addTask(new Deadline(deadlineSplit[0], deadlineSplit[1], taskType));
                    break;
                case EVENT:
                    String[] eventSplit = inputToAdd.split(",");
                    taskList.addTask(new Event(eventSplit[0], eventSplit[1], taskType));
                    break;
                default:
            }
            ui.printAddAcknowledgement(taskList);
        } catch (EmptyDescriptionException e1) {
            ui.showEmptyDescriptionException(e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            ui.showWrongFormat(e2);
        }
    }

    public static void processDone(TaskList taskList, Scanner sc, Ui ui) throws IndexOutOfBoundsException {
        ui.printDonePrompt();
        int taskNum = sc.nextInt();
        sc.nextLine();
        try {
            if (taskList.getTask(taskNum - 1).isDone()) {
                throw new InvalidDoneCommandException("This task is already done!");
            } else {
                taskList.getTask(taskNum - 1).markAsDone();
                ui.printDoneAcknowledgement(taskList, taskNum);
            }
        } catch (IndexOutOfBoundsException e1) {
            ui.showIndexOutOfBoundsException(e1);
        } catch (InvalidDoneCommandException e2) {
            ui.showInvalidDoneCommandException(e2);
        }
    }

    public static void processDelete(TaskList taskList, Scanner sc, Ui ui) throws IndexOutOfBoundsException {
        ui.printDeletePrompt();
        int taskNum = sc.nextInt();
        sc.nextLine();
        try {
            Task task = taskList.getTask(taskNum - 1);
            taskList.removeTask(taskNum - 1);
            ui.printDeleteAcknowledgement(taskList, task);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundsException(e);
        }
    }

    public static void processFind(TaskList taskList, Scanner sc, Ui ui) throws IndexOutOfBoundsException {
        ui.printFindPrompt();
        String keyword = sc.next();
        sc.nextLine();
        ui.printFoundTasksHeader();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                ui.printTask(taskList, i);
            } else {
                continue;
            }
        }
    }
}
