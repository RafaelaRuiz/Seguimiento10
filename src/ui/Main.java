package ui;

import java.io.IOException;
import java.util.*;

import exceptions.InvalidNumberException;
import exceptions.NotTypeMedalException;
import model.Country;
import model.Tournament;

public class Main {
    public static void main(String[] args) throws IOException {
        Tournament tournament = new Tournament();
        tournament.load();
        Scanner lector = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("====Menu====\n" +
                    "1. Ingresar un país\n" +
                    "2. Mostrar medallería\n" +
                    "3. Mostrar total de medallas\n" +
                    "4. Mostrar países\n" +
                    "5. Salir");
            option = Integer.parseInt(lector.nextLine());
            switch (option) {
                case 1:
                try{
                    System.out.println("Escriba la entrada con el formato Nombre::Medalla::Cantidad");
                    String input = lector.nextLine();
                    String[]data = input.split("::");
                    tournament.addCountry(data[0], data[1], Integer.parseInt(data[2]));
                    tournament.save();
                }catch(InvalidNumberException e){
                    e.printStackTrace();
                }catch(NotTypeMedalException ex){
                    ex.printStackTrace();
                }
                    
                    break;
                case 2:
                    tournament.showConventionalMedals();
                    break;
                case 3:
                    tournament.showTotalMedals();
                    break;
                case 4:
                    tournament.showAlphabetical();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 5);
    }

}
