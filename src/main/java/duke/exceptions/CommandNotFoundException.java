package duke.exceptions;

public class CommandNotFoundException extends DukeException {

    /**
     * Creates a CommandNotFoundException exception with te default error message.
     */
    public CommandNotFoundException() {
        this("OOPS!!! I'm sorry, but I don't know what that means... (´∀`)\n"
                + "So far I support the following tasks, eg: \n\n"
                + "ADD TASK: \n- todo do laundry \n- deadline submit hw2 /by 2020-08-11 \n"
                + "- event Hazel's birthday /at 2020-09-26 \n"
                + "- period-task collect IC /from 2020-08-11 /to 2020-11-14 \n\n"
                + "LIST ALL TASKS/TASKS ON CERTAIN DAY: \n"
                + "- list \n- list 2020-09-17 \n\n"
                + "MARK TASK AS DONE & DELETE TASK BY INDEX: \n"
                + "- done 1 \n- delete 3 \n\n"
                + "FIND TASK BY KEYWORD"
                + "- find hw3 \n\n"
                + "Quit the app and save your tasks by saying \"bye\" to YURINA CHAN. (☆▽☆)");
    }

    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
