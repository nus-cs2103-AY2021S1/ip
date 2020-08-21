package main.java;

public class WillyException extends Exception {
    String style = "\t______________________________________________________________\n";
    String error;

    public WillyException(String errorMessage) {
        super(errorMessage);
        this.error = errorMessage;
    }

    @Override
    public String toString(){
        return style + "\t" + error + "\n" + style;
    }
}
