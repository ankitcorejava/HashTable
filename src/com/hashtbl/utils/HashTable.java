package com.hashtbl.utils;

import java.util.LinkedList;

public class HashTable {
    // Node to represent key-value pairs in the chain
    private static class Node {
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] table; // Array of LinkedLists for chaining
    private int size; // Number of key-value pairs stored
    private int capacity; // Current capacity of the table
    private static final double LOAD_FACTOR = 0.75; // Threshold for resizing

    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    public HashTable() {
        this(16); // Default initial capacity
    }

    // Hash function: Maps a key to an index
    private int hash(String key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }

    // Add or update a key-value pair
    public void put(String key, String value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Node node : table[index]) {
            if (node.key.equals(key)) {
                node.value = value; // Update value if key exists
                return;
            }
        }

        table[index].add(new Node(key, value));
        size++;

        if ((double) size / capacity > LOAD_FACTOR) {
            resize(); // Resize table if load factor is exceeded
        }
    }

    // Retrieve value for a key
    public String get(String key) {
        int index = hash(key);
        LinkedList<Node> chain = table[index];

        if (chain != null) {
            for (Node node : chain) {
                if (node.key.equals(key)) {
                    return node.value; // Return value if key is found
                }
            }
        }

        return null; // Return null if key is not found
    }

    // Remove a key-value pair
    public void remove(String key) {
        int index = hash(key);
        LinkedList<Node> chain = table[index];

        if (chain != null) {
            for (Node node : chain) {
                if (node.key.equals(key)) {
                    chain.remove(node);
                    size--;
                    return;
                }
            }
        }
    }

    // Resize the hash table when the load factor is exceeded
    private void resize() {
        int oldCapacity = capacity;
        capacity = capacity * 2; // Double the capacity
        LinkedList<Node>[] oldTable = table;
        table = new LinkedList[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            LinkedList<Node> chain = oldTable[i];
            if (chain != null) {
                for (Node node : chain) {
                    put(node.key, node.value); // Rehash all elements
                }
            }
        }
    }

    // Utility: Print all key-value pairs (for debugging)
    public void printTable() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            if (table[i] != null) {
                for (Node node : table[i]) {
                    System.out.print("(" + node.key + ", " + node.value + ") ");
                }
            }
            System.out.println();
        }
    }
}

