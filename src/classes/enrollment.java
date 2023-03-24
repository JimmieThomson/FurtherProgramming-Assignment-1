package classes;
/*
 *  Date Made: 9/3/2023
 *
 *  James Thomson
 *
 *  Description: Importing and formatting information contained in .CSV
 *
 * */

import java.util.*;

public class enrollment {
    //Hashmap of the current courses that are listed
    private final HashMap<String, String> enrolledClasses;
    //Local scanner as not to initialize it with every Method
    private final Scanner scanner;
    //Class initialization
    private final csvContentMaker Content;


    public enrollment() {
        this.enrolledClasses = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.Content = new csvContentMaker();
    }

    //Runs the program
    public void run() {
        System.out.println("Welcome to MyTimetable! \n");
        mainMenu();
    }

    //Creates a Main Menu and directs user to methods when given a value
    public void mainMenu() {
        //While loop is used as not to create memory Problems :)
        while (true) {
            System.err.flush();
            System.out.flush();
            System.out.print("-------------------------------------------------------------------------------- \n" +
                    "> Select from main menu \n" +
                    "-------------------------------------------------------------------------------- \n" +
                    "   1) Search by keyword to enroll \n" +
                    "   2) Show my enrolled courses \n" +
                    "   3) Withdraw from a course \n" +
                    "   4) Exit \n" +
                    "Please select:");
            String item = scanner.nextLine();
            //Standard Switch to replace if statements
            switch (item) {
                case "1":
                    searchEnrollment();
                    break;
                case "2":
                    showEnrolledCourses();
                    break;
                case "3":
                    removeEnrolledCourses();
                    break;
                case "4":
                    return;
                default:
                    System.err.println("Please use a Valid input (1,2,3,etc)");
            }
        }
    }

    //This method gets a users input of name of what course they wish to enroll with then places that into a hashmap
    public void searchEnrollment() {
        //brand being a course key word or something
        System.out.print("Please provide a brand:");
        String keyword = scanner.nextLine();

        //Method is called here that takes the processing to another method
        String[] Matches = SearchKeyWord(keyword).toArray(new String[0]);

        int lengthOfMatches = Matches.length;
        if (lengthOfMatches != 0) {
            System.out.println("-------------------------------------------------------------------------------- \n" +
                    "> Select from matching list \n" +
                    "--------------------------------------------------------------------------------");

            //Loops through each keyword and places a number next to them
            for (int i = 0; i < lengthOfMatches; i++) {
                System.out.println((i + 1) + ") " + Matches[i]);
            }
            System.out.print(lengthOfMatches + 1 + ") Go to main menu\n" + "Please select: ");
            int Course = scanner.nextInt() - 1;

            //Gets the course name and add that course name by sending it to addCourses method
            //Returns the user back to the main menu by adding an exit after every key word is displayed
            if ((lengthOfMatches + 1) == Course) {
                return;
            } else {
                addCourse(Matches[Course]);
            }
            scanner.nextLine();
        } else {
            System.err.println("No Courses under that word are found!");
        }
    }

    //Shows the enrolled courses in a formatted way
    public boolean showEnrolledCourses() {
        int Counter = 1;

        //Used is there is no currently enrolled courses
        if(!enrolledClasses.isEmpty()) {

            //Loops through each courseName in the enrolled classes HashMap
            for (String name : this.enrolledClasses.keySet()) {
                System.out.print(Counter + ") " + name + " ");

                String[] parts = this.enrolledClasses.get(name).split(",");
                StringBuilder formattedOutput = new StringBuilder();

                //Formatting the seperated array into spaces
                for (String word : parts) {
                    formattedOutput.append(word).append(" ");
                }

                //Prints out the course every loop
                System.out.println(formattedOutput);
                Counter++;
            }
            return true;
        }else{
            return false;
        }
    }

    //Removes enrolled courses
    public void removeEnrolledCourses() {
        //Method to show what the user what they are currently enrolled with
        if(showEnrolledCourses()) {
            System.out.print("Please Select: ");
            String course = scanner.nextLine();
            int Counter = 1;

            //As there is no way to specify using an int value a loop must be used if the numbers match using a for loop and counter
            for (String name : this.enrolledClasses.keySet()) {
                if (course.equalsIgnoreCase(String.valueOf(Counter))) {
                    this.enrolledClasses.remove(name);
                    System.out.println(name + " has been REMOVED!");
                    break;
                }
                Counter++;
            }
        }else{
            System.err.println("There are no Enrolled courses!");
        }
    }

    //Returns a Dynamic array of matching words from the csvContent phrases
    public ArrayList<String> SearchKeyWord(String keyword) {
        ArrayList<String> Phrases = new ArrayList<>();
        //Looping through each Hash
        for (String CourseNames : Content.getCourseNames()) {
            //Separating words from the phrase using \\s splitter and placing it into a separate array
            String[] words = CourseNames.split("\\s+"); // splits by whitespace
            for (String word : words) {
                //Checking if words match them placing them into a separate dynamic array Phrases
                if (word.equalsIgnoreCase(keyword)) {
                    Phrases.add(CourseNames);
                    break;
                }
            }
        }

        return Phrases;
    }

    //Adds a course onto the dynamic array enrolledClasses
    public void addCourse(String courseName) {
        try {
            //Using the csvContentMaker class to get the key and value
            HashMap<String, String> csvContent = Content.getCsvContent();
            //adding it to the HashMap
            this.enrolledClasses.put(courseName, csvContent.get(courseName));
        } catch (Exception e) {
            System.err.println("An error Occurred (Course does not exist)!");
        }
    }

}
