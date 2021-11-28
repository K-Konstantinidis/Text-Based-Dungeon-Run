/***************************************************************************
	Copyright 2021 Konstantinidis Konstantinos

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
***************************************************************************/
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	Scanner keyboard = new Scanner(System.in);
	private	int character; //User character
	String[] monsters = {"Skeleton", "Zombie", "Assasin", "Ancient Dragon"};
	Random rand = new Random();
	private String enemy; //Chosen enemy
	private int potions = 0; //Starting potions
	private int hp = 100; //Starting hp
	private int enemyhp = 75; //Max enemy hp
	private int choice;
	private int dmg = 50; //Max users damage
	int enemyshp; //Enemy hp if it isn't an Ancient Dragon
	int score = 0;
	
	public Game(){
		System.out.println("========================================");
		System.out.println("\t Welcome to the Dungeon");
		System.out.println("========================================");
		System.out.println("Choose your Character: ");
		
		System.out.print("\t 1. Warrior");
		System.out.println(" (As a Warrior you have more defence so there is a chance to take less damage)");
		System.out.print("\t 2. Archer");
		System.out.println("  (As an Archer you have more attack so there is a chance to deal more damage)");
		System.out.print("\t 3. Mage");
		System.out.println("    (As a Mage you are more lucky so there is a bigger chance to find healing potions)");
		
		System.out.print("\nType the number of your Character: ");
		character = keyboard.nextInt();

		while(character != 1 && character != 2 && character != 3) {
			System.out.println("\nYour choice must be either 1, 2 or 3 \n\t (1) => Warrior \n\t (2) => Archer \n\t (3) => Mage");
			System.out.print("Type the number of your Character: ");
			character = keyboard.nextInt();
		}
		
		//Character choice
		if(character == 1) {
			System.out.println("\n========================================================");
			System.out.println("\tYou are a Warrior \n");
			System.out.println("\tHere is a little gift: 3 healing potions \n");
			potions += 3;
			System.out.println("========================================================");
		}
		else if(character == 2) {
			System.out.println("\n========================================================");
			System.out.println("\tYou are an Archer \n");
			System.out.println("\tHere is a little gift: 3 healing potions \n");
			potions += 3;
			System.out.println("========================================================");
		}
		else if(character == 3) {
			System.out.println("\n========================================================");
			System.out.println("\tYou are a Mage \n");
			System.out.println("\tHere is a little gift: 3 healing potions \n");
			potions += 3;
			System.out.println("========================================================");
		}
		
		while(true) { //While game is running
			int randEnemy = rand.nextInt(8);
				enemyhp = 75; //Make max enemy hp 75 every time
			if(randEnemy == 2) {
				enemy = "Ancient Dragon";
			}
			else {
				enemy = monsters[rand.nextInt(monsters.length)];
			}
			
			while(randEnemy != 2 && enemy == "Ancient Dragon") { //If enemy is ancient dragon but randEnemy isn't, then change the enemy to something else
				enemy = monsters[rand.nextInt(monsters.length)];
			}
			
			if(enemy == "Zombie" || enemy == "Skeleton") {
				System.out.println("\tA " + enemy + " has appeared! \n");
			}
			else {
				System.out.println("\tAn " + enemy + " has appeared! \n");
			}
			
			enemyshp = rand.nextInt(enemyhp - 25); //Give the enemy random hp
			while(true) {
				if(enemyshp < 20) { //Make the random enemy hp between 20-50
					enemyshp = rand.nextInt(enemyhp - 25);
				}
				else{
					break;
				}
			}
			
			while(true) {
				if(enemy == "Ancient Dragon") { //If enemy is Ancient Dragon, then it will have 75hp
					System.out.println("\tYour HP = " + hp);
					System.out.println("\t" + enemy + "'s HP = " + enemyhp + "\n");
				}
				else { //Else enemyshp is random
					System.out.println("\tYour HP = " + hp);
					System.out.println("\t" + enemy + "'s HP = " + enemyshp + "\n");
				}
				
				System.out.println("\tWhat would you like to do? \n");
				
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink health potion");
				System.out.println("\t3. Run");
				
				choice = keyboard.nextInt();
				
				while(choice != 1 && choice != 2 && choice != 3) {
					System.out.println("\nYour choice must be either 1, 2 or 3 \n\t (1) => Attack \n\t (2) => Drink health potion \n\t (3) => Run");
					System.out.print("Type the number of your choice: ");
					choice = keyboard.nextInt();
				}
				
				int randmg = rand.nextInt(dmg); //Get random damage to user attack
				
				if(choice == 1) { //if user chose to attack
					if(character == 2) { //if user character is an archer give the random damage a chance to become higher
						randmg = rand.nextInt(dmg + 10);
					}
					System.out.println("\n\t>You strike the " + enemy + " for " + randmg + " damage \n");
					if(enemy == "Ancient Dragon") {
						enemyhp -= randmg;
						randmg = rand.nextInt(dmg - 10);
						if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
							randmg = rand.nextInt(dmg - 20);
						}
						if(enemyhp > 0) { //if enemy didn't die by the attack then user will take some retaliation damage
							System.out.println("\t>You receive " + randmg + " in retaliation \n");
							hp -= randmg;
						}
					}
					else {
						enemyshp -= randmg;
						randmg = rand.nextInt(dmg - 20);
						if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
							randmg = rand.nextInt(dmg - 25);
						}
						if(enemyshp > 0) { //if enemy didn't die by the attack then user will take some retaliation damage
							System.out.println("\t>You receive " + randmg + " in retaliation \n");
							hp -= randmg;
						}
					}
					
					if(enemyhp <= 0 || enemyshp <= 0) { //If enemy died
						System.out.println("========================================================");
						System.out.println("\t" + enemy + " was defeated!");
						System.out.println("\tYour HP = " + hp);
						System.out.println("========================================================");
						//Score points for the killed enemy
						if(enemy =="Skeleton") {
							score += 10;
						}
						else if(enemy == "Zombie") {
							score += 15;
						}
						else if(enemy == "Assasin") {
							score += 20;
						}
						else{
							score += 50;
						}
						
						int chance = rand.nextInt(8); //Get a 1/8 chance to find a potion
						if(enemy == "Ancient Dragon") { //If the user killed an Ancient dragon then they automatically get 2 potions
							System.out.println("\tAfter defeating the dragon you found 2 healing potions!");
							potions += 2;
							System.out.println("\tYour potion count is: " + potions);
							System.out.println("========================================================");
						}
						else if(chance == 2) { //Else they have a 1/8 chance to find one
							System.out.println("\tYou got lucky and found a healing potion!");
							potions += 1;
							System.out.println("\tYour potion count is: " + potions);
							System.out.println("========================================================");
						}
						else if(character == 3) { //If user character is a Mage the they have a 2/8 chance to find one
							if(chance == 3) {
								System.out.println("\tYou got lucky and found a healing potion!");
								potions += 1;
								System.out.println("\tYour potion count is: " + potions);
								System.out.println("========================================================");
							}
						}
						break;
					}
				}
				else if(choice == 2) { //If user chose to drink a potion
					if(potions > 0) { //If they have more than 0 potions then they can drink one 
						System.out.println("\tYou drank a healing potion healing you for 45 health\n");
						hp += 45;
						potions -= 1;
						System.out.println("========================================================");
						System.out.println("\tYour HP is now: " + hp);
						System.out.println("\tYour potion count is: " + potions);
						System.out.println("========================================================");
						if(enemy == "Ancient Dragon") {
							randmg = rand.nextInt(dmg - 10);
							if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
								randmg = rand.nextInt(dmg - 20);
							}
							System.out.println("\t>You receive " + randmg + " from an enemy attack \n");
							hp -= randmg;
						}
						else {
							randmg = rand.nextInt(dmg - 20);
							if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
								randmg = rand.nextInt(dmg - 25);
							}
							System.out.println("\t>You receive " + randmg + " from an enemy attack \n");
							hp -= randmg;
						}
					}
					else {
						System.out.println("\tYou don't have enough healing potions to drink. Defeat enemies for a chance to get one!\n");
					}
				}
				else if(choice == 3) { //if user chose to run
					int i = rand.nextInt(3); //User will have a 1/3 chance to escape
					if(i == 1) {	
						System.out.println("\tYou got lucky and managed to run away from the " + enemy + "!");
						break;
					}
					else {
						System.out.println("\tThe " + enemy + " didn't let you run away which resulted in receiving some damage");
						if(enemy == "Ancient Dragon") {
							randmg = rand.nextInt(dmg - 10);
							if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
								randmg = rand.nextInt(dmg - 20);
							}
							System.out.println("\t>You receive " + randmg + " from an enemy attack \n");
							hp -= randmg;
						}
						else {
							randmg = rand.nextInt(dmg - 20);
							if(character == 1) { //if user character is a warrior give the random enemy attack damage a chance to become lower
								randmg = rand.nextInt(dmg - 25);
							}
							System.out.println("\t>You receive " + randmg + " from an enemy attack \n");
							hp -= randmg;
						}
					}
				}
				
				if(hp <= 0) { //If the user died then stop the game
					break;
				}	
			}
			
			if(hp <= 0 ) { //If the user died show these messages
				System.out.println("\n========================================================");
				System.out.println("\tYou died inside the dungeon...");
				System.out.println("\tYour score is: " + score + "!" + "\n");
				System.out.println("\tThanks for playing!");
				System.out.println("========================================================");
				break;
			}
			
			//If the user is alive show these messages
			System.out.println("\tWhat would you like to do now?");
			System.out.println("\t1. Continue fighting");
			System.out.println("\t2. Exit the dungeon");
			
			choice = keyboard.nextInt();
			
			//Invalid choice
			while(choice != 1 && choice != 2) {
				System.out.println("\nYour choice must be either 1 or 2 \n\t (1) => Continue fighting \n\t (2) => Exit the dungeon");
				System.out.print("Type the number of your choice: ");
				choice = keyboard.nextInt();
			}
			
			if(choice == 1) {
				System.out.println("========================================================");
				System.out.println("\tYou continue on your adventure!");
				System.out.println("========================================================");
			}
			else {
				System.out.println("========================================================");
				System.out.println("\tYou exit the dungeon, successful from your adventures!");
				System.out.println("\tYour score is: " + score + "!" + "\n");
				System.out.println("\tTHANKS FOR PLAYING!");
				System.out.println("========================================================");
				break;
			}
		}
	}
}
