import java.util.Arrays;
public class Main1{

    public static void main(String args[]){
        String inputStr = "First Line\r\nSecond Line\rThird Line";
        String[] linesArr = inputStr.lines().toArray(String[]::new);
        System.out.println(Arrays.toString(linesArr));
    }
}