package XmlExtraction;

class Node{
	Object info;
	Node next;
	
	Node(Object info){
		this.info=info;
		this.next=null;
	}
	 Node(Object info,Node node){
		 this.info=info;
		 this.next=node;
	 }
}
public class LinkedList {
	Node head;
	Node tail;
	 
	LinkedList(){
		head=tail=null;
	}
	
	public boolean isEmpty() {
		return (head==null && tail==null);
	}
	 
	public void addToHead(Object info) {
		if(isEmpty()) {
			head=tail=new Node(info);
		}else {
			head=new Node(info,head);
		}	
	}
	
	public Object deleteFromHead() {
		if(head==null && tail==null) {
            return 0;
	   }else {
		   Object el=head.info;
		   if(head==tail) {
			   head=tail=null;
		   }else {
			   head=head.next;
		   }
		   return el;
	   }
	}
}