package com.company;

import java.util.Scanner;
import java.lang.Math;
public class Main {


    static double weightA = Math.random();
    static double weightB = Math.random();
    static double bias = Math.random();
    static double LR = 0.1; //learning rate


    static int target[] = new int[4];
    static double Y[] = new double[4];
    static int A[] = new int[4];
    static int B[] = new int[4];
    static int YY[] = new int [4]; //Final answer



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        int epochCount = 1;
        int i;
        int j;
        int k;


        for(k=0;k<4;k++)
        {
            System.out.println("Enter value of A" + "["+k+"]");
            A[k] = input.nextInt();
            System.out.println("Enter value of B" + "["+k+"]");
            B[k] = input.nextInt();
            System.out.println("Enter value of target" + "["+k+"]");
            target[k] = input.nextInt();

        }

        while( epochCount < 10){

            System.out.println(epochCount + " Epoch Number");

            for(i = 0; i < 4; i++){

                System.out.println("Training Set: " + (i+1));

                training(A[i],B[i],target[i],Y[i]);

            }


            for(j = 0; j < 4 ; j++){

                YY[j] = condition(A[j],B[j],weightA,weightA,bias);
            }

            for(j = 0; j < 4; j++){
                System.out.println(YY[j]);
            }


            epochCount++;


        }


    }

    public static void training(int A,int B,int target, double Y){

        Y = bias + weightA*A + weightB*B;
        weightA = weightA + A*(target-Y)*LR;
        weightB = weightB + B*(target-Y)*LR;
        bias = bias + (target-Y)*LR;

        System.out.println(Y + " " + weightA + "  " + weightB + "  " + bias);


    }






    public static int condition(int A, int B, double weightA, double weightB, double bias){

        if( (A*weightA + B*weightB + bias) >= 0 )
            return 1;//when condition is true

        else
            return 0;//when condition is false
    }



}
