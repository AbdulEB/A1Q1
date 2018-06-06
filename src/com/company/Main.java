package com.company;

/* Author: Abdul El Badaoui
* Student Number: 5745716
* Description: The following program provides the student scores based on the correct number ordered historical events
* they provided. This problem is the derivative of the LCS problem in dynamic programing and the example in class
* was used to construct this algorithm
* */

import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner fileInput; // to read in the file

    public static void main(String[] args) {
        //surrounded in a try-catch clause in case the file is not in the src directory
        try {
            fileInput = new Scanner(new FileInputStream(new File("a1q1in.txt")));
            int n = fileInput.nextInt(); // number of historical events
            int [] x = new int[n]; // the array which will store the correct number of events
            for (int i =0; i<n ; i++){ //storing the correct order of the historical events
                x[i] = fileInput.nextInt();
            }
            studentScoreLCS(n, x);//runs the students Scores

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    //the algorithm that will provide the score for each student
    public static void studentScoreLCS(int n, int [] x) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileOutput = new PrintWriter("a1q1out.txt", "UTF-8");// output file creation
        int count =0; // to count the number of students
        while(fileInput.hasNext()){ // while there are students, check the score for each one
            count++;
            int [] y = new int[n]; // array to hold the student answer
            for (int i = 0 ; i<n ;  i++){//stores the student answer
                y[i]= fileInput.nextInt();
            }
            int [][] c = new int [n+1][n+1]; // 2d array to compare array X and Y and provide the student score
            for (int i = 0 ; i<=n ; i++){//initialize the row and column of the comparision 2d array
                c[0][i]=0;
                c[i][0]=0;
            }
            for (int i = 1 ; i<=n; i++){//filling up the values of each cell based on adjacency
                for (int j=1; j<=n ; j++){
                    // if equal looks to the cell left and above of it value and added by 1 for the current cell
                    if (x[i-1]==y[j-1]){
                        c[i][j] = c[i-1][j-1]+1;
                    }
                    //if above cell is greater or equal then the cell that is left of it, current cell equal to the
                    // above cell
                    else if (c[i-1][j]>= c[i][j-1]){
                        c[i][j] = c[i-1][j];
                    }
                    // all other conditions current cell is equal to the left cell
                    else{
                        c[i][j] = c[i][j-1];
                    }
                }
            }
            fileOutput.println("Student "+count+ " score is "+c[n][n]); //print the student score
        }
        fileOutput.close();// closes the file out put writer
    }
}
