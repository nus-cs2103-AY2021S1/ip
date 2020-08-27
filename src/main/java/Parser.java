public class Parser {
    /**
     * method to split user input into an array separated by spaces
     * @param command user input
     * @return returns a command object with input as the required String array
     */
    public static Command parse(String command){
        return new Command(command.split(" "));
    }
}