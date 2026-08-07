import java.util.*;

class Solution {
    int A, B; // target exponents needed for prime 2 and prime 3
    int[][] finishDist; // finishDist[i][j] = min extra "non-one" digits needed
                         // to go from state (i,j) up to (A,B)
    int[] dx = {1, 0, 2, 1, 3, 0};      // contribution to power-of-2 for digits {2,3,4,6,8,9}
    int[] dy = {0, 1, 0, 1, 0, 2};      // contribution to power-of-3 for digits {2,3,4,6,8,9}
    int[] digitVal = {2, 3, 4, 6, 8, 9};

    public String smallestNumber(String num, long t) {
        long[] fac = factorExponents(t);
        if (fac == null) return "-1"; // t has a prime factor other than 2,3,5,7 -> impossible

        int a = (int) fac[0], b = (int) fac[1];
        long c = fac[2], d = fac[3];
        A = a; B = b;
        buildFinishDist();
        int K0 = finishDist[0][0];
        long Lmin = c + d + K0;

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = num.charAt(i) - '0';

        // ---- Step 1: check num itself ----
        if (isZeroFree(digits) && satisfies(digits, a, b, c, d)) {
            return num;
        }

        // ---- Step 2: try to keep the same length, bumping a digit ----
        int[] pre2 = new int[n + 1], pre3 = new int[n + 1];
        long[] pre5 = new long[n + 1], pre7 = new long[n + 1];
        boolean[] prefixHasZero = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            int[] e = exponentsOfDigit(digits[i]);
            pre2[i + 1] = pre2[i] + e[0];
            pre3[i + 1] = pre3[i] + e[1];
            pre5[i + 1] = pre5[i] + e[2];
            pre7[i + 1] = pre7[i] + e[3];
            prefixHasZero[i + 1] = prefixHasZero[i] || (digits[i] == 0);
        }

        for (int p = n - 1; p >= 0; p--) {
            if (prefixHasZero[p]) continue; // digits[0..p-1] must be zero-free

            int i2 = Math.min(pre2[p], A);
            int i3 = Math.min(pre3[p], B);
            long cHave = pre5[p];
            long dHave = pre7[p];

            for (int v = digits[p] + 1; v <= 9; v++) {
                int[] e = exponentsOfDigit(v);
                int ni2 = Math.min(i2 + e[0], A);
                int ni3 = Math.min(i3 + e[1], B);
                long nc = cHave + e[2];
                long nd = dHave + e[3];
                long cRem = Math.max(0, c - nc);
                long dRem = Math.max(0, d - nd);
                int suffixLen = n - p - 1;
                int K = finishDist[ni2][ni3];

                if (cRem + dRem + K <= suffixLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, p);
                    sb.append((char) ('0' + v));
                    sb.append(buildSuffix(suffixLen, ni2, ni3, cRem, dRem));
                    return sb.toString();
                }
            }
        }

        // ---- Step 3: need an extra digit (or more) ----
        long L = Math.max((long) n + 1, Lmin);
        return buildSuffix((int) L, 0, 0, c, d);
    }

    // Smallest zero-free string of the given length, starting from accumulated
    // state (i,j) for powers of 2/3, still needing cRem fives and dRem sevens.
    private String buildSuffix(int length, int i, int j, long cRem, long dRem) {
        int K = finishDist[i][j];
        long ones = length - cRem - dRem - K;

        List<Integer> nonOne = new ArrayList<>();
        int ci = i, cj = j, remaining = K;
        while (remaining > 0) {
            for (int idx = 0; idx < digitVal.length; idx++) {
                int ni = Math.min(ci + dx[idx], A);
                int nj = Math.min(cj + dy[idx], B);
                if (finishDist[ni][nj] == remaining - 1) {
                    nonOne.add(digitVal[idx]);
                    ci = ni; cj = nj;
                    remaining--;
                    break;
                }
            }
        }
        for (long k = 0; k < cRem; k++) nonOne.add(5);
        for (long k = 0; k < dRem; k++) nonOne.add(7);
        Collections.sort(nonOne);

        StringBuilder sb = new StringBuilder();
        for (long k = 0; k < ones; k++) sb.append('1');
        for (int v : nonOne) sb.append((char) ('0' + v));
        return sb.toString();
    }

    private void buildFinishDist() {
        finishDist = new int[A + 1][B + 1];
        for (int[] row : finishDist) Arrays.fill(row, -1);
        finishDist[A][B] = 0;

        for (int i = A; i >= 0; i--) {
            for (int j = B; j >= 0; j--) {
                if (i == A && j == B) continue;
                int best = Integer.MAX_VALUE;
                for (int idx = 0; idx < digitVal.length; idx++) {
                    int ni = Math.min(i + dx[idx], A);
                    int nj = Math.min(j + dy[idx], B);
                    int f = finishDist[ni][nj];
                    if (f >= 0) best = Math.min(best, f + 1);
                }
                finishDist[i][j] = best;
            }
        }
    }

    private boolean isZeroFree(int[] digits) {
        for (int v : digits) if (v == 0) return false;
        return true;
    }

    private boolean satisfies(int[] digits, int a, int b, long c, long d) {
        int p2 = 0, p3 = 0;
        long p5 = 0, p7 = 0;
        for (int v : digits) {
            int[] e = exponentsOfDigit(v);
            p2 += e[0]; p3 += e[1]; p5 += e[2]; p7 += e[3];
        }
        return p2 >= a && p3 >= b && p5 >= c && p7 >= d;
    }

    private int[] exponentsOfDigit(int v) {
        switch (v) {
            case 2: return new int[]{1, 0, 0, 0};
            case 3: return new int[]{0, 1, 0, 0};
            case 4: return new int[]{2, 0, 0, 0};
            case 5: return new int[]{0, 0, 1, 0};
            case 6: return new int[]{1, 1, 0, 0};
            case 7: return new int[]{0, 0, 0, 1};
            case 8: return new int[]{3, 0, 0, 0};
            case 9: return new int[]{0, 2, 0, 0};
            default: return new int[]{0, 0, 0, 0}; // 0 or 1
        }
    }

    private long[] factorExponents(long t) {
        int a = 0, b = 0;
        long c = 0, d = 0;
        while (t % 2 == 0) { t /= 2; a++; }
        while (t % 3 == 0) { t /= 3; b++; }
        while (t % 5 == 0) { t /= 5; c++; }
        while (t % 7 == 0) { t /= 7; d++; }
        if (t != 1) return null;
        return new long[]{a, b, c, d};
    }
}