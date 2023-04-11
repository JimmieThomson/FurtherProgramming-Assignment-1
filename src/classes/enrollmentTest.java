package classes;

/*
 *  Date Made: 1/4/2023
 *
 *  James Thomson
 *
 *  Description: Testing the Methods in the class enrollment
 *
 * */

import classes.customExceptions.NoCourseFoundException;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class enrollmentTest {
    private enrollment enrollmentClassLink;

    @Before
    public void setUp() {
        enrollmentClassLink = new enrollment();
    }
    @After
    public void tearDown() {
        enrollmentClassLink = null;
    }

    //Used to demonstrate that when no enrolled classes are in the Hashmap then it will return false
    @Test
    public void showEnrolledCoursesIfEmpty() {
        assertFalse(enrollmentClassLink.showEnrolledCourses());
    }

    //Used to demonstrate when there is a class the is enrolled to return True;
    @Test
    public void showEnrolledCoursesIfClassIsEnrolled() throws NoCourseFoundException {
        enrollmentClassLink.addCourse("Java programming");
        assertTrue(enrollmentClassLink.showEnrolledCourses());
    }

    //Exception should be thrown if a class key does not exist but tries to be added as a enrollment class
    @Test(expected = NoCourseFoundException.class)
    public void searchKeyWordFalseItem() throws NoCourseFoundException{
        enrollmentClassLink.addCourse("Java prog");
    }

    //Returns a list with the class names with the work "Java" no capitals
    @Test
    public void SearchKeyWordJava(){
        assertNotNull(enrollmentClassLink.SearchKeyWord("Java"));
    }

    //Returns an empty list if a class with that name does not exist
    @Test
    public void SearchKeyWordNoWords(){
        ArrayList<String> empty = new ArrayList<>();
        assertEquals(empty, enrollmentClassLink.SearchKeyWord(""));
    }
    //Gets if a class with an appropriate name has been added successfully!
    @Test
    public void addClassesValid() throws NoCourseFoundException{
        enrollmentClassLink.addCourse("Programming skills");
        assertNotNull(enrollmentClassLink.getEnrolledClasses());
    }
}