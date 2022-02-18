/**
 * <<Boundary>>
 * 
 * Classe che gestisce la componente visiva del programma 
 * e quindi i messaggi di output.
 */

package it.uniba.main;

public class Stampa {
/** Metodo che visualizza un messaggio che suggerisce l'inizio di una partita, per poter accedere alla funzione richiesta*/
	public void iniziaPartita() {
        System.out.print("Per visualizzarlo devi prima iniziare una partita, digitando il comando 'gioca'\n");

	}
/** Metodo che visualizza una damiera e la disposizione delle caselle numerate*/	
	public void stampaNumeri() {
		System.out.print("-------------------------------------\n");
        System.out.print("|  1 |\u2588\u2588\u2588|  2 |\u2588\u2588\u2588|  3 |\u2588\u2588\u2588|  4 |\u2588\u2588\u2588|\n");
        System.out.print("-------------------------------------\n");
        System.out.print("|\u2588\u2588\u2588|  5 |\u2588\u2588\u2588|  6 |\u2588\u2588\u2588|  7 |\u2588\u2588\u2588|  8 |\n");
        System.out.print("-------------------------------------\n");
        System.out.print("|  9 |\u2588\u2588\u2588| 10 |\u2588\u2588\u2588| 11 |\u2588\u2588\u2588| 12 |\u2588\u2588\u2588|\n");
        System.out.print("-------------------------------------\n");
        System.out.print("|\u2588\u2588\u2588| 13 |\u2588\u2588\u2588| 14 |\u2588\u2588\u2588| 15 |\u2588\u2588\u2588| 16 |\n");
        System.out.print("-------------------------------------\n");
        System.out.print("| 17 |\u2588\u2588\u2588| 18 |\u2588\u2588\u2588| 19 |\u2588\u2588\u2588| 20 |\u2588\u2588\u2588|\n");
        System.out.print("-------------------------------------\n");
        System.out.print("|\u2588\u2588\u2588| 21 |\u2588\u2588\u2588| 22 |\u2588\u2588\u2588| 23 |\u2588\u2588\u2588| 24 |\n");
        System.out.print("-------------------------------------\n");
        System.out.print("| 25 |\u2588\u2588\u2588| 26 |\u2588\u2588\u2588| 27 |\u2588\u2588\u2588| 28 |\u2588\u2588\u2588|\n");
        System.out.print("-------------------------------------\n");
        System.out.print("|\u2588\u2588\u2588| 29 |\u2588\u2588\u2588| 30 |\u2588\u2588\u2588| 31 |\u2588\u2588\u2588| 32 |\n");
        System.out.print("-------------------------------------\n");
        
	}
/** Metodo che viusualizza la spiegazione e il funzionamento dei vari comandi digitabili*/	
	public void stampaAiuto() {
         System.out.print("Per sapere come si gioca e le varie regole visitare il sito: http://www.fid.it/corsi/italiana/regole.htm        n");
         System.out.print(" Digitare il comando 'gioca' per avviare una nuova partita                                                      \n");
         System.out.print(" Digitare il comando 'esci' per uscire dal gioco (é necessario abbandonare la partita se ne é stata inizata una)\n");
         System.out.print(" Digitare il comando 'abbandona' per abbandonare la parita e arrendersi                                                                \n");
         System.out.print(" Digitare il comando 'numeri' per visualizzare una damiera con le varie caselle numerate                                               \n");
         System.out.print(" Digitare il comando 'tempo' per visualizzare il tempo trascorso (in minuti) dall'inizio della partita (Disponibile solo a partita iniziata)\n");
         System.out.print(" Digitare il comando 'damiera' per visualizzare una damiera con la posizione delle pedine (Disponibile solo a partita iniziata)             \n");
         System.out.print(" Digitare il comando 'mossa' per visualizzare le mosse effettuate da entrambi i giocatori (Disponibile solo a partita iniziata)             \n"); //issue#33
         System.out.print(" Digitare il comando 'prese' per visualizzare le pedine prese da entrambi i giocatori (Disponibile solo a partita iniziata)             \n"); //issue#31


         System.out.print(" \n Le mosse devono avere il seguente formato per uno spostare una pedina --> '<CASELLA DI PARTENZA>-<CASELLA DI DESTINAZIONE> ES.18-13  (E' necessario aver iniziato la partita con il comando 'gioca'\n");
         
         System.out.print(" \n Le mosse devono avere il seguente formato per una presa semplice      --> '<CASELLA DI PARTENZA>x<CASELLA DI DESTINAZIONE> ES.18x9  (E' necessario aver iniziato la partita con il comando 'gioca'\n"); //issue#25

	}
/** Metodo che permette la visualizzazione della damiera completa delle pedine/dame nella loro posizione attuale */	
	public void stampaDamiera(Dama dama_bianca[],Dama dama_nera[]) {
		 int riga = 0;
         int riquadro;
         int cella = 0;
         boolean trovato=false;
         for (int i = 0; i < 33; i++) {
             System.out.print("-");
         }
         System.out.print("\n");
         while (riga <= 7) {        //conteggio righe stampate
             if ((riga % 2) == 0) { //controllo se trattasi di un riga pari o dispari per 
                 riquadro = 0;      
             } else {
                 riquadro = 1;
             }

             for (int i = 0; i < 33; i++) { // un ciclo per ogni carattere che compone la riga 
                 if (i % 4 == 0) {      //un riquadro ï¿½ composto da 4 caratteri e alla fine si divide con "|"
                     System.out.print("|");
                     riquadro++;            //se un riquadro é dispari é bianco altrimenti é nero 
                 } else {                        //se non é terminato il riquadro controlla il carattere da inserire
                     if ((riquadro % 2) == 0) {

                         System.out.print("\u2588"); //stampa riquadro bianco


                     } else { //altrimenti se il riquadro é nero...
                         trovato=false;
                     	for (int k = 0; k < 12; k++) {  //controlla se tra le dame c'é una che si trova in quel riquadro(casella)
                         
                     		if (dama_bianca[k].rileva_cella() == cella) { //controllo corrispondenza cella
                             	System.out.print(" "+dama_bianca[k].stampa_pedina()+" "); //stampa immagine della pedina
                                 trovato = true;
                                 k=13; //finalizza l'indice del ciclo
                                 i=i+2; //avanza il contatore dei simboli per quel riquadro

                                 cella++; //cella terminata
                             }
                         }
                             if(trovato==false){
                             	for (int k = 0; k < 12; k++) {  //controlla se tra le dame c'ï¿½ una che si trova in quel riquadro(casella)
                               
                                     if (dama_nera[k].rileva_cella() == cella) { //controllo corrispondenza cella
                                     	System.out.print(" "+dama_nera[k].stampa_pedina()+" "); //stampa immagine della pedina
                                         trovato = true;
                                         k=13;    
                                         i=i+2;  //cella terminata

                                         cella++; //cella terminata
                                     }
                                 }
                             }
                             if (trovato==false) { 
                             	System.out.print("   "); //viene visualizzata la cella vuota
                                 i=i+2; //cella terminata
                                 cella++; 
                             }
                         }
                     }
                 }
  System.out.print("\n");

             for (int i = 0; i < 32; i++) {
                 System.out.print("-");
             }
             System.out.print("\n");

             riga++; //riga successiva
             }

     
	}
	
	
	
}
