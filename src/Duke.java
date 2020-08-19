import input.UserInput;
import function.DukeFunction;
import data.DukeData;

public class Duke {

    public static void main(String[] args) {
        DukeFunction.greet();

        while (!DukeData.exitLoop) {
            String inputLine = UserInput.getOneLine();
            DukeFunction.checkCommand(inputLine);
        }
    }
}
