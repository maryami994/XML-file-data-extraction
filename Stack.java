package XmlExtraction;

public class Stack extends LinkedList {
	int counter;
	
	Stack (){
		this.counter=-1;
	}
	
	public boolean isEmpty() {
		return(counter==-1);
	}
	
	public void push(Object info) {	
		addToHead((info));
		counter++;
	}
	
	public Object pop() {
		if(isEmpty()) {
			return -1;
		}else { 
			counter--;
			return deleteFromHead();
		}
	}
	public int Size() {
		return counter+1;
	}
	public Object peek() {
		if (isEmpty()) { 
			System.out.println("Stack is Empty"); 
			return (char) -1;  
		}
		 else { 
		 Object tmp = deleteFromHead(); 
		 addToHead(tmp); 
		 return tmp;
		 }
	}

}