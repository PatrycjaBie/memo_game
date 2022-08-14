import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // zmienna do wprowadzania imienia przez użytkownika
        Scanner scanner = new Scanner(in);

        System.out.println("Podaj imię"); //drukuje w systemie by podać imię
        String name = scanner.nextLine();

        //System.out.println("Cześć " + name);

        if (!name.isEmpty()) {
            System.out.println("Cześć " + name + "!");

        } else {
            while (name.isEmpty()) {
                System.out.println("Imię nie może być puste. Podaj imię:");
                name = scanner.nextLine();

                if (!name.isEmpty()) {
                    System.out.println("Cześć " + name + "!");
                    break;
                }
            }
        }
        System.out.println("Witam Cię w grze memory");
        System.out.println("");

        System.out.println("Wybierz poziom trudności");
        System.out.println("Wpisz 1 - jeśli wybierasz poziom łatwy");
        System.out.println("Wpisz 2 - jeśli wybierasz poziom średni");
        String difficultlevel = scanner.nextLine();

        List<String> listaSlow = loadwords(); // Cześć tu jest lista. Lista bęzi etekstowa. listaSłów to jej nazwa; gdy wykona się metoda loadwords to zostanie zwócona listasłow i będę mogła jej użyć
        Collections.shuffle(listaSlow); // miesza listę słów
        List<String> listaSloiv = wordsfromlist(listaSlow, 4);

        new Gra(10, listaSloiv).start(scanner);
    }

    static List<String> loadwords() throws FileNotFoundException { // żeby wykonało się metoda loadwords
        File plik = new File("src/words.txt");
        Scanner odczyt = new Scanner(plik);
        List<String> listaSlow = new ArrayList<String>(); //stwórz nową listę w któej beda słowa
        while (odczyt.hasNext()) {
            listaSlow.add(odczyt.nextLine()); //odczytaj aż się skończą linie
        }
        return listaSlow; // zwracam lista słów
    }

    static List<String> wordsfromlist(List<String> wejscowaLista, int liczbaslow) {
        List<String> listaSlowiv = new ArrayList<String>();
        ;

        int pozycja = 0;
        while (pozycja < liczbaslow) {
            String element = wejscowaLista.get(pozycja);
            listaSlowiv.add(element);
            pozycja = pozycja + 1; // żeby przeszło do następnej linijki
        }
        return listaSlowiv;
    }

    static List<Integer> listNumbers(int liczba) {
        List<Integer> listNumbers = new ArrayList<Integer>();
        int pozycja = 0;
        while (pozycja < liczba) {
            listNumbers.add(pozycja);
            pozycja = pozycja + 1;
        }
        pozycja = 0;
        while (pozycja < liczba) {
            listNumbers.add(pozycja);
            pozycja = pozycja + 1;
        }
        return listNumbers;
    }

}