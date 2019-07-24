package com.company;

import java.util.Scanner;

public class Main {

    static int weightA = 0;
    static int weightB = 0;
    static int bias = 0 ;


    static int target[] = new int[4];
    static int Y[] = new int[4];
    static int A[] = new int[4];
    static int B[] = new int[4];


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int epochCount = 1;
        int i;
        int j;
        int k;


        for (k = 0; k < 4; k++) {
            System.out.println("Enter value of A" +"["+k+"]");
            A[k] = input.nextInt();
            System.out.println("Enter value of B" + "["+k+"]");
            B[k] = input.nextInt();
            System.out.println("Enter value of target" + "["+k+"]");
            target[k] = input.nextInt();
        }

        while (epochCount < 5) {

            System.out.println(epochCount + " Epoch Number");

            for (i = 0; i < 4; i++) {

                System.out.println("Training Set: " + (i + 1));

                training(A[i], B[i], target[i]);

            }


            for (j = 0; j < 4; j++) {

                Y[j] = condition(A[j], B[j], weightA, weightA, bias);
            }

            for (j = 0; j < 4; j++) {
                System.out.println(Y[j]);
            }


            epochCount++;
        }


    }


    public static void training(int A,int B,int target){

        weightA = weightA + A*target;
        weightB = weightB + B*target;
        bias = bias + target;

        System.out.println(weightA + "  " + weightB + "  " + bias);


    }






    public static int condition(int A, int B, int weightA, int weightB, int bias){

        if( (A*weightA + B*weightB + bias) >= 0 )
            return 1;//when condition is true

        else
            return 0;//when condition is false
    }

}
