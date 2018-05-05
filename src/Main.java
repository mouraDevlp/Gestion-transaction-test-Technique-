import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

import org.omg.IOP.TransactionService;


/**
 * Nos magasins produisent tous les jours des fichiers de logs contenant les informations relatives à leur activité de vente
 * journalière. De plus, chaque magasin possède son propre référentiel de prix journalier.
 * Le fichier des transactions journalières contient ces infos: txId|datetime|magasin|produit|qte
 * Et celui du référentiel produit: produit|prix
 * 
 * où :
 * txId : id de transaction (nombre)
 * datetime : date et heure au format ISO 8601
 * magasin : UUID identifiant le magasin
 * produit : id du produit (nombre)
 * qte : quantité (nombre)
 * prix : prix du produit en euros
 * 
 * Besoin : 
 * Déterminer, pour chaque heure, les 100 produits qui ont les meilleures ventes 
 * et ceux qui génèrent le plus gros Chiffre d'Affaire par magasin.
 */
public class Main {

	public static void main(String[] args) throws IOException {

		List<Transaction> transactions = loadTransactions();
		System.out.println(transactions.size());
		loadTransationsPrix(transactions);

		//printByHour(transactions);
		printByMagasin(transactions);
	}

	private static List<String> loadLines(String path) throws IOException {
		return Files.readAllLines(Paths.get(path));
	}


	private static List<Transaction> loadTransactions() throws IOException {
		List<String> lines = loadLines("transactions_20150114.txt");
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		String[] mots;
		Transaction transaction;
		
		for(String line : lines) {
			mots = null;
			mots = line.split("\\|");

			transaction = null;
			transaction = new Transaction();

			transaction.setTxtId(mots[0]);
			transaction.setDate(mots[1]);
			transaction.setMagasin(mots[2]);
			transaction.setIdProduit(mots[3]);
			transaction.setQuantite(Integer.parseInt(mots[4]));	

			transactions.add(transaction);	
		}

		return transactions;
	}

	private static void loadTransationsPrix(List<Transaction> transactions) throws IOException {
		List<String> lines = loadLines("reference_prod-8e588f2f-d19e-436c-952f-1cdd9f0b12b0_20150114.txt");
		
		// 1ère méthode
		Map<String, String> produitsPrixMap = new TreeMap<>();
		for(String line : lines) {
			produitsPrixMap.put(line.split("\\|")[0], line.split("\\|")[1]);
		}
		
		for(Transaction transaction : transactions) {
			transaction.setPrix(Double.parseDouble(produitsPrixMap.get(transaction.getIdProduit()) == null ? "0" : produitsPrixMap.get(transaction.getIdProduit())));
		}
		
		
		// 2ème méthode
//		boolean trouve;
//		
//		for(Transaction transaction : transactions) {
//			trouve = false;
//			for(String line : lines) {
//				if (transaction.getIdProduit().equals(line.split("\\|")[0])) {
//					transaction.setPrix(Double.parseDouble(line.split("\\|")[1]));
//					trouve = true;
//					break;	
//				}
//			}
//			if (!trouve) transaction.setPrix(0.0); 
//		}
	}

	private static void printByHour(List<Transaction> transactions) {
		Map<String,List<Transaction>> hourMap = new TreeMap<>();
		
		for(Transaction transaction : transactions) {
			String hour = transaction.getDate().substring(0,11);
			hourMap.computeIfAbsent(hour,k->new ArrayList<Transaction>()).add(transaction);
		}
		
		//TODO
		for(Entry<String, List<Transaction>> entry : hourMap.entrySet()) {
		    String cle = entry.getKey();
		    List<Transaction> valeur = entry.getValue();
		    
		    // traitements
			Collections.sort(valeur, new compareTransactionByQuantityDesc());	
			System.out.println();
			System.out.println();
			
			System.out.println("Heure : " + cle);
			System.out.println();
			for (int i = 0; i < valeur.size();i++) {
				System.out.println(valeur.get(i));
			}
		}
	}
	private static void printByMagasin(List<Transaction> transactions) {
		Map<String,List<Transaction>> magasinMap = new TreeMap<>();
		
		for(Transaction transaction : transactions) {
			magasinMap.computeIfAbsent(transaction.getMagasin(),k->new ArrayList<Transaction>()).add(transaction);
		}
		
		//TODO
		for(Entry<String, List<Transaction>> entry : magasinMap.entrySet()) {
		    String cle = entry.getKey();
		    List<Transaction> valeur = entry.getValue();
		    
		    // traitements
			Collections.sort(valeur, new compareTransactionByChiffreAffaireDesc());
			System.out.println();
			System.out.println();
			
			System.out.println("magasin : " + cle);
			System.out.println();
			for (int i = 0; i < 100; i++) {
				System.out.println(valeur.get(i));	
			}
		}
		

	}
	
	
}