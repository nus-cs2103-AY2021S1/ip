package main.java;

public class DukeException extends Exception {
    String style = "\t______________________________________________________________\n";
    String error;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.error = errorMessage;
    }

    @Override
    public String toString(){
        return style + "\t" + error + "\n" + style;
    }
}
