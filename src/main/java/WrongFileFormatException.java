public class WrongFileFormatException extends DukeException{

    WrongFileFormatException(String filePath){
        this.errorMessage = "The file '" + filePath + "' has the wrong file format.\n" +
                "Make sure it follows the following format for the following tasks types:\n" +
                "1: [T], [D], [E]\n" +
                "2: [X], [DONE]\n" +
                "3: <TaskName>\n" +
                "4: (by: <DateTime>)(for deadline) OR (at/on: <DateTime>)(for event)\n";
    }
}
