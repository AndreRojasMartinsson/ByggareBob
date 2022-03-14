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
	private static String[] spelare;
	private static String[] bilder;
	private static final Random rand = new Random();

	private static void tömSkärmen() {
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
			}
			System.out.println("För att fortsätta skriv A.");
			respons = input.next().charAt(0);
			tömSkärmen();
			
		}

		System.out.println("");

	}
}