import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Implements methods that prints out messages to console.
 */

public class Ui {
    private final BufferedReader inputReader;

    /**
     * Instantiates Ui object and BufferedReader object to scan user input.
     */
    public Ui() {
        Reader inputStreamReader = new InputStreamReader(System.in);
        this.inputReader = new BufferedReader(inputStreamReader);
    }

    /**
     * Outputs the total number of tasks in ArrayList.
     */
    public String printTaskCount() {
        if (Task.totalTasks > 1) {
            return "You have a total of " + Task.totalTasks + " tasks in the list.";
        } else {
            return "You have a total of " + Task.totalTasks + " task in the list.";
        }
    }

    /**
     * Outputs welcome message.
     */
    public String printWelcomeMessage() {
        String message = "Hello there! My name is Duke. \nHow may I assist you?";
        return message;
    }

    /**
     * Outputs exit message after exiting program.
     * @return Response object
     */
    public Response printByeMessage() {
        String goodbye = "Goodbye. Hope to see you again soon!";
        Response responseObject = new Response(goodbye, true);
        return responseObject;
    }

    /**
     * Outputs done message after task is completed.
     * @param doneTask Task that is added.
     * @return Response object
     */
    public Response doneMessage(Task doneTask) {
        String done = "Great job! This task has been marked as done:";
        String finalOutput = done + "\n" + doneTask;
        Response responseObject = new Response(finalOutput);
        return responseObject;
    }

    /**
     * Outputs added message after task is added.
     * @return Response object
     */
    public Response addedMessage(Task newTask) {
        String added = "Thank you for your input. The following task has been added to the list:";
        String finalOutput = added + "\n" + " " + newTask.toString() + "\n" + printTaskCount();
        Response responseObject = new Response(finalOutput);
        return responseObject;
    }

    /**
     * Outputs remove message after task is removed.
     * @return Response object
     */
    public Response removeMessage(Task deletedTask) {
        String removeMsg = "The following task has been successfully removed:";
        String finalOutput = removeMsg + "\n" + deletedTask + "\n" + printTaskCount();
        Response responseObject = new Response(finalOutput);
        return responseObject;
    }

    /**
     * Lists tasks in ArrayList.
     * @param arrayOfTasks Our main ArrayList.
     * @return Response object
     */
    public Response listTasks(TaskList arrayOfTasks) {
        String listMsg = "These are the tasks in your list:";
        String finalOutput = listMsg + "\n" + arrayOfTasks.toString();
        Response responseObject = new Response(finalOutput);
        return responseObject;
    }

    /**
     * Prints message to show matching tasks.
     * @return Response object
     */
    public Response matchingMessage(TaskList arrayOfTasks) {
        String matchingMsg = "Here are your matching tasks in your list";
        String finalOutput = matchingMsg + "\n" + arrayOfTasks.toString();
        Response responseObject = new Response(finalOutput);
        return responseObject;
    }

    /**
     * Prints message to say that there are no matching tasks found.
     * @return Response object
     */
    public Response noMatchMessage() {
        String noMatchMsg = "There are no matching tasks!";
        Response responseObject = new Response(noMatchMsg);
        return responseObject;
    }

    /**
     * Output error message if unable to write to file path.
     * @return Response object
     */
    public Response writeError() {
        String goodbye = "Bye. Hope to see you again!";
        String finalOutput = "Tasks could not be saved to 'duke.txt'. Please check that you have a 'data' folder "
                             + "containing 'duke.txt'." + "\n\n" + goodbye;
        Response responseObject = new Response(finalOutput, true);
        return responseObject;
    }

    /**
     * Output message if done writing to file path.
     * @return Response object
     */
    public Response finishWriting() {
        String goodbye = "Goodbye. Hope to see you again soon!";
        String finalOutput = "Tasks have been successfully saved to duke.txt!" + "\n\n" + goodbye;
        Response responseObject = new Response(finalOutput, true);
        return responseObject;
    }

    /**
     * Output generic error message.
     * @return Response object
     */
    public Response returnError(String errorMessage) {
        Response responseObject = new Response(errorMessage);
        return responseObject;
    }

    /**
     * Output error message for list command failure.
     * @return Response object
     */
    public Response listError() {
        String output = "Failed to list tasks. There can be two reasons:\n"
                + "1. Missing 'duke.txt' in 'data' folder of project directory.\n "
                + "2. Your list of tasks is currently empty.";
        Response responseObject = new Response(output);
        return responseObject;
    }
}
