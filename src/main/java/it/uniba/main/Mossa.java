/**
 * 
 * <<Control>>
 * 
 * Classe che si occupa della decodifica e quindi dell'esecuzione
 * delle mosse immesse e nonché dei controlli che necessari
 */

package it.uniba.main;

public class Mossa {
	
	/** carica la cella di partenza della pedina immessa */
	private int cella_partenza = 0;
	
	/** carica la cella di destinazione della pedina immessa */
	private int cella_destinazione = 0;
	
	/** carica la cella di destinazione finale in caso di mossa multipla */
	private int cella_destinazione2 = 0; 

	/** carica la cella di destinazione finale in caso di mossa multipla */
    private int cella_destinazione3 = 0;
	
	/** variabile di stato che indica se si tratta di una mossa multipla o meno */
	private int lunghezza_mossa = 0;
	
	/** Memorizza il tipo di mossa che si vuole svolgere */
	private String tipologia_mossa = new String();
    
	/** Memorizza l'indice della dama */
 	private int indice_dama = 0;
	
	/** Rileva in base alla mossa inserita dal giocatore, le funzioni da chiamare 
	 * e restituisce true se tutto viene eseguito correttamente
	 */
	public boolean suddividiTipologia(Dama dama_nera[], Dama dama_bianca[],String colore) {
		boolean eseguito = false;
		boolean pass = true;
		
		if ((this.tipologia_mossa.contentEquals("presa_semplice"))) { 
			if (this.lunghezza_mossa == 2){
				if(this.controlloPresa(dama_bianca, dama_nera, colore)) {

				if(this.presa_semplice(dama_nera, dama_bianca, colore)) {
					eseguito = true;
			
				}
				}
			}
			}
			if (this.lunghezza_mossa >= 3) {    

				this.tipologia_mossa = "presa_semplice";
				
				if (this.validaMossa(dama_bianca, dama_nera, colore)) {

					
					if (this.controlloPresa(dama_bianca, dama_nera, colore)) {
					
					int backup = this.cella_partenza;
					this.cella_partenza = this.cella_destinazione;
					this.cella_destinazione = this.cella_destinazione2;
					
										if(this.controlloPresa(dama_bianca, dama_nera, colore)) {

					                      if (this.validaMossa(dama_bianca, dama_nera, colore)) {
					                    	 
					                    	  if (this.lunghezza_mossa == 4) {
					                    		  pass = false;
					                    		  int backup2 = 0;
					                    		  
					                    		  backup2 = this.cella_partenza;
					                    		  this.cella_partenza = this.cella_destinazione;
					                    		  this.cella_destinazione = this.cella_destinazione3;
					                    		  
					                    			if(this.controlloPresa(dama_bianca, dama_nera, colore)) {

									                      if (this.validaMossa(dama_bianca, dama_nera, colore)) {
									                    	  this.cella_destinazione = this.cella_partenza;
									                    	  this.cella_partenza = backup2;
									                    	  pass = true;
									                    	  
					                    		  
									                      }
					                    			}
					                    	  }
					                    	  if(pass) {

						this.cella_destinazione = this.cella_partenza;
						this.cella_partenza = backup;
						    
						if(this.presa_semplice(dama_nera, dama_bianca, colore)) {

							this.cella_partenza = this.cella_destinazione;
							this.cella_destinazione = this.cella_destinazione2;
							
							if(this.presa_semplice(dama_nera, dama_bianca, colore)) {
								
								
		                    	  if (this.lunghezza_mossa == 4) {
		                    		  this.cella_partenza = this.cella_destinazione;
		                    		  this.cella_destinazione = this.cella_destinazione3;
		                    		  if(this.presa_semplice(dama_nera, dama_bianca, colore)) {
		                    			  pass = true;
		                    		  }
		                    	  }

								if(pass) {
							eseguito = true;

								}
							}
							}
						
						}						
					}
				}
				}
				}
				
			
		}
		
        if (this.tipologia_mossa.contentEquals("spostamento")) {
			if(this.spostaPedina(dama_nera, dama_bianca, colore)) {
				eseguito = true;
			}
		}		
		
		return eseguito;
	}
		
