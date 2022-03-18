package ma.fstm.ilisi.gestioncantact.model.bo;

import java.io.Serializable;

public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private String tele;
	private String nom;    
	private String prenom;

	public int getId() {
		return id;    
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tel) {
		this.tele = tel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Contact( String tel, String nom, String prenom) {
		this.tele = tel;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Contact() {
		
	}
	@Override
	public String toString() {
		return " [id=" + id + ", tele=" + tele + ", nom=" + nom + ", prenom=" + prenom + "]";
	}



	    


    
}