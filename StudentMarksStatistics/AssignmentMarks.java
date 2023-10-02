 

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
        List<Results> results = readMarks(fileName);

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
       
    //method to handle the file and read all the contents from the given file
    
    public static List<Results> readMarks(String fileName){
        List<Results> results = new ArrayList<>();

        try {
            FileReader reader = new FileReader(System.getProperty("user.dir") + "/resources/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String data;
            int lineCounter = 1;

            //loop to iterate through every lines in the file
            while ((data = bufferedReader.readLine()) != null) {

                //skip the first and second line
                if (lineCounter == 1 || lineCounter == 2) {
                    lineCounter++;
                    continue;
                }

                String[] details = data.split(",");

                Results result = new Results();
                try {
                    result.setLastName(details[0].trim());
                    result.setFirstName(details[1].trim());
                    result.setStudentId(details[2].trim());
                    result.setAsn1(tryParse(details[3]));
                    result.setAsn2(0.0);
                    result.setAsn3(0.0);
                    if (details.length >= 5) {
                        result.setAsn2(tryParse(details[4]));
                    }
                    if (details.length >= 6) {
                        result.setAsn3(tryParse(details[5]));
                    }
                    result.setTotalMarks(result.getAsn1()+result.getAsn2()+result.getAsn3());
                    results.add(result);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Some data are missing in the line number " + lineCounter + " of the given file.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid datatype in line number " + lineCounter + " of the given file.");
                }
                lineCounter++;
            }

        }catch (FileNotFoundException e){
            System.out.println("File does not exist........ Stopping the application.......");
        } catch (IOException e){
            e.printStackTrace();
        }

        return results;
    }
    
    
    //method to parse the string into double. if it encounters any error while parsing
    public static Double tryParse(String str){
        try{
            return Double.parseDouble(str);
        } catch (Exception e){
            return 0.0;
        }
    }




}
