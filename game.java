import java.util.Scanner;

class game {
	// Deklarera Constants
	private static final int MAX_SPELARE = 5;
	private static final int MIN_SPELARE = 2;

	// Deklarera Globala Variabler
	private static Scanner input = new Scanner(System.in);
	private static int AntalSpelare;
	private static String[] spelare;

	private static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println("\b");
		}
	}

	public static void main(String[] args) {

		// Ta hand om Välkomm Skärmen
		System.out.println("Välkommen till Byggare Bob! (Deluxe Edition)");
		System.out.println("För att starta skriv in hur många spelare du vill ha: (2-5)");

		AntalSpelare = input.nextInt();
		while (!(AntalSpelare >= MIN_SPELARE && AntalSpelare <= MAX_SPELARE)) {
			clearScreen();
			System.out.println("Skriv in hur många spelare du vill ha: (Minst 2, Max 5)");
			AntalSpelare = input.nextInt();
		}

		clearScreen();
		spelare = new String[AntalSpelare];
		System.out.println("Ange namnet på spelarna en efter en:");
		for (int i = 1; i <= AntalSpelare; i++) {
			System.out.println("Spelare " + i + "'s Namn':");

		}

	}
}