	/** Controlla che la casella di destinazione immessa non sia occupata e ne restituisce lo stato*/
	private boolean controllaDestinazione(Dama dama_bianca[], Dama dama_nera[]){
		boolean occupata = false;
				
				for(int i = 0 ; i < 12 ; i++) {
					if(dama_bianca[i].rileva_cella() == this.cella_destinazione) {
						System.out.println("Casella di destinazione occupata");
						occupata = true;
					}
				}
		
				for(int i = 0 ; i < 12 ; i++) {
					if(dama_nera[i].rileva_cella() == this.cella_destinazione) {
						System.out.println("Casella di destinazione occupata");
						occupata = true;
					}
				}
				
		return occupata;
	}

	/** Metodo che acquisice la mossa digitata dal giocatore */
	public void decodificaMossa(String movimento) {
		if (movimento.contains("-") ) {
			tipologia_mossa = "spostamento";

					String [] decodifica = movimento.split("-");
try {
		this.cella_partenza  = Integer.parseInt(decodifica[0]);
		this.cella_partenza --;
		this.cella_destinazione = Integer.parseInt(decodifica[1]);
		this.cella_destinazione--;
		this.lunghezza_mossa = 2;
		} catch (NumberFormatException ex) {

		}
		}
		if( movimento.contains("x")) { 
			String [] decodifica = movimento.split("x");
			tipologia_mossa = "presa_semplice";
			try {
				this.cella_partenza  = Integer.parseInt(decodifica[0]);
				this.cella_partenza --;
				this.cella_destinazione = Integer.parseInt(decodifica[1]);
				this.cella_destinazione--;
				this.lunghezza_mossa = 2;
				
				if(decodifica.length >= 3) {
					tipologia_mossa = "presa_multipla";

			this.lunghezza_mossa =decodifica.length;
			this.cella_destinazione2 = Integer.parseInt(decodifica[2]); 
			this.cella_destinazione2--;
			
			if(decodifica.length >=4) {
				this.cella_destinazione3 = Integer.parseInt(decodifica[3]); 
				this.cella_destinazione3--;
			}
		}
				} catch (NumberFormatException ex) {

				}
		}
		
				
		
		
	

	}
	
	/** Finalizza le operazioni di presa e restituisce true se viene eseguito tutto correttamente */
	  private boolean effettuaPresa(Dama dama_bianca[], Dama dama_nera[],String colore) { 
		boolean presente = false;
		
		
		if (colore.contentEquals("nero")) {

			if (this.controlloRiga()) {
				
if(dama_nera[this.indice_dama].tipoPedina().contentEquals("dama")) {
					
					for(int i = 0 ; i < 12 ; i++) {

					if(((dama_bianca[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 9))) || ((dama_bianca[i].rileva_cella() ==(this.cella_partenza - 3)) && (this.cella_destinazione == (this.cella_partenza - 7))))  {
						dama_bianca[i].eliminaPedina();
						presente = true;
						i = 12;
					 }
					
					

					else if(((dama_bianca[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 7))) || ((dama_bianca[i].rileva_cella() == (this.cella_partenza + 5)) && (this.cella_destinazione == (this.cella_partenza + 9))))  {
							dama_bianca[i].eliminaPedina();

							presente = true;
							 i = 12;
						}
					}
				}else {
		for(int i = 0 ; i < 12 ; i++) {

			if(((dama_bianca[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 7))) || ((dama_bianca[i].rileva_cella() == (this.cella_partenza + 5)) && (this.cella_destinazione == (this.cella_partenza + 9))))  {
				dama_bianca[i].eliminaPedina();

				presente = true;
				 i = 12;
			}
		}
			}
		}
			
	    if (!(this.controlloRiga())) {
	    	
if(dama_nera[this.indice_dama].tipoPedina().contentEquals("dama")) {
				
				for(int i = 0 ; i < 12 ; i++) {

					if(((dama_bianca[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 7))) || ((dama_bianca[i].rileva_cella() ==(this.cella_partenza - 5)) && (this.cella_destinazione == (this.cella_partenza - 9))))  {
						dama_bianca[i].eliminaPedina();
						presente = true;
						i = 12;
		             }
				
				

				else if(((dama_nera[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 9))) || ((dama_bianca[i].rileva_cella() ==(this.cella_partenza + 3)) && (this.cella_destinazione == (this.cella_partenza + 7))) )  {
					dama_bianca[i].eliminaPedina();
					presente = true;
					i = 12;
				 }
				}
			}else {
		for(int i = 0 ; i < 12 ; i++) {
			if(((dama_bianca[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 9))) || ((dama_bianca[i].rileva_cella() ==(this.cella_partenza + 3)) && (this.cella_destinazione == (this.cella_partenza + 7))) )  {
				dama_bianca[i].eliminaPedina();
				presente = true;
				i = 12;
			 }
		}
	    }
		}
			
		}
		if (colore.contentEquals("bianco")) {

			if (this.controlloRiga()) {
				
				if(dama_bianca[this.indice_dama].tipoPedina().contentEquals("dama")) {
					
					for(int i = 0 ; i < 12 ; i++) {

					if(((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 9))) || ((dama_nera[i].rileva_cella() ==(this.cella_partenza - 3)) && (this.cella_destinazione == (this.cella_partenza - 7))))  {
						dama_nera[i].eliminaPedina();
						presente = true;
						i = 12;
					 }
					
					

					else if(((dama_nera[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 7))) || ((dama_nera[i].rileva_cella() == (this.cella_partenza + 5)) && (this.cella_destinazione == (this.cella_partenza + 9))))  {
							dama_nera[i].eliminaPedina();

							presente = true;
							 i = 12;
						}
					}
				}else {
				
		for(int i = 0 ; i < 12 ; i++) {

			if(((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 9))) || ((dama_nera[i].rileva_cella() ==(this.cella_partenza - 3)) && (this.cella_destinazione == (this.cella_partenza - 7))))  {
				dama_nera[i].eliminaPedina();
				presente = true;
				i = 12;
			 }
		}
			}
		}
					
