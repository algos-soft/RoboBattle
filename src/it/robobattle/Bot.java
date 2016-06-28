package it.robobattle;

import java.awt.*;

public interface Bot {

	/**
	 * Restituisce il nome del bot.
	 * @return il nome del bot
	 */
	String getNome();

	/**
	 * Restituisce lo slogan del bot.
	 * @return lo slogan del bot
	 */
	String getSlogan();


	/**
	 * Restituisce il colore del bot.
	 * @return il colore del bot
	 */
	Color getColore();


	/**
	 * Inverte le lettere di una parola.
	 * @param in la parola in ingresso
	 * @return la parola scritta al contrario.
	 */
	String invertWord(String in);

	/**
	 * Calcola la checksum di una parola.
	 * Determina il valore di ogni carattere e restituisce la somma totale.
	 * @param in la parola in ingresso
	 * @return la checksum
	 */
	int calcChecksum(String in);

	/**
	 * Decripta una parola in base a una chiave data.
	 *
	 * @param in  la parola da decrittare
	 * @param key la chiave
	 * @return la parola decrittata.
	 * <p/>
	 * Ogni carattere ha un valore numerico short corrispondente.
	 * Essendo sia char che short valori a 16 bit, sono intercambiabili.
	 * Per ottenere il numero dal carattere o il carattere dal
	 * numero basta un casting, come da esempio:
	 * char c1 = 'x';
	 * short n = (short)c1;
	 * char c2 = (char)n;
	 * <p/>
	 * La chiave ricevuta contiene solo lettere minuscole.
	 * La somma dei valori delle vocali contenute nella chiave dà un numero segreto.
	 * Alle lettere in posizione pari va aggiunto il numero segreto.
	 * Dalle lettere in posizione dispari va sottratto il numero segreto.
	 * La prima lettera si intende alla posizione 1.
	 */
	String decryptWord(String in, String key);

	/**
	 * Ordina alfabeticamente le lettere di una parola.
	 * @param in la parola da ordinare
	 * @return la parola ordinata.
	 */
	String sortWord(String in);





}
