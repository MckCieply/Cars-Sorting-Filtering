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
    //Pytanie jakie modele wyswietlic
    public static int userInputModel(){
        int inModel = 0;
        System.out.print("Wybierz na podstawie jakiego modelu szukasz danych:\n"
                + "1. Volkswagen Scirocco\n"
                + "2. Mitsubishi Lancer\n"
                + "3. Volvo c30\n"
                + "4. Wszystkie\n");
        do{
            System.out.print(">>> ");
            // Sprawdzanie czy uzytkownik wprowadzil poprawne dane
            // try czy wprowadzil cyfre
            try{ 
                inModel = scann.nextInt();
                //Sprawdzanie czy liczba odpowiada pozycji w menu
                if (!(inModel > 0 && inModel < 5)){
                    System.out.println("Cos sie nie zgadza, sprobuj jeszcze raz \n"
                            + "Pamietaj, nalezy podac liczbe odpowiadajacej pozycji w menu");
                }
            }
            // catch jak wprowadzil znak inny od cyfry oraz czyszczenie \n uzywajac nextLine
            catch(java.util.InputMismatchException e){
                System.out.print("Podany zostal znak inny niz liczba\n"
                        + "Prosze podac liczbe\n");
                scann.nextLine();
            }
        }
        //Wykonywanie az uzytkownik nie poda liczby w zakresie 1-4
        while(!(inModel > 0 && inModel < 5));
        return inModel;
    }
    //Przypisane wyborowi uzytkownika poprawnego modelu
    public static String convertModel(int model){
        String name;
        if(model == 1)
            name = "VW Scirocco";
        else if(model == 2)
            name = "Mitsubishi Lancer";
        else if(model == 3)
            name = "Volvo c30";
        else
            name = "Wszystkie";
        return name;
    }
    //Pytanie o zakres cenowy modeli
    public static int[] userInputPriceRange(){
        int priceFrom = -1, priceTo;
        int prices[] = new int [2];
        System.out.print("Interesuja mnie modele... \n"
                + "Jeśli jest to nieistotne prosze wpisać 0\n"
                + "w zasiegu cenowym \n");
        do{
            //Sprawdzanie czy uzytkownik wprowadzil poprawne dane
            System.out.print("Od: \n>>> ");
            try{
                priceFrom = scann.nextInt();
                prices[0] = priceFrom;
                if (priceFrom != 0){
                    System.out.print("Do: \n>>> ");
                    priceTo = scann.nextInt();
                    //Sprawdzenie czy jest poprawny zakres
                    if (priceTo < priceFrom){
                        System.out.print("Niepoprawny przedzial cenowy\n");
                        priceFrom = -1;
                    }
                    else{
                        prices[1] = priceTo;
                    }
                }
                //Jesli zostalo podane zero, pominiecie widelek
                else if(priceFrom == 0){
                    prices[0] = prices[1] = 0;
                    System.out.print("Pomijanie widelek cenowych...\n");
                }        
            }
            //catch jak nie jest to cyfra, i czyszczenie \n z wejścia
        catch(java.util.InputMismatchException e){
            System.out.print("Podany zostal znak inny niz liczba\n "
                    + "Prosze podac liczbe\n");
            scann.nextLine();
            // Przypisywanie -1 obu zmiennym zeby przy drugim podejsciu nie czytalo starych danych
            // -1 jest nasza wartoscia domyslna, poniewaz 0 ma zastosowanie w programie
            priceFrom = priceTo = -1;
            }
        }while(!(priceFrom >= 0));
        return prices;
    }
    //Pytanie o zakres przebiegu modeli
    public static int[] userInputMilRange(){
        int mileageFrom ,mileageTo;
        int mileages[] = new int [2];
        System.out.print("Z przebiegiem...\n");
        do{
            //Sprawdzanie czy uzytkownik wprowadzil poprawne dane
            System.out.print("Od: \n>>> ");
            try{
                mileageFrom = scann.nextInt();
                mileages[0] = mileageFrom;
                if (mileageFrom != 0){
                    System.out.print("Do: \n>>> ");
                    mileageTo = scann.nextInt();
                    mileages[1] = mileageTo;
                    //Sprawdzanie czy zakres jest poprawny
                    if (mileageTo < mileageFrom){
                        System.out.print("Niepoprawny przedzial przebiegu\n");
                        mileageFrom = -1;
                    }
                }
                //Jesli zostalo podane 0 to pomijanie
                else if (mileageFrom == 0){
                    System.out.print("Pomijanie widelek przebiegu...");
                    mileages[0] = mileages[1] = 0;
                }
            }
            //Catch dla wprowadzonego znaku innego niz liczba oraz przypisane wartosci
            //gwarantujacej kolejny przebieg
            catch(java.util.InputMismatchException e){
                System.out.print("Podany zostal znak inny niz liczba\n"
                        + "Prosze podac liczbe\n");
                scann.nextLine();
                mileageFrom = mileageTo = -1;
            }
        }while(!(mileageFrom >= 0));
        return mileages;
    }
    // Metoda wezmie model, zakres cenowy oraz zakres przebiegu do wyswietlenia
    public static void summary(String name, int prices[], int mileages[]){
        System.out.printf("\n-------------------------------\n"
        + "Podsumowanie wprowadzonych danych\n\n"
        + "Model: %s\n", name);
        if(prices[0] == 0 && prices[1] == 0)
            System.out.print("Cena: Dowolna\n");
        else
            System.out.printf("Cena:  %dPLN - %dPLN\n", prices[0], prices[1]);
        if(mileages[0] == 0 && mileages[1] == 0)
            System.out.print("Przebieg: Dowolny\n");
        else
            System.out.printf("Przebieg: %dkm - %dkm\n", mileages[0], mileages[1]);
        System.out.print("-------------------------------\n");
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
    //Osobne otwarcie pliku do sprawdzenia ile rekordow w nim jest
    public static int howMuchRows(String f) throws IOException{
        BufferedReader in = null;
        String row;
        int counter = 0;
        try{
            in = new BufferedReader(new FileReader(f));
            
            while((row = in.readLine())!= null){
                counter +=1;
                }
            }
        finally{
            if(in != null) 
                in.close();
        }
        return counter;
    }
    public static String[][] readFile(String f) throws IOException{
        BufferedReader in = null;
        String row;
        String results[][] = new String[howMuchRows("cars.txt")][6];
        String values[] = null;
        
    }
    public static void ifCondition(int inModel,int prices[],int mileages[], String txtData[]){
        
    }
    public static void main(String[] args) throws IOException {
        //int inModel = userInputModel();
        //String inModelName = convertModel(inModel);
        //int prices[] = userInputPriceRange();
        //int mileages[] = userInputMilRange();
        //summary(inModelName, prices, mileages);
        
        String data[][] = readFile("cars.txt");
        //for(String element:data){
        //    System.out.println(element);
        }
    }