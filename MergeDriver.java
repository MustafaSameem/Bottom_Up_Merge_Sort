import java.util.*;
import java.lang.*;

public class MergeDriver {

    public static void main(String[] args) {

        // for 2 inputs in command line
        if (args.length == 2)
        {
            Random object = new Random();
            int size = Integer.parseInt(args[1]); //convert value in string array to int

            int[] array = new int[size];
            System.out.print("Sorting: ");
            for(int i=0; i < size; i++)
            {
                int random_value = object.nextInt(1000); //create bound at 100 for random generated values
                array[i] = random_value;
                System.out.print(array[i] + " ");
            }

            if(Objects.equals(args[0], "MS")) {
                MergeSort.Sort(array);
            }
            if(Objects.equals(args[0], "SMS")) {
                StructuredMergeSort.sort(array);
            }
        }



        // for more than 2 inputs in command line
        if (args.length > 2)
        {
            int size2 =  args.length-1;
            int[] array = new int[size2];

            System.out.print("Sorting: ");
            for(int i=1; i < args.length; i++)
            {
                array[i-1] = Integer.parseInt(args[i]);
                System.out.print(array[i-1] + " ");
            }

            if(Objects.equals(args[0], "MS")) {
                MergeSort.Sort(array);
            }

            if(Objects.equals(args[0], "SMS")) {
                StructuredMergeSort.sort(array);
            }
        }
    }
}




