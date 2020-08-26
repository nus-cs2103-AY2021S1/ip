package Exceptions;

// Base Exception to extend from
public class DukeException extends Exception{

    public DukeException(String message){
        super(message);
    }
    public String toString(){
        return "Please use the appropriate commands" ;
    }
}
