package com.company;

import java.util.Scanner;
import java.lang.Math;

// knapsack problem

public class Main {

    public static int weight[];
    public static int price[];
    public static int variable;
    public static int totalWeight[] = new int[6];
    public static int totalPrice[] = new int [6];
    public static int weightLimit;
    public static int epoch = 0;
    public static int epochCounter = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        System.out.println("Enter number of variables");
        variable = input.nextInt();
        System.out.println("Enter the weight limit for knapsack problem");
        weightLimit = input.nextInt();
        System.out.println("Enter the number of epochs to run");
        epoch = input.nextInt();

        weight = new int[variable];
        price = new int[variable];


        for(int i = 0; i< variable; i++){

            System.out.println("Enter weight of variable"+(i+1));
            weight[i] = input.nextInt();

            System.out.println("Enter price of variable"+(i+1));
            price[i] = input.nextInt();

        }


        int s[][] = new int[6][variable];// [number of chromosome with two extra offspring (4+2)] [no of gene]

        // random initialization of 2D array
        for(int i = 0; i < 4; i++){   //i < 4 for initial 4 chromosomes excluding offsprings

            for(int j = 0; j < variable; j++){

                s[i][j] = getRandom();
            }

        }

        setWeight(s);

        setPrice(s);

        sortSet(s,totalPrice,totalWeight);

        printSet(s);



        while(epoch >= 0){

            System.out.println(epochCounter + " Epoch ......................................................");

            createOffspring(s);

            sortSet(s,totalPrice,totalWeight);

            printSet(s);


            epochCounter++;

            epoch--;

        }


        finalOutput(s,totalWeight,totalPrice);


    }


    public static int getRandom(){

        double random = Math.random();

        if(random<0.5)
            return 0;
        else
            return 1;

    }


    public static void setWeight(int s[][]){

        for (int i = 0; i < 6; i++){

            totalWeight[i] = 0;

            for(int j = 0; j < variable; j++){

                totalWeight[i] =  totalWeight[i] + s[i][j]*weight[j];
                //System.out.println(weight[j] + "     " + s[i][j] + "   " + totalWeight[i]);

            }

        }

    }


    public static void printSet(int s[][]){

        for(int i = 0; i < 6; i++){


            for(int j = 0; j < variable; j++){

                System.out.print(s[i][j]);

            }
            System.out.println("     Total weight is = "+ totalWeight[i] + "     Total Price is =" + totalPrice[i]);
            System.out.println(" ");


        }
        System.out.println("-----------------------------------------------------------------------------------");
    }



    public static void setPrice(int s[][]){

        for(int i = 0; i < 6; i++){

            totalPrice[i] = 0;

            for(int j = 0; j < variable ; j++){

                if(totalWeight[i] <= weightLimit){

                    totalPrice[i] = totalPrice[i] + s[i][j]*price[j];
                }
                else
                    totalPrice[i]=0;

            }

        }


    }


    public static void sortSet(int s[][], int totalPrice[], int totalWeight[]){

        int tempPrice;
        int tempSet;
        int tempWeight;

        for(int ii = 0; ii < 6; ii++) {

            for (int i = 1; i < 6-ii; i++) { // (array.length-1) required for bubble sort

                if (totalPrice[i-1] <= totalPrice[i]) {


                    tempPrice = totalPrice[i-1];
                    totalPrice[i-1] = totalPrice[i];
                    totalPrice[i] = tempPrice;

                    for (int j = 0; j < variable; j++) {

                        tempSet = s[i - 1][j];
                        s[i - 1][j] = s[i][j];
                        s[i][j] = tempSet;
                    }

                    tempWeight = totalWeight[i-1];
                    totalWeight[i-1] = totalWeight[i];
                    totalWeight[i] = tempWeight;

                }

            }
        }



    }

    public static void createOffspring(int s[][]){

        double crossOverPoint = getCrossOverPoint() * variable; //getting the cross over position for offspring

        int splittingPoint = (int) crossOverPoint;

        //  System.out.println(crossOverPoint);
        //  System.out.println(splittingPoint);

        int temp1[] = new int[variable];
        int temp2[] = new int [variable];

        for( int j =0; j < splittingPoint ;j++){

            temp1[j] = s[0][j];
            temp2[j] = s[1][j];

        }

        for(int j = splittingPoint; j < variable; j++){

            temp1[j] = s[1][j];
            temp2[j] = s[0][j];
        }


        //System.out.println(splittingPoint);

       /* for(int i = 0; i < variable; i++){

            System.out.println(temp1[i] + "    " + temp2[i]);

        }

        */

        for(int j = 0; j < variable; j++){  //adding twp offspring

            s[4][j]= temp1[j];
            s[5][j] = temp2[j];
        }


        mutation(s);// mutation

        setWeight(s); //setting weight after insertion of offspring

        setPrice(s); // setting price after insertion of offspring

    }


    public static double getCrossOverPoint(){

        double crossOverPoint = 0;


        while(crossOverPoint < 0.3 || crossOverPoint > 0.7)
        {
            crossOverPoint = Math.random();
            // System.out.println(crossOverPoint);

        }

        return(crossOverPoint);


    }


    public static void mutation(int s[][]){

        double mutationChance = Math.random();


        if(mutationChance < 0.05){

            for(int i = 4; i <= 5; i++){

                int mutationPosition = (int) (Math.random() * variable);

                for(int j = 0; j < variable; j++){

                    if(j == mutationPosition){

                        if(s[i][mutationPosition] == 1){

                            s[i][mutationPosition] = 0;
                        }

                        else {

                            s[i][mutationPosition] = 1;
                        }

                    }


                }


            }

            System.out.println("Mutation has occurred");

        }


    }

    public static void finalOutput(int s[][], int totalWeight[], int totalPrice[]){

        System.out.println("The selected items are: ");

        for(int j = 0; j < variable; j++){

            if(s[1][j] == 1){

                System.out.println("Variable" + (j+1));
            }

        }

        System.out.println("The weight is " + totalWeight[1]);
        System.out.println("The price is " + totalPrice[1]);


    }

}
