package ma.fstm.ilisi.gestioncantact.controller;


import java.util.List;
import java.sql.SQLException;

import ma.fstm.ilisi.gestioncantact.model.bo.Contact;
import ma.fstm.ilisi.gestioncantact.model.dao.DAOContact;
import ma.fstm.ilisi.gestioncantact.view.creation;
import ma.fstm.ilisi.gestioncantact.view.main;



public class CantactController {
	List<Contact> contacts =null;
	public CantactController() {
		// TODO Auto-generated constructor stub
	}
	

	/*
	
	public String ajouter(String s1, String S2) {
	Resultat re = new Resultat();
	ContactView con = new ContactView();
	return "";
	
	9WAADAAAAA ASSAAAAT 
	                         
	}*/
	
	public void redirection()
	{
		creation cre = new creation();
		cre.setVisible(true);
		main ma =new main();
		ma.setVisible(false);
		
	}
	public  void creation(String nom,String tele,String prenom) throws SQLException
	{
		Contact c = new Contact(nom, tele,prenom);
		new DAOContact().create(c);
		main ma =new main();
		ma.setVisible(true);
		creation cre = new creation();
		cre.setVisible(false);
		
	}
	
	public  List<Contact> retrve() throws SQLException
	{
		
		return new DAOContact().retrieve();
	}
	 public boolean  deleteContact(Contact c){
	        boolean b= DAOContact.getDAOContact().delete(c);
	        main ma =new main();
			ma.setVisible(true);
	        if( b && contacts!=null)
	                contacts.remove(c);
	        return b;
	    }
}
