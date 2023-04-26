import java.util.Arrays;

public class App {


    //Priority queue using heap
    public static void main(String[] args) throws Exception {
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
       
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(10);
        queue.enqueue(7);
        queue.enqueue(1);
        queue.enqueue(8);
        queue.enqueue(3);
        queue.enqueue(6);
        queue.enqueue(9);

        queue.changePriority(3, 11);
        queue.dequeue();

        
        for(int i = 0; i <= queue.size()-1;i++)
        System.out.println("Initial queue: " + queue.heap.get(i));
    }
}
