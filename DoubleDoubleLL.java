package classhomeandlabs2024;
//chase mccracken
public class DoubleDoubleLL {
    private Node head;
    private Node current;

    public DoubleDoubleLL() {
        head = null;
        current = null;
    }

    public class Node {
        double data;
        Node next;
        Node prev;

        public Node(double data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public void gotoNext() {
        if (current != null) {
            current = current.next;
        }
    }

    public void gotoPrev() {
        if (current != null) {
            current = current.prev;
        }
    }

    public void reset() {
        current = head;
    }

    public void gotoEnd() {
        while (current != null && current.next != null) {
            current = current.next;
        }
    }

    public boolean hasMore() {
        return current != null;
    }

    public Double getCurrent() {
        return (current != null) ? current.data : null;
    }

    public void setCurrent(double data) {
        if (current != null) {
            current.data = data;
        }
    }

    public void add(double data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    public void addAfterCurrent(double data) {
        if (current != null) {
            Node newNode = new Node(data);
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;
        }
    }

    public void remove(double data) {
        Node temp = head;
        while (temp != null && temp.data != data) {
            temp = temp.next;
        }
        if (temp != null) {
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            } else {
                head = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        }
    }

    public void removeCurrent() {
        if (current != null) {
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            }
        }
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public boolean contains(double data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}