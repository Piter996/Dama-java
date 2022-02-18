/**
 * <<Boundary>>
 * 
 * Classe principale, a diretto contatto con l'utente, 
 * indirizzando i vari input verso la classe responsabile
 */


package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

//import it.uniba.sotorrent.GoogleDocsUtils;

import java.util.*;
/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */
@SuppressWarnings("unused")
public final class AppMain {
    private static Scanner input;


	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * 	 * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(final String[] args){
System.out.println("Current working dir: " + System.getProperty("user.dir"));

String comando = new String();
Stampa visualizza = new Stampa();
input = new Scanner(System.in);     //test di lettura da tastiera

if (args.length > 0) {
			switch (args[0]) {
			case "it":
				     System.out.println("Applicazione avviata.");
				break;

			case "en":
				     System.out.println("Application started.");
				break;
				
            case "--help":
                     visualizza.stampaAiuto();
                break;
                            
            case "-h":
                     visualizza.stampaAiuto();
                break;

			}
		} else {
			System.out.println("Uso lingua di default 'it'");
			System.out.println("Applicazione avviata.");
		}
 System.out.print("            ***Benvenuti in Dama Italiana*** \n");

  while((comando.contentEquals("esci"))==false)      { 
	  
	        System.out.print("Inserire un comando o digitare help per conoscere i comandi disponibili\n");
	        
	        comando = input.nextLine();
                
     switch (comando) {
  
              case "esci":
	                     System.out.println("Sei sicuro di voler uscire? Digitare si per uscire o altro per continuare");
	                     comando = input.nextLine();

	                    
	                     if(comando.contains("si")) {
	                     System.exit(0);
                         }
                     break;
                     
              case"damiera":
                          visualizza.iniziaPartita();
                     break;
  
              case"numeri":
                          visualizza.stampaNumeri();
                  	 break;
                        	  
			  case "help":
                          visualizza.stampaAiuto();
                     break;
                             
			  case "tempo":    
                          visualizza.iniziaPartita();
                     break;
                         
			  case "mosse": 
                          visualizza.iniziaPartita();
                     break;
                     
			  case "prese": 
				          visualizza.iniziaPartita();
				     break;
                     
              case "gioca":
                        	
                         
                             Dama dama_bianca[] = new Dama[13];                                              
                             Dama dama_nera[] = new Dama[13];
                             Mossa mossa = new Mossa();
                             String turno = "nero";
                             String mossa_eseguita = "primo_giro";
                             
                             List <String> mosse = new ArrayList<>(); 
                                                                                                           
                             
                             for (int i = 0; i < 12; i++) {                             //inizializzazione pedine bianche             
                                Dama transizione = new Dama();                                           
                                transizione.inizializza_pedine_bianche(transizione, i);                 
                                dama_bianca[i] = transizione;                                         
                             }                                                                        
                                                                                                     
                             for (int i = 0; i < 12; i++) {                             //inizializzazione pedine nere          
                                Dama transizione = new Dama();                                    
                                transizione.inizializza_pedine_nere(transizione, i);             
                                dama_nera[i] = transizione;                                    
                             }                                                                 
                                                                                               
                             System.out.print("Damiera inizializzata\n");

                             
                             
                             
                            	Cronometro tempo_bianco = new Cronometro();
                            	
                            	Cronometro tempo_nero = new Cronometro();
                            	
                            	tempo_bianco.azzera();
                            	
                            	tempo_nero.azzera();

                                System.out.println("Cronometro partito\n");               
                              
                             
                             
                             
                                               while((comando.contains("abbandona"))==false){
                                            	   if(turno.contentEquals("nero") && (mossa_eseguita.contentEquals("primo_giro"))) {
                                            		   mossa_eseguita = "nessuna_mossa";

                                                     
                                                       turno = "bianco";
                                                   	   
                                                   	   tempo_bianco.avanza();  

                                                       System.out.println("SEI IL "+turno.toUpperCase());                      //Switch turno
                                            	   }
                                            	   else if ((mossa_eseguita.contentEquals("eseguita")) && (turno.contentEquals("nero"))) {
                                            		   mossa_eseguita = "nessuna_mossa";
                                            		   System.out.println("Cambio giocatore ");
                                                       
                                                       turno = "bianco";
                                                   	   tempo_nero.ferma();
                                                   	   tempo_bianco.avanza();  

                                                       System.out.println("SEI IL "+turno.toUpperCase());         
                                            	   }
                                            	   else if(turno.contentEquals("bianco") && (mossa_eseguita.contentEquals("primo_giro") || mossa_eseguita.contentEquals("eseguita"))) {
                                                      
                                            		   System.out.println("Cambio giocatore ");
                                            		  
                                            		   turno = "nero";
                                            		   mossa_eseguita = "nessuna_mossa";
                                                       
                                            		  tempo_bianco.ferma();  
                                                   	   tempo_nero.avanza();  
                                            		   
                                            		   System.out.println("SEI IL "+turno.toUpperCase());                      //Switch turno

                                            	   }
                                            	   
                                                           System.out.print("Inserire un comando valido o digitare help\n");
                                                                            comando = input.nextLine();
                
                             switch (comando) {
                             
                             case "prese": 
                            	 System.out.print("Bianco: ");
                            	 for (int i = 0; i < 12; i++) {                             //inizializzazione pedine bianche             

                            		 if(dama_nera[i].rileva_cella() == -1) {
                            			 
                                    	 System.out.print(dama_nera[i].stampa_pedina());

                            		 }
                            	 }         
                            	 System.out.println();
                                                                                                          
                            	 System.out.print("Nero: ");
                            	 for (int i = 0; i < 12; i++) {                             //inizializzazione pedine bianche             

                            		 if(dama_bianca[i].rileva_cella() == -1) {
                            			 
                                    	 System.out.print(dama_bianca[i].stampa_pedina());

                            		 }
                            	 } 
                            	 System.out.println();

       				             break;
                             
                             case "mosse": 
                            	        mosse.forEach(System.out::println);
                            	 break;
                            
                             case "damiera":
			                	        visualizza.stampaDamiera(dama_bianca, dama_nera);
                            	 break;
 			             
                             case"numeri":
                                        visualizza.stampaNumeri();
                                 break;
                                                   
                             case "tempo":
                                 System.out.println("Per il Bianco sono passati: "+tempo_bianco.toString()+" minuti");  
                                 System.out.println("Mentre");  
                                 System.out.println("Per il Nero sono passati: "+tempo_nero.toString()+" minuti\n");     
                                 break;
 
		                     case "help":
		                    	       visualizza.stampaAiuto();
                                 break;
                                                          
			                 case "abbandona":
			                	 
                             System.out.print("\nVuoi davvvero abbandonare la partita (la vittoria sara' assegnata all'avversario)? digita si per uscire\n");
		                 
                             comando = input.nextLine();
			           
                             if (comando.contentEquals("si")){
			        	   
                            	 if(turno.contentEquals("bianco")) {
			        	   
                            		 System.out.print("IL NERO HA VINTO\n");
			        	   }
			        	   
                            	 if(turno.contentEquals("nero")) {
				        	
                            		 System.out.print("IL BIANCO HA VINTO\n");
			                                         	   }
			        	   tempo_bianco.ferma();
			        	   tempo_nero.ferma();

			                 comando = "abbandona";

			                                               }
                             break;
                                               
                             default: 
                            	 mossa.decodificaMossa(comando);

                            	 if(mossa.validaMossa(dama_bianca, dama_nera, turno)) {

                            		 if (mossa.suddividiTipologia(dama_nera,dama_bianca,turno)) {
                         		   
                            	           mossa_eseguita = "eseguita";
                            	           
                            	           visualizza.stampaDamiera(dama_bianca, dama_nera);
                            	           
                            	           if(turno.contentEquals("bianco")) { 
                            	        	   mosse.add("B:"+comando);
                            	           }
                            	           else if(turno.contentEquals("nero")) {
                            	        	   mosse.add("N:"+comando);
                            	           }
                            	                                                                      }
                             }
                              else {
                            	  System.out.println("Mossa inserita non valida");
                              }
                             }
                                                                                                      }
	                      }
}
}
}
