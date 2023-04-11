package classes.customExceptions;

public class NoEnrolledCoursesFound extends Exception{
    public NoEnrolledCoursesFound(String message){
        super(message);
    }
}
