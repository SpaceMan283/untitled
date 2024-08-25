

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda implementujuca binarny vyhladavaci strom cisel
 */
public class BVS {

    /**
     * Sukromna privatna trieda reprezentujuca uzol stromu
     */
    private static class Uzol {
        // Hodnota ulozena v uzle
        int hodnota;
        // Referencia na laveho syna
        Uzol lavy;
        // Referencia na praveho syna
        Uzol pravy;

        /**
         * Jednoduchy konstruktor
         */
        public Uzol(int hodnota, Uzol lavy, Uzol pravy) {
            this.hodnota = hodnota;
            this.lavy = lavy;
            this.pravy = pravy;
        }
    }

    /**
     * Referencia na uzol, ktory je korenom stromu
     */
    private Uzol koren = null;

    /**
     * Vrati, ci strom obsahuje zadanu hodnotu
     *
     * @param hodnota hladana hodnota
     */
    public boolean contains(int hodnota) {
        Uzol aktualny = koren;
        while (aktualny != null) {
            if (aktualny.hodnota == hodnota)
                return true;
            if (hodnota < aktualny.hodnota) {
                aktualny = aktualny.lavy;
            } else if (aktualny.hodnota < hodnota) {
                aktualny = aktualny.pravy;
            }
        }
        return false;
    }

    /**
     * Prida zadanu hodnotu do stromu (ak v nom este nie je)
     *
     * @param hodnota vkladana hodnota
     */
    public void add(int hodnota) {
        if (koren == null) {
            koren = new Uzol(hodnota, null, null);
            return;
        }

        Uzol aktualny = koren;
        while (true) {
            if (aktualny.hodnota == hodnota)
                break;

            if (hodnota < aktualny.hodnota) {
                if (aktualny.lavy == null) {
                    aktualny.lavy = new Uzol(hodnota, null, null);
                    break;
                } else
                    aktualny = aktualny.lavy;
            }

            if (aktualny.hodnota < hodnota) {
                if (aktualny.pravy == null) {
                    aktualny.pravy = new Uzol(hodnota, null, null);
                    break;
                } else
                    aktualny = aktualny.pravy;
            }
        }
    }

    /**
     * Odstráni hodnotu zo stromu, ak je prítomná
     *
     * @param hodnota hodnota na odstránenie
     */
    public void remove(int hodnota) {
        Uzol aktualny = koren;
        Uzol rodicAktualneho = null;

        while (aktualny != null) {
            if (aktualny.hodnota == hodnota)
                break;

            rodicAktualneho = aktualny;

            if (hodnota < aktualny.hodnota) {
                aktualny = aktualny.lavy;
            } else if (aktualny.hodnota < hodnota) {
                aktualny = aktualny.pravy;
            }
        }

        if (aktualny == null)
            return;

        int pocetDetiAktualneho = 0;
        if (aktualny.lavy != null)
            pocetDetiAktualneho++;
        if (aktualny.pravy != null)
            pocetDetiAktualneho++;

        if (pocetDetiAktualneho == 0) {
            if (rodicAktualneho == null)
                koren = null;
            else {
                if (rodicAktualneho.lavy == aktualny)
                    rodicAktualneho.lavy = null;
                else
                    rodicAktualneho.pravy = null;
            }
        }

        if (pocetDetiAktualneho == 1) {
            Uzol dietaAktualneho = (aktualny.lavy != null) ? aktualny.lavy : aktualny.pravy;

            if (rodicAktualneho == null)
                koren = dietaAktualneho;
            else {
                if (rodicAktualneho.lavy == aktualny)
                    rodicAktualneho.lavy = dietaAktualneho;
                else
                    rodicAktualneho.pravy = dietaAktualneho;
            }
        }

        if (pocetDetiAktualneho == 2) {
            Uzol rodicMaxima = aktualny;
            Uzol maximum = aktualny.lavy;
            while (maximum.pravy != null) {
                rodicMaxima = maximum;
                maximum = maximum.pravy;
            }

            aktualny.hodnota = maximum.hodnota;

            if (rodicMaxima.lavy == maximum)
                rodicMaxima.lavy = maximum.lavy;
            else
                rodicMaxima.pravy = maximum.lavy;
        }
    }

    /**
     * Privatna metoda na vypis stromu s korenom v zadanom uzle
     *
     * @param koren uzol, ktory je korenom vypisovaneho stromu
     */
    private void vypisStrom(Uzol koren) {
        if (koren == null)
            return;

        vypisStrom(koren.lavy);
        System.out.println(koren.hodnota);
        vypisStrom(koren.pravy);
    }

    /**
     * Vypise hodnoty v strome v inorder prechode
     */
    public void vypis() {
        vypisStrom(koren);
    }

    /**
     * Metóda na porovnanie dvoch stromov s pamäťovou zložitosťou O(n)
     *
     * @param iny druhý strom na porovnanie
     * @return true, ak oba stromy obsahujú rovnakú množinu hodnôt
     */
    public boolean rovnakeHodnotyON(BVS iny) {
        List<Integer> tentoInorder = new ArrayList<>();
        List<Integer> inyInorder = new ArrayList<>();
        inorderDoListu(this.koren, tentoInorder);
        inorderDoListu(iny.koren, inyInorder);
        return tentoInorder.equals(inyInorder);
    }

    /**
     * Pomocná metóda na inorder prechod a uloženie hodnôt do listu
     *
     * @param uzol uzol, z ktorého začíname
     * @param list list, do ktorého ukladáme hodnoty
     */
    private void inorderDoListu(Uzol uzol, List<Integer> list) {
        if (uzol != null) {
            inorderDoListu(uzol.lavy, list);
            list.add(uzol.hodnota);
            inorderDoListu(uzol.pravy, list);
        }
    }

    /**
     * Metóda na porovnanie dvoch stromov s pamäťovou zložitosťou O(1)
     *
     * @param iny druhý strom na porovnanie
     * @return true, ak oba stromy obsahujú rovnakú množinu hodnôt
     */
    public boolean rovnakeHodnotyO1(BVS iny) {
        return rovnakeHodnotyRekurzivne(this.koren, iny.koren);
    }

    /**
     * Pomocná rekurzívna metóda na porovnanie uzlov
     *
     * @param uzol1 prvý uzol na porovnanie
     * @param uzol2 druhý uzol na porovnanie
     * @return true, ak oba uzly a ich podstromy sú rovnaké
     */
    private boolean rovnakeHodnotyRekurzivne(Uzol uzol1, Uzol uzol2) {
        if (uzol1 == null && uzol2 == null)
            return true;
        if (uzol1 == null || uzol2 == null)
            return false;
        return uzol1.hodnota == uzol2.hodnota &&
                rovnakeHodnotyRekurzivne(uzol1.lavy, uzol2.lavy) &&
                rovnakeHodnotyRekurzivne(uzol1.pravy, uzol2.pravy);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        BVS bvs1 = new BVS();
        BVS bvs2 = new BVS();

        int[] hodnoty1 = { 4, 2, 6, 1, 3, 5, 7 };
        int[] hodnoty2 = { 4, 6, 2, 7, 5, 1, 3 };

        for (int hodnota : hodnoty1)
            bvs1.add(hodnota);

        for (int hodnota : hodnoty2)
            bvs2.add(hodnota);

        System.out.println("Stromy majú rovnaké hodnoty (O(n)): " + bvs1.rovnakeHodnotyON(bvs2));
        System.out.println("Stromy majú rovnaké hodnoty (O(1)): " + bvs1.rovnakeHodnotyO1(bvs2));
    }
}
