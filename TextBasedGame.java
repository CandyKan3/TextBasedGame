package textbasedgame;
import java.util.*;
/*
 * Developer: Bryce Kane
 * Version 1.0 
 * 12/17/2018
 * 
 * 
 * 
 * This program is made to be a simple text based game 
 * Will display a welcome message then ask the user how big the world they would like to explore would be
 * generates enemies at random spots among the world and as the user moves through the world he can encounter various enemies
 * Game will have pokemon style combat, with randomized hits as well as health potions
 * oooo and you can pick up health potions randomly among the map
 * for killing an enemy you get gold 
 * there is a shop option too where you can select weapons and armor that act as damage multipliers 
 * At the end of the map there will be a final boss. 
 * Lets obtain the grain
 */
public class TextBasedGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Kworld!");
		System.out.println("Please enter the hight of your world");
		int hight= input.nextInt();
		System.out.println("Please enter the width of your world");
		int width = input.nextInt();
		String[][] world = new String[hight][width];
		String[][] enemies = new String[hight][width];
		generateWorld(world);
		generateEnemies(enemies);
		int userchoice = 0; 
		int health = 100;
		//going to have to store potions as an array? I need to be able to return health and the current number of potions. 
		int[] potions = {0,0,1,0}; 
		int armor = 1;
		int weapon =1;
		int gold = 0;
		
		while(userchoice!= -1) {
			System.out.println("Press 1 to explore in the world \n Press 2 to display inventory \n Press 3 to go to the store \n Press -1 to exit!");
			userchoice = input.nextInt(); 
			switch(userchoice) {
			case 1: {
				exploreWorld(world);
			}
			break;
			case 2:{
				displayInventory(health, potions, weapon, armor, gold);
				
			}
			break;
			case 3:{
				
			}
			break;
			}
		} 
	}
	//This method generates the movable world for a user and puts them on a random point on the map
	public static void generateWorld(String[][] world) {
		Scanner input = new Scanner(System.in);
	
		for(int i =0; i < world.length; i++) {
			for(int j =0; j< world[i].length; j++) {
				world[i][j] = "*";
				//System.out.println(i + " "+ j);
			}
		}

		int x = (int)(Math.random()*world.length);
		int y = (int)(Math.random()*world[0].length);
		world[x][y] = "X";
	}
	// add , String[][] enemyloc, String[][] healthloc
	public static void exploreWorld(String[][] world) {
		Scanner input = new Scanner(System.in);
		int userx = 0;
		int usery =0;
		for(int i =0; i< world.length; i++) {
			for(int j = 0; j< world[i].length; j++) {
				if(world[i][j] == "X") {
					 userx = i;
					 usery = j;
				}
			}
		}
		printWorld(world);
		String y = "";
		while(!y.equals("E")) {
			printMoveMenu();
			printWorld(world);
			y = input.next().toUpperCase();
			switch(y) {
			case "W":{
				if(canmove(userx, usery, y, world )) {
					System.out.println("MovingUp");
					world[userx][usery] = "*";
					world[userx-1][usery] = "X";
					userx = userx-1;
				}
			}
			break;
			case "A":{
				if(canmove(userx, usery, y, world )) {
					System.out.println("Moving Left");
					world[userx][usery] = "*";
					world[userx][usery-1] = "X";
					usery = usery-1;
				}
				
			}
			break;
			case "S": {
				if(canmove(userx, usery, y, world )) {
					System.out.println("Moving Down");
					world[userx][usery] = "*";
					world[userx+1][usery] = "X";
					userx= userx+1;
				}
				
			}
			break; 
			case "D": {
				if(canmove(userx, usery, y, world )) {
					System.out.println("Moving Right");
				world[userx][usery] = "*";
				world[userx][usery+1] = "X";
				usery=usery+1;
			}
				
			}
		
	}
		}

}
	public static boolean canmove(int row, int colum, String direct, String[][] world) {
		switch(direct) {
		case "W":{
			boolean rowt = false;
			boolean columt = false;
			row--;
			if(row>=0 && row<world.length) {
				if(colum>=0 && colum<world[row].length) {
					return true;
				}
			}
		}
		break;
		case "A":{
			boolean rowt = false;
			boolean columt = false;
			colum--;
			if(row>=0 && row<world.length) {
				if(colum>=0 && colum<world[row].length) {
					return true;
				}
			}
			
		}
		break;
		case "S": {
			boolean rowt = false;
			boolean columt = false;
			row++;
			if(row>=0 && row<world.length) {
				if(colum>=0 && colum<world[row].length) {
					return true;
				}
			}
		}
		break; 
		case "D": {
			boolean rowt = false;
			boolean columt = false;
			colum++;
			if(row>=0 && row<world.length) {
				if(colum>=0 && colum<world[row].length) {
					return true;
				}
			}
		}
		break;
		}
		return false;
	}

	public static void printWorld(String[][] world) {
		for(int i =0; i< world.length; i++) {
			for(int j = 0; j<world[0].length; j++){
				System.out.print(world[i][j]);
			}
			System.out.println("");
		}
	}
	public static void printMoveMenu() {
		System.out.println("Press W to move up \n Press A to move left \n Press S to move down \n Press D to move right \n Press E to exit");
	}
	public static int displayInventory(int health, int[] potions, int weapon, int armor, int gold) {
		Scanner input = new Scanner(System.in);
		System.out.println("You have "+ health + " HP!");
		System.out.print("Your potion inventory is {");
		for(int i =0; i< potions.length; i++) {
			System.out.print(potions[i]);
		}
		System.out.println("}");
		System.out.println("You have "+ weapon + " as a damage multiplyer");
		System.out.println("You have "+ armor + " as a damage resist!");
		System.out.println("You have "+ gold + " gold!");
		System.out.println("Press 1 to take a potion \n Press 2 to exit");
		int x = input.nextInt();
		switch(x) {
		case 1:{
			boolean haspotion = false;
			for(int i = potions.length-1; i>=0; i--) {
				if(potions[i] ==1) {
					haspotion = true;
					System.out.println("Taking potion...");
					health+=25;
					if(health > 100)
					{
						health = 100;
					}
					potions[i] = 0;
					break;
				}
			}
			if(!haspotion) {
				System.out.println("Error no potion was found!");
			}
			
			return health;
		}
	
		case 2:{
			return health;
		}
	
		}
		return health;
	
		
	}
	public static void generateEnemies(String[][] enemy, String[][] world) {
		int x =0;
		int y = 0;
		
		for(int i =0; i< 5; i++) {
		x = (int)(Math.random()*world.length);
		y =(int)(Math.random()*world[0].length);
		enemy[x][y] = "Enemy";
		}
	}
	
}
