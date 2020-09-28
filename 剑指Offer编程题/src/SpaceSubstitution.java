public class SpaceSubstitution {
    //2
    /*
    * 题目描述
    请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
    */
    public static void main(String[] args){
        StringBuffer sb = new StringBuffer();
        sb.append("We Are Happy");
            System.out.println();
    }
    //直接调用自带函数
    public String replaceSpace(StringBuffer str) {
        return str.toString().replace(" ", "%20");
    }

    /*
* 方法: 逆向遍历
    分析：由于函数返回为void，说明此题不能另外开辟数组，需要in-place操作。我们知道字符串的遍历无非是从左到右和从右到左两种。
    1）如果从左到右，会发现如果遇到空格，会将原来的字符覆盖。于是，此方法不行。
    2）那么就考虑从右向左，遇到空格，就填充“20%“，否则将原字符移动应该呆的位置。
    具体过程：
    length为原字符串最后一个字符的位置，new_lngth为结果字符串的最后一个位置

    如果str[length]不等于空格，就复制，然后指针分别左移一位。

    如果str[length]等于空格，就填充“20%”

    一直进行上述步骤，直到字符串遍历完毕
*/
        public String replaceSpace2(StringBuffer str) {
            //计算空格的数量
            int blankNum=0;
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)==' '){
                    blankNum++;
                }
            }
            //记录初始的字符串、插入后的字符串的长度
            int originalStringLength=str.length();
            int newStringLength=originalStringLength+2*blankNum;
            //重新设置str的长度
            str.setLength(newStringLength);
            //定义两个指针，分别指向新旧字符串的末尾
            int indexOfOriginalString=originalStringLength-1;
            int indexOfNewString=newStringLength-1;
            //结束条件及确保是否越界
            while(indexOfOriginalString>=0&&indexOfNewString>indexOfOriginalString){
                if(str.charAt(indexOfOriginalString)==' '){
                    //插入语%20
                    str.setCharAt(indexOfNewString--,'0');
                    str.setCharAt(indexOfNewString--,'2');
                    str.setCharAt(indexOfNewString--,'%');
                }else{
                    str.setCharAt(indexOfNewString--,str.charAt(indexOfOriginalString));
                }
                indexOfOriginalString--;
            }
            return str.toString();
        }
    }

