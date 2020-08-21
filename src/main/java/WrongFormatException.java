package main.java;

public class WrongFormatException extends DukeException {
    @Override
    public String toString(){
        String s = "OOPS!!! Please use the dd/MM/yyyy HHmm format.\n";
        return s;
    }
}
