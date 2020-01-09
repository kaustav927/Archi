public class nk_TicTacToe {
	
	private int boardSize;
	private int max_levels; 
	private int inline;
	private char [][] gameBoard;
	

	// the constructor creates the grid with empty space characters based on the boardSize parameter
	// it also initializes max_levels
	public nk_TicTacToe (int boardSize, int inline, int max_levels){
		this.boardSize= boardSize;
		this.max_levels=max_levels;
		gameBoard =new char [boardSize][boardSize];
	
		// for loop fills the squares with all space characters as the default 
		for (int i=0; i<boardSize; i++){
			for (int j=0; j<boardSize; j++){
				gameBoard [i][j]=' ';
			}
		}
	}

	// the Dictionary method constructs the hash table dictionary with a specified size
	public Dictionary createDictionary()
	{
		Dictionary dictionary = new Dictionary(7237); //the size must be a prime number so i chose 7237
		return dictionary; 	
	}

	// this method checks whether the string representing the gameBoard is in the dictionary 
	// if it is, the method returns the associated score of the string using the get() method from Dictionary
	public int repeatedConfig(Dictionary configurations){
		
		// Declaring the score to be returned 
		int score;
		// initializing the board configuration string which is to be filled 
		String boardConfig="";
		// using 2 nested for loops to make a string that represents the content of game board 
		for (int i=0; i<boardSize; i++){
			for(int j=0; j<boardSize; j++){
				boardConfig +=gameBoard[i][j];
			}
		}
		
		// the .get() method will return the accosiated score if the board configuration string is in the dictionary
		// if it is not in the dictionary, get() will return -1 to score 
 		score= (int) configurations.get(boardConfig);
		return score;
	}
	
	
	public void insertConfig(Dictionary configurations, int score){
		
		// initializing the board configuration string which is to be filled 
		String boardConfig="";
		// using 2 nested for loops to make a string that represents the content of game board 
		for (int i=0; i<boardSize; i++){
			for(int j=0; j<boardSize; j++){
				boardConfig +=gameBoard[i][j];
			}
		}
		// inserting the boardConfig into the the dictionary as a record object 
		Record newPair = new Record(boardConfig, score);
		configurations.insert(newPair);
		
	}
	
	// stores the symbol into the game board 
	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col]=symbol;
	}
	
	
	
	public boolean squareIsEmpty (int row, int col){
		// if the given position on game board is filled with a space character ' ', then it will return true, and false otherwise. 
		if (gameBoard[row][col]==' '){
			return true; 
		}
		else {
			return false;
		}
	}
	
	

	public boolean wins(char symbol){
		
		//initializing variables
		boolean result=false;
		
		//int verticalCount=0;
		
		
		
		// 2 nested for loops iterate through each entry in the game board
		for(int i=0; i<boardSize; i++){
			for (int j=0; j<boardSize; j++){
				int horizontalCount=0;
				// first while loop tests to see if a particular cell has the same symbols above and bellow it  
				while(i<inline && (i+horizontalCount)<boardSize){
					if (gameBoard[i+horizontalCount][j]==symbol){
						horizontalCount++; // this variables stores the number of symbols in a row 
					}
					if(horizontalCount == boardSize){
						result= true; // if the number of symbols in a row is equal to the board size, then one of the players won
					}
					//break;// while loop must be broken so that the for loops outside can iterate through the contents of the while
				}
				result= false;
				
				
				int verticalCount=0;
				// second while loop tests to see if a particular cell has the same symbols to its right or left  
				while(j<boardSize && (j+verticalCount)<boardSize){
					if (gameBoard[i][j+verticalCount]==symbol){
						verticalCount++;
					}
					if(verticalCount == boardSize){
						result= true; 
					}
					//break;
				}
				result= false; 
				
				int diag_LR_Count=0;
				// third while loop tests to see if a particular cell has the same symbols from its top left to bottom right 
				while((i<inline) && (j<inline)&& (diag_LR_Count+j)<boardSize && (diag_LR_Count+i)<boardSize){
					if (gameBoard[i+diag_LR_Count][j+diag_LR_Count]==symbol){
						diag_LR_Count++;
					}
					if (diag_LR_Count==boardSize){
						result= true; 
					}
					break;
				}
				result= false;
				
				
				
				int diag_RL_Count=0;
				// fourth while loop tests to see if a particular cell has the same symbols from its top right to bottom left
				while((i<inline) && (j<inline) && (j-diag_RL_Count)>=0 && (diag_RL_Count+i)<boardSize){
					if(gameBoard[i-diag_RL_Count][j+diag_RL_Count]==symbol){
						diag_RL_Count++;
					}
					if(diag_RL_Count==boardSize){
						result= true; 
					}
					//break;
				}
				result=false; 
				
				
				
				
			}
		}
		return result;
	}
		
	
	//returns true if the game board has no empty positions left AND no player has won
	public boolean isDraw(){
		int numSquaresFull =0;
		int numSquaresIterated=0;
		
		// nested for loops used to iterate through each cell of the game board
		for(int i=0; i<boardSize; i++){
			for (int j=0; j<boardSize; j++){
				// if a particular cell is not empty, the variable numSquaresFull will increase by 1
				if(squareIsEmpty(i,j)==false){
					numSquaresFull++;
				}
				// every time the inner for loop ends, 1 is added to the number of squares iterated
				numSquaresIterated++; 
				}
			}
		// if all of the squares in the game board are full, method will return true
		if(numSquaresIterated==numSquaresFull){
			return true;
		}
		else{ 
			return false; 
		}
		
	}
	
	
	
	public int evalBoard(){
		// if the this.wins returns 'O', the computer wins and the method returns 3
		if(this.wins('O')==true){
			return 3;
		}
		// if the this.wins returns 'X', the human wins and the method returns O
		else if( this.wins('X')==true){
			return 0;
		}
		// if the this.isDraw() returns true, the computer wins and the method returns 2
		else if(this.isDraw()==true){
			return 2;
		}
		// the method returns 1 if the game is still undecided (there are still empty squares)
		else {
			return 1; 
		}
	}
}













