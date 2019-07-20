package com.company;

import java.util.Scanner;
import java.lang.Math;
public class Main {

    // Back propagation implementation of XOR gate based on classwork


    static double weight[][] = new double[6][6];
    static double bias[] = new double [6];
    static double LR = 0.1; //learning rate
    static double O[] = new double[6];
    static double I[] = new double[6];
    static double E[] = new double[6];


    static int target[] = new int[4];
    static int A[] = new int[4];
    static int B[] = new int[4];
    static int YY[] = new int [4];



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        int epochCount = 5;


        randomInitialization(bias,weight);


        for(int k = 0; k < 4 ; k++)
        {
            System.out.println("Enter value of A" + "["+k+"]");
            A[k] = input.nextInt();
            System.out.println("Enter value of B" + "["+k+"]");
            B[k] = input.nextInt();
            System.out.println("Enter value of target" + "["+k+"]");
            target[k] = input.nextInt();

        }

        int epoch =1;


        while(epochCount > 0) {

            System.out.println("Epoch number " + epoch + "---------------------------------");

            for (int k = 0; k <= 3; k++) {
                System.out.println("Training set " + (k+1)+ " ======================");
                training(A[k], B[k], target[k]);
                for(int i = 1; i <= 2;i++){

                    for(int j =3; j <= 4; j++){

                        System.out.print("weight" + i +""+ j + "  ");
                        System.out.println(weight[i][j]);
                        System.out.println("............................................");
                    }

                }

            }
            epochCount--;
        }





    }



    public static void randomInitialization(double bias[], double weight[][]){

        System.out.println("Random initialization");

        System.out.println("For bias");

        for(int i = 3; i <= 5; i++){

            bias[i] = Math.random();
            System.out.print("bias" + i + " =  ");
            System.out.println(bias[i]);

        }

        System.out.println("For weight");

        for(int i = 1; i <= 2; i++){
            for(int j = 3; j <= 4; j++){
                weight[i][j] = Math.random();
                weight[j][5] = Math.random();
            }

        }

        for(int i=1; i<=2 ;i++){
            for(int j=3; j<=4 ;j++){

                System.out.print("weight"+i+""+j+" = ");
                System.out.println(weight[i][j]);
                System.out.print("weight"+j+"5 = ");
                System.out.println(weight[j][5]);

            }
        }

        System.out.println("=========================================================");





    }


    public static void training(int A, int B, int target){
        //step2

        I[1] = A;
        I[2] = B;
        O[1] = I[1];
        O[2] = I[2];

        //step3
        for(int j = 3; j <= 4; j++){

            I[j] = weight[1][j] * O[1] + weight[2][j] * O[2] + bias[j];
            O[j] = 1/(1 + Math.exp(-I[j]));
        }

        //step4
        I[5] = weight[3][5] * O[3] + weight[4][5] + bias[5];
        O[5] = 1/(1 + Math.exp(-I[5]) );

        //step5
        E[5] = O[5] * (1 - O[5]) * (target- O[5]);

        for(int j = 3; j <= 4; j++){

            E[j] = O[j] * (1 - O[j]) * (E[5] * weight[j][5]);
        }


        //step6 update
        for(int i = 1; i <= 2; i++){

            for(int j = 3; j <= 4; j++){

                weight[i][j] = weight[i][j] + ( LR * E[j] * O[i] );

            }
        }

        for(int j = 3; j <= 4; j++){

            bias[j] = bias[j] + ( LR * E[j] );

            weight[j][5] = weight[j][5] + ( LR * E[5] * O[j] );
        }

        bias[5] = bias[5] + ( LR * E[5] );


    }





}
