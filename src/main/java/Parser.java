public class Parser {
    private final static String BYE_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String DONE_COMMAND = "done";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
    private final static String DELETE_COMMAND = "delete";

    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        String command = tempArray[0];

        if (command.equals(BYE_COMMAND)) {
            return new ByeCommand(fullCommand);
        }
        if (command.equals(LIST_COMMAND)) {
            return new ListCommand(fullCommand);
        }
        if (command.equals(DONE_COMMAND)) {
            return new DoneCommand(fullCommand);
        }
        if (command.equals(DELETE_COMMAND)) {
            return new DeleteCommand(fullCommand);
        }
        if (isTask(command)) {
            return new AddCommand(fullCommand);
        }

        throw new InvalidCommandException();
    }

    public static boolean isTask(String command) {
        return isDeadlineCommand(command) || isToDoCommand(command) || isEventCommand(command);
    }

    public static boolean isDeadlineCommand(String command) {
        return command.equals(DEADLINE_COMMAND);
    }

    public static boolean isToDoCommand(String command) {
        return command.equals(TODO_COMMAND);
    }

    public static boolean isEventCommand(String command) {
        return command.equals(EVENT_COMMAND);
    }





//    public void runDuke() throws InvalidTimeException {
//
//        ArrayList<Task> list = storage.getCurrentTasks();
//        messageEcho("Hello! I'm Duke! Welcome back!\n" + listIterator(list));
//        Scanner sc = new Scanner(System.in);
//        Parser parser = new Parser();
//
//        while (sc.hasNext()) {
//            String word = sc.nextLine();
//            String[] tempArray = word.trim().split(" ");
//            String command = parser.getCommand(tempArray[0]);
//
//            try {
//                if (command.equals(BYE_COMMAND)) {
//                    messageEcho("Bye. Hope to see you again soon!");
//                    break;
//                }
//                if (word.equals(LIST_COMMAND)) {
//                    messageEcho(listIterator(list));
//                    continue;
//                }
//
//
//                if (tempArray[0].equals(DONE_COMMAND)) {
//                    if (tempArray.length != 2) {
//                        throw new InvalidDoneFormatException();
//                    }
//                    if (!isNumber(tempArray[1])) {
//                        throw new IncorrectDoneInputException(list.size());
//                    }
//
//                    int itemIndex = Integer.parseInt(tempArray[1]) - 1;
//                    if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > list.size()) {
//                        throw new IncorrectDoneInputException(list.size());
//                    }
//                    list.get(itemIndex).markAsDone();
//                    messageEcho("Nice! I've marked this task as done:\n" + list.get(itemIndex).toString());
//                    continue;
//                }
//
//                if ((tempArray[0].equals(DELETE_COMMAND))) {
//                    if (tempArray.length != 2) {
//                        throw new InvalidDeleteFormatException();
//                    }
//                    if (!isNumber(tempArray[1])) {
//                        throw new IncorrectDeleteInputException(list.size());
//                    }
//
//                    int itemIndex = Integer.parseInt(tempArray[1]) - 1;
//                    if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > list.size()) {
//                        throw new IncorrectDeleteInputException(list.size());
//                    }
//                    Task deletedTask = list.get(itemIndex);
//                    list.remove(itemIndex);
//                    messageEcho("Noted. I've removed this task:\n" + deletedTask.toString()
//                            + "\nNow you have " + list.size() + " tasks in the list.");
//                    continue;
//                }
//
//                if (tempArray[0].equals(DEADLINE_COMMAND)) {
//                    if (tempArray.length == 1) {
//                        throw new InvalidDeadlineFormatException();
//                    }
//                    String deadlineAndDate = word.substring(9);
//                    String[] deadlineDateArray = deadlineAndDate.trim().split(" /by ");
//                    if (deadlineDateArray.length != 2) {
//                        throw new InvalidDeadlineFormatException();
//                    }
//                    Deadline newDeadlineTask = new Deadline(deadlineDateArray[0], Time.getFormatedTime(deadlineDateArray[1]));
//                    list.add(newDeadlineTask);
//                    messageEcho(
//                            "Got it. I've added this task:\n" + newDeadlineTask.toString() +
//                                    "\nNow you have " + list.size() + " tasks in the list."
//                    );
//                    continue;
//                }
//
//                if (tempArray[0].equals(TODO_COMMAND)) {
//                    if (!(tempArray.length > 1)) {
//                        throw new InvalidToDoFormatException();
//                    }
//                    String task = word.substring(5);
//                    ToDo newToDoTask = new ToDo(task);
//                    list.add(newToDoTask);
//                    messageEcho(
//                            "Got it. I've added this task:\n" + newToDoTask.toString() +
//                                    "\nNow you have " + list.size() + " tasks in the list."
//                    );
//                    continue;
//                }
//
//                if (tempArray[0].equals(EVENT_COMMAND)) {
//                    if (tempArray.length == 1) {
//                        throw new InvalidEventFormatException();
//                    }
//                    String eventAndDate = word.substring(6);
//                    String[] eventDateArray = eventAndDate.trim().split(" /at ");
//                    if (eventDateArray.length != 2) {
//                        throw new InvalidEventFormatException();
//                    }
//                    Event newEventTask = new Event(eventDateArray[0], Time.getFormatedTime(eventDateArray[1]));
//                    list.add(newEventTask);
//                    messageEcho(
//                            "Got it. I've added this task:\n" + newEventTask.toString() +
//                                    "\nNow you have " + list.size() + " tasks in the list."
//                    );
//                    continue;
//                }
//
//                throw new InvalidCommandException();
//
//            } catch (DukeException e) {
//                messageEcho(e.getMessage());
//            }
//        }
//
//        storage.saveFile(list);
//    }
}
