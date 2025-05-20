package project;

import java.io.Serializable;

public class Node <E> implements Serializable{
	int key;
	Student data;
	Node<E> leftChild;
	Node<E> rightChild;
	public Node(int k,Student s)
	{
		key=k;
		data=s;
		leftChild=null;
		rightChild=null;
	}
	public void display() {
		System.out.print(key+":");
		System.out.println(data);

	}
}
