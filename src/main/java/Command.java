public class Command {
    public String description;
    public String[] parameters;

    public Command() {
        this.description = "";
        this.parameters = new String[0];
    }

    public Command(String[] parameters) {
        this.description = "";
        this.parameters = parameters;
    }

    public boolean isEmpty() {
        return this.parameters.length == 0;
    }

    public String[] getParameters() { return this.parameters ; }

}