		if (!(this.controlloRiga())) {
			if(dama_bianca[this.indice_dama].tipoPedina().contentEquals("dama")) {
				
				for(int i = 0 ; i < 12 ; i++) {

					if(((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 7))) || ((dama_nera[i].rileva_cella() ==(this.cella_partenza - 5)) && (this.cella_destinazione == (this.cella_partenza - 9))))  {
						dama_nera[i].eliminaPedina();
						presente = true;
						i = 12;
		             }
				
				

				else if(((dama_nera[i].rileva_cella() == (this.cella_partenza + 4)) && (this.cella_destinazione == (this.cella_partenza + 9))) || ((dama_nera[i].rileva_cella() ==(this.cella_partenza + 3)) && (this.cella_destinazione == (this.cella_partenza + 7))) )  {
					dama_nera[i].eliminaPedina();
					presente = true;
					i = 12;
				 }
				}
			}else {
			
		for(int i = 0 ; i < 12 ; i++) {

			if(((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) && (this.cella_destinazione == (this.cella_partenza - 7))) || ((dama_nera[i].rileva_cella() ==(this.cella_partenza - 5)) && (this.cella_destinazione == (this.cella_partenza - 9))))  {
				dama_nera[i].eliminaPedina();
				presente = true;
				i = 12;
             }
		}
		}
		}

		}
		
