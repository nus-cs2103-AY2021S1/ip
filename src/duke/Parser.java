package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.WrongInputException;
import duke.command.Command;

public class Parser {

    public static Command parse(String command) throws DukeException {

            try {
                String[] cmd = command.split(" ", 2);
                String firstWord = cmd[0];
//                try {
//                    defCommand = Command.valueOf(firstWord.toUpperCase()); //input to upper case
//                } catch (IllegalArgumentException e) {
//                    throw new WrongInputException();
//                }

                if (cmd.length < 2) {

                    if (firstWord.equals("list")) {
                        return new ListCommand();

                    } else if (firstWord.equals("bye")) {
                        return new ExitCommand();
                    } else {
                        throw new DukeException("The description of the task cannot be empty.");

                    }

                } else {
                    String taskDetails = removeFirstWord(command);

                    if (firstWord.equals("done")) {
                        return new DoneCommand(taskDetails);

                    } else if (firstWord.equals("todo")) {
                        return new TodoCommand(taskDetails);

                    } else if (firstWord.equals("deadline")) {
                        return new DeadlineCommand(taskDetails);

                    } else if (firstWord.equals("event")) {
                        return new EventCommand(taskDetails);

                    } else if (firstWord.equals("delete")) {
                        return new DeleteCommand(taskDetails);

                    } else if (firstWord.equals("show")) {
                        return new ShowCommand(taskDetails);

                    } else {
                        throw new WrongInputException();
                        //System.out.println(wrong.getMessage());
                    }
                }
//                switch (defCommand) {
//                    case LIST:
//                        processList();
//                        break;
//                    case BYE:
//                        processBye();
//                        return;
//                    case DONE:
//                        processDone(taskDetails);
//                        break;
//                    case TODO:
//                        processTodo(taskDetails);
//                        break;
//                    case DEADLINE:
//                        processDeadline(taskDetails);
//                        return new DeadlineCommand(taskDetails);
//                        break;
//                    case EVENT:
//                        processEvent(taskDetails);
//                        break;
//                    case DELETE:
//                        processDelete(taskDetails);
//                        break;
//                    case SHOW:
//                        processShow(taskDetails);
//                        break;
//                    default:
//                        WrongInputException wrong = new WrongInputException();
//                        System.out.println(wrong.getMessage());
//                        break;
//                }
            } catch (DukeException exc) {
                System.out.println("Error!");
                throw new DukeException(exc.getMessage());
            }

            //throw new WrongInputException();
    }

    private static String removeFirstWord(String command) throws DukeException {
        try {
            String[] cmd = command.split(" ", 2);
            return cmd[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

//    public void processDelete(String duke.command) throws DeleteException {
//        try {
//            String theRest = removeFirstWord(duke.command);
//            Integer taskNum = Integer.parseInt(theRest);
//            int index = taskNum - 1;
//            System.out.println(index);
//            taskList.deleteTask(index);
//
//        } catch (DukeException d) {
//            throw new DeleteException("Please enter a number. I cannot delete nothing :(");
//        }
//    }
//
//    public void processTodo(String duke.command) throws TodoException {
//        try {
//            String theRest = removeFirstWord(duke.command);
//            Todo todo = new Todo(theRest);
//            taskList.saveToList(todo);
//        } catch (DukeException e) {
//            throw new TodoException();
//        }
//    }
//
//    public void processDeadline(String duke.command) throws DeadlineException {
//        try {
//            String theRest = removeFirstWord(duke.command);
//            String[] taskAndDeadlineAndTime = theRest.split(" /by ", 2);
//            Deadline deadline;
//
//            try {
//                String duke.task = taskAndDeadlineAndTime[0];
//                String dateAndTime = taskAndDeadlineAndTime[1];
//                String[] dateTime = dateAndTime.split(" ", 2);
//
//                String date = dateTime[0];
//
//                try {
//                    LocalDate localDate = LocalDate.parse(date);
//
//                    if (dateTime.length < 2) {
//                        deadline = new Deadline(duke.task, localDate);
//                    } else {
//                        String time = dateTime[1];
//                        LocalTime localTime = LocalTime.parse(time);
//                        deadline = new Deadline(duke.task, false, localDate, localTime);
//                    }
//                    taskList.saveToList(deadline);
//                } catch (DateTimeParseException e) {
//                    System.out.println("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
//                }
//
//            } catch (IndexOutOfBoundsException e) {
//                throw new DeadlineException("Please specify the duke.task and deadline.");
//            }
//
//        } catch (DukeException d) {
//            throw new DeadlineException("Please specify the duke.task and deadline.");
//        }
//    }
//
//    public void processEvent(String duke.command) throws EventException {
//        try {
//            String theRest = removeFirstWord(duke.command);
//            String[] eventAndDateAndTime = theRest.split(" /at ", 2);
//            Event event;
//
//            try {
//                String eventDesc = eventAndDateAndTime[0];
//                String eventDateAndTime = eventAndDateAndTime[1];
//
//                String[] dateTime = eventDateAndTime.split(" ", 2);
//
//                String date = dateTime[0];
//
//                try {
//                    LocalDate localDate = LocalDate.parse(date);
//
//                    if(dateTime.length < 2) {
//                        event = new Event(eventDesc, localDate);
//                    } else {
//
//                        String time = dateTime[1];
//                        String[] startEndTime = time.split("-");
//                        if (startEndTime.length < 2) {
//                            String startTime = startEndTime[0];
//                            LocalTime localTime = LocalTime.parse(startTime);
//                            event = new Event(eventDesc, false, localDate, localTime);
//                        } else {
//                            String startTime = startEndTime[0];
//                            String endTime = startEndTime[1];
//                            LocalTime localStartTime = LocalTime.parse(startTime);
//                            LocalTime localEndTime = LocalTime.parse(endTime);
//                            event = new Event(eventDesc, false, localDate, localStartTime, localEndTime);
//                        }
//                    }
//
//                    taskList.saveToList(event);
//
//                } catch (DateTimeParseException e) {
//                    System.out.println("Please enter the date in YYYY/MM/DD format and time in HH:MM format..");
//                }
//
//            } catch (IndexOutOfBoundsException e) {
//                throw new EventException("Please specify the event name and date.");
//            }
//
//        } catch (DukeException d) {
//            throw new EventException("Please specify the event name and date.");
//        }
//    }

//    public void processDone(String duke.command) throws DoneException {
//        try {
//            String theRest = removeFirstWord(duke.command);
//            Integer taskNum = Integer.parseInt(theRest);
//            taskList.markTaskAsDone(taskNum);
//            //updateData(this.todos);
//
//        } catch (DukeException d) {
//            throw new DoneException("Please enter a valid duke.task number");
//        }
//    }

//    public void processShow(String duke.command) throws CalendarException {
//        try {
//            String date = removeFirstWord(duke.command);
//            showCalendar(date);
//        } catch (DukeException e) {
//            throw new CalendarException("Invalid input date format. Please try again.");
//        }
//    }

//    public void processList() {
//        taskList.listItems();
//    }

}
