package duke.exceptions;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException() {
        this("OOPS!!! I'm sorry, but I don't know what that means... (´∀`)\n"
                + "So far I support the following tasks, eg: \n\n"
                + "ADD TASK: \ntodo do sth \ndeadline submit hw2 /by 2020-08-11 \nevent Hazel's birthday /at 2020-09-26 \n"
                + "period-task collect IC /from 2020-08-11 /to 2020-11-14 \n\n"
                + "LIST ALL TASKS/ TASKS ON CERTAIN DAY, eg: \n"
                + "list \nlist 2020-09-17 \n\n "
                + "MARK TASK AS DONE & DELETE TASK BY INDEX,eg: \n"
                + "done 1 \ndelete 3 \n\n"
                + "find task by keyword, eg: \n find hw3 \n\n"
                + "Quit the app and save yours tasks by saying \"bye\" to YURINA CHAN.");
    }

    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