return presente;
		
	}

	/** Contolla se la cella presa in considerazione si trova all'interno di una riga pari, e restituisce true in caso positivo */
	private boolean controlloRiga() {
		
	    boolean riga_pari = false;

		if ((((this.cella_partenza + 1) > 4) && ((this.cella_partenza + 1) < 9)) || (((this.cella_partenza + 1) > 12) && ((this.cella_partenza + 1) < 17)) || (((this.cella_partenza + 1) > 20) && ((this.cella_partenza + 1) <25 )) || (((this.cella_partenza + 1) > 28) && ((this.cella_partenza + 1) < 33))) {
			riga_pari = true;

		}
		else {
			riga_pari = false;

		}
		
		return riga_pari;
	}
	
    /** Controlla che la mossa inserita dal giocatore sia valida in base alle varie caratteristiche dalla pedina che si sta muovendo
     * e restituisce true in caso positivo
     */		
	public boolean validaMossa (Dama dama_bianca [],Dama dama_nera[], String colore) {
			boolean valida=false;

           if(this.tipologia_mossa.contentEquals("presa_multipla")) { 
				valida = true;
				
           }
                    
           
           switch(colore) {
           
           case "bianco":
			for (int i = 0; i < 12; i++) {
					if (dama_bianca[i].rileva_cella() == cella_partenza) {
						this.indice_dama = i;
						i=12;						
					}
				}
			
           break;
			
           case "nero":
        	   for (int i = 0; i < 12; i++) {
					if (dama_nera[i].rileva_cella() == cella_partenza) {
						this.indice_dama = i;
						i=12;			
					}
				}
			break;
			
			
			}
	
			switch (colore) {
			
			case "bianco":
			 if(dama_bianca[indice_dama].tipoPedina().contentEquals("dama")) {
				    
				 if(((cella_partenza + 1)%8 == 0) || (cella_partenza + 1)%8 == 1) {


					   if(((cella_destinazione + 1) == ((cella_partenza + 1)  - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1)  + 4))) {

					   valida = true;
					   
					   }
			    }
				 if (this.controlloRiga()) {
						
				 if(this.tipologia_mossa.contentEquals("spostamento")) {

					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 3)) ) {
					 				         valida = true;

					 				         }
					 				
					                    if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 5)) ) {
				                             valida = true;

            				        }
				 }
			     if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { //issue#25
					 				         valida = true;

					 				         }
					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { //issue#25
									         valida = true;

									         }
					 }
					 				
					 		}
					 				
					 				if(!(this.controlloRiga())) {


					 if(this.tipologia_mossa.contentEquals("spostamento")) {
					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 5)) ) {
					 				         valida = true;

					 				         }
					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 3)) ) {
									         valida = true;

									         }
					 				}
					 if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { //issue#25
					 				         valida = true;

					 				         }
					 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { //issue#25
									         valida = true;


									         }
					 			}
					 			
					 					}
			 }
			        break;
			 
			case "nero":
				 if(dama_nera[indice_dama].tipoPedina().contentEquals("dama")) {
					    
					 if(((cella_partenza + 1)%8 == 0) || (cella_partenza + 1)%8 == 1) {

						   if(((cella_destinazione + 1) == ((cella_partenza + 1)  - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1)  + 4))) {
						   
						   valida = true;
						   
						   }
				    }
					 if (this.controlloRiga()) {
							
							
					 if(this.tipologia_mossa.contentEquals("spostamento")) {
						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 3)) ) {
						 				         valida = true;

						 				         }
						 				
						                    if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 5)) ) {
					                             valida = true;
	            				        }
					 }
				     if(this.tipologia_mossa.contentEquals("presa_semplice")) {

						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { //issue#25
						 				         valida = true;

						 				         }
						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { //issue#25
										         valida = true;

										         }
						 }
						 				
						 		}
						 				
						 				if(!(this.controlloRiga())) {

						 if(this.tipologia_mossa.contentEquals("spostamento")) {
						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 5)) ) {
						 				         valida = true;

						 				         }
						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 3)) ) {
										         valida = true;

										         }
						 				}
						 if(this.tipologia_mossa.contentEquals("presa_semplice")) {

						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { //issue#25
						 				         valida = true;

						 				         }
						 					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { //issue#25
										         valida = true;

										         }
						 			}
						 			
						 					}
				 }
				        break;
				 
				
			 
	}
			
			
	if(colore.contentEquals("bianco")) {

		if((((cella_partenza + 1) <= 32) && cella_partenza > cella_destinazione) && (cella_destinazione + 1) >=0) {

			if(this.tipologia_mossa.contentEquals("spostamento")) {
			
			    if(((cella_partenza + 1)%8 == 0) || (cella_partenza + 1)%8 == 1) {

				   if((cella_destinazione + 1) == ((cella_partenza + 1)  - 4)) {
					
					valida = true;
				}
				
				}
			    
			}
				else if (this.tipologia_mossa.contentEquals("presa_semplice")){
					     if (((cella_partenza + 1)%8 == 0) && ((cella_destinazione + 1) == ((cella_partenza + 1)  - 9))) { // caso di presa semplice 
												
							valida = true;
				
						
					}
					if (((cella_partenza + 1)%8 == 1) && ((cella_destinazione + 1) == ((cella_partenza + 1)  - 7))) { // caso di presa semplice 
						
						valida = true;
			
					
				}
				}
					
		if(!(((cella_partenza + 1)%8 == 0) || (cella_partenza + 1)%8 == 1)) {
 

				if (this.controlloRiga()) {
					
					
if(this.tipologia_mossa.contentEquals("spostamento")) {
					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 3)) ) {
				         valida = true;

				         }
				}

if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { 
				         valida = true;

				         }
		    	}
				
		}
				
				if(!(this.controlloRiga())) {

if(this.tipologia_mossa.contentEquals("spostamento")) {
					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 5)) ) {
				         valida = true;

				         }
				}
