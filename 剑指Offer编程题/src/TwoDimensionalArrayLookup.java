public class TwoDimensionalArrayLookup {
//1
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*
        * 题目描述
        在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
        每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
        判断数组中是否含有该整数。
        */
        int array[][]={{1,2,3,4,22,66,88},{1,2,3,4,22,66,88},{1,2,3,4,22,66,88}};
        boolean a =  Find2(2,array);
        if(a ==false){
            System.out.println("没找到");
        }else{
            System.out.println("找到了");
        }
    }
       /*暴力解题法:
        分析：直接遍历一遍数组，即可判断目标target是否存在。
        复杂度分析
        时间复杂度：O(n^2)，因为最坏情况下，数组中的元素都需要遍历一次。
        空间复杂度：O(1)
        */
    public static  boolean Find1(int target, int[][] array) {
        if(array.length == 0||array[0].length == 0){return false;}
        for( int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                if(target == array[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

   /*从左下找(二维的二分法)：
   分析：
    利用该二维数组的性质：
    每一行都按照从左到右递增的顺序排序，
    每一列都按照从上到下递增的顺序排序
    改变个说法，即对于左下角的值 m，m 是该行最小的数，是该列最大的数

    每次将 m 和目标值 target 比较：
    当 m < target，由于 m 已经是该列最大的元素，想要更大只有从列考虑，取值右移一位
    当 m > target，由于 m 已经是该行最小的元素，想要更小只有从行考虑，取值上移一位
    当 m = target，找到该值，返回 true
    用某行最小或某列最大与 target 比较，每次可剔除一整行或一整列

    时间复杂度：o(行高+列宽)
    空间复杂度：o(1)
   * */
    public static boolean Find2(int target,int [][]array){
        int rows = array.length;
        if(rows == 0){
            return false;
        }
        int cols = array[0].length;
        if(cols == 0){
            return false;
        }
        int row = rows - 1;
        int col = 0;
        while(row>=0 && col <cols){
            if(array[row][col]>target){
                row --;
            }else if(array[row][col]<target){
                col++;
            }else{
                return true;
            }
        }
        return false;
    }

}