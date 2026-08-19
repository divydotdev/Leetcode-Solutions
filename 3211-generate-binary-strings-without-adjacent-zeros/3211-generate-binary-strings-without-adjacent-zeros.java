class Solution {
    public List<String> validStrings(int n) {
         List<String> ans = new ArrayList<>();
        bt(n, "", ans);
        return ans;
    }
    private void bt(int n, String s, List<String> ans) {
        if (s.length() == n) {
            ans.add(s);
            return;
        }
        bt(n, s + "1", ans);
        if (s.length() == 0 || s.charAt(s.length() - 1) != '0') {
            bt(n, s + "0", ans);
        }
    }
}