package HuffmanTree;

import java.util.*;

/**
 * Created by windons8 on 2017/11/17.
 */
public class HuffmanTree {
    public static class Node<E> {
        private E data;
        private double weight;
        Node<E> leftchild;
        Node<E> rightchild;

        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        public String toString() {
            return "Node [data=" + data + ", weight=" + weight + "]";
        }
    }

    private static <E> Node<E> createTree(List<Node<E>> nodes) {
        while (nodes.size() > 1) {
            quicksort(nodes);
            Node<E> left = nodes.get(nodes.size() - 1);
            Node<E> right = nodes.get(nodes.size() - 2);
            Node<E> parent = new Node<>(null, left.weight + right.weight);

            parent.leftchild = left;
            parent.rightchild = right;

            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);

            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static <E> void swap(List<Node<E>> nodes, int i, int j) {
        Node<E> tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }

    private static <E> void subsort(List<Node<E>> nodes, int start, int end) {
        if (start < end) {
            Node base = nodes.get(start);
            int i = start;
            int j = end + 1;
            while (true) {
                while (i < end && nodes.get(++i).weight >= base.weight) {}
                while (j > start && nodes.get(--j).weight <= base.weight) ;
                if (i < j) {
                    swap(nodes, i, j);
                } else
                    break;
            }
            swap(nodes, start, j);
            subsort(nodes, start, j - 1);
            subsort(nodes, j + 1, end);
        }
    }

    public static <E> void quicksort(List<Node<E>> nodes) {
        subsort(nodes, 0, nodes.size() - 1);
    }

    public static List<Node> breadthFirst(Node root) {
        Queue<Node> queue = new ArrayDeque<Node>();
        List<Node> list = new ArrayList<Node>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            list.add(queue.peek());
            Node p = queue.poll();
            if (p.leftchild != null) {
                queue.offer(p.leftchild);
            }
            if (p.rightchild != null) {
                queue.offer(p.rightchild);
            }
        }
        return list;
    }

    public static void main(String[] arg) {
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        nodes.add(new Node<String>("A", 40));
        nodes.add(new Node<String>("B", 7.0));
        nodes.add(new Node<String>("C", 10));
        nodes.add(new Node<String>("D", 30));
        nodes.add(new Node<String>("E", 12));
        nodes.add(new Node<String>("F", 2));

        Node<String> root = HuffmanTree.createTree(nodes);

        System.out.println(breadthFirst(root));


    }
}
