public class Queue {
    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        head = tail = null;
        size = 0;
    }

    public void enqueue(Airplane airplane) {
        Node newNode = new Node(airplane);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = tail;
        }
        size++;
    }

    public Airplane dequeue() {
        if (head == null) return null;
        Airplane airplane = head.airplane;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return airplane;
    }

    public Airplane peek() {
        return (head != null) ? head.airplane : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.airplane.toString());
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}