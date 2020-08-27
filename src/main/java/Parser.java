public class Parser {
    public static Command parse(String command){
        return new Command(command.split(" "));
    }
}