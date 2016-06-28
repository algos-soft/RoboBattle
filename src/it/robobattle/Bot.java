package it.robobattle;

import java.awt.*;

/**
 * Interfaccia che descrive le funzioni di un Bot.
 */
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
	 * @return la parola scritta al contrario
	 */
	String invertWord(String in);

	/**
	 * Calcola la checksum di una parola.
	 * </p>
	 * Determina il valore di ogni carattere e restituisce la somma totale.
	 * @param in la parola in ingresso
	 * @return la checksum
	 */
	int calcChecksum(String in);

	/**
	 * Decripta una parola in base a una chiave data.
	 * <p/>
	 * Ogni carattere ha un valore numerico short corrispondente.
	 * Essendo sia char che short valori a 16 bit, sono intercambiabili.
	 * <p/>
	 * Per ottenere il numero dal carattere o il carattere dal
	 * numero basta un casting, come da esempio:
	 * </p>
	 * <code>
	 * char c1 = 'x';<br>
	 * short n = (short)c1;<br>
	 * char c2 = (char)n;<br>
	 * </code>
	 * <p/>
	 * La somma dei valori delle vocali contenute nella chiave dà un numero segreto.<br>
	 * Alle lettere in posizione pari va aggiunto il numero segreto.<br>
	 * Dalle lettere in posizione dispari va sottratto il numero segreto.<br>
	 * La prima lettera si intende alla posizione 1.<br>
	 * La chiave ricevuta contiene solo lettere minuscole.<br>
	 * @param in  la parola da decrittare
	 * @param key la chiave
	 * @return la parola decrittata.
	 */
	String decryptWord(String in, String key);

	/**
	 * Ordina alfabeticamente le lettere di una parola.
	 * @param in la parola da ordinare
	 * @return la parola ordinata.
	 */
	String sortWord(String in);





}
