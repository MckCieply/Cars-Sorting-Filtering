//Program pracuje na pliku tekstowym w ktorym z wierszach sa dane, pooddzielane przecinkami
//Dane zostaly skonwertowane z bazy danych w sqlite3 do pliku txt
//Data powstania: 27.12.2022
//Autor: 
package carsproject;

import java.io.*;
import java.util.Scanner;

//Project tasks
//Using db or csv file
//Use ifs and queries to properly show selected cars from menu
//ex. take from user model, price range, mileage range and show deals
public class CarsProject {
    
    static Scanner scann = new Scanner(System.in);
    
    public static int userInputModel(){
        int inModel = 0;
        System.out.print("Wybierz na podstawie jakiego modelu szukasz danych:\n"
                + "1. Volkswagen Scirocco\n"
                + "2. Mitsubishi Lancer\n"
                + "3. Volvo c30\n"
                + "4. Wszystkie\n");
        do{
            System.out.print(">>> ");
            try{ 
                inModel = scann.nextInt();
                if (!(inModel > 0 && inModel < 5)){
                    System.out.println("Cos sie nie zgadza, sprobuj jeszcze raz \n"
                            + "Pamietaj, nalezy podac liczbe odpowiadajacej pozycji w menu");
                }
            }
            catch(java.util.InputMismatchException e){
                System.out.print("Podany zostal znak inny niz liczba\n "
                        + "Prosze podac liczbe\n");
                scann.nextLine();
            }
        }
        while(!(inModel > 0 && inModel < 5));
        return inModel;
    }
    public static int[] userInputPriceRange(){
        int priceFrom, priceTo = 0;
        int prices[] = new int [2];
        System.out.print("Interesuja mnie modele... \n"
                + "Jeśli jest to nieistotne prosze wpisać 0\n"
                + "w zasiegu cenowym \n");
        do{
            System.out.print("Od: \n>>> ");
            priceFrom = scann.nextInt();
            prices[0] = priceFrom;
            if (priceFrom != 0){
                System.out.print("Do: \n>>> ");
                priceTo = scann.nextInt();
                if (priceTo < priceFrom){
                    System.out.print("Niepoprawny przedzial cenowy\n");
                    priceFrom = -1;
                }
                else{
                    prices[1] = priceTo;
                }
            }
            else if(priceFrom == 0){
                System.out.print("Pomijanie widelek cenowych...\n");
            }
        }while(!(priceFrom >= 0));
        return prices;
    }
    public static int[] userInputMilRange(){
        int mileageFrom ,mileageTo;
        int mileages[] = new int [2];
        System.out.print("Z przebiegiem...\n");
        do{
            System.out.print("Od: \n>>> ");
            mileageFrom = scann.nextInt();
            mileages[0] = mileageFrom;
            if (mileageFrom != 0){
                System.out.print("Do: \n>>> ");
                mileageTo = scann.nextInt();
                mileages[1] = mileageTo;
                if (mileageTo < mileageFrom){
                    System.out.print("Niepoprawny przedzial przebiegu\n");
                    mileageFrom = -1;
                }
            }
            else if (mileageFrom == 0){
                System.out.print("Pomijanie widelek przebiegu...");
                mileages[1] = 0;
        }
        }while(!(mileageFrom >= 0));
        return mileages;
    }
    public static void getResults(String make){
        
    }
    
    public static String[] readRow(String row){
        String elements[] = row.split(",");
        String make = elements[0];
        String model = elements[1];
        int price = Integer.parseInt(elements[2]);
        int year = Integer.parseInt(elements[3]);
        int mileage = Integer.parseInt(elements[4]);
        return 0;
    }
    public static void readFile(String f) throws IOException{
        BufferedReader in = null;
        String row;
        try{
            in = new BufferedReader(new FileReader(f));
            
            while((row = in.readLine())!= null){
                //String values[] = readRow(row);
            }
        } 
        finally{
            if(in != null) 
                in.close();
        }
        
        
    }
    public static void main(String[] args) throws IOException {
        int prices[] = userInputPriceRange();
        for(int element: prices){
        System.out.println(element);
        }
        
        int mileages[] = userInputMilRange();
        for(int element: mileages){
        System.out.println(element);
        }
        //readFile("cars.txt");
    }
    
}
