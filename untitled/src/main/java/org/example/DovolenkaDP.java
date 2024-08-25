


public class DovolenkaDP {

    public static void main(String[] args) {
        int M = 3;
        int H = 2;
        int m = 1;
        int h = 2;

        DovolenkaDP dp = new DovolenkaDP();
        System.out.println(count(M, H, m, h));


    }

    public static int count(int M, int H, int m, int h) {
        int[][] dp = new int[M+1][H+1];

        // 0 dni u mora + 0 dni v horach = 1 moznost - ziadna
        // musi byt 1 kvoli DP logike
        dp[0][0] = 1;


        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= H; j++) {
                if (dp[i][j] > 0) {
                    for (int k = 1; k <= m && i + k <= M; k++) {
                        dp[i + k][j] += dp[i][j];
                    }

                    for (int l = 1; l <= h && j + l <= H; l++) {
                        dp[i][j + l] += dp[i][j];
                    }
                }
            }
        }


        return dp[M][H];
    }






}
