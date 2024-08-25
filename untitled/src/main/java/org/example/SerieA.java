



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SerieA {

    public static class Gol {
        int start;
        int koniec;

        public Gol(int start, int koniec) {
            this.start = start;
            this.koniec = koniec;
        }

        @Override
        public String toString() {
            return "Gol{" +
                    "start=" + start +
                    ", koniec=" + koniec +
                    '}';
        }
    }


    public int maxPocetGolov(int[][] zapasy) {
        // vytvorim zaciatok a konec kazdeho golu
        int maxGoly = 0;

        List<Gol> goly = new ArrayList<>();

        for (int zapas[] : zapasy) {
            int start;
            int koniec;
            boolean jeStandardna = zapas[1] == 1;

            if (jeStandardna) {
                if (zapas[0] == 0) {
                    start = zapas[0];
                    koniec = zapas[0] + 2;
                } else {
                    start = zapas[0] - 1;
                    koniec = zapas[0] + 2;
                }

            } else {
                if (zapas[0] <= 3) {
                    start = 0;
                    koniec = zapas[0] + 2;
                } else {
                    start = zapas[0] - 3;
                    koniec = zapas[0] + 1;
                }
            }

            goly.add(new Gol(start, koniec));

        }

        // usporiadam podla toho kedy koncia
        goly.sort(Comparator.comparingInt(g -> g.koniec));
//        System.out.println(goly);


        int poslednyKoniec = -1;

        for (Gol gol : goly) {
            if (gol.start > poslednyKoniec) {
                maxGoly++;
                poslednyKoniec = gol.koniec;
                System.out.print(gol + " ");

            }
        }
        System.out.println();

        return maxGoly;
    }

    public static void main(String[] args) {
        int[][] zapasy = {{13, 0}, {14, 0}, {20, 1}, {15, 0}, {45, 1}, {50, 1}, {90, 0}};

        // 10-15
        // 11-16
        // 12-17
        // ------------------ iba jeden sa prida - 10-15 lebo bude pozerat ten a zvysne nie
        // 19-22
        // 44-47
        // 49-52
        // 87-92
        // ------------------ +4


        // ulohy 1 a 2
        SerieA greedy = new SerieA();
        System.out.println(greedy.maxPocetGolov(zapasy));
    }


}