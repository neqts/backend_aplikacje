import java.util.*;
import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        //ARRAY LISTS
        System.out.println("ArrayList:");
        ArrayList<String> cars = new ArrayList<String>();

        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println("Before: "+cars);
        Iterator<String> iCars = cars.iterator();
        int i =1;
        while (iCars.hasNext()) {
            String currentName = iCars.next();
            if (i % 2 == 0) {
                iCars.remove();
            }
            i++;
        }
        System.out.println("After: " +cars);
        System.out.println("\n");
 //-----------------------------------------------------------------------------------------------
        //Linked
        System.out.println("LinkedList:");
        LinkedList<String> colors = new LinkedList<String>();
        colors.add("Blue");
        colors.add("Green");
        colors.add("Orange");
        colors.add("Red");
        System.out.println("Before: " +colors);
        Iterator<String> iColors = colors.iterator();
        int j = 1;
        while (iColors.hasNext()) {
            String currentName = iColors.next();
            if (j % 2 == 0) {
                iColors.remove();
            }
            j++;
        }
        System.out.println("After: " +colors);
        System.out.println("\n");
        //---------------------------------------------------------------------------------------
        System.out.println("HashSet:");
        HashSet<String> number = new HashSet<String>();
        number.add("1");
        number.add("2");
        number.add("1");
        number.add("3");
        number.add("4");
        number.add("4");
        System.out.println("After: " +number);
        System.out.println("\n");
        //-------------------------------------------------------------------------------------
       HashMap<String, String> capitalCities = new HashMap<String, String>();
        System.out.println("HashMap:");
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington");
        int m=1;
        for (String k : capitalCities.keySet()) {
            System.out.println(m+". "+ k + " - " + capitalCities.get(k));
            m++;
        }


    }
}

