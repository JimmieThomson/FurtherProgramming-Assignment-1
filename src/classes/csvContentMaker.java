package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 *  Date Made: 22/3/2023
 *
 *  James Thomson
 *
 *  Description: Storing information regarding csv into readable methods
 *
 * */

public class csvContentMaker {
    /*
    * csvContent Hold the values of all courses using a Hash Map
    * As a Hashmap cannot have duplicates this is perfect for courses
    * It's also easy to find
    */
    private final HashMap<String, String> csvContent;
    public csvContentMaker(){
        this.csvContent = content();
    }

    //This method is used when initialising the local private variable csvContent to store all the information inside the csv
    public HashMap<String, String> content() {
        ArrayList<String> csv = new ArrayList<>();
        //https://www.javatpoint.com/how-to-read-csv-file-in-java
        //Lines 22 to 33 are not my code, the above html link is where this code is from
        try {
            Scanner sc = new Scanner(new File("src/course.csv"));
            sc.useDelimiter(",|\r\n");   //sets the delimiter pattern

            while (sc.hasNext())  //returns a boolean value
            {
                //Adding to a temp csv array
                csv.add(sc.next());
            }
            HashMap<String, String> temp = new HashMap<>();

            /* This loop places everything into the hashmap
             * by first taking each item from the above while loop from the dynamic array
             * and taking the first item from each line, assuming each line is 7 intervals long
             * as the key, then placing the rest of the information in a formatted string using commas as separators :)
             * */
            for (int i = 0; i < csv.size(); i += 7) {
                String formatted = String.format("%s,%s,%s,%s,%s,%s", csv.get(1 + i), csv.get(2 + i), csv.get(3 + i), csv.get(4 + i), csv.get(5 + i), csv.get(6 + i));
                temp.put(csv.get(i), formatted);
            }
            sc.close();  //closes the scanner
            return temp;
        }catch(FileNotFoundException e) {
            System.err.println(e.getCause() + ": File was not found!");
        }
        //Returns a null value if there was a problem!
        return null;
    }

    //Returns a dynamic array of all course names in the csv file
    public ArrayList<String> getCourseNames(){
        //gets all key sets and places them into a Dynamic array and returns that value
        return new ArrayList<>(this.csvContent.keySet());
    }

    //Returns the value of the Hashmap stored named csvContent
    public HashMap<String, String> getCsvContent() {
        return csvContent;
    }
}
