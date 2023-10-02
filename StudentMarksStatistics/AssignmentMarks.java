
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignmentMarks {
    //main method
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("+++++++++++++");
        System.out.println("-- WELCOME --");
        System.out.println("+++++++++++++");

        System.out.println("\n* Please enter the name of the file you want to read *");
        String fileName = scan.nextLine();

        //Calling the readMarks and readUnit methods and storing the data as Results in results list and String
        List<Results> results = readMarks(fileName);
        // String unitName = readUnit(fileName);

        if (results != null){
            //infinite loop
            while(true){

                //F5: Simple menu system 
                System.out.println("\n++++++++++++++++++++++++++++++++++");
                System.out.println("-------------- MENU --------------");
                System.out.println("++++++++++++++++++++++++++++++++++");
                System.out.println("* 1. Print total marks of all students *" );
                System.out.println("* 2. Print students with total marks lower than the given threshold *");
                System.out.println("* 3. Print top 5 students with highest total marks *");
                System.out.println("* 4. Print top 5 students with lowest total marks *");
                System.out.println("* 5. Exit *");
                System.out.println("* Please choose options from 1 to 5. Type the number and press ENTER to execute the respective function: *");

                int fcn = scan.nextInt();

                //if the input is 5 then break the loop to terminate the application
                if(fcn == 5)
                    break; 

                //switch-case to select which functions the user wants to use
                switch (fcn){
                    case 1:
                        totalMarks(results);
                        break;
                    case 2:
                        System.out.println("* Enter the threshold mark: ");
                        double threshold = scan.nextDouble();
                        belowThreshold(results, threshold);
                        break;
                    case 3:
                        top5Students(results, "highest");
                        break;
                    case 4:
                        top5Students(results, "lowest");
                        break;
                    default:
                        System.out.println("* Invalid option. Please select from 1 to 5 *");
                }
            }
        }
    }

    //F1: Method to handle the file and read all the contents from the given file
    public static List<Results> readMarks(String fileName) {
        List<Results> results = new ArrayList<>();

        while (true) { // Infinite loop until a valid file is found or the user exits
            try {
                if (fileName.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program...");
                    System.exit(0); // Terminate the program on user demand
                }

                // Create a new FileReader and BufferedReader for each attempt
                FileReader reader = new FileReader(System.getProperty("user.dir") + "/resources/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String data;
                int lineCounter = 1;

                // loop to iterate through every line in the file
                while ((data = bufferedReader.readLine()) != null) {
                    // skip the first and second line
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
                        result.setTotalMarks(result.getAsn1() + result.getAsn2() + result.getAsn3());
                        results.add(result);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Some data are missing in line number " + lineCounter + " of the given file.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid datatype in line number " + lineCounter + " of the given file.");
                    }
                    lineCounter++;
                }

                // Close the BufferedReader after reading
                bufferedReader.close();

                // Break out of the loop if the file is found and processed successfully
                break;

            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please enter a valid filename or type 'exit' to exit: ");
                Scanner scanner = new Scanner(System.in);
                fileName = scanner.nextLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return results;
    }

    //Method to read the Unit name from the file
    public static String readUnit(String fileName){
        String unitName = null;
        boolean fileNotFound = true; // Flag to track if the file is not found

        while (fileNotFound) {
            try {
                if (fileName.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program...");
                    System.exit(0); // Terminate the program on user demand 
                }
                FileReader reader = new FileReader(System.getProperty("user.dir") + "/resources/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str = bufferedReader.readLine();
                unitName = str.split(":")[1].trim();
                fileNotFound = false; // Set the flag to false if the file is found

                // Close the BufferedReader after reading
                bufferedReader.close();
                // Break out of the loop if the file is found and processed successfully
                break;

            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please enter a valid filename or type 'exit' to exit: ");
                Scanner scanner = new Scanner(System.in);
                fileName = scanner.nextLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return unitName;
    }

    
    //F2: Method to print results with calculated total marks
    private static void totalMarks(List<Results> results) {
        System.out.println("*** Students with individual total marks and assignment marks: *** ");
        for(Results result: results){
            System.out.println("* Name: " + result.getFirstName() + " " + result.getLastName() +
                ", Student ID: " + result.getStudentId() + ", A1: " + result.getAsn1() +
                ", A2: " + result.getAsn2() + ", A3: " + result.getAsn3() + ", Total Marks: " +
                result.getTotalMarks() + " *");
        }
    }

    //F3:Method to print results with total marks below the given threshold
    private static void belowThreshold(List<Results> results, double threshold) {
        System.out.println("*** Students with total marks lower than " + threshold + ": ***");
        for (Results result: results){
            if(result.getTotalMarks() < threshold){
                System.out.println("* Name: " + result.getFirstName() + " " + result.getLastName() +
                    ", Student ID: " + result.getStudentId() + ", Total Marks: " +
                    result.getTotalMarks() + " *");
            }
        }
    }

    //F4:Method to print either top 5 highest or top 5 lowest scoring students
    private static void top5Students(List<Results> results, String str) {
        Results[] resultsArr = results.toArray(new Results[0]);

        //insertion sorting the results array in ascending order according to the total marks of the results
        for(int i = 1; i < resultsArr.length; i++){
            int j = i;
            while(j > 0 && resultsArr[j].getTotalMarks() < resultsArr[j-1].getTotalMarks()){
                Results temp = resultsArr[j];
                resultsArr[j] = resultsArr[j-1];
                resultsArr[j-1] = temp;
                j--;
            }
        }
        //if the given parameter is lowest print the results of the sorted array from the begining of the array to get lowest ones
        if(str.equals("lowest")){
            for(int i = 0; i < 5; i++){
                System.out.println("* Name: " + resultsArr[i].getFirstName() + " " + resultsArr[i].getLastName() +
                    ", Student ID: " + resultsArr[i].getStudentId() + ", Total Marks: " +
                    resultsArr[i].getTotalMarks() + " *");
            }
        }
        //else print the results from the end of the sorted array to get the highest ones
        else {
            for(int i = resultsArr.length - 1; i > resultsArr.length - 6; i--){
                System.out.println("* Name: " + resultsArr[i].getFirstName() + " " + resultsArr[i].getLastName() +
                    ", Student ID: " + resultsArr[i].getStudentId() + ", Total Marks: " +
                    resultsArr[i].getTotalMarks() + " *");
            }
        }

    }


    //Method to parse the string into double. if it encounters any error while parsing
    public static Double tryParse(String str){
        try{
            return Double.parseDouble(str);
        } catch (Exception e){
            return 0.0;
        }
    }


}
