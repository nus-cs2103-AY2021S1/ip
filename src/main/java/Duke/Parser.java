package main.java.Duke;

import main.java.Duke.Commands.*;
import main.java.Duke.DukeException.DukeArrayException;
import main.java.Duke.DukeException.DukeException;
import main.java.Duke.Task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

     /**
     * Parses the user input and returns a Command to be executed.
     *
     * @param userInput User input from the user.
     * @param tasklist Current task list.
     * @return Command to be executed.
     * @throws DateTimeParseException if user input for date is not in the correct format.
     * @throws DukeException if user input is invalid.
     */
     public Command parse(String userInput, TaskList tasklist) {

         String commandType;
         String description = null;

         //Splits the userInput into type of command and its description
        if (userInput.contains(" ")) {
            commandType = userInput.substring(0, userInput.indexOf(' '));
            description = userInput.substring(userInput.indexOf(' ') + 1);
        } else {
            commandType = userInput;
        }
         

        switch (commandType) {
            case "list":
                return new listCommand(tasklist);

            case "todo":

                assert userInput.length() > 5 : "No description for todo";

                try {
                    if (userInput.length() <= 5) {
                        throw new DukeException();
                    }
                    String todoDescription = userInput.substring(5);
                    toDo task = new toDo(todoDescription, false);
                    return new addCommand(tasklist, task);

                } catch (DukeException e) {
                    return new exceptionCommand(tasklist, duke.ui.showException("Must include description for todo"));
                }

            case "deadline":

                assert userInput.contains("/by") : "deadline must include /by";

                try {
                    if (!userInput.contains("/by")) {
                        throw new DukeException();
                    } else {
                        int index = userInput.indexOf("/by");
                        if(userInput.substring(9,index).length() == 0){
                            throw new DukeException();
                        }

                        if(userInput.substring((index + 4)).length() == 0){
                            throw new DukeException();
                        }
                        String deadlineDateString = userInput.substring(index + 4);

                        LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
                        Deadline deadline = new Deadline(userInput.substring(9, index), false, deadlineDate);
                        return new addCommand(tasklist, deadline);
                    }
                } catch (DateTimeParseException e) {
                    return new exceptionCommand(tasklist, duke.ui.showException("Please input date in the format: YYYY-MM-DD"));
                } catch (DukeException e) {
                    return new exceptionCommand(tasklist, duke.ui.showException("deadline must include '<description> /by <date>'"));
                }


            case "event":

                assert userInput.contains("/at") : "event must include /at";

                try {
                    if (!userInput.contains("/at")) {
                        throw new DukeException();
                    } else {
                        int index = userInput.indexOf("/at");
                        if (userInput.substring(6,index).length()==0) {
                            throw new DukeException();
                        }
                        if (userInput.substring(index + 3).length() < 1) {
                            throw new DukeArrayException();
                        }
                        String eventDateString = userInput.substring(index + 4);

                        LocalDate eventDate = LocalDate.parse(eventDateString);
                        Event event = new Event(userInput.substring(6, index), false, eventDate);
                        return new addCommand(tasklist, event);
                    }
                } catch (DukeArrayException e){
                    return new exceptionCommand(tasklist,duke.ui.showException("event must include date after /at"));
                } catch (DateTimeParseException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Please input date in the format: YYYY-MM-DD"));
                } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("event must include '<description> /at <date>'"));
                }


        case "delete":

            assert userInput.length() >7 : "Must include number after delete";

            try {
                if (userInput.length() <= 6) {
                    throw new DukeException();
                }
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskNumber >= tasklist.list.size()) {
                    throw new DukeArrayException();
                }
                return new deleteCommand(tasklist, taskNumber);
            } catch (DukeArrayException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Number cannot be longer than the list."));
            } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include number after 'delete'"));
            } catch (NumberFormatException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include number after 'delete'"));
            }

        case "done":

            assert userInput.length() >5 : "Must include number after done";

            try {
                if (userInput.length() < 6) {
                    throw new DukeException();
                }
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskNumber >= tasklist.list.size()) {
                    throw new DukeArrayException();
                }
                return new doneCommand(tasklist, taskNumber);
            } catch (DukeArrayException e){
                return new exceptionCommand(tasklist,duke.ui.showException("Number cannot be longer than list."));
            } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include number after 'done'."));
            }

        case "bye":
            return new endCommand(tasklist);

        case "find":

            assert userInput.length() >5 : "Must include name after 'find'";

            try {
                if (userInput.length() < 6) {
                    throw new DukeException();
                }
                return new findCommand(tasklist, description);
            } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include name after 'find'."));
            }

        case "expense":
            try {
                if (!userInput.contains("/amount")) {
                    throw new DukeException();
                }
                int amountIndex = userInput.indexOf("/amount");
                String expenseString = userInput.substring(8,amountIndex);
                int expenseAmount = Integer.parseInt(userInput.substring(amountIndex + 8));
                return new addExpenseCommand(tasklist, new Expense(expenseString,false,expenseAmount));

            } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include '/amount' after expense"));
            }

        case "listExpense":
            return new listExpenseCommand(tasklist);

        case "deleteExpense":
            try {
                if (userInput.length() <= 14) {
                    throw new DukeException();
                }
                int expenseNumber = Integer.parseInt(userInput.substring(14)) - 1;
                if (expenseNumber >= tasklist.expenses.size()) {
                    throw new DukeArrayException();
                }
                return new deleteExpenseCommand(tasklist, expenseNumber);
            } catch (DukeArrayException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Number cannot be longer than the list."));
            } catch (DukeException e) {
                return new exceptionCommand(tasklist,duke.ui.showException("Must include number after 'deleteExpense'"));
            } catch (NumberFormatException e) {
                return new exceptionCommand(tasklist, duke.ui.showException("Must include number after 'deleteExpense'"));
            }

        case "totalExpense":
            return new totalExpenseCommand(tasklist,"");

        default:
            return new Command(tasklist,duke.ui.badInput());

        }
     }

}
