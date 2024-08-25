

public class BackTrack {

    private int hracky = 4;
    private int[] moznosti = new int[hracky];
    private int najmensiaCena = Integer.MAX_VALUE;
    private int[] najlepsiaMoznost = new int[hracky];

    private int[][] predajca = {{15, 50, 25, -1, 10, 25},
                                {20, 45, -1, 25, 20, 22},
                                {30, 100, 30, 30, 30, 30}
                                                            };

    public void generuj(int odIdx){
        if(odIdx == moznosti.length){
            spracuj();
            return;
        }

        for(int indexPredajcu = 0; indexPredajcu < predajca.length; indexPredajcu++){
            moznosti[odIdx] = indexPredajcu;
            generuj(odIdx + 1);
        }


    }

    private void spracuj() {

        int[] idxHraciek = {2, 3, 4, 5};

        int[] nakup = new int[predajca.length];

        int[] postovne = new int[predajca.length];

        boolean[] freePostovne = new boolean[predajca.length];


        for (int i = 0; i < hracky; i++) {
            int idxPredavajuceho = moznosti[i];

            int cenaJednejHracky = predajca[idxPredavajuceho][idxHraciek[i]];

            if(cenaJednejHracky != -1){
                nakup[idxPredavajuceho] += cenaJednejHracky;
                postovne[idxPredavajuceho] = predajca[idxPredavajuceho][0];
                freePostovne[idxPredavajuceho] = (nakup[idxPredavajuceho] >= predajca[idxPredavajuceho][1]);
            } else {
                return;
            }
        }

        int celkovaCena = 0;

        for (int i = 0; i < nakup.length; i++) {

            celkovaCena += nakup[i];

            if(nakup[i] > 0 && freePostovne[i] == false){
                celkovaCena += postovne[i];
            }
        }

        if (celkovaCena < najmensiaCena) {
            najmensiaCena = celkovaCena;
            najlepsiaMoznost = moznosti.clone();
        }

    }

    public void vyprintuj(int[] najlepsiaMoznost) {
        for (int num : najlepsiaMoznost) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
    BackTrack backTrack = new BackTrack();
    backTrack.generuj(0);
    System.out.println(backTrack.najmensiaCena);
    backTrack.vyprintuj(backTrack.najlepsiaMoznost);
    }

}
