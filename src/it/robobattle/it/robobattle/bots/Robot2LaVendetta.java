	package it.robobattle.it.robobattle.bots;

	import it.robobattle.Bot;

	import java.awt.Color;

public class Robot2LaVendetta implements Bot {
		
		private String szSort; // stringa di ritorno per il metodo sort.

		public Robot2LaVendetta() {
			super();
		}

		/**
		 * Restituisce il nome del bot.
		 * @return il nome del bot
		 */
		public String getNome(){
			String getNome = "TheDestroyer";
			return getNome;
		}

		/**
		 * Restituisce lo slogan del bot.
		 * @return lo slogan del bot
		 */
		public String getSlogan(){

			String getSlogan = "Non vincer� per� distrugerra tutti";
			return getSlogan;
		}


		/**
		 * Restituisce il colore del bot.
		 * @return il colore del bot
		 */
		public Color getColore(){
			Color colore = Color.BLACK;
			return colore;
		}


		/**
		 * Inverte le lettere di una parola.
		 * @param in la parola in ingresso
		 * @return la parola scritta al contrario.
		 */
		public String invertWord(String in){
			String invertito = "";
			for(int i=in.length()-1;i>=0;i--){
				char arr = in.charAt(i);
				invertito+= arr;			
			}
			return invertito;
		}

		/**
		 * Calcola la checksum di una parola.
		 * Determina il valore di ogni carattere e restituisce la somma totale.
		 * @param in la parola in ingresso
		 * @return la checksum
		 */
		public int calcChecksum(String in){
			int checkSum = 0;
			for(int i=0;i<in.length();i++){
				char arr = in.charAt(i);
				checkSum+= (int) arr;
			
			}
			
			return checkSum;
		}

		/**
		 * Decripta una parola in base a una chiave data.
		 * @param in la parola da decrittare
		 * @param key la chiave
		 * @return la parola decrittata.
		 * La somma delle vocali contenute nella chiave dà un numero segreto.
		 * Alle lettere in posizione pari va aggiunto il numero segreto
		 * Alle lettere in posizione dispari va sottratto il numero segreto
		 */
		public String decryptWord(String in, String key){
			String parola = new String();
			return parola;
		}

		/**
		 * Ordina alfabeticamente le lettere di una parola.
		 * eventuale difetto: meno ordinata � la parola piu tempo ci mette.
		 * @param in la parola da ordinare
		 * @return la parola ordinata.
		 */

		public String sortWord(String in){
			int lInTot = in.length(); 		// memorizza la lunghezza della stringa in arrivo.
			char[] arr = in.toCharArray();	// popola un array di char con la parola in arrivo, SEMBRA IL PIU VELOCE !
			
//			char[] arr = new char[lInTot];	// idem ma
//			for (int i = 0; i<lInTot; i++) 	// con un
//				arr[i] = in.charAt(i);		// ciclo for

			int i=0;
//			while (i < lInTot) { 			// idem ma con un ciclo while
//				arr[i] = in.charAt(i);
//				i++;
//			}
				
			char tmp; // usata per memorizzare il carattere alfabeticamente precedente di ogni singola iterazione
			// bLoop usato per ripetere il loop nel caso.
			// � true se in una singola iterazione, il secondo char � alfabeticamente precedente al primo.
			boolean bLoop = false;	

//			for (int i=0; i < lInTot -1; i++)  // for sembra meno veloce di while

			i=0;
			while (i <lInTot -1) // si itera la parola. while sembra pi� veloce del for
			{
				if (arr[i] > arr[i+1] ) // se la prima lettera � maggiore della seconda
				{						// inverte le loro posizioni
					tmp = arr[i+1];		// memorizza temporaneamente il valore della seconda 
					arr[i+1] = arr[i];	// memorizza al posto della seconda il valore della prima
					arr[i] = tmp;		// memorizza al posto della prima il valore che aveva la seconda.
					bLoop = true;		// imposta a true bLoop.
				}
				
				
				if (bLoop) // true azzera il contatore "i" per ripetere l'iterazione dall'inizio
				{
//					i = -1;//per for -1, x while 0
					i = 0;//per for -1, x while 0
					bLoop = false; // riporta a false il valore di bLoop.
				}
				else		// x while
					i++;	// x while
			}
			
			// finito il ciclo ritorna la stringa ordinata.
			szSort = new String(arr);// sembra pi� veloce del copyValueOf
			return szSort;//.copyValueOf(arr);
		}
		

	}
