public class DescriptionEmptyException extends Exception{
    DescriptionEmptyException(String type) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.",type));
    }
}
