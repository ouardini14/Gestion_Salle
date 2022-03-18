package ma.fstm.ilisi.gestioncantact.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ma.fstm.ilisi.gestioncantact.controller.CantactController;
import ma.fstm.ilisi.gestioncantact.model.bo.Contact;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel dlm = new DefaultListModel();
		try {
			for(Contact c : new CantactController().retrve()){
			     dlm.addElement(c);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		JList list = new JList(dlm);
		
		
		 
		list.setBounds(54, 37, 319, 182);
		contentPane.add(list);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CantactController().redirection();
			} 
		});
		btnAjouter.setBounds(54, 232, 85, 21);
		contentPane.add(btnAjouter);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact contact= (Contact) list.getSelectedValue(); 
				
				new CantactController().deleteContact(contact);
			}
		});
		button.setBounds(161, 229, 85, 21);
		contentPane.add(button);
		
		JButton button_1 = new JButton("uPDATE");
		button_1.setBounds(288, 229, 85, 21);
		contentPane.add(button_1);
		
	}

}
