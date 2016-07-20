package volcano;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main implements ActionListener{
	
	//variables for populateBoard
	int boardSize = 12; //change this to change the height and width dimensions of the board
	String[][] boardArray = new String[boardSize][boardSize];
	int i;
	int j;
	String monkey; //as opposed to a Sock Monkey.
	int sudoku;
	
	//variables for playFrame
	JFrame frame;
	JButton[][] btnArray = new JButton[boardSize][boardSize];
	JPanel playArea;
	JPanel scoreBoard;
	JLabel scoreLabel;
	int btnSize = 50;   //change this to change the size of all buttons
	ImageIcon blue = new ImageIcon("blusqr.png");
	ImageIcon red = new ImageIcon("redsqr.png");
	ImageIcon black = new ImageIcon("blksqr.png");
	ImageIcon thedoge;
	
	//variables for erupt
	Point bean;
	int ArrayLocX;
	int ArrayLocY;
	boolean stop;
	boolean pinery; //it's like binary but smells faintly of pine.
	boolean brinary; //it's like binary but smells faintly of brine.
	int r;
	int l;
	int u;
	int d;
	String potato;
	
	//scoring variables
	int blueScore;
	int redScore;
	String blueString;
	String redString;
	int genScore;
	
	//so-called AI variables
	int bestColumn;
	int bestRow;
	int secondBestRow;
	int counteri;
	int counter;
	int counter1;
	int counter2;
	int timeOut;
	
	public Main() {
		
		pinery = true;
		brinary = true;
		thedoge = blue;
		potato = "2";
		blueScore = 0;
		redScore = 0;
		
		populateBoardSudoku();
		populateBoardSudoku();
		populateBoardSudoku();
		
		playFrame();
		
		
		
	}
	
	public void populateBoardSudoku() {
	
		sudoku = 0;
		Integer[] sudArr = new Integer[boardSize];
		
		while(sudoku<boardSize){
		sudArr[sudoku] = sudoku;
		sudoku++;
		}
		
		Collections.shuffle(Arrays.asList(sudArr));
		
		i = 0;
		
		while(i<boardSize){
			
			j = 0;
			
			while(j<boardSize){
			
				if(i == sudArr[j]){
			boardArray[i][j] = "1";}
				else{
					if(boardArray[i][j] != "1"){
						boardArray[i][j] = "0";
						}
					}
			System.out.print(boardArray[i][j]);
			j++;
			
			}
			
		System.out.print("\n");
			
		i++;
		}
		
	}

	private void playFrame(){
		
		frame = new JFrame("Click the buttons! Blue goes first!");
		frame.setSize(boardSize*(btnSize + 10), boardSize*(btnSize + 7) + 40);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// The program should end when the window is closed		
		frame.setLayout(new FlowLayout()); // Set the window's layout manager
		
		playArea = new JPanel();
		playArea.setPreferredSize(new Dimension(boardSize*(btnSize + 5), boardSize*(btnSize + 5)));
		
		setUpBoard();
		
		scoreBoard = new JPanel();
		scoreBoard.setPreferredSize(new Dimension(700, 20));
		scoreLabel = new JLabel();
		scoreLabel.setText("Blue: 0  Red: 0");
		scoreBoard.add(scoreLabel);

		frame.add(scoreBoard);
		frame.add(playArea);
		frame.setVisible(true);
		
		
	}
	
	private void setUpBoard(){
		
	j = 0;
		
		while(j<boardSize){
			
			i = 0;
			
			while(i<boardSize){
				
				btnArray[i][j] = new JButton();				
				btnArray[i][j].addActionListener(this);
				btnArray[i][j].setPreferredSize(new Dimension(btnSize, btnSize));
				//System.out.println(i + ", " + j + ", " + boardArray[i][j]);
				if(boardArray[i][j]=="1"){btnArray[i][j].setIcon(black);}
				playArea.add(btnArray[i][j]);
				i++;
				
			}
			j++;
		}
		
	}
	
	private void erupt(){
		
		//System.out.println(pinery);
		incrementScore();
	
		if(pinery == true){
			btnArray[ArrayLocX][ArrayLocY].setIcon(thedoge);
			boardArray[ArrayLocX][ArrayLocY]="2";
			flow();			
			thedoge = red;
			potato = "3";
			AINotSucky();
		}
		else{
			btnArray[ArrayLocX][ArrayLocY].setIcon(thedoge);
			boardArray[ArrayLocX][ArrayLocY]="3";
			flow();
			pinery = true;
			thedoge = blue;
			potato = "2";
			
			
		}
		
		blueString = Integer.toString(blueScore);
		redString = Integer.toString(redScore);
		scoreLabel.setText("Blue: " + blueString + "   Red: " + redString);
	}
	
	private void incrementScore() {
	
		if(pinery==true&&brinary==true){
			blueScore++;
		}
		else{
			redScore++;
		}
		
		
	}

	private void flow() {
				
		r=1;
		l=1;
		u=1;
		d=1;
		stop = false;
		
		while(ArrayLocX+r > 0 && ArrayLocX+r < boardSize && stop == false){
			if(boardArray[ArrayLocX+r][ArrayLocY]=="0"){
				btnArray[ArrayLocX+r][ArrayLocY].setIcon(thedoge);
				incrementScore();
				boardArray[ArrayLocX+r][ArrayLocY]="potato";
				r++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocX-l >= 0 && ArrayLocX-l < boardSize && stop == false){
			if(boardArray[ArrayLocX-l][ArrayLocY]=="0"){
				btnArray[ArrayLocX-l][ArrayLocY].setIcon(thedoge);
				incrementScore();
				boardArray[ArrayLocX-l][ArrayLocY]="potato";
				l++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocY-u >= 0 && ArrayLocY-u < boardSize && stop == false){
			if(boardArray[ArrayLocX][ArrayLocY-u]=="0"){
				btnArray[ArrayLocX][ArrayLocY-u].setIcon(thedoge);
				incrementScore();
				boardArray[ArrayLocX][ArrayLocY-u]="potato";
				u++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocY+d > 0 && ArrayLocY+d < boardSize && stop == false){
			if(boardArray[ArrayLocX][ArrayLocY+d]=="0"){
				btnArray[ArrayLocX][ArrayLocY+d].setIcon(thedoge);
				incrementScore();
				boardArray[ArrayLocX][ArrayLocY+d]="potato";
				d++;}
				else{stop=true;}
		}
		
		
		
	}

	private void AINotSucky(){ //maybe this one won't suck so much?
		//rewrite flow such that it and scoring are independent. Write score(). Iteratively check every available space on the grid and pick the one that is worth the most points. >:)
		
	counter=1;
	i = 0;
	
	
		while(i<boardSize){
			
			j = 0;
			
			while(j<boardSize){
				
				ArrayLocX = i;
				ArrayLocY = j;
				
				
				if(boardArray[i][j]=="0"){
					
					score();
					
					if(genScore>=counter){counter = genScore; bestColumn=i; bestRow=j;   }
				}
				
				j++;
			
			}
			
			i++;
		
		}
		System.out.println(counter);
		if(counter>1){pinery = false;}
		if(counter==1){brinary = false;}
		ArrayLocX=bestColumn;
		ArrayLocY=bestRow;
		
		
		
		
		if(boardArray[ArrayLocX][ArrayLocY]=="0"){erupt(); System.out.printf("red clicks at %d, %d\n", ArrayLocX, ArrayLocY);}
		
		
		
	}
	
	private void score() {
		
		r=1;
		l=1;
		u=1;
		d=1;
		stop = false;
		genScore=1;
		
		while(ArrayLocX+r > 0 && ArrayLocX+r < boardSize && stop == false){
			if(boardArray[ArrayLocX+r][ArrayLocY]=="0"){
				genScore++;
				r++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocX-l >= 0 && ArrayLocX-l < boardSize && stop == false){
			if(boardArray[ArrayLocX-l][ArrayLocY]=="0"){
				genScore++;
				l++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocY-u >= 0 && ArrayLocY-u < boardSize && stop == false){
			if(boardArray[ArrayLocX][ArrayLocY-u]=="0"){
				genScore++;
				u++;}
			else{stop=true;}
		}
		
		
		stop = false;
		
		while(ArrayLocY+d > 0 && ArrayLocY+d < boardSize && stop == false){
			if(boardArray[ArrayLocX][ArrayLocY+d]=="0"){
				genScore++;
				d++;}
				else{stop=true;}
		}
		
		
		
		}
	
	public static void main(String[] args) {
	
	new Main();

	}

	public void actionPerformed(ActionEvent e) {
		
		bean = ((JButton) e.getSource()).getLocation();
		
		ArrayLocX = (int)((bean.getX()/(btnSize + 5)));
		ArrayLocY = (int)((bean.getY()/(btnSize + 5)));
		
		if(boardArray[ArrayLocX][ArrayLocY]=="0"){erupt();}
		
		
				
	}

}