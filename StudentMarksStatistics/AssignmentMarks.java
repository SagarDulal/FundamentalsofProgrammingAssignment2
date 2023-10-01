 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignmentMarks {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("+++++++++++++");
        System.out.println("-- WELCOME TO the Program --");
        System.out.println("+++++++++++++");

        System.out.println("\n* Please enter the name of the file you want to read *");
        String fileName = scan.nextLine();
                System.out.println("\n++++++++++++++++++++++++++++++++++");
                System.out.println("-------------- MENU ----------------");
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("* 1. Print total marks of all students *" );
                System.out.println("* 2. Print students with total marks lower than the given threshold *");
                System.out.println("* 3. Print top 5 students with highest total marks *");
                System.out.println("* 4. Print top 5 students with lowest total marks *");
                System.out.println("* 5. Exit *");
                System.out.println("* Please choose options from 1 to 5. Type the number and press ENTER to execute the respective function: *");             
    }

}
