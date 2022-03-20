public class Main {
    public static void main(String[] args)
    {
        int[] arr = new int[]{ 4, 5, 7, 11, 12, 15, 15, 21, 40, 45};
        var index = f(arr,11);

        System.out.println(index);
    }
    static double f(int[] x, double y) {
        var index =0;
        var limit = x.length -1;
        while (index<=limit){
            var point = Math.ceil((index+limit)/2);
            var entry = x[(int) point];
            if(y>entry){
                index = (int) (point+1);
            }
            else if(y<entry){
                limit = (int) (point -1);
            }
            else {
                return point;
            }
        }
        return -1;
    }
}
