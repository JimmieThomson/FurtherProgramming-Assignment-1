/*
 *  Date Made: 9/3/2023
 *
 *  Author: James Thomson
 *
 *  Description: All the tasks and initialization happen here
 * */

import java.util.ArrayList;
import java.util.Scanner;

import classes.fileImport;

public class Main {
    private static fileImport imp;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        imp = new fileImport("/Users/jamesthomson/IdeaProjects/FurtherProgramming Assignment 1/src/course.csv");
        try {
            System.out.println(imp.content());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Welcome to MyTimetable! \n");
        mainMenu();
    }

    public static void mainMenu() {
        while(true) {
            System.out.print("-------------------------------------------------------------------------------- \n" +
                    "> Select from main menu \n" +
                    "-------------------------------------------------------------------------------- \n" +
                    "   1) Search by keyword to enroll \n" +
                    "   2) Show my enrolled courses \n" +
                    "   3) Withdraw from a course \n" +
                    "   4) Exit \n" +
                    "Please select:");
            String item = scanner.nextLine();
            if (item.equals("1")) {
                searchByKeyword();
            } else if (item.equals("2")) {
                showEnrolledCourses();
            }
        }
    }

    public static void searchByKeyword() {
        System.out.print("Please provide a brand:");
        String keyword = scanner.nextLine();
        String[] Matches = imp.SearchKeyWord(keyword).toArray(new String[0]);
        int lengthOfMatches = Matches.length;
        System.out.println("-------------------------------------------------------------------------------- \n" +
                "> Select from matching list \n" +
                "--------------------------------------------------------------------------------");
        for (int i = 0; i < lengthOfMatches; i++) {
            System.out.println((i + 1) + ") " + Matches[i]);
        }
        System.out.print(lengthOfMatches + 1 + ") Go to main menu\n" + "Please select: ");
        int Course = scanner.nextInt() - 1;
        if ((lengthOfMatches + 1) == Course) {
            return;
        } else {
            imp.addCourse(Matches[Course]);
        }
        scanner.nextLine();
    }
    public static void showEnrolledCourses(){
        int i = 1;
        for (ArrayList<String> course : imp.getEnrolledClasses()) {
            System.out.printf(i + ") %s \t %s \t %s\n", course.get(0), course.get(3), course.get(5));
            i++;
        }
    }
}