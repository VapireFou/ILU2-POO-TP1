package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {

		private Etal[] etals;

		private Marche(int nbr_etal) {
			this.etals= new Etal[nbr_etal];
			for (int i = 0; i<nbr_etal; i++) {
				etals[i] = null;
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			Etal etal = new Etal();
			etal.occuperEtal(vendeur, produit, nbProduit);
			if (indiceEtal < this.etals.length)
				this.etals[indiceEtal] = etal;
		}
		
		private int trouverEtalLibre() {
			int libre = -1;
			int i = 0;
			while (i < this.etals.length && libre == -1){
				if(etals[i] == null)
					libre = i ;
				i++;
			}
			
			return libre;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbr_etal = 0; 
			for (int i = 0; i < etals.length; i++) {
				if (etals[i] != null && etals[i].contientProduit(produit)) 
					nbr_etal ++;
			}
			
			Etal[] liste = new Etal[nbr_etal];
			int indice_tab = 0;
			for (int e = 0; e < etals.length; e++) {
				if (etals[e] != null && etals[e].contientProduit(produit))
					liste[indice_tab] = etals[e];
			}
			
			return liste;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal find = null;
			int i = 0;
			while (i < etals.length && find == null) {
				if (etals[i].getVendeur() == gaulois)
					find = etals[i];
				i++;
			}
		return find;
		}
		private void afficherMarche() {
			int nbrEtalVide = 0;
			for (int i =0; i < etals.length; i++) {
				if (etals[i] == null)
					nbrEtalVide ++;
				else
					etals[i].afficherEtal();
			}
			System.out.println("Il reste " + nbrEtalVide + " étals non utilisés dans le marché.\n");
		}
	}
	
	public static void main(String[] args) {
		Marche marche = new Marche(10);
		System.out.println(marche.trouverEtalLibre() );
		marche.utiliserEtal(0, new Gaulois("Asterix", 12), "truc", 12);
		System.out.println(marche.trouverEtalLibre());
	}
	
};