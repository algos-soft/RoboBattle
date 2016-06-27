package it.robobattle.robotester;

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
	 * @param in la parola da decrittare
	 * @param key la chiave
	 * @return la parola decrittata.
	 * La somma delle vocali contenute nella chiave dà un numero segreto.
	 * Alle lettere in posizione pari va aggiunto il numero segreto
	 * Alle lettere in posizione dispari va sottratto il numero segreto
	 */
	String decryptWord(String in, String key);

	/**
	 * Ordina alfabeticamente le lettere di una parola.
	 * @param in la parola da ordinare
	 * @return la parola ordinata.
	 */
	String sortWord(String in);





}
