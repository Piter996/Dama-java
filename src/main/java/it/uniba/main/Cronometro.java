/**
 * <<Entity>>
 * 
 * Classe che realizza un cronometro che misura
 * intervalli temporali espressi in millisecondi. Basata sull'orologio
 * di sistema .
 */

package it.uniba.main;

public class Cronometro {

  /** Accumulatore contenente il numero dei millisecondi trascorsi. */
  private long contatore;

  /** Istante temporale dell'ultimo avvio del cronometro. */
  private long avviato_a;

  /** Variabile di stato che indica se il cronometro sta avanzando oppure no. */
  private boolean avanzando;



  public Cronometro() { azzera(); }

  /** Metodo per (fermare ed) azzerare del cronometro. */
  public void azzera() {
    synchronized (this) {
      contatore = 0;
      avanzando = false;
    }
  }

  /**
   * Metodo che fa (ri)partire il conteggio. Non azzera il
   * cronometro, ma fa procedere la misura del tempo partendo dal
   * valore immagazzinato nell'accumulatore.
   */
  public void avanza() {
    synchronized (this) {
      avviato_a = System.currentTimeMillis();
      avanzando = true;
    }
  }

  /**
   * Metodo che blocca l'avanzamento del cronometro.*/
  public void ferma() {
    synchronized (this) {
      contatore += System.currentTimeMillis() - avviato_a;
      avanzando = false;
    }
  }

  /** Azzera il cronometro e ne fa partire il conteggio. */
  public void avanzaDaCapo() {
    azzera();
    avanza();
  }

  /**
   * Lettura del conteggio corrente effettuato dal cronometro.
   * Chiamate successive a questo metodo riportano valori diversi
   * nel caso in cui il cronometro stia avanzando.
   */
  public long leggi() {
    synchronized (this) {
      return avanzando ? contatore + System.currentTimeMillis() - avviato_a
                       : contatore;
    }
  }

  /**
   * Conversione in stringa del conteggio corrente. La lettura del
   * valore viene effettuata mediante il metodo <code>leggi()</code>.
   */
  public String toString() {
    return "" + ((leggi())/60000);
  }
}

