import java.text.CollationElementIterator;
import java.util.*;

public class Gra {
    int iloscszans;
    int iloscslowdozgadiecia;
    List<Word> slowka;

    List<Integer> indexy;

    public Gra(int iloscszans, List<String> slowka) {
        this.iloscszans = iloscszans;
        this.iloscslowdozgadiecia = slowka.size();
        this.indexy = generateIndexes(slowka.size());

        this.slowka = new ArrayList<>();
        for (String slowo : slowka) {
            this.slowka.add(new Word(slowo));
        }
    }

    void start(Scanner scanner) throws Exception {
        while (iloscszans > 0 && iloscslowdozgadiecia > 0) {
            Set<Integer> odkryte = new HashSet<>();
            printGame(odkryte);

            int koordynaty1 = contact(scanner, this.slowka.size());
            odkryte.add(koordynaty1);
            printGame(odkryte);

            int koordynaty2;
            while (true) {
                koordynaty2 = contact(scanner, this.slowka.size());
                if (odkryte.contains(koordynaty2)) {
                    continue;
                }
                odkryte.add(koordynaty2);
                break;
            }
            printGame(odkryte);

            Thread.sleep(2000);
            System.out.println();

            int idx = this.indexy.get(koordynaty1);
            if (idx == this.indexy.get(koordynaty2)) {
                this.slowka.get(idx).setSolved();
                this.iloscslowdozgadiecia--;
            }
            this.iloscszans--;
        }
        if (iloscslowdozgadiecia == 0) {
            System.out.println("Wygrales");
        } else {
            System.out.println("Przegrales!");
        }
    }

    static int contact(Scanner scanner, int liczbaSlow) {
        System.out.println();
        System.out.println("Podaj parametr do odkrycia (od A0 - A3 lub B0-B3)");
        while (true) {
            String parametr = scanner.nextLine();

            if (parametr.isEmpty()) {
                System.out.println("Parametr nie może być pusty. Podaj parametr:");
                continue;
            }

            if (parametr.length() != 2) {
                System.out.println("Parametr jest nieprawidłowy. Podaj parametr:");
                continue;
            }

            int letter = parametr.charAt(0) - 'A';
            int number = parametr.charAt(1) - '0';
            if (letter < 0 || letter > 'Z') {
                System.out.println("Pierwszy koordynat jest nieprawidłowy. Podaj parametr:");
                continue;
            }
            if (number < 0 || number > '9') {
                System.out.println("Drugi koordynat jest nieprawidłowy. Podaj parametr:");
                continue;
            }
            int index = letter * liczbaSlow + number;
            if (index > liczbaSlow * 2) {
                System.out.println("Parametr jest nieprawidłowy. Podaj parametr:");
                continue;
            }
            return index;
        }
    }

    void printGame(Set<Integer> odkryte) {
        for (int i = 0; i < this.indexy.size(); i++) {
            Word word = this.slowka.get(this.indexy.get(i));
            if (i % this.slowka.size() == 0) {
                System.out.println();
            }

            if (word.isVisble() || odkryte.contains(i)) {
                System.out.print(word.word);
            } else {
                System.out.print("X");
            }
            System.out.print(" ");
        }
    }


    static List<Integer> generateIndexes(int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < count; j++) {
                list.add(j);
            }
        }
        Collections.shuffle(list);
        return list;
    }

    static class Word {
        String word;
        boolean solved;

        public Word(String word) {
            this.word = word;
        }

        boolean isVisble() {
            return this.solved;
        }

        void setSolved() {
            this.solved = true;
        }
    }
}
