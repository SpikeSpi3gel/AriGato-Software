//Driver for HSD

import java.io.*;
import java.util.*;

public class HighSchoolDungeon{
    
    //Instance Vars
    private Character ryder;
    private Prep _prep;
    private Jock _jock;
    private Nerd _nerd;
    private int gameStarted; //takes 1 or 0
    private boolean gameOver;

    private InputStreamReader isr;
    private BufferedReader in;
    private IOException win= new IOException();

    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public HighSchoolDungeon() {
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    /*=============================================
      void newGame() -- facilitates info gathering to begin a new game
      pre:  
      post: according to user input, modifies instance var for difficulty 
      and instantiates a Warrior
      =============================================*/
    public void newGame() {
	String s="";
	String name = "";
	s += "High School Dungeon\n";
	s += "An everyday high school dungeon crawler\n";
	s += "An Ari-Gato Software Production\n";

	s += "\n";
	s += "Enter 1 to continue..."; //just to test readint capability; remove later
	System.out.println(s);
	while (!(gameStarted==1)){
		try {
		    gameStarted = Integer.parseInt(in.readLine());
		    if (gameStarted !=1){
			throw win;
		    }
		}
		catch ( IOException | NumberFormatException  e ) {
		    System.out.println("That's not right");
		    System.out.println("Enter 1 to continue...");
		}
	}
	
	/* Will implement if time allows 
	s += "\nChoose your difficulty: \n";
	s += "\t1: Easy\n";
	s += "\t2: Not so easy\n";
	s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
	s += "Selection: ";
	System.out.print( s );

	try { // IMPORTANT
	    difficulty = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }
	*/

	s = "Congratulations on getting into a prestigious NYC public high school!\n"; //add flavor text later
	s += "Blah, blah, blah, exposition and introductions\n";
	s += "'Alright, everyone.' the teacher says. 'Introduce yourself!'\n";
	s += "What's your name?\n";
	s += "Name: ";
	System.out.print( s );
	
	try {
	    name = in.readLine();
	}
	catch ( IOException e ) { }
	
	//instantiate the player's character's shelves
	int charType=0;	

	//add flavor text here
	s = "What were you renowned for in middle school?\n";
	s += "\t1: I was the top Jock. I was the master of the school yard.\n";
	s += "\n\t2: I was the top Nerd. I was the master of the library.\n";
	s += "\n\t3: I was the top Prep. I was the master of the cafeteria.\n";
	System.out.println( s );

	while ((charType!=1)&&(charType!=2)&&(charType!=3)){
	    try {
		charType = Integer.parseInt( in.readLine() );
		if (!((charType==1)||(charType==2)||(charType==3))){
		    throw win;
		}  
	    }
	    catch ( IOException | NumberFormatException e ) {
		System.out.println("That's not right,try again");
	    }
	}
	
	s = "\nYou are: ";
	
	if (charType==1){
	    ryder = new Jock(name); //placeholder Object, remind Shanjeed to have constructor in Character that takes a name
	    // s+=ryder; //will insert name later
	}

	if (charType==2){
	    ryder = new Nerd(name);
	    //s+="Nerd";
	}

	if (charType==3){
	    ryder = new Prep(name);
	    //s+="Prep";
	}

	s+=ryder;
	System.out.println(s+"\n");

	store();
	ryder.printInv();
	
    }//end newGame()

    
    /*==============================================================================================================================================
      void store
      launches the store interface, should use everytime we encounter a store
      ==============================================================================================================================================*/
    public void store() {
	String y="";
	y += "Welcome to the store!\n";
	y += "What can I do for you, intrepid high school student?\n";
	int purchase = 0;
	y += "Something you'd like to buy?\n";
	y += "You currently have " + ryder.getCash() + " cash.\n\n";	
	y += "1: HP Potion. Recovers 20% of your health. Costs 100 cash.\n";
	y += "2: MP Potion. Recovers 20% of your mana. Costs 100 cash.\n";
	y += "3: I'm done with the store.\n";
	System.out.println( y );	
	System.out.print("Enter 1, 2, or 3...\n");
	// try buying something

	while ((purchase!=1)&&(purchase!=2)&&(purchase!=3)){
	    try {
		purchase = Integer.parseInt( in.readLine() );
		if (!((purchase==1)||(purchase==2)||(purchase==3))){
		    throw win;
		}
		//if want to buy HP Potion
		if (purchase==1){
		    if (ryder.getCash() >= 100)
			{
			    System.out.println("You bought a HP Potion! Here you go.");
			    ryder.setCash(-100);
			    System.out.print("Your cash reserve is now "); System.out.println(ryder.getCash());
			    ryder.addHPPotion();
			    store();
			}
		    else
			{
			    System.out.println("You don't have enough money, you twerp. You trying to chisel me?\n");
			    store();
			}
		}
		//if want to buy MP Potion
		if (purchase==2){
		    if (ryder.getCash() >= 100)
			{
			    System.out.println("You bought a MP Potion! Here you go.");
			    ryder.setCash(-100);
			    System.out.print("Your cash reserve is now "); System.out.println(ryder.getCash());			
			    ryder.addMPPotion();
			    store();			
			}
		    else
			{
			    System.out.println("You don't have enough money, you twerp. You trying to chisel me?\n");
			    store();
			}
		}
		//if want to leave
		if (purchase==3) {
		    exitStore();
		}
		
	    }
	    catch ( IOException | NumberFormatException e ) {
		System.out.println("Thats not right\n");
		store();
		break;
		}
	}
    } //end store()

    public void exitStore(){
	System.out.println("Are you sure you want to exit the store? Y/N");
	String exit="";
	while ((exit!="n")&&(exit!="y")&&(exit!="N")&&(exit!="Y")){
	    try {
		exit = in.readLine();
		if (!(exit.equals("n")||exit.equals("N")||exit.equals("y")||exit.equals("Y"))){
		    throw win;
		}
		if (exit.equals("N")|| exit.equals("n")){
		    store();
		}
		if (exit.equals("y") || exit.equals("Y")){
		    break;
		}
	    }
	    catch (IOException e) {
		//System.out.println("here1");
		System.out.println("I'm sorry, I didn't get that. Enter y for yes or n for no.");	    
	    }
	    break;
	}
    }
   
    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior pat has been initialized
      post: Returns true if player wins (monster dies).
      Returns false if monster wins (player dies).
      =============================================*/
    /*
    public boolean playTurn() {
	
       	int i = 1;
	int d1, d2;
	
	int difficulty=1;
	
	if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "\nNothing to see here. Move along!" );
	else {
	    System.out.println( "\nLo, yonder monster approacheth!" );
	    
	    smaug = new Monster();
	    
	    while( smaug.isAlive() && pat.isAlive() ) {
		
		// Give user the option of using a special attack:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "\nDo you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }
		
		if ( i == 2 )
		    pat.specialize();
		else
		    pat.normalize();
		
		d1 = pat.attack( smaug );
		d2 = smaug.attack( pat );
		
		System.out.println( "\n" + pat.getName() + " dealt " + d1 +
				    " points of damage.");
		
		System.out.println( "\n" + "Ye Olde Monster smacked " + pat.getName() +
				    " for " + d2 + " points of damage.");
	    }//end while
	    
	    //option 1: you & the monster perish
	    if ( !smaug.isAlive() && !pat.isAlive() ) {
		System.out.println( "'Twas an epic battle, to be sure... " + 
				    "You cut ye olde monster down, but " +
				    "with its dying breath ye olde monster. " +
				    "laid a fatal blow upon thy skull." );
		return false;
	    }
	    //option 2: you slay the beast
	    else if ( !smaug.isAlive() ) {
		System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
		return true;
						}
	    //option 3: the beast slays you
	    else if ( !pat.isAlive() ) {
		System.out.println( "Ye olde self hath expired. You got dead." );
		return false;
	    }
	}//end else
	
	return true;
    }//end playTurn()
    */

    
    public static void main(String[] args) {      	
	//loading...
	HighSchoolDungeon game = new HighSchoolDungeon();
	
	int encounters = 0;
	/*
	while( encounters < MAX_ENCOUNTERS ) {
	    if ( !game.playTurn() )
		break;
	    encounters++;
	    System.out.println();
	}
	*/
	System.out.println( "You graduated!" );
    }    
}


