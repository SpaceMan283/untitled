


public class Vespa {
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

    public void pridajVespu(int rok) {

        Uzol novy = new Uzol();
        novy.hodnota = rok;

        // ak nemam ziadne uzly
        if (prvy == null) {
            prvy = novy;
            return;
        }

        // ak je prva hodnota vacsia ako nova
        if (prvy.hodnota >= rok) {
            novy.dalsi = prvy;
            prvy = novy;
            return;
        }

        Uzol aktualny = prvy;

        while (aktualny.dalsi != null && aktualny.dalsi.hodnota < rok) {
            aktualny = aktualny.dalsi;
        }

        novy.dalsi = aktualny.dalsi;
        aktualny.dalsi = novy;
    }




    public static void main(String[] args) {
        Vespa v = new Vespa();
        v.pridajNaZaciatok(1980);
        v.pridajNaZaciatok(1980);
        v.pridajNaZaciatok(1975);
        v.pridajNaZaciatok(1969);
        v.pridajNaZaciatok(1951);


        System.out.println(v);

        v.pridajVespu(1990);
        System.out.println(v);

    }

}