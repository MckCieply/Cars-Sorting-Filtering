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
    
    public static void readRow(String f, int row){
        BufferedReader in = null;
    }
    public static void readFile(){
        
    }
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        System.out.print("Wybierz na podstawie jakiego modelu szukasz danych:\n"
                + "1. Volkswagen Scirocco\n"
                + "2. Mitsubishi Lancer\n"
                + "3. Volvo c30\n"
                + "4. Wszystkie\n"
                + "0. Wyjdz\n");
        int model = scann.nextInt();
        
        System.out.print("Interesuja mnie modele... \n"
                + "Jeśli jest to nieistotne prosze wpisać 0\n"
                + "w zasiegu cenowym \n"
                + "Od: ");
        int priceFrom = scann.nextInt();
        if (priceFrom != 0){
            System.out.print("Do: ");
            int priceTo = scann.nextInt();
        }
        else{
            System.out.print("Pomijamy widelki cenowe...");
        }
        System.out.print("Z przebiegiem...\n"
                + "Od: ");
        int mileageFrom = scann.nextInt();
        if (mileageFrom != 0){
            System.out.print("Do: ");
            int mileageTo = scann.nextInt();
        }
    }
    
}
