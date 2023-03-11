package classes;/*
 *  Date Made: 9/3/2023
 *
 *  James Thomson
 *
 *  Description: Importing and formatting information contained in .CSV
 *22
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fileImport {
    String path;
    List<ArrayList<String>> csvContent;
    List<ArrayList<String>> enrolledClasses;


    public fileImport(String path) {
        this.path = path;
        this.csvContent = new ArrayList<>();
        this.enrolledClasses = new ArrayList<>();
    }

    //Returns a Dynamic Multidimensional array
    public List<ArrayList<String>> content() throws FileNotFoundException {
        ArrayList<String> csv = new ArrayList<>();
        //https://www.javatpoint.com/how-to-read-csv-file-in-java
        Scanner sc = new Scanner(new File(this.path));
        sc.useDelimiter(",|\r\n");   //sets the delimiter pattern

        while (sc.hasNext())  //returns a boolean value
        {
            //Adding to a temp csv array
            csv.add(sc.next());
        }
        ArrayList<String> temp = new ArrayList<>();

        /* This loop goes through each item in the csv
         * It is assumed that every 7 items there is a new course
         * Thus after every seven it is added to the multi array
         * Temp is renewed after every 7 not sure if this will increase memory
        */
        for (int i = 0; i < csv.size(); i++) {
            if ((i % 7) == 0 && i != 0) {
                this.csvContent.add(temp);
                temp = new ArrayList<>();
            }
            temp.add(csv.get(i));
        }
        sc.close();  //closes the scanner
        return csvContent;
    }

    //Returns a Dynamic array of matching words from the csvContent phrases
    public ArrayList<String> SearchKeyWord(String keyword) {
        ArrayList<String> Phrases = new ArrayList<>();
        //Looping through each array
        for (ArrayList<String> course : this.csvContent) {
            //Separating words from the phrase using \\s splitter and placing it into a separate array
            String[] words = course.get(0).split("\\s+"); // splits by whitespace
            for (String word : words) {
                //Checking if words match them placing them into a separate dynamic array Phrases
                if (word.equalsIgnoreCase(keyword)) {
                    Phrases.add(course.get(0));
                    break;
                }
            }
        }

        return Phrases;
    }

    public boolean addCourse(String courseName){
        for (ArrayList<String> course : this.csvContent) {
            if(course.get(0).equalsIgnoreCase(courseName)){
                enrolledClasses.add(course);
                System.out.println(enrolledClasses);
                return true;
            }
        }
        return false;
    }

    public List<ArrayList<String>> getEnrolledClasses(){
        return this.enrolledClasses;
    }
}
