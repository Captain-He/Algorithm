public class Teachersday {
    /*liu 104,110,200,200,214,203,113,110,112,104,113,202,212,103,110,214,203,113,110,112,104,113,202,134,114,213*/
    /*lu 104,110,200,200,214,203,113,110,112,104,113,202,212,103,110,214,203,113,110,112,104,113,202,134,213*/
    /*ji 104,110,200,200,214,203,113,110,112,104,113,202,212,103,110,214,203,113,110,112,104,113,202,124,141*/
    public static void main(String[] args) {
        int num[] = {104, 110, 200, 200, 214, 203, 113, 110, 112, 104, 113, 202, 212, 103, 110, 214, 203, 113, 110, 112, 104, 113, 202, 134, 213};
        for (int aNum : num) {
            String res = (aNum / 100 == 1) ? "0100" : "0101";
            for (int j = 4; j > 0; j--) {
                if (aNum % 10 == j) res += "1";
                else if (aNum / 10 % 10 == j) res += "1";
                else res += "0";
            }
            System.out.print(res);
        }
    }
}
