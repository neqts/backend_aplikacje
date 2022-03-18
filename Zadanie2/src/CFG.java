import java.io.FileWriter;
import java.io.IOException;



class GFG {

    public static void main(String[] args)
    {

        try {

            FileWriter fw = new FileWriter("FileWithText.txt");
            fw.write("Java jest fajna");
            fw.close();

            System.out.println("\nFile write done");
        }


        catch (IOException e) {

            System.out.println(
                    "There are some IOException");
        }
    }
}
