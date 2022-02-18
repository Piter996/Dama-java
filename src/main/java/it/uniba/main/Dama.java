/**
 * <<Entity>>
 * 
 * Classe che realizza una pedina e che ne gestisce 
 * i cambiamenti e le eventuali visualizzazione 
 */

package it.uniba.main;

public class Dama {
    
	/** Contiene il colore della pedina */ 
	private String colore_pedina;
    
    /** Memorizza il tipo di pedina */
    private String tipo_pedina;
    
    /** Carattere unicode corrispondente alla tipologia di pedina/dama */
    private String unicode;
    
    /**numero della cella in cui é posizionata la pedina/dama */
    private int cella;
    
    /** costruttore dama */
    public Dama() {
    	    this.colore_pedina="empty";
    	    this.tipo_pedina="empty";
    	    this.unicode="empty";
    	    this.cella=0;
    	    
        }
    
      /** Metodo per l'inzializzazione delle pedine bianche */
      public void inizializza_pedine_bianche(Dama transizione,int i) {
    	transizione.colore_pedina="bianca";
    	transizione.tipo_pedina="pedina";
    	transizione.unicode="\u26C0";
    	transizione.cella=i+20;
        }
       
      /** Posiziona la pedina fuori dalla damiera, eliminandola dalla partita */
       public void eliminaPedina() {
    	this.cella = -1;
    }
    
       /** Metodo per l'inizializzazione delle pedine nere*/
       public void inizializza_pedine_nere(Dama transizione,int i) {
        	transizione.colore_pedina="nera";
        	transizione.tipo_pedina="pedina";
        	transizione.unicode="\u26C2";
        	transizione.cella=i;
        }
   
        /** Metodo per la damatura delle pedine bianche */
        private void toDama_bianca() {
        	this.tipo_pedina="dama";
        	this.unicode="\u26C1";
        	
        }
    
        /**Consente la restituzione del tipo della pedina*/
        public String tipoPedina() {
        	return this.tipo_pedina;
        }
        
        /** Metodo per damatura delle pedine nere */
        private void toDama_nera() {
        	this.tipo_pedina="dama";
        	this.unicode="\u26C3";
        }
        
        /** Metodo che posiziona la pedina nella cella richiesta*/
        public void posiziona_pedina(Dama transizione,int n_cella) {
        	transizione.cella=n_cella;
        	 
        }
        
        /** Metodo che sposta pedina nella casella richiesta*/
        public void spostaPedina(int nuovaCella) {
        	this.cella=nuovaCella;
        	if((this.colore_pedina.contentEquals("bianca")) && (this.cella >= 0 && this.cella <= 4) ) {
        		this.toDama_bianca();
        	}
        	if((this.colore_pedina.contentEquals("nera")) && (this.cella >= 28 && this.cella <= 31) ) {
        		this.toDama_nera();
        	}

        }
        
        /** Consente la restituzione della cella in cui la pedina é posizionata*/
        public int rileva_cella() {
        	return this.cella;
        }
        
        /** Consente la restituzione del carattere unicode della pedina*/ 
        public String stampa_pedina() {
           	return this.unicode;
        }
}
