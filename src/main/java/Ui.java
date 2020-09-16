/**
 * Ui class is the main handler of users inputs,
 * Ui takes in user inputs, categorises it and stores it.
 */
public class Ui {

    private final Parser parser;
    private String description;
    private String correctedInput;
    private DateAndTime byOrAt;

    /**
     * Constructs a ui object to handle user inputs.
     */
    public Ui() {
        parser = new Parser();
    }

    public String greetings(){
        return "Hi, this is Duke the All-Knowing task manager.\n"
                + "Here is my command list, please follow exactly:\n"
                + "bye\n"
                + "list\n"
                + "find <feature>\n"
                + "done <seq number>\n"
                + "delete <seq number>\n"
                + "todo <description>\n"
                + "event <description> /at <2020-02-02> <18:00>\n"
                + "deadline <description> /by <2020-02-02> <18:00>\n"
                + "reminder\n"
                + "Hope we have a good time together :).\n";
    }

    /**
     * A helper which deals with the Bye instruction.
     * @return a string to say bye to the user
     */
    private String dealBye(){
        return " Bye. Hope to see you again soon!\n"
                + "You can now close this window to quit me as a program.";
    }

    /**
     * A helper which deals with the List instruction.
     * @return loads and returns all tasks stored
     */
    private String dealList(){
        return TaskList.read();
    }

    /**
     * A helper which deals with the Find instruction.
     * @param input a string, which is text entered by the user
     * @return all tasks containing the string
     */
    private String dealFind(String input){
        return TaskList.find(input.substring(Parser.FIND.length() + 1));
    }

    /**
     * A helper which deals with the Done instruction.
     * @param input a string, which is text entered by the user
     * @return a task, which now has a completion state of a tick
     */
    private String dealDone(String input){

        int ref = Integer.parseInt(Character.toString(
                input.charAt(Parser.DONE_VALID.length() - 1))) - 1;

        if (ref >= TaskList.taskStorage.size()) {
            return new DukeException("I am afraid that it is not possible" +
                    "to do an unknown task.").toString();
        }

        assert TaskList.taskStorage.get(ref) != null;

        Task done = TaskList.taskStorage.get(ref).markAsDone();
        TaskList.taskStorage.remove(TaskList.taskStorage.get(ref));
        TaskList.taskStorage.add(ref, done);
        Storage.write(TaskList.taskStorage);

        return ("Nice! I've marked this task as done:\n"
                + TaskList.taskStorage.get(ref));
    }

    /**
     * A helper which deals with the Delete instruction.
     * @param input a string, which is text entered by the user
     * @return a string representation of a task, which now has a completion state of a tick
     */
    private String dealDelete(String input){

        int ref = Integer.parseInt(Character.toString(
                input.charAt(Parser.DEL_VALID.length() - 1))) - 1;

        if (ref >= TaskList.taskStorage.size()) {
            return new DukeException("I am afraid that it is not possible" +
                    "to delete an unknown task.").toString();
        }

        assert TaskList.taskStorage.get(ref) != null;

        String result = TaskList.delete(ref);
        Storage.write(TaskList.taskStorage);

        return result;
    }

    /**
     * A helper which deals with the Todo instruction.
     * @param input a string, which is text entered by the user
     * @return a string representation of a Todo task
     */
    private String dealTodo(String input){

        if (!parser.isValidTodo(input)) {
            return new DukeException(
                    "The description of a todo cannot be empty lah."
            ).toString();
        }

        description = input.substring(Parser.TODO_VALIDATION.length(),
                Parser.TODO_VALIDATION.length() + 1);

        if (parser.isEmptyDescription(description)) {
            return new DukeException(
                    "OOPS!!! The description of a todo cannot be empty."
            ).toString();
        }

        correctedInput = input.substring(Parser.TODO_VALIDATION.length());
        byOrAt = new DateAndTime();
        String result = TaskList.write(correctedInput, Parser.TODO, byOrAt);
        Storage.write(TaskList.taskStorage);

        return result;

    }

    /**
     * A helper which deals with the Event instruction.
     * @param input a string, which is text entered by the user
     * @return a string representation of an Event task
     */
    private String dealEvent(String input){

        if (!parser.isValidEvent(input)){
            return new DukeException(
                    "The description of an event cannot be empty lah."
            ).toString();
        }

        description = input.substring(Parser.EVT_VALIDATION.length(),
                Parser.EVT_VALIDATION.length() + 1);

        if(parser.isEmptyDescription(description)){
            return new DukeException(
                    "The description of an event cannot be empty lah."
            ).toString();
        }

        String dateAndTime = input.substring(input.indexOf("/")
                + Parser.BYORAT.length());

        assert (dateAndTime.contains("-") && dateAndTime.contains(":"))
                : "Invalid date or time format \n please follow this: 2020-02-02 18:00";

        byOrAt = new DateAndTime(dateAndTime);
        correctedInput = input.substring(Parser.EVT_VALIDATION.length());
        String result = TaskList.write(correctedInput, Parser.EVT, byOrAt);
        Storage.write(TaskList.taskStorage);

        return result;

    }

    /**
     * A helper which deals with the Deadline instruction.
     * @param input a string, which is text entered by the user
     * @return a string representation of a Deadline task
     */
    private String dealDeadline(String input){

        if(!parser.isValidDeadline(input)){
            return new DukeException(
                    "The description of a deadline cannot be empty lah."
            ).toString();
        }

        description = input.substring(Parser.DDL_VALIDATION.length(),
                Parser.DDL_VALIDATION.length() + 1);

        if (parser.isEmptyDescription(description)) {
            return new DukeException(
                    "The description of a deadline cannot be empty lah."
            ).toString();
        }

        String dateAndTime = input.substring(input.indexOf("/")
                + Parser.BYORAT.length());

        assert (dateAndTime.contains("-") && dateAndTime.contains(":"))
                : "Invalid date or time format \n please follow this: 2020-02-02 18:00";

        byOrAt = new DateAndTime(dateAndTime);
        correctedInput = input.substring(Parser.DDL_VALIDATION.length());
        String result = TaskList.write(correctedInput, Parser.DDL, byOrAt);
        Storage.write(TaskList.taskStorage);

        return result;

    }

    private String dealReminder(){
        return TaskList.remind();
    }

    private String dealStarter(){

                String logo = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from \n" + logo;

    }

    /**
     * Workhorse of the project, deal with all possible instructions exactly as desired.
     * @param input string text from the user
     * @return a string representing results of the instruction
     */
    public String deal(String input) {

        if(parser.isStarter(input)){
            return dealStarter();
        }

        if (parser.isReminder(input)){
            return dealReminder();
        }

        if (parser.isBye(input)) {
            return dealBye();
        }

        if (parser.isList(input)) {
            return dealList();
        }

        if (parser.isFind(input)) {
            return dealFind(input);
        }

        if(parser.isDone(input)){
            return dealDone(input);
        }

        if(parser.isDelete(input)){
            return dealDelete(input);
        }

        if(parser.isTodo(input)){
            return dealTodo(input);
        }

        if(parser.isEvent(input)){
            return dealEvent(input);
        }

        if(parser.isDeadline(input)){
            return dealDeadline(input);
        }

        return new DukeException(
                "OOPS!!! I'm sorry, but I don't know what that means :-("
        ).toString();

    }

}
