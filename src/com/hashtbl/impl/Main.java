package com.hashtbl.impl;

import com.hashtbl.utils.HashTable;

public class Main {
	public static void main(String[] args) {
		HashTable hashTable = new HashTable();

		// Insert key-value pairs
		hashTable.put("name", "Alice");
		hashTable.put("age", "25");
		hashTable.put("city", "Wonderland");

		// Retrieve values
		System.out.println("Name: " + hashTable.get("name")); // Alice
		System.out.println("Age: " + hashTable.get("age")); // 25
		System.out.println("City: " + hashTable.get("city")); // Wonderland

		// Update value
		hashTable.put("city", "New York");
		System.out.println("Updated City: " + hashTable.get("city")); // New York

		// Remove a key
		hashTable.remove("age");
		System.out.println("Age after removal: " + hashTable.get("age")); // null

		// Print the table
		System.out.println("\nHash Table:");
		hashTable.printTable();
	}
}
