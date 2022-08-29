public class JumpList {

    //tracking queue
    int head, tail;
    int arr_size;
    Arun[] runs;
    int reset =-1;


    //constructor to initialize array of objects size and trackers
    JumpList(int arr_length)
    {
        runs = new Arun[arr_length];
        head =-1;
        tail =-1;
        arr_size = arr_length;
    }

    boolean isEmpty()
    {
        if (head == reset)
            return true;
        else
            return false;
    }

    void enqueue (Arun arun_object) {
        //classic full queue (exactly arr_size operations)
        if (head ==0 && tail == arr_size-1)
            System.out.println("full, must dequeue");
        //if rear starts at 0 because circular increment
        if (head == tail +1)
            System.out.println("full, must dequeue");

        //if not full
        else {
            // when queue goes from empty to 1 element
            if (head == reset)
                head++;

            // resets tail after reaching end
            tail = (tail+1) %arr_size;
            runs[tail] = arun_object;
        }
    }

    Arun dequeue() {
        Arun output_obj;
        if(isEmpty()) {
            return null;
        }
        else {
            output_obj = runs[head];
            //reset because queue becomes empty
            if (head == tail)
            {
                head = reset;
                tail = reset;
            }
            //reposition
            else {
                head = (head+1) %arr_size;
            }
            return output_obj;
        }
    }
}
