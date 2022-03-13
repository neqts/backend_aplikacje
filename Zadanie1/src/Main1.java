import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main1 {
    public static void main(String[] args){
        File file = new File("file");
        int length = (int) file.length();

        try(   //try-with resources ==using()
               FileInputStream stream = new FileInputStream(file);
               InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)
               ;)
        {

            char[] data = new char[1024];
            int readBytes = reader.read(data,0,length);
            if(readBytes!=length){
                throw new IOException("File reading error.");
            }

            reader.read(data,0,1024);

            String text = new String(data);
            System.out.println(text);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}

