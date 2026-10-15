class Solution {
    public int sumCounts(List<Integer> nums) {
        int sum=0;
    for (int i = 0; i < nums.size(); i++) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int dis=0;
    for (int j = i ; j < nums.size(); j++) {
        int curr=nums.get(j);
        if(!map.containsKey(curr)){
            dis++;
        }
        map.put(curr,1);
        sum+=(dis*dis);
    }
}
return sum;
    }
}