//Program pracuje na pliku tekstowym w ktorym z wierszach sa dane, pooddzielane przecinkami
//Dane zostaly skonwertowane z bazy danych w sqlite3 do pliku txt
//Data powstania: 27.12.2022
//Autor: 
package carsproject;

import java.io.*;
import java.util.Scanner;

public class CarsProject {
    
    static Scanner scann = new Scanner(System.in);
    public static void menu()throws IOException{
        System.out.print("Prosze wybrac operacje: \n1. Zmienic dane ogloszenia. \n2. Wyszukac ogloszenia.\n>>> ");
        int choice = scann.nextInt();
        do{
            // Sprawdzanie czy uzytkownik wprowadzil poprawne dane
            // try czy wprowadzil cyfre
            try{ 
                System.out.print(">>> ");
                //Sprawdzanie czy liczba odpowiada pozycji w menu
                if (!(choice > 0 && choice < 3)){
                    System.out.println("Zostala wprowadzona zla liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
                }
            }
            // catch jak wprowadzil znak inny od cyfry oraz czyszczenie \n uzywajac nextLine
            catch(java.util.InputMismatchException e){
                System.out.print("Zostal wprowadzony znak inny niz liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
                scann.nextLine();
            }
            if(choice == 1){
                edit();
            }
            else if(choice == 2){
                int inModel = userInputModel();
                int prices[] = userInputPriceRange();
                int mileages[] = userInputMilRange();
                summary(inModel, prices, mileages);
                System.out.printf("+------------+----------+----------+---------- +----------+\n");
                System.out.printf("| Marka      | Model    | Rok prod | Cena      | Przebieg |\n");
                System.out.printf("+------------+----------+----------+---------- +----------+\n");
                readFile("cars.txt", inModel, prices, mileages);
                System.out.printf("+------------+----------+----------+-----------+----------+\n");
            }
        }
        //Wykonywanie az uzytkownik nie poda 1 lub 2
        while(!(choice > 0 && choice < 3));
    }
    //Funkcja do edycji jednego ogloszenia
    public static void edit() throws IOException{
        System.out.print("Prosze podac ID ogloszenia do edycji: \n>>> ");
        long givenID = scann.nextLong();   //6103819587
        System.out.print("Prosze podac nowy przebieg: \n>>> ");
        long givenMileage = scann.nextLong();
        System.out.print("Prosze podac nowa cene: \n>>> ");
        long givenPrice = scann.nextLong();
        BufferedReader reader = new BufferedReader(new FileReader("cars.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));
        String row;
        while((row = reader.readLine())!= null){
            String[] elements = row.split(",");
            if (Long.parseLong(elements[0]) == givenID){
                writer.write(elements[0]+","+elements[1]+","+elements[2]+","+givenPrice+","+elements[4]+","+givenMileage+"\n");
            }
            else{
                writer.write(row+"\n");
            }
        }
        reader.close();
        writer.close();
        replace();
    }
    public static void replace(){
        File cars = new File("cars.txt");
        File temp = new File("temp.txt");
        cars.delete();
        boolean flag = temp.renameTo(cars);
        //cars.deleteOnExit();
        if (flag == true)
            System.out.print("Success");
    }
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
                    System.out.println("Zostala wprowadzona zla liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
                }
            }
            // catch jak wprowadzil znak inny od cyfry oraz czyszczenie \n uzywajac nextLine
            catch(java.util.InputMismatchException e){
                System.out.print("Zostal wprowadzony znak inny niz liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
                scann.nextLine();
            }
        }
        //Wykonywanie az uzytkownik nie poda liczby w zakresie 1-4
        while(!(inModel > 0 && inModel < 5));
        return inModel;
    }
    //Pytanie o zakres cenowy modeli
    public static int[] userInputPriceRange(){
        int priceFrom = -1, priceTo;
        int prices[] = new int [2];
        System.out.print("Jesli cena nie gra roli, prosze podac 0\nCena...\n");
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
                        System.out.print("Niepoprawny przedzial cenowy.\n Sprobuj jeszcze raz.\n");
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
            System.out.print("Zostal wprowadzony znak inny niz liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
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
        System.out.print("Jesli przebieg nie gra roli, prosze podac 0\nZ przebiegiem...\n");
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
                        System.out.print("Niepoprawny przedzial przebiegu.\n Sprobuj jeszcze raz.\n");
                        mileageFrom = -1;
                    }
                }
                //Jesli zostalo podane 0 to pomijanie
                else if (mileageFrom == 0){
                    System.out.print("Pomijanie widelek przebiegu...\n");
                    mileages[0] = mileages[1] = 0;
                }
            }
            //Catch dla wprowadzonego znaku innego niz liczba oraz przypisane wartosci
            //gwarantujacej kolejny przebieg
            catch(java.util.InputMismatchException e){
                System.out.print("Zostal wprowadzony znak inny niz liczba. \n"
                            + "Sprobuj jeszcze raz.\n");
                scann.nextLine();
                mileageFrom = mileageTo = -1;
            }
        }while(!(mileageFrom >= 0));
        return mileages;
    }
    // Metoda wezmie model, zakres cenowy oraz zakres przebiegu do wyswietlenia
    public static void summary(int model, int prices[], int mileages[]){
        String name;
        if(model == 1)
            name = "VW Scirocco";
        else if(model == 2)
            name = "Mitsubishi Lancer";
        else if(model == 3)
            name = "Volvo c30";
        else
            name = "Wszystkie";
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
        String wait;
        do{
            scann.nextLine();
            System.out.print("Press enter to continue...\n");
            wait = scann.nextLine();
        }while(wait.equals("\n"));
    }
    public static void ifCondition(String row,  int inputName, int prices[], int mileages[]){
        String elements[] = row.split(",");
        //int id = Integer.parseInt(elements[0]);
        String brand = elements[1];
        String model = elements[2];
        int txtName = 0;
        int price = Integer.parseInt(elements[3]);
        int year = Integer.parseInt(elements[4]);
        int mileage = Integer.parseInt(elements[5]);
        if(model.equals("Scirocco"))
            txtName = 1;
        else if(model.equals("Lancer"))
            txtName = 2;
        else if(model.equals("c30"))
            txtName = 3;
        // Sprawdzanie tylko prices/mileages[0] poniewaz druga pozycja musi byc 0 jak pierwsza jest 0
        if(txtName == inputName || inputName == 4){
            if(price >= prices[0] && price <= prices[1] && prices[0] != 0 &&
               mileage >= mileages[0] && mileage <= mileages[1] && mileages[0] != 0)
                printOut(brand, model, year, price, mileage);
            
            else if(prices[0] == 0 && mileage >= mileages[0] && mileage <= mileages[1] && mileages[0] != 0)
                printOut(brand, model, year, price, mileage);
            
            else if(mileages[0] == 0 && price >= prices[0] && price <= prices[1] && prices[0] != 0)
                printOut(brand, model, year, price, mileage);

            else if(mileages[0] == 0 && prices[0] == 0)
                //System.out.println(model + " " + price +" PLN " + mileage + "km");
                printOut(brand, model, year, price, mileage);
        }
    }  
    public static void readFile(String file, int inModel, int prices[], int mileages[]) throws IOException{
        BufferedReader in = null;
        String row;
        try{
            in = new BufferedReader(new FileReader(file));
            
            while((row = in.readLine())!= null){
                ifCondition(row, inModel, prices, mileages);
            }
        } 
        finally{
            if(in != null) 
                in.close();
        }
    }
    public static void printOut(String brand, String model, int year, int price, int mileage){
        String format = "| %-11s| %-9s| %-9d| %-6dPLN | %-6dkm |\n";
        //Brand min. 12 places, Model min 8 places, year 4, price 6places + PLN, mileage 6places +km
        System.out.printf(format, brand, model, year, price, mileage);
    }
    public static void main(String[] args) throws IOException {
        menu();
        }
    }