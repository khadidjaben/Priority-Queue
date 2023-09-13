
// the import statements section, all the methods we need from java packages 
import java.util.ArrayList;

// the class creation
public class PriorityQueue<integer> {

    public int children;
    public int initialCapacity;
    private ArrayList<Integer> heap;

    public PriorityQueue(int x) {
        this.children = (int) Math.pow(2, x); // x the parameter that determines the number of children per parent node
        this.heap = new ArrayList<>(initialCapacity);
    }

    // the methods implemented for the priority queue made of a power of two max heap

    public void insert(Integer value) {

        heap.ensureCapacity(initialCapacity);
        heap.add(value);

        int index = heap.size() - 1;

        while (index > 0 && heap.get(parent(index)) < heap.get(index)) {
            swap(index, parent(index));
            index = parent(index);
        }
        heap.trimToSize();
    }

    public int popMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }
        int maxValue = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifydown(0);
        }
        return maxValue;
    }

    private void heapifydown(int index) {
        int largest = index;
        for (int i = 1; i <= children; i++) {
            int childIndex = index * children + i;
            if (childIndex < heap.size() && heap.get(childIndex).compareTo(heap.get(largest)) > 0) {
                largest = childIndex;
            }
        }
        if (largest != index) {
            swap(index, largest);
            heapifydown(largest);
        }
    }

    private int parent(int index) {
        return (index - 1) / children;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public static void main(String[] args) {
        // an example test for the implemented power of two max heap !
        PriorityQueue<Integer> heap = new PriorityQueue<>(3);

        heap.insert(30);
        heap.insert(300);
        heap.insert(2);
        System.out.println("the heap size is: " + heap.size());
        System.out.println("the number of children for each parent: " + heap.children);
        System.out.println("the popped values are: ");
        System.out.println(heap.popMax());
        System.out.println(heap.popMax());
        System.out.println(heap.popMax());
        System.out.println("Is it Empty? " + heap.isEmpty());

    }
}