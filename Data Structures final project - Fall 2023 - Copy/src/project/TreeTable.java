package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeTable implements Serializable {
	private Tree[] treetable;
	private final int size = 25;

	public TreeTable() {
		treetable = new Tree[size];
		for (int i = 0; i < treetable.length; i++)
			treetable[i] = new Tree();
	}

	public int hashfunc(int id) {
		int year = id / 100000;
		return year % size;
	}

	public void insert(Student s) {
		int id = s.getId();
		int pos = hashfunc(id);
		Tree treepos = treetable[pos];
		treepos.insertstudent(id, s);
	}

	public void printstudent(int year) {
		int pos = year % size;
		Tree treepos = treetable[pos];
		treepos.traverse(2);
	}

	public Student find(int id) {
		int pos = hashfunc(id);
		Tree tree = treetable[pos];
		return tree.searchstudentobj(id);
	}

	public Student update(int id) {
		int pos = hashfunc(id);
		Tree tree = treetable[pos];
		boolean found = tree.searchstudentbool(id);
		Student foundstudent = tree.searchstudentobj(id);
		if (found == true) {
			System.out.println("What do you want to update:" + "\n" + "1- name" + "\n" + "2-address" + "\n" + "3- GPA");

			Scanner choice = new Scanner(System.in);
			System.out.println("enter choice: (1-3)");
			int selected = choice.nextInt();
			if (selected == 1) {
				Scanner choice1 = new Scanner(System.in);
				System.out.println("Enter new name: ");
				String newname = choice1.next();
				foundstudent.setName(newname);
			} else if (selected == 2) {
				Scanner choice2 = new Scanner(System.in);
				System.out.println("Enter new address: ");
				String newaddress = choice2.next();
				foundstudent.setAddress(newaddress);
			} else if (selected == 3) {
				Scanner choice3 = new Scanner(System.in);
				System.out.println("Enter new GPA: ");
				double newGPA = choice3.nextDouble();
				foundstudent.setGPA(newGPA);
			} else
				System.out.print("Invalid choice");
		}
		return null;
	}

	public boolean remove(int id) {
		int pos = hashfunc(id);
		Tree tree = treetable[pos];
		if (tree.searchstudentbool(id)) {
			tree.remove(id);
			return true;
		}
		return false;
	}

	public void printall() {
		for (int i = 0; i < treetable.length; i++) {
			if (treetable[i] != null)
				treetable[i].traverse(1);
		}

	}

	public ArrayList<Student> studentWithGPA(double targetGPA) {
		ArrayList<Student> result = new ArrayList<>();
		for (int i = 0; i < treetable.length; i++) {
			Tree t = treetable[i];
			if (t != null) {
				t.FindStudentsGpa(t.getRoot(), targetGPA, result);
			}
		}
		for (Student s : result)
			System.out.println(s);

		return result;

	}

	public int numberStudents() {
		int sum = 0;
		for (int i = 0; i < treetable.length; i++) {
			Tree t = treetable[i];
			sum += t.countstudents(t.getRoot());
		}
		return sum; // Return the calculated sum
	}

	public int numberStudents(int year) {
		int sum = 0;
		int pos = year % size;
		Tree t = treetable[pos];

		return t.countstudents(t.getRoot());

	}

	 public Student highestGPA(int year) {
	        int pos = hashfunc(year * 100000); 
	        Tree tree = treetable[pos];

	       
	        Student highestGPAStudent = null;
	        double maxGPA = 0.0;

	       
	        if (tree != null) {
	            highestGPAStudent = findHighestGPA(tree.getRoot(), maxGPA);
	        }
	       return highestGPAStudent;
	 }
	 
	 private Student findHighestGPA(Node node, double maxGPA) {
	        if (node == null) {
	            return null;
	        }

	        Student currentStudent = node.data;

	        Student highestGPAStudent = null;
			
	        if (currentStudent.getGPA() > maxGPA) {
	            maxGPA = currentStudent.getGPA();
	            highestGPAStudent = currentStudent;
	        }

	        
	        Student leftMax = findHighestGPA(node.leftChild, maxGPA);
	        Student rightMax = findHighestGPA(node.rightChild, maxGPA);

	       
	        if (leftMax != null && leftMax.getGPA() > maxGPA) {
	            maxGPA = leftMax.getGPA();
	            highestGPAStudent = leftMax;
	        }
	        if (rightMax != null && rightMax.getGPA() > maxGPA) {
	            maxGPA = rightMax.getGPA();
	            highestGPAStudent = rightMax;
	        }

	        return highestGPAStudent;
	    }
	 
	 public void saveToFile() {
	        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("treeTableData.dat"))) {
	            objectOut.writeObject(this);
	            System.out.println("saved");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	   
	 public void loadFile() {
	        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("treeTableData.dat"))) {
	            TreeTable loadedTreeTable = (TreeTable) objectIn.readObject();
	            this.treetable = loadedTreeTable.treetable;
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }}
	 
	 public void menu() {
		 boolean exists = new File("treeTableData.dat").exists();
		 boolean exit = false;
		 if(exists)
			 loadFile();

	        while (!exit) {
	            System.out.println("Choose an option:");
	            System.out.println("1. Insert a student");
	            System.out.println("2. Find a student");
	            System.out.println("3. Update a student record");
	            System.out.println("4. Remove a student");
	            System.out.println("5. Print students admitted in a year");
	            System.out.println("6. Print all students");
	            System.out.println("7. Get students with GPA below a gpa");
	            System.out.println("8. Get student with the highest GPA in a specific year");
	            System.out.println("9. Get overall number of students");
	            System.out.println("10. Get number of students in a specific year");
	            System.out.println("11- highest GPA per year (from project assignemt)");
	            System.out.println("0. Exit");

	            Scanner menu = new Scanner(System.in);
	            int choice = menu.nextInt();
	          
	            switch (choice) {
	                case 1:
	                	System.out.println("Enter the ID:");
	                	Scanner c1 = new Scanner(System.in);
	                	int idc=c1.nextInt();
	                	System.out.println("Enter the GPA:");
	                	Scanner c2 = new Scanner(System.in);
	                	double gpac = c2.nextDouble();
	                	System.out.println("Enter the Address:");
	                	Scanner c3 = new Scanner(System.in);
	                	String adchoice=c3.next();
	                	System.out.println("Enter the student name:");
	                	Scanner c4 = new Scanner(System.in);
	                	String name = c4.next();
	                	Student student = new Student(idc,gpac,adchoice,name);
	                	insert(student);           		                		                	
	                    break;
	                case 2:
	                	System.out.println("Enter the ID of the student:");
	                	Scanner c11 = new Scanner(System.in);
	                	int idc1=c11.nextInt();
	                    find(idc1);
	                    break;
	                case 3:
	                	System.out.println("Enter the ID of the student:");
	                	Scanner c112 = new Scanner(System.in);
	                	int idc2=c112.nextInt();
	                    update(idc2);
	                    break;
	                case 4:
	                	System.out.println("Enter the ID of the student:");
	                	Scanner c1121 = new Scanner(System.in);
	                	int idc21=c1121.nextInt();
	                    remove(idc21);
	                    break;
	                case 5:
	                	System.out.println("Enter the year:");
	                	Scanner year = new Scanner(System.in);
	                	int ychoice=year.nextInt();
	                	printstudent(ychoice);
	                    break;
	                case 6:
	                	printall();
	                    break;
	                case 7:
	                	System.out.println("Enter the GPA:");
	                	Scanner gpa = new Scanner(System.in);
	                	double GPAchoice=gpa.nextDouble();
	                	studentWithGPA(GPAchoice);
	                    break;
	                
	                    
	                case 8:
	                	System.out.println("Enter the year:");
	                	Scanner yeargpa = new Scanner(System.in);
	                	int ychoicegpa=yeargpa.nextInt();
	                	System.out.println(highestGPA(ychoicegpa));
	                    break;
	                case 9:
	                	System.out.println(numberStudents());
	                    break;
	                case 10:
	                	System.out.println("Enter the year:");
	                	Scanner SNGPA = new Scanner(System.in);
	                	int SNGPAc=SNGPA.nextInt();
	                	numberStudents(SNGPAc);
	                    break;
	                    
	                case 11:
	                	 highestGpaYear();
	                    
	                
	                case 0:
	                	System.out.println("you have exited!");
	                	exit=true;
	                	saveToFile();
	                    break;
	                
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }}}
		 
	 public String[] highestGpaYear() { //from assighenemt
		 int year = 2000;
		 int index = 0;
		 String[] gpa_arr= new String[25];
		 for(int i=0;i<treetable.length;i++) {
			 Student s = highestGPA(year);
			 if(s!=null) {
			 double GPA = s.getGPA();
			 String record =  ""+ year +"-"+GPA;
			 gpa_arr[index] = record;
			 year++;
			 index++;
		 }}
		 
		
		return gpa_arr;
			 
		
		
		 
	 
	 
}}
	 
	 
	        

