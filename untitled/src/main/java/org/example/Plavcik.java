

import java.util.*;

class Plaz {

    public static boolean boliVsetciNarazNaPlazi(int[][] casy) {
        // Vytvoríme zoznam udalostí
        List<int[]> udalosti = new ArrayList<>();

        // Pridáme príchody a odchody
        for (int[] cas : casy) {
            udalosti.add(new int[]{cas[0], 1});  // Príchod
            udalosti.add(new int[]{cas[1], -1}); // Odchod
        }

        // Zoradíme udalosti, ak majú rovnaký čas, odchody idú pred príchody
        udalosti.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int pocetNavstevnikov = 0;
        int maxNavstevnikov = casy.length;

        // Prejdeme zoznam udalostí
        for (int[] udalost : udalosti) {
            pocetNavstevnikov += udalost[1];
            if (pocetNavstevnikov == maxNavstevnikov) {
                return true;  // Ak sú všetci naraz na pláži
            }
        }

        return false;  // Ak sa to nikdy nestalo
    }

    public static void main(String[] args) {
        int[][] casy = {
                {1, 5},
                {2, 6},
                {3, 8},
                {4, 7}
        };

        System.out.println(boliVsetciNarazNaPlazi(casy)); // Output: true
    }
}
