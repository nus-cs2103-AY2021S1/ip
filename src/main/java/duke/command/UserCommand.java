package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UserCommand {

     String userInput;

     public Boolean isExit = false;

     public UserCommand(String userInput){
          this.userInput = userInput;
     }

     public void execute(TaskList taskList, Ui ui) throws DukeException {

     }


     public boolean isExit() {
          return isExit();
     }
}
