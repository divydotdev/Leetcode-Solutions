class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int x=nums1.length;
        int y=nums2.length;
        Map<Integer, Integer> ng = new HashMap<>();
        Stack<Integer> st= new Stack<>();
        int[] res = new int[x];
        Arrays.fill(res,-1);
        for(int i=0;i<y;i++){
            while(!st.isEmpty() && st.peek()<nums2[i]){
                ng.put(st.pop(),nums2[i]);
            }
            st.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = ng.getOrDefault(nums1[i], -1);
        }
    return res;
    }
}


/*


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ng = new HashMap<>();
        Deque<Integer> st = new ArrayDeque<>();

        for (int num : nums2) {
            while (!st.isEmpty() && st.peek() < num) {
                ng.put(st.pop(), num);
            }
            st.push(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = ng.getOrDefault(nums1[i], -1);
        }
        return res;        
    }
}


*/