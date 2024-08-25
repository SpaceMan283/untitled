
import java.util.*;

class Dream {

    String nazov;
    int zaciatok;
    int trvanie;
    int skusenosti;


    public Dream(String nazov, int zaciatok, int trvanie, int skusenosti) {
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.trvanie = trvanie;
        this.skusenosti = skusenosti;
    }

    public int koniec() {
        return zaciatok + trvanie;
    }
}


    public class Zivot {

        public static void main(String[] args) {
            List<Dream> sny = Arrays.asList(
                    new Dream("absolvovanie VŠ", 19, 5, 1000),
                    new Dream("cesta okolo sveta", 24, 1, 40000),
                    new Dream("aučenie sa hrať na cimbal", 22, 2, 2000),
                    new Dream("založenie start-up", 20, 2, 6000)
            );

            int maxzisk = najdimaximalnyzisk(sny);
            System.out.println("Maximálny zisk: " + maxzisk);
        }

        private static int najdimaximalnyzisk(List<Dream> sny) {

            sny.sort(Comparator.comparingInt(g -> g.zaciatok));

            int n = sny.size();
            int[] dp = new int[n];
            int[] predchodca = new int[n];
            Arrays.fill(predchodca, -1);

            int aktualnySen = 0;

            dp[0] = sny.get(0).skusenosti;

            for (int i = 1; i < n; i++) {

                int zisk = sny.get(i).skusenosti;

                if (sny.get(aktualnySen).koniec() <= sny.get(i).zaciatok) {
                    zisk += dp[aktualnySen];
                    predchodca[i] = aktualnySen;
                }

                for (int j = aktualnySen; j < i; j++) {
                    if (sny.get(aktualnySen).koniec() <= sny.get(i).zaciatok && dp[j] > dp[aktualnySen]) {
                        aktualnySen = j;
                        zisk = sny.get(i).skusenosti + dp[j];
                        predchodca[i] = j;
                    }
                }

                if (zisk > dp[i - 1]) {
                    dp[i] = zisk;
                } else {
                    dp[i] = dp[i - 1];
                }
            }


            List<Dream> realizovaneSny = new ArrayList<>();
            int index = n - 1;
            while (index >= 0) {
                if (index == 0 || dp[index] != dp[index - 1]) {
                    realizovaneSny.add(sny.get(index));
                    index = predchodca[index];
                } else {
                    index--;
                }
            }

            Collections.reverse(realizovaneSny);
            System.out.println("Realizovane sny :");
            for (Dream dream : realizovaneSny) {
                System.out.println("realizuj:" + dream.nazov);
            }

            return dp[n - 1];

        }
    }

