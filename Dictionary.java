import java.util.LinkedList;

public class Dictionary implements DictionaryADT {
	
	
	//creating a hash table 
	private LinkedList<Record>[] hashTable; 
	int size; // initializing the size of the dictionary 
	private int numEntries=0;// Initializing the number of entries
	 
	
    // the Dictionary method is the constructor which initializes the size of the dictionary.
	// it then uses a for loop to iterate through the dictionary and point each index to a linked list node of type record. 
	public Dictionary(int size){
		this.size=size;
		hashTable= new LinkedList[size];
		for(int i=0; i< hashTable.length; i++){
			
			hashTable[i]= new LinkedList<Record>();
			
		}
	}
	
	
	
	// this method "insert" takes a pair object and inserts it into the hash table 
	public int insert(Record pair) throws DictionaryException {
		//getting the string from the given pair 
		String stringtoAdd = pair.getConfig();
		int indexToAdd = hashFunction(stringtoAdd);
		//initializing the resulting integer which is to be returned
		//int result=0; // method needs to return 0 if there were no collisions produced
		
		// if the index of the hash table where the pair is to be inserted is not empty (!null)...
		if (hashTable[indexToAdd] != null){
			
			// the for loop will see if the index of the hash table where the pair is to be inserted doesn't already have the same config entry 
			for (int i=0; i< hashTable[indexToAdd].size(); i++){
				// if pair.getConfig is already in the dictionary, an exception is thrown 
				if (hashTable[indexToAdd].get(i).getConfig().equals(stringtoAdd))
				{
					throw new DictionaryException(); 
				}
				// if this else is executed, that means there has been a collision, and separate chaining is used to handle to collision 
				else
				{
					hashTable[indexToAdd].addLast(pair); // the pair is added to a node pointed to by the index of the array which produced the collision
					numEntries++;
					return 1; // the result to return has to be 1 since a collision was produced
				}
			}
		}
		// if the index of the hash table where the pair is to be inserted is empty, then the pair is simply added to the hash table 
		 hashTable[indexToAdd].add(pair);
	     numEntries ++; 
		 return 0; 
	}
	
	
	
	
	
	// the remove method removes the entry given the config from the dictionary
	public void remove(String config) throws DictionaryException{
		int nodeToRemove=hashFunction(config);
		// if the entry exists in the dictionary, the entry will be removed using the .remove() linked list helper method
		if(get(config) !=-1){
			hashTable[nodeToRemove].remove();
		}
		// if the entry does not exist within the dictionary, an exception is thrown 
		else{ 
			throw new DictionaryException(); 
		}
	}  
		
	

	
	public int get(String config){
		int nodeToGet = hashFunction(config);// using the hash Function to get the corresponding key for the value we want to get 
		int counter=0;
		while (hashTable[nodeToGet] != null && counter<hashTable[nodeToGet].size()){
			
			String matchingString= hashTable[nodeToGet].get(counter).getConfig();
			
			if(matchingString.equals(config)){
				return hashTable[nodeToGet].get(counter).getScore();
			}
			counter++;
				
		}
		//returns -1 if the config is not in the dictionary
		return -1;
	}
	
	

	
	
	public int numElements(){
		return numEntries; 
	}
	
	
	// private hash function returns an appropriate index to place config within the hash table 
	private int hashFunction(String config){
		int M=size;
		int x= 300;// Multiplier 
		int index = (int) config.charAt(config.length()-1);
		int k= config.length()-2;
		
		for(int i= k; i>=0; i--){
			index=(index*x+(int)config.charAt(i))%M;
			index=(int) (index+Math.pow(x, i));
		}
		return index; 
	}

}
