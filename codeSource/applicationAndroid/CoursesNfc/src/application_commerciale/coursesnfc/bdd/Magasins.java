package application_commerciale.coursesnfc.bdd;

public class Magasins {
	private int magasin_id;
	private String magasin_nom;
	private String magasin_adresse;

	public Magasins(int id, String nom, String adresse){
		magasin_id = id;
		magasin_nom = nom;
		magasin_adresse = adresse;
	}
	public int getMagasin_id() {
		return magasin_id;
	}

	public void setMagasin_id(int magasin_id) {
		this.magasin_id = magasin_id;
	}
	
	public String getMagasin_nom(){
		return magasin_nom;
	}
	
	public String getMagasin_adresse(){
		return magasin_adresse;
	}
}
