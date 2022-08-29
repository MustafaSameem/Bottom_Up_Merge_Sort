# IterativeMergeSort
This project sorts values in ascending order
User can input their own values or decide to make a list of random generated numbers to be sorted

The approach used is an iterative bottom up merge sort as opposed to the regular top down recursive merge sort.
A circular queue is used to store "runs" which allows the program to be iterative.

The terminal is required to run this program. Use format: java MergeDriver "MS" 23 89 3 89 23 OR "SMS" 12 
More information below

        System.out.println("To sort your own values, enter your data in the following order: MS int int int int...");
        System.out.println("To sort a random set of values, enter your data in the following order: MS size_of_list");
        System.out.println("To perform a Structured pass before the iterative MergeSort, change MS to SMS and keep the same format");
        

