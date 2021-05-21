/**
 *
 ** The code is optimizing from up to down.
 *
 ** Variables are used to store information to be referenced and manipulated in a computer program.
 ** They also provide a way of labeling data with a descriptive name, so our programs can be understood more clearly by the reader and ourselves.
 ** It is helpful to think of variables as containers that hold information.
 *
 *! The basic difference is that primitive variables store the actual values, whereas reference variables store the addresses of the objects they refer to.
 *! Let’s assume that a class Person is already defined. If you create an int variable a, and an object reference variable person, they will store their values in memory.
 *
 *? [If statement rules]
 ** if (condition){
 **      expressions;
 **      ...
 ** } else if (condition) {
 **      expressions;
 **      ...
 ** } else {
 **      expressions;
 **      ...
 ** }
 *
 * * Condition is an expression that returns everytime a boolean value.
 */

package com.homework3;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // int num1 = 24;
        // int num2 = 25;
        // int num3 = 26;
        // int maxNum = num1;

        // if (maxNum < num2){
        //     maxNum = num2;
        // }

        // if (maxNum < num3){
        //     maxNum = num3;
        // }

        // System.out.println("Max number: " + maxNum);





//         boolean exit = true;
//
//         Scanner input = new Scanner(System.in);
//
//         while(exit){
//
//             System.out.println("Please type one char (A, B, C, D, F) (Type 0 for exit): ");
//
//             char[] gradeInput = input.nextLine().toCharArray();
//
//             switch (gradeInput[0]) {
//                 case 'A':
//                     System.out.println("Perfect : Success!");
//                     break;
//                 case 'B':
//                     System.out.println("Very Good : Success!");
//                     break;
//                 case 'C':
//                     System.out.println("Good : Success!");
//                     break;
//                 case 'D':
//                     System.out.println("Not Bad : Success!");
//                     break;
//                 case 'F':
//                     System.out.println("Unfortunately U R Failed!");
//                     break;
//                 case '0':
//                     exit = false;
//                     break;
//
//                 default:
//                     System.out.println("Please type a character that is one of givens!");
//                     break;
//             }
//
//         }
//
//         input.close();





        // String[] credits = { "Quick Credit", "Happy Retired Credit", "Mortgage Credit", "Farmer Credit",
        // 		"Military Credit", "Education Credit" };

        // for (int i = 0; i < credits.length; i++) {
        // 	System.out.println(credits[i]);
        // }

        // // foreach
        // for (String credit : credits) {
        // 	System.out.println(credit);
        // }




        // double[] myList = { 1.2, 6.3, 4.3, 5.6 };
        // double total = 0;
        // double max = myList[0];

        // for (double num : myList){
        //     if (max < num){
        //         max = num;
        //     }

        //     total += num;
        // }

        // System.out.println("Total: " + total);
        // System.out.println("Max: " + max);




        // String[][] cities = new String[3][3];

        // cities[0][0] = "İstanbul";
        // cities[0][1] = "Bursa";
        // cities[0][2] = "Bilecik";
        // cities[1][0] = "Ankara";
        // cities[1][1] = "Konya";
        // cities[1][2] = "Kayseri";
        // cities[2][0] = "Malatya";
        // cities[2][1] = "Şanlıurfa";
        // cities[2][2] = "Gaziantep";

        // for (int i = 0; i < 3; i++){
        //     System.out.println("-------------------");
        //     for (int j = 0; j < 3; j++){
        //         System.out.println(cities[i][j]);
        //     }
        // }
        // System.out.println("-------------------");



//        String message = "Today, weather is very good.";
//        System.out.println(message);

//        System.out.println("Element count: " + message.length());
//        System.out.println("5th element: " + message.charAt(4));
//        System.out.println(message.concat(" Hurray!"));
//        System.out.println(message.startsWith("w"));
//        System.out.println(message.endsWith("."));

//        char[] chars = new char[5];
//        message.getChars(0, 4, chars, 0);
//        System.out.println(chars);

//        System.out.println(message.indexOf('a'));
//        System.out.println(message.lastIndexOf('r'));




//        Scanner input = new Scanner(System.in);
//
//        checkPrime(input);

    }

    static void checkPrime(Scanner input){

        boolean exit = true;

        String result = "";

        while(exit){

            result = "";

            System.out.println("\nPlease type a positive number (Type 0 for exit): ");

            int number = input.nextInt();

            if (number == -1){
                exit = false;
                break;
            }

            if (number == 0 || number == 1){
                result = "It isn't a prime number.";
            } else if(number == 2){
                result = "It is a prime number.";
            } else {
                for (int i = 2; i < number; i++){
                    if(number % i == 0){
                        result = "It isn't a prime number.";
                        break;
                    } else {
                        result = "It is a prime number.";
                    }
                }
            }

            System.out.println(result);

        }

        input.close();

    }

}
