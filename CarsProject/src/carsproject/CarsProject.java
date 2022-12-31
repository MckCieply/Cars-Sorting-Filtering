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
        int inModel = 0, priceFrom, priceTo, mileageFrom, mileageTo;
        Scanner scann = new Scanner(System.in);
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
            
        
        System.out.print("Interesuja mnie modele... \n"
                + "Jeśli jest to nieistotne prosze wpisać 0\n"
                + "w zasiegu cenowym \n"
                + "Od: ");
        do{
            System.out.print(">>> ");
            priceFrom = scann.nextInt();
            if (priceFrom != 0){
                System.out.print("Do: ");
                priceTo = scann.nextInt();
            }
            else{
                System.out.print("Pomijamy widelki cenowe...\n");
            }
        }while(!(priceFrom >= 0));
        
        System.out.print("Z przebiegiem...\n"
                + "Od: ");
        mileageFrom = scann.nextInt();
        if (mileageFrom != 0){
            System.out.print("Do: ");
            mileageTo = scann.nextInt();
        }
        readFile("cars.txt");
    }
    
}
