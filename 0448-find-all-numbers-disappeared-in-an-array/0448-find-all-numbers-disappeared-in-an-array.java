class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List <Integer> list=new ArrayList();
        for(int i=0;i<nums.length;i++){
            int val=Math.abs(nums[i]);
            nums[val-1]= -Math.abs(nums[val-1]);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0)     list.add(i+1);
        }
        return list;
    }
}