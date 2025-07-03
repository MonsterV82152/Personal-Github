public class Bases {
    private int[][] testcases;

    public Bases(int[][] inp) {
        testcases = inp;
    }

    public int biToDec() {
        int a = 0;
        for (int[] tes : testcases) {
            int result = 0;
            for (int i = 0; i < tes.length; i++) {
                if (i == 0) {
                    result -= tes[i] * Math.pow(2, tes.length - 1);
                } else {
                    result += tes[i] * Math.pow(2, tes.length - i - 1);
                }
            }
            a+=result;
        }

        return a;
    }
}

