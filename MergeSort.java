public class MergeSort {

    static int comparisons =0;
    static void Sort(int[] arr)
    {
        //start time
        int start_time = (int) System.nanoTime();

        //terminate
            if(arr.length < 2) {
                return;
            }

            //initialize dest[] and jump list
            int size = arr.length;
            int dest[] = new int[size];
            for(int i=0; i < size; i++) {
                dest[i] = arr[i];
            }
            JumpList jmp_obj = new JumpList(size);

            //enqueue all runs, start from size 1 because bottom up merge sort
            for(int i=0; i < size; i++)
            {
                Arun A_obj = new Arun();
                A_obj.setStart(i);
                A_obj.setLength(1);
                jmp_obj.enqueue(A_obj);
            }


            //Iterate over jump list and merge runs. update the runs and store again in jump list. (like SMS)
            for(int j=1; j < size;)
            {
                for (int i=0; i < size/2; i++)
                {
                    Arun obj1 = jmp_obj.dequeue();
                    Arun obj2 = jmp_obj.dequeue();
                    merge(arr, dest, obj1, obj2, jmp_obj);
                }
                // reposition last element in jmp_list array, so you can iterate over jump list
                if(size%2 != 0) {
                    Arun obj_odd = jmp_obj.dequeue();
                    jmp_obj.enqueue(obj_odd);
                }
                size = (size+1)/2;
                if(size < 2)
                    break;
            }

            //print results
            System.out.println();
            System.out.println("Comparisons: " + comparisons);
            System.out.print("Sorted: ");
            for(int i=0; i < dest.length; i++) {
                System.out.print(dest[i] + " ");
            }

        int final_time = (int) System.nanoTime();
        System.out.println();
        System.out.println("Completed in: " + (final_time - start_time) + "ns");
    }


    static void merge(int[] src, int[] dest, Arun run1, Arun run2, JumpList Jump_obj)
    {
        //define size for 2 arrays
        int size_L, size_R, Total_size;
        size_L = run1.getLength();
        size_R = run2.getLength();


        //size of both arrays combined
        Total_size = size_L + size_R;

        //define variables to sort both arrays
        int a =0;
        int b =0;
        int c = run1.getStart();

        //create 2 arrays
        int[] Arr1 = new int[size_L];
        int[] Arr2 = new int[size_R];

        //copy run1 and run2 into arrays
        for(int i =0; i < size_L; i++)
            Arr1[i] = dest[run1.getStart() + i];

        for(int i =0; i < size_R; i++)
            Arr2[i] = dest[run2.getStart() + i];

        //sort values in ascending order
        while(a < size_L && b < size_R)
        {
            comparisons++;
            if(Arr1[a] <= Arr2[b])
            {
                dest[c] = Arr1[a];
                a++;
            }
            else
            {
                dest[c] = Arr2[b];
                b++;
            }
            c++;
        }

        //copy last elements if any remains
        while (a < size_L)
        {
            dest[c] = Arr1[a];
            a++;
            c++;
        }
        while (b < size_R)
        {
            dest[c] = Arr2[b];
            b++;
            c++;
        }

        Arun fused_obj = new Arun();
        fused_obj.setLength(Total_size);
        fused_obj.setStart(run1.getStart());
        Jump_obj.enqueue(fused_obj);
    }

}
