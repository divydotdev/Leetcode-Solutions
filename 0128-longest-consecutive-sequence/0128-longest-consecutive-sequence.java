class Solution {
    public int longestConsecutive(int[] nums) {
    int count=0;
     if (nums.length == 0) 
            return 0;
        Set<Integer> h = new HashSet<>();
        for (int num : nums) h.add(num);

    for (int num : h){
        if(!h.contains(num-1)){
            int l=1;
        
        while(h.contains(num + l)){
            l++;
        }
        count = Math.max(l,count);}
    }

    return count;
    }
}