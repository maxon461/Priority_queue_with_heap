import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> {
    public ArrayList<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<T>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    public void enqueue(T item) {
        heap.add(item);
        int i = heap.size() - 1;
        while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) > 0) {
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    public T dequeue() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        T item = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        maxHeapify(0);
        return item;
    }

    private void maxHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heap.size() && heap.get(l).compareTo(heap.get(largest)) > 0) {
            largest = l;
        }
        if (r < heap.size() && heap.get(r).compareTo(heap.get(largest)) > 0) {
            largest = r;
        }
        if (largest != i) {
            Collections.swap(heap, i, largest);
            maxHeapify(largest);
        }
    }

    public void changePriority(int i, T item) {
        if (i < 0 || i >= heap.size()) {
            throw new IndexOutOfBoundsException();
        }
        T oldItem = heap.get(i);
        heap.set(i, item);
        if (item.compareTo(oldItem) > 0) {
            while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) > 0) {
                Collections.swap(heap, i, parent(i));
                i = parent(i);
            }
        } else {
            maxHeapify(i);
        }
    }

    public void delete(int i) {
        if (i < 0 || i >= heap.size()) {
            throw new IndexOutOfBoundsException();
        }
        heap.set(i, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        maxHeapify(i);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void sortInPlace(T[] array) {
        heap = new ArrayList<T>(Arrays.asList(array));
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            maxHeapify(i);
        }
        for (int i = heap.size() - 1; i > 0; i--) {
            Collections.swap(heap, 0, i);
            maxHeapify(0);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.get(i);
        }
    }
}