if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					if(((cella_destinazione + 1) == ((cella_partenza + 1) - 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) - 7)) ) { 
				         valida = true;

				         }
			}
			
					}
			}
				
			}
		}
				
	
	if(colore.contentEquals("nero")) {

		if((((cella_partenza + 1) >= 0) && cella_partenza < cella_destinazione) && (cella_destinazione + 1) <= 32) {

			if(((cella_partenza + 1)%8 == 0) || (cella_partenza + 1)%8 == 1) {
				
				if(this.tipologia_mossa.contentEquals("spostamento")) {

				   if((cella_destinazione + 1) == ((cella_partenza + 1)  + 4)) {
					
					valida = true;

				}
			}
				
				else if (this.tipologia_mossa.contentEquals("presa_semplice")){
				     if (((cella_partenza + 1)%8 == 0) && ((cella_destinazione + 1) == ((cella_partenza + 1) + 7))) { // caso di presa semplice 
											
						valida = true;
			
					
				}
				if (((cella_partenza + 1)%8 == 1) && ((cella_destinazione + 1) == ((cella_partenza + 1) + 9))) { // caso di presa semplice 
					
					valida = true;
		
				
			}
			}
				
		}
			else { 
				if (this.controlloRiga()) {

					if(this.tipologia_mossa.contentEquals("spostamento")) {
					
					   if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 5)) ) {
				         valida = true;

				         }
				}
					
					if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { //issue#25
				         valida = true;

				         }
				}
				}
				
				if(!(this.controlloRiga())) {
					
					if(this.tipologia_mossa.contentEquals("spostamento")) {

					   if(((cella_destinazione + 1) == ((cella_partenza + 1) + 4)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 3)) ) {
				         valida = true;

				         }
				}
					
					if(this.tipologia_mossa.contentEquals("presa_semplice")) {

					   if(((cella_destinazione + 1) == ((cella_partenza + 1) + 9)) || ((cella_destinazione + 1) == ((cella_partenza + 1) + 7)) ) { 
				         valida = true;

				         }
				}
					}
			}
		}
			
	
	}
	return valida;
	}

	/** Effettua le operazioni di presa_semplice e restituisce true se viene eseguito tutto correttamente*/
    private boolean presa_semplice(Dama dama_nera[], Dama dama_bianca[], String colore) { 
    	
		boolean trovato = false;
		boolean eseguito = false;
                              
		if (colore.contentEquals("bianco")) {               //inizio riconoscimento del colore

			for (int k = 0; k < 12; k++) {                               //controlla se tra le dame c'é una che si trova in quel riquadro(casella)

         		if (dama_bianca[k].rileva_cella() == cella_partenza && !(this.controllaDestinazione(dama_bianca,dama_nera))) { //controllo corrispondenza cella

         			if (this.effettuaPresa(dama_bianca, dama_nera, colore)) { //controlla se c'é una pedina da mangiare

             			dama_bianca[k].spostaPedina(cella_destinazione);
         			
             			eseguito = true;

                 	
                 	trovato = true;
                 	k=12;
         		}
         		
         		 if (k == 11 && trovato == false) {
                 	System.out.println("Non e' una tua pedina"); //fine iterazione senza trovare alcuna pedina
                 }
         		
             }
		}
		}
		else if (colore.contentEquals("nero")) {

           for (int k = 0; k < 12; k++) { //controlla se tra le dame c'é una che si trova in quel riquadro(casella)
         		if (dama_nera[k].rileva_cella() == cella_partenza && !(this.controllaDestinazione(dama_bianca,dama_nera))) { //controllo corrispondenza cella

         			if (this.effettuaPresa(dama_bianca, dama_nera, colore)) {

             			dama_nera[k].spostaPedina(cella_destinazione);
             			eseguito = true;

                 	
                 	         			
         			
                 	trovato = true;
                 	k=12;
         		}
                 
         		if (k == 11 && trovato == false) {
                 	System.out.println("Non e' una tua pedina"); //fine iterazione senza trovare alcuna pedina
                 }
             }
		    	}
		}
		else {
			System.out.println("Ho un problema con il riconoscimento del colore");
		        }
		
     		return eseguito;
     		 }
    
	
    /** Sposta la pedina indicata nella cella stabilita e restituisce true se tutto é stato eseguito correttamente */	
	private boolean spostaPedina(Dama dama_nera[], Dama dama_bianca[], String colore){
		boolean trovato = false;
		boolean eseguito = false;
		System.out.println(colore);
                                                
		if (colore.contentEquals("bianco")) {               //inizio riconoscimento del colore

 			if (!(this.controllaDestinazione(dama_bianca, dama_nera))) { //controlla se la casella di destinazione é libera

			for (int k = 0; k < 12; k++) {                               //controlla se tra le dame c'é una che si trova in quel riquadro(casella)
         		if (dama_bianca[k].rileva_cella() == cella_partenza && !(this.controllaDestinazione(dama_bianca,dama_nera))) { //controllo corrispondenza cella


             			dama_bianca[k].spostaPedina(cella_destinazione);
         			
             			eseguito = true;

                 	
                 	trovato = true;
                 	k=12;
         		}
         		
         		 if (k == 11 && trovato == false) {
                 	System.out.println("Non e' una tua pedina"); //fine iterazione senza trovare alcuna pedina
                 }
         		
             }
		}
		}
		else if (colore.contentEquals("nero")) {

 			if (!(this.controllaDestinazione(dama_bianca, dama_nera))) {

           for (int k = 0; k < 12; k++) { //controlla se tra le dame c'é una che si trova in quel riquadro(casella)
         		if (dama_nera[k].rileva_cella() == cella_partenza && !(this.controllaDestinazione(dama_bianca,dama_nera))) { //controllo corrispondenza cella


             			dama_nera[k].spostaPedina(cella_destinazione);
             			eseguito = true;

                 	
                 	         			
         			
                 	trovato = true;
                 	k=12;
         		}
                 
         		if (k == 11 && trovato == false) {
                 	System.out.println("Non e' una tua pedina"); //fine iterazione senza trovare alcuna pedina
                 }
             }
		    	}
		}
		else {
			System.out.println("Ho un problema con il riconoscimento del colore");
		        }
		
		                                                           	  //fine del riconoscimento del colore
     		return eseguito;
     		}
	
    /** Controlla se é possibile eseguire le operazioni di presa semplice richiesta dal giocatore e restituisce true in caso positivo*/
     private boolean controlloPresa(Dama dama_bianca[], Dama dama_nera[],String colore) { 
	boolean presente = false;
	
	
	if (colore.contentEquals("nero")) {

		if (this.controlloRiga()) {
	for(int i = 0 ; i < 12 ; i++) {

		if((dama_bianca[i].rileva_cella() == (this.cella_partenza + 4)) || dama_bianca[i].rileva_cella() == (this.cella_partenza + 5))  {
			
			if (dama_bianca[i].tipoPedina().contentEquals("dama")){
				
				if(dama_nera[indice_dama].tipoPedina().contentEquals("dama")) {
					presente = true;
					 i = 12;
				}
			}
			 else {
			
			presente = true;
			 i = 12;
			}
		}
	}
	}
		
    if (!(this.controlloRiga())) {
	for(int i = 0 ; i < 12 ; i++) {
		if((dama_bianca[i].rileva_cella() == (this.cella_partenza + 4)) || dama_bianca[i].rileva_cella() ==(this.cella_partenza + 3))  {
			
if (dama_bianca[i].tipoPedina().contentEquals("dama")){
				
				if(dama_nera[indice_dama].tipoPedina().contentEquals("dama")) {
					presente = true;
					 i = 12;
				}
			}
			 else {
			
			presente = true;
			i = 12;
		 }
		}
	}
	}
		
	}
	if (colore.contentEquals("bianco")) {

		if (this.controlloRiga()) {
	for(int i = 0 ; i < 12 ; i++) {

		if((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) || dama_nera[i].rileva_cella() ==(this.cella_partenza - 3))  {
			
if (dama_nera[i].tipoPedina().contentEquals("dama")){
				
				if(dama_bianca[indice_dama].tipoPedina().contentEquals("dama")) {
					presente = true;
					 i = 12;
				}
			}
			 else {
			
			presente = true;
			i = 12;
		 }
		}
	}
	}
				
	if (!(this.controlloRiga())) {
	for(int i = 0 ; i < 12 ; i++) {

		if((dama_nera[i].rileva_cella() == (this.cella_partenza - 4)) || dama_nera[i].rileva_cella() ==(this.cella_partenza - 5))  {
			
if (dama_nera[i].tipoPedina().contentEquals("dama")){
				
				if(dama_bianca[indice_dama].tipoPedina().contentEquals("dama")) {
					presente = true;
					 i = 12;
				}
			}
			 else {
			
			presente = true;
			i = 12;
         }
		}
	}
	}

	}
	
return presente;
	
}

}