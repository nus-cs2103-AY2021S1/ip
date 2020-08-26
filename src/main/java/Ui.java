import java.util.Scanner;

/**
 * Ui class is the main processor for users inputs,
 * Ui takes in user inputs, categorises it and stores it.
 */
public class Ui {

    private final Scanner scanner;
    private final Parser parser;

    public Ui(){
        scanner = new Scanner(System.in);
        parser = new Parser();
    }

    public void deal(){
        for (int i = 0; i < 100000; i++) {

            String input = this.scanner.nextLine();

            if (!parser.isBye(input)) {

                if (!parser.isList(input)) {

                    if (!parser.isDone(input) && !parser.isDelete(input)) {

                        if (parser.isTodo(input) || parser.isEvent(input)
                                || parser.isDeadline(input)) {

                            String description;
                            String correctedInput;
                            DateAndTime byOrAt;

                            if (parser.isTodo(input)) {

                                if(parser.isValidTodo(input)) {

                                    description = input.substring(Parser.TODO_VALIDATION.length(),
                                            Parser.TODO_VALIDATION.length() + 1);

                                    if (!parser.isEmptyDescription(description)) {

                                        correctedInput = input.substring(Parser.TODO_VALIDATION.length());

                                        byOrAt = new DateAndTime();
                                        TaskList.write(correctedInput, Parser.TODO, byOrAt);
                                        Storage.write(TaskList.taskStorage);

                                    } else {
                                        System.out.println(
                                                new DukeException(
                                                        "OOPS!!! The description of a todo cannot be empty."));
                                    }

                                }else{
                                    System.out.println(
                                            new DukeException(
                                                    "The description of a todo cannot be empty lah."));
                                }

                            } else {

                                String dateAndTime = input.substring(input.indexOf("/")
                                        + Parser.BYORAT.length());

                                if (parser.isEvent(input)) {

                                    if(parser.isValidEvent(input)) {

                                        description = input.substring(Parser.EVT_VALIDATION.length(),
                                                Parser.EVT_VALIDATION.length() + 1);

                                        if (!parser.isEmptyDescription(description)) {

                                            byOrAt =new DateAndTime(dateAndTime);

                                            correctedInput = input.substring(Parser.EVT_VALIDATION.length());
                                            TaskList.write(correctedInput, Parser.EVT, byOrAt);
                                            Storage.write(TaskList.taskStorage);

                                        } else {
                                            System.out.println(new DukeException(
                                                    "The description of an event cannot be empty lah."));
                                        }
                                    }else{
                                        System.out.println(new DukeException(
                                                "The description of an event cannot be empty lah."));
                                    }

                                }else {

                                    if(parser.isValidDeadline(input)) {

                                        description = input.substring(Parser.DDL_VALIDATION.length(),
                                                Parser.DDL_VALIDATION.length() + 1);

                                        if (!parser.isEmptyDescription(description)) {

                                            byOrAt = new DateAndTime(dateAndTime);

                                            correctedInput = input.substring(Parser.DDL_VALIDATION.length());
                                            TaskList.write(correctedInput, Parser.DDL, byOrAt);
                                            Storage.write(TaskList.taskStorage);

                                        } else {
                                            System.out.println(
                                                    new DukeException(
                                                            "The description of a deadline cannot be empty lah.")
                                            );
                                        }
                                    }else{
                                        System.out.println(
                                                new DukeException(
                                                        "The description of a deadline cannot be empty lah.")
                                        );
                                    }

                                }
                            }

                        } else {
                            System.out.println(
                                    new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(")
                            );
                        }

                    } else {

                        if(parser.isDone(input)) {

                            int ref = Integer.parseInt(Character.toString(
                                    input.charAt(Parser.DONE_VALID.length() - 1))) - 1;

                            if (TaskList.taskStorage.get(ref) != null) {

                                Task done = TaskList.taskStorage.get(ref).markAsDone();
                                TaskList.taskStorage.remove(TaskList.taskStorage.get(ref));
                                //^here
                                TaskList.taskStorage.add(ref, done);

                                Storage.write(TaskList.taskStorage);

                                System.out.println("Nice! I've marked this task as done:\n"
                                        + TaskList.taskStorage.get(ref));

                            } else {

                                System.out.println("I am afraid that it is not possible" +
                                        "to do an unknown task.");

                            }

                        }else{

                            int ref = Integer.parseInt(Character.toString(
                                    input.charAt(Parser.DEL_VALID.length() - 1))) - 1;

                            TaskList.delete(ref);
                            Storage.write(TaskList.taskStorage);

                        }

                    }
                }else {

                    TaskList.read();
                }
            } else {
                System.out.println(" Bye. Hope to see you again soon!");
                this.scanner.close();
                System.exit(0);
            }
        }

    }

}
