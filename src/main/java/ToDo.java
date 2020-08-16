package main.java;

public class ToDo extends Task{
        protected String description;

        ToDo(String description){
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
}
