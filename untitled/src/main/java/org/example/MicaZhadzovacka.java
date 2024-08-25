


public class MicaZhadzovacka {


    public static void main(String[] args) {
        int[][] mapa = {{5, 1, 1},
                {3, 2, 15},
                {2, 5, 8},
                {1, 2, 4},
                {9, 5, 4}};

        MicaZhadzovacka z = new MicaZhadzovacka();
        System.out.println(z.zhadzovanie(mapa));
    }



    public int zhadzovanie(int[][] mapa) {
        int riadky = mapa.length;
        int stlpce = mapa[0].length;
        int[][] dp = new int[riadky][stlpce];
        int[][] cesta = new int[riadky][stlpce];
        int pocetVeci = 0;

        // prvy riadok rovnaky ako v mape - nemam co v nom menit
        for (int j = 0; j < stlpce; j++) {
            dp[0][j] = mapa[0][j];
        }


        for (int i = 1; i < riadky; i++) {
            for (int j = 0; j < stlpce; j++) {

                // najprv zoberiem direkt nad tym hodnotu
                int najvacsiaHodnota = dp[i-1][j];


                if (j > 0 && dp[i-1][j-1] > najvacsiaHodnota) {
                    najvacsiaHodnota = dp[i-1][j-1];
                }

                if (j < stlpce - 1 && dp[i-1][j+1] > najvacsiaHodnota) {
                    najvacsiaHodnota = dp[i-1][j+1];
                }

                dp[i][j] = mapa[i][j] + najvacsiaHodnota;


            }



        }



        for (int i = 0; i < stlpce; i++) {
            if (pocetVeci < dp[riadky-1][i]) {
                pocetVeci = dp[riadky-1][i];
            }
        }

        return pocetVeci;


    }

}
