package listlinked;

public class ListLinked<T extends Comparable<T>> {
    private Node<T> first;

    public ListLinked() {
        first = null;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = first;
        first = newNode;
    }

    public void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (first == null) {
            first = newNode;
        } else {
            Node<T> aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
        }
    }

    public boolean search(T x) {
        Node<T> aux = first;
        while (aux != null) {
            if (aux.value.equals(x)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public boolean removeNode(T x) {
        if (first == null)
            return false;

        if (first.value.equals(x)) {
            first = first.next;
            return true;
        }

        Node<T> aux = first;
        while (aux.next != null && !aux.next.value.equals(x)) {
            aux = aux.next;
        }

        if (aux.next != null) {
            aux.next = aux.next.next;
            return true;
        }
        return false;
    }

    public int length() {
        int count = 0;
        Node<T> aux = first;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;
    }

    public T get(int index) {
        if (index < 0 || first == null) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        Node<T> aux = first;
        int count = 0;
        
        while (aux != null) {
            if (count == index) {
                return aux.value;
            }
            count++;
            aux = aux.next;
        }
        
        // Si el bucle termina y no encontró el índice, es porque el índice es mayor al tamaño
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
    }

    public void print() {
        Node<T> aux = first;
        while (aux != null) {
            System.out.println(aux.value);
            aux = aux.next;
        }
        System.out.println();
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = first;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        first = prev;
    }

    public T getMin() {
        Node<T> aux = first;
        T min = aux.value;
        while (aux != null) {
            if (aux.value.compareTo(min) < 0) {
                min = aux.value;

            }
            aux = aux.next;

        }

        return min;

    }
}
