


public class ZlucenieVlakov {
    private static class Uzol {
        int hodnota;
        Uzol dalsi;
    }

    /**
     * Referencia na prvy uzol spajaneho zoznamu
     */
    private Uzol prvy = null;

    /**
     * Prida novu hodnotu na zaciatok spajaneho zoznamu
     *
     * @param hodnota pridavana hodnota
     */
    public void pridajNaZaciatok(int hodnota) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = hodnota;
        pridavany.dalsi = prvy;
        prvy = pridavany;
    }

    @Override
    public String toString() {
        String vysledok = "[";
        Uzol aktualny = prvy;
        while (aktualny != null) {
            if (aktualny != prvy)
                vysledok += ", ";

            vysledok += aktualny.hodnota;
            aktualny = aktualny.dalsi;
        }

        return vysledok + "]";
    }

    /**
     * Vrati sucet hodnot ulozenych v spajanom zozname
     */
    public int sucet() {
        // Referencia na uzol zoznamu, na ktorom sa prave nachadzame
        Uzol aktualny = prvy;
        // Premenna, v ktorej akumulujeme sucet
        int vysledok = 0;
        // Kym sme na nejakom uzle ...
        while (aktualny != null) {
            // Priratame hodnotu uzla
            vysledok += aktualny.hodnota;
            // Presunieme sa na dalsi uzol v zozname
            aktualny = aktualny.dalsi;
        }

        return vysledok;
    }


    public ZlucenieVlakov zlucVlaky(ZlucenieVlakov A, ZlucenieVlakov B) {

        // ked oba su prazdne tak vratim jeden z nich - prazdny
        if (A.prvy == null && B.prvy == null) {
            return A;
        }

        // ked mam prazdny zoznam A, tak sa nic nemeni vraciam B
        if (A.prvy == null) {
            return B;
        }

        // prazdny zoznam B - vraciam A
        if (B.prvy == null) {
            return A;
        }


        Uzol aktualnyA = A.prvy;
        Uzol aktualnyB = B.prvy;

        while (aktualnyA != null && aktualnyB != null) {
            // budem nahradzovat hodnoty v A suctom
            aktualnyA.hodnota += aktualnyB.hodnota;

            if (aktualnyA.dalsi == null && aktualnyB.dalsi == null) {
                break;
            }

            // ak A je kratsie ako B
            // musim dodat do A dalsie prvky z B
            if (aktualnyA.dalsi == null && aktualnyB.dalsi != null) {
                aktualnyA.dalsi = aktualnyB.dalsi;
                aktualnyA = aktualnyA.dalsi;

            }
            // ak B je kratsie ako A
            // necham prvky z A

            if (aktualnyA.dalsi != null && aktualnyB.dalsi == null) {
                aktualnyA = aktualnyA.dalsi;
            }

            // posuvam sa v obidvoch dalej
            aktualnyA = aktualnyA.dalsi;
            aktualnyB = aktualnyB.dalsi;


        }





        return A;
    }

    public static void main(String[] args) {
        ZlucenieVlakov z = new ZlucenieVlakov();
        ZlucenieVlakov z2 = new ZlucenieVlakov();

//        z.pridajNaZaciatok(4);
//        z.pridajNaZaciatok(1);
//        z.pridajNaZaciatok(5);
//        z.pridajNaZaciatok(12);
//        z.pridajNaZaciatok(5);
//        z.pridajNaZaciatok(8);

//        z2.pridajNaZaciatok(11);
//        z2.pridajNaZaciatok(3);
//        z2.pridajNaZaciatok(3);
//        z2.pridajNaZaciatok(1);
//        z2.pridajNaZaciatok(3);
//        z2.pridajNaZaciatok(2);

        z.pridajNaZaciatok(5);
        z.pridajNaZaciatok(15);
        z.pridajNaZaciatok(10);

        z2.pridajNaZaciatok(10);
        z2.pridajNaZaciatok(10);


        ZlucenieVlakov zlucenieVlakov = new ZlucenieVlakov();

        System.out.println(z);
        System.out.println(z2);


        System.out.println(zlucenieVlakov.zlucVlaky(z, z2));
    }
}