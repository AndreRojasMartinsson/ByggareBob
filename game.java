import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class game {
	// Deklarera Constants
	private static final int MAX_SPELARE = 5;
	private static final int MIN_SPELARE = 2;
	private static final String[] SPELARE_BILDER = {"😊","🎶","😺","🤡","🐨","🐻","🐹","🥰"};

	// Deklarera Globala Variabler
	private static Scanner input = new Scanner(System.in);
	private static int AntalSpelare;
	private static int[] Happenings;
	private static int[][] spelPlan_SPELARE;
	private static int SpelareNu;
	private static String[] spelPlan = new String[26];
	private static int vunnit = -1;
	private static String[] spelare;
	private static String[] bilder;
	private static final Random rand = new Random();

	private static void tömSkärmen() {
		for (int i = 0; i < 50; i++) {
			System.out.println("\b");
		}
	}

	private static void genereraHappenings() {
		for (int i = 0; i < spelPlan.length; i++) {
			spelPlan[i] = "💿";

			if(rand.nextInt(10) < 2) {
				spelPlan[i] = "📀";
			}
		}
	}

	private static int[] fåSpelarePlats(int spelareNummer) {
		int[] plats = new int[2];
		for (int x = 0; x < spelPlan_SPELARE.length; x++) {
			for (int y = 0; y < AntalSpelare; y++) {
				if (spelPlan_SPELARE[x][y] == spelareNummer) {
					plats[0] = x;
					plats[1] = y;
				}
			}
		}

		return plats;
	}

	public static void main(String[] args) {

		// Ta hand om Välkomm Skärmen
		System.out.println("Välkommen till Byggare Bob! (Deluxe Edition)");
		System.out.println("För att starta skriv in hur många spelare du vill ha: (2-5)");

		AntalSpelare = input.nextInt();
		while (!(AntalSpelare >= MIN_SPELARE && AntalSpelare <= MAX_SPELARE)) {
			tömSkärmen();
			System.out.println("Skriv in hur många spelare du vill ha: (Minst 2, Max 5)");
			AntalSpelare = input.nextInt();

		}

		// Få fram Spelarnas Namn
		input.nextLine();
		tömSkärmen();
		spelare = new String[AntalSpelare];
		bilder = new String[AntalSpelare];
		System.out.println("Ange namnet på spelarna en efter en:");

		int SpelareNummer = 1;
		for (int i = 0; i < AntalSpelare; i++) {
			System.out.println("Spelare " + SpelareNummer + "'s Namn':");
			spelare[i] = input.nextLine();
			SpelareNummer++;
			tömSkärmen();
		}

		for (int i = 0; i < bilder.length; i++) {
			bilder[i] = "";
		}

		

		// Tilldela alla spelare en slumpmässig Figur.
		for (int i = 0; i < AntalSpelare; i++) {
			boolean Tagen = true;
			String _bild = "";
			while (Tagen == true) {
				Tagen = false;
				int randomNummer = rand.nextInt(SPELARE_BILDER.length);
				_bild = SPELARE_BILDER[randomNummer];

				// Se till så att spelare inte får samma figur.
				for (int x = 0; x < bilder.length; x++) {
					if (bilder[x].equals(_bild)) {
						Tagen = true;
					}
				}

				if (Tagen == false) {
					bilder[i] = _bild;
				}
			}
		}

		char respons = 'L';
		while (respons != 'A') {
			SpelareNummer = 1;
			for(int i = 0; i < bilder.length; i++) {
				System.out.println("Spelare " + SpelareNummer + "'s Figur': " + bilder[i]);
				SpelareNummer++;
			}

			System.out.println("För att fortsätta skriv A.");
			respons = input.next().charAt(0);
			tömSkärmen();
			
		}

		spelPlan_SPELARE = new int[26][AntalSpelare];

		// Tilldela Spel Plan Spelare
		for (int i = 0; i < spelPlan_SPELARE.length; i++) {
			for (int x = 0; x < AntalSpelare; x++) {
				spelPlan_SPELARE[i][x] = -1;
			}
		}

		// Sätt spelarna på första
		for (int i = 0; i < AntalSpelare; i++) {
			spelPlan_SPELARE[0][i] = i;
		}
		System.out.println(spelPlan_SPELARE[0][0]);
		System.out.println(spelPlan_SPELARE[0][1]);

		SpelareNu = 0;

		/*
		spelPlan_SPELARE[1][1] = 1;
		spelPlan_SPELARE[0][1] = -1;

		spelPlan_SPELARE[3][0] = 0;
		spelPlan_SPELARE[0][0] = -1;
		*/

		genereraHappenings();
		input.nextLine();
		while (vunnit == -1) {

			System.out.println(" ");
			/*
			for (int i = 0; i < spelPlan_SPELARE.length; i++) {
				for (int y = 0; y < AntalSpelare; y++) {
					if (spelPlan_SPELARE[i][y] == -1) {
						System.out.print("  ");
						continue;
					}



					System.out.println(bilder[spelPlan_SPELARE[i][y]]);
				}
				System.out.print(" ");
			}

			*/

			
			int rad = 0;
			for (int i = 0; i < AntalSpelare; i++) {
				for (int x = 0; x < spelPlan_SPELARE.length; x++) {
					if(spelPlan_SPELARE[x][rad] == -1) {
						System.out.print("   ");
						continue;
					}
					System.out.print(bilder[spelPlan_SPELARE[x][rad]] + " ");
				}
				System.out.println("");
				rad++;
			}

			for (int i = 0; i < spelPlan.length; i++) {
				System.out.print(spelPlan[i] + " ");
			}

			System.out.println("\n\n");


			System.out.println("[Spelare " + (SpelareNu + 1)  + "]: Skriv a för att Kasta Tärning:");
			input.nextLine();

			int tärningsNummer = rand.nextInt(8) + 1;
			System.out.println("Du fick: " + tärningsNummer);

			int[] plats = fåSpelarePlats(SpelareNu);
			System.out.println(plats[0] + tärningsNummer);
			System.out.println(spelPlan.length);

			int nyX = plats[0] + tärningsNummer;
			if (nyX > 24) {
				spelPlan_SPELARE[plats[0]][plats[1]] = -1;
				spelPlan_SPELARE[25][plats[1]] = plats[1];

				vunnit = SpelareNu;
				break;
			} else {
				spelPlan_SPELARE[plats[0]][plats[1]] = -1;
				spelPlan_SPELARE[nyX][plats[1]] = plats[1];
			}





			if (spelPlan[nyX].equals("📀")) {
				if (nyX > 0) {
					tömSkärmen();
					rad = 0;
					for (int i = 0; i < AntalSpelare; i++) {
						for (int x = 0; x < spelPlan_SPELARE.length; x++) {
							if(spelPlan_SPELARE[x][rad] == -1) {
								System.out.print("   ");
								continue;
							}
							System.out.print(bilder[spelPlan_SPELARE[x][rad]] + " ");
						}
						System.out.println("");
						rad++;
					}

					for (int i = 0; i < spelPlan.length; i++) {
						System.out.print(spelPlan[i] + " ");
					}
		
					System.out.println("\n\n");	
					
					
					int _vald = rand.nextInt(100);
					if (_vald < 45) {
						System.out.println("Oh no you land on Happening!");
						System.out.println("You need to solve this math problem.");
						System.out.println("\nIf you win you do not get any punishment, and move 1 place.\nIf you lose you go back 1-8 cards (randomily picked)");
						System.out.println("\nTo start enter any key:");
						input.nextLine();

						int tal1 = rand.nextInt(50) + 1;
						int tal2 = rand.nextInt(40) + 1;

						System.out.println("What is " + tal1 + " + " + tal2 + "? :");
						int svar = input.nextInt();

						if (svar == (tal1 + tal2)) {
							System.out.println("Du vann! Du hoppar nu 1 steg framåt.");
							plats = fåSpelarePlats(SpelareNu);
							nyX = plats[0] + 1;
							spelPlan_SPELARE[plats[0]][plats[1]] = -1;
							spelPlan_SPELARE[nyX][plats[1]] = plats[1];
						} else {
							int antal = rand.nextInt(7) + 1;
							System.out.println("Du förlorade! Du hoppar nu " + antal + " steg bakåt.");
	
							plats = fåSpelarePlats(SpelareNu);
							nyX = plats[0] - antal;
	
							if (nyX <= 0) {
								spelPlan_SPELARE[plats[0]][plats[1]] = -1;
								spelPlan_SPELARE[0][plats[1]] = plats[1];
							} else {
								spelPlan_SPELARE[plats[0]][plats[1]] = -1;
								spelPlan_SPELARE[nyX][plats[1]] = plats[1];
							}	
						}
					} else {
						System.out.println("Oh no you land on Happening!");
					System.out.println("Play this game of blackjack as a punishment.");
					System.out.println("\n\nRules:\n\t - The goal of blackjack is to beat the dealer's hand without going over 21.\n\t - You start with 0 points\n\t - To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n\t - If you go over 21 you bust, and the dealer wins regardless of the dealer's hand. \n\t - Dealer will hit until his/her cards total 17 or higher.");
					System.out.println("\nIf you win you do not get any punishment, and move 1 place.\nIf you lose you go back 1-8 cards (randomily picked)");
					System.out.println("\nTo start enter any key:");
					input.nextLine();

					// Lokala Variabler
					boolean Vann = false;
					boolean Förlorade = false;
					int dinPoäng = 0;
					int dealerPoäng = 0;

					while(true) {
						System.out.println("Poäng:\n\tDealer: " + dealerPoäng + "\n\tDu: " + dinPoäng);
						System.out.println("Hit eller Stand? (Hit = Y, Stand = N):");
						char _respons = input.next().charAt(0);

						if (_respons == 'N' || _respons == 'n') {
							int val = rand.nextInt(100);
							if (val < 75) {
								int hand = rand.nextInt(11);
								dealerPoäng = dealerPoäng + hand;
	
								if (dealerPoäng > 21) {
									Vann = true;
									break;
								}
	
								if (dealerPoäng == 21 && dinPoäng < 21) {
									Förlorade = true;
									break;
								}
	
								if (dinPoäng == 21 && dealerPoäng < 21) {
									Vann = true;
									break;
								}
	
							}
						} else {
							if(_respons == 'Y' || _respons == 'y') {
								int hand = rand.nextInt(11);
								dinPoäng = dinPoäng + hand;
								System.out.println("Du fick: " + hand);
	
								if (dinPoäng > 21) {
									Förlorade = true;
									break;
								}
	
								if (dealerPoäng == 21 && dinPoäng < 21) {
									Förlorade = true;
									break;
								}
	
								if (dinPoäng == 21 && dealerPoäng < 21) {
									Vann = true;
									break;
								}
							}
	
							
							int val = rand.nextInt(100);
							if (val < 75) {
								int hand = rand.nextInt(11);
								dealerPoäng = dealerPoäng + hand;
	
								if (dealerPoäng > 21) {
									Vann = true;
									break;
								}
	
								if (dealerPoäng == 21 && dinPoäng < 21) {
									Förlorade = true;
									break;
								}
	
								if (dinPoäng == 21 && dealerPoäng < 21) {
									Vann = true;
									break;
								}
	
							}
						}

						
					}

					System.out.println("Poäng:\n\tDealer: " + dealerPoäng + "\n\tDu: " + dinPoäng + "\n");
					if (Förlorade) {
						int antal = rand.nextInt(7) + 1;
						System.out.println("Du förlorade! Du hoppar nu " + antal + " steg bakåt.");

						plats = fåSpelarePlats(SpelareNu);
						nyX = plats[0] - antal;

						if (nyX <= 0) {
							spelPlan_SPELARE[plats[0]][plats[1]] = -1;
							spelPlan_SPELARE[0][plats[1]] = plats[1];
						} else {
							spelPlan_SPELARE[plats[0]][plats[1]] = -1;
							spelPlan_SPELARE[nyX][plats[1]] = plats[1];
						}


					}

					if (Vann) {
						System.out.println("Du vann! Du hoppar nu 1 steg framåt.");
						plats = fåSpelarePlats(SpelareNu);
						nyX = plats[0] + 1;
						spelPlan_SPELARE[plats[0]][plats[1]] = -1;
						spelPlan_SPELARE[nyX][plats[1]] = plats[1];
					}  
					}

					

					System.out.println("Write any key to continue:");
					input.nextLine();
					input.nextLine();
				}
			}

			if(SpelareNu == AntalSpelare - 1) {
				SpelareNu = 0;
			} else {
				SpelareNu++;
			}
			tömSkärmen();
		}

		tömSkärmen();


		
		// Skriv ut spelarna.
		int rad = 0;
		for (int i = 0; i < AntalSpelare; i++) {
			for (int x = 0; x < spelPlan_SPELARE.length; x++) {
				if(spelPlan_SPELARE[x][rad] == -1) {
					System.out.print("   ");
					continue;
				}
				System.out.print(bilder[spelPlan_SPELARE[x][rad]] + " ");
			}
			System.out.println("");
			rad++;
		}

		// Skriv ut SPel Plan
		for (int i = 0; i < spelPlan.length; i++) {
			System.out.print(spelPlan[i] + " ");
		}


		// Skriv ut vem som vann.
		System.out.println("\n\n");
		System.out.println("Spelare " + vunnit + " vann!");

	}
}