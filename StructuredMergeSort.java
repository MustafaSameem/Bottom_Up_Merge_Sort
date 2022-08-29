public class StructuredMergeSort {


   static void sort(int[] arr) {

       //start time
       int start_time = (int) System.nanoTime();

       //terminate
       if(arr.length < 2) {
           return;
       }


       int temp;
       int SMS_compares = arr.length-1;

       //structuring loop
       for(int i=0; i < arr.length-1; i++)
       {
           //check equality, if yes, increment by i by 1
           int equal_counter =0;
           while (arr[i] == arr[i+1]) {
               i++;
               equal_counter++;
               if(i >= arr.length-1)
                   break;
           }
           //check descending runs
           int desc_counter=0;
               while (arr[i] >= arr[i + 1]) {
                   desc_counter = desc_counter + equal_counter;
                   i++;
                   desc_counter++;
                   if (i >= arr.length - 1)
                       break;
               }
           //check ascending runs
           if(desc_counter==0) {
               while (arr[i] <= arr[i + 1]) {
                   i++;
                   if (i >= arr.length - 1)
                       break;
               }
           }
           //swaps descending runs
           if(desc_counter>=1) {
               for(int k=0; k<(desc_counter+1)/2; k++) {
                   temp = arr[i-k];
                   arr[i-k] = arr[i-desc_counter+k];
                   arr[i-desc_counter+k] = temp;
               }
           }
       }
       //all runs are ascending from this point


       //copy array into dest[] and create jump list
       int dest[] = new int[arr.length];
       for(int i=0; i < arr.length; i++) {
           dest[i] = arr[i];
       }
       JumpList jmp_obj = new JumpList(arr.length);


       // create objects for all ascended runs and store in jmp list
       int number_of_runs =0;
       for(int i=0; i <= arr.length-1; i++)
       {
           //if last element in ascending runs is alone, enqueue it
           if(i == arr.length-1) {
               number_of_runs++;

               Arun A_obj = new Arun();
               A_obj.setLength(1);
               A_obj.setStart(i);
               jmp_obj.enqueue(A_obj);
               break;
           }

           //enqueue ascending runs
           int run_size =0;
           while (arr[i] <= arr[i + 1])
           {
               run_size++;
               i++;
               if (i >= arr.length - 1)
                   break;
           }
           number_of_runs++;

           Arun A_obj = new Arun();
           A_obj.setLength(run_size+1);
           A_obj.setStart(i-run_size);
           jmp_obj.enqueue(A_obj);
       }

       //Iterate over the jump list and call merge to merge values and update jump list
       for(int j=1; j < number_of_runs;)
       {
           for (int i=0; i < number_of_runs/2; i++)
           {
               Arun obj1 = jmp_obj.dequeue();
               Arun obj2 = jmp_obj.dequeue();
               MergeSort.merge(arr, dest, obj1, obj2, jmp_obj);
           }
           // reposition last element in jmp_list array, so you can iterate over jump list
           if(number_of_runs%2 != 0) {
               Arun obj_odd = jmp_obj.dequeue();
               jmp_obj.enqueue(obj_odd);
           }
           number_of_runs = (number_of_runs+1)/2;
           if(number_of_runs < 2)
               break;
       }


       //print results
       System.out.println();
       System.out.println("Comparisons: " + (MergeSort.comparisons + SMS_compares));
       System.out.print("Sorted: ");
       for(int i=0; i < dest.length; i++) {
           System.out.print(dest[i] + " ");
       }

       int final_time = (int) System.nanoTime();
       System.out.println();
       System.out.println("Completed in: " + (final_time - start_time) + "ns");

   }
}

