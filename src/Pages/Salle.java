package Pages;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Salle extends JFrame  {
    private JPanel contentPane;
    private JTextField num;
    private JComboBox type;
    private JTextField prof;
    private JComboBox reservation;
    private JComboBox departement;
    private JTextField Date;
    private JTextField hr;
    JLabel lbldepartement;
    JLabel lblDate;
    JLabel lblprof;
    JLabel lbltype;
    JLabel lblreservation;
    JLabel lblnum;
    JLabel lblhr;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Salle frame = new Salle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public int Get_Res_Num(int num,int id,Date date,String heur_rs){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");
            String sql = "Select num_res from reservation where num='"+num+"'and id='"+id+"',date_res='"+date+"',heur_rs='"+heur_rs+"'";

            pst = conn.prepareStatement(sql);
            ResultSet  rs = pst.executeQuery();
        if(rs.next()==true){
            return rs.getInt(1);
        }

        }
        catch (Exception e){

        }
        return 1;
    }
    public Salle() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 421);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(SystemColor.window);
        panel.setBounds(0, -32, 655, 421);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.controlDkShadow);
        panel_1.setBounds(0, 0, 126, 444);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblWelcomeAdmin = new JLabel("Welcome Admin");
        lblWelcomeAdmin.setForeground(Color.WHITE);
        lblWelcomeAdmin.setBounds(10, 37, 112, 30);
        panel_1.add(lblWelcomeAdmin);

        JButton Disconnect = new JButton("se deconnecter");
        Disconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginM frames = new LoginM();
                frames.setTitle("Connexion");
                frames.setVisible(true);
                dispose();
            }
        });
        Disconnect.setBounds(6, 354, 114, 25);
        panel_1.add(Disconnect);
        Disconnect.setForeground(Color.WHITE);
        Disconnect.setFont(new Font("Dialog", Font.BOLD, 14));
        Disconnect.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Disconnect.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        Disconnect.setBackground(new Color(204, 102, 102));

        JButton actualiser = new JButton("Actualiser");

        actualiser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Salle frame = new Salle();
                    frame.setTitle("Gestion des salles");
                    frame.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, e);
                }
            }
        });
        actualiser.setBounds(6, 150, 114, 25);
        panel_1.add(actualiser);
        actualiser.setForeground(Color.WHITE);
        actualiser.setFont(new Font("Dialog", Font.BOLD, 14));
        actualiser.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        actualiser.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        actualiser.setBackground(new Color(204, 102, 102));


        JButton sauvegarder = new JButton("Ajouter une salle");
        sauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");
                    String sql = "Select *from salle where num='"+num.getText()+"'";

                    pst = conn.prepareStatement(sql);
                    ResultSet  rs = pst.executeQuery();
                   if(rs.next()== true){
                       JOptionPane.showMessageDialog(panel,"Une salle avec ce numero deja existe!!");
                   }
                    else {

                        if(reservation.getSelectedItem().equals("non")) {
                            String sql2 = "insert into salle (num,type,reserve,departement) values('"+num.getText()+"','"+type.getSelectedItem()+"','"+reservation.getSelectedItem()+"','"+departement.getSelectedItem()+"')";
                            pst = conn.prepareStatement(sql2);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(panel,"Une nouvelle salle a été ajouté");
                            type.setSelectedItem("cours");
                            departement.setSelectedItem("Informatique");
                            reservation.setSelectedItem("non");
                            lblprof.setVisible(false);
                            lblDate.setVisible(false);
                            prof.setVisible(false);
                            Date.setVisible(false);
                            lblhr.setVisible(false);
                            hr.setVisible(false);
                            hr.setText(" ");
                            prof.setText(" ");
                            Date.setText(" ");
                        }
                       if(reservation.getSelectedItem().equals("oui")) {
                           if (prof.getText().equals("") || Date.getText().equals("")|| num.getText().equals("")||hr.getText().equals("")) {
                               JOptionPane.showMessageDialog(panel, "Veulliez remplir tous les champs !!");
                           }
                           if (!isDateValid(Date.getText())) {
                               JOptionPane.showMessageDialog(panel, "Veulliez respecter la format du date !!");
                           } else {
                               String sql3= "select id from user_en where lname='" + prof.getText() + "'";
                               pst = conn.prepareStatement(sql3);
                               ResultSet rs1= pst.executeQuery();

                               if(rs1.next()==true){
                               String sql2 = "insert into salle  values('" + num.getText() + "','" + type.getSelectedItem() + "','" + reservation.getSelectedItem() + "','" + rs1.getInt(1) + "','" + Date.getText() + "','" + hr.getText() + "','" + departement.getSelectedItem() + "')";
                               pst = conn.prepareStatement(sql2);
                               pst.executeUpdate();

                                   String sql4 = "insert into reservation(num,date_res,heur_res,id) values('" + num.getText() + "','" + Date.getText() + "','" + hr.getText() + "','" + rs1.getInt(1) + "')";
                                   pst = conn.prepareStatement(sql4);
                                   pst.executeUpdate();
                               JOptionPane.showMessageDialog(panel,"Une nouvelle salle a été ajouté");
                               type.setSelectedItem("cours");
                               departement.setSelectedItem("Informatique");
                               reservation.setSelectedItem("non");
                               lblprof.setVisible(false);
                               lblDate.setVisible(false);
                               prof.setVisible(false);
                               Date.setVisible(false);
                                   lblhr.setVisible(false);
                                   hr.setVisible(false);
                                   hr.setText(" ");
                               prof.setText(" ");
                               Date.setText(" ");
                               num.setText(" ");
                           }
                               else{
                                   JOptionPane.showMessageDialog(panel,"Aucun utilisateur avec ce nom!!");


                               }
                           }
                       }


                   }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel,e);
                }

            }
        });
        sauvegarder.setBounds(6, 302, 114, 25);
        panel_1.add(sauvegarder);
        sauvegarder.setForeground(Color.WHITE);
        sauvegarder.setFont(new Font("Dialog", Font.BOLD, 14));
        sauvegarder.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        sauvegarder.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        sauvegarder.setBackground(new Color(204, 102, 102));

        JButton Supprimer = new JButton("Supprimer");
        Supprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Connection conn = null;
                PreparedStatement pst = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");


                   String sql2 = "delete from reservation where num='"+num.getText()+"'";
                    pst = conn.prepareStatement(sql2);
                    pst.executeUpdate();

                    String sql = "delete from salle where num='"+num.getText()+"'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(panel,"la salle numero "+num.getText()+" est supprimé");
                    conn.close();
                    type.setSelectedItem("cours");
                    departement.setSelectedItem("Informatique");
                    reservation.setSelectedItem("non");
                    lblprof.setVisible(false);
                    lblDate.setVisible(false);
                    prof.setVisible(false);
                    Date.setVisible(false);
                    lblhr.setVisible(false);
                    hr.setVisible(false);
                    hr.setText(" ");
                    prof.setText(" ");
                    Date.setText(" ");
                    num.setText(" ");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel,e);
                }


            }
        });
        Supprimer.setForeground(Color.WHITE);
        Supprimer.setFont(new Font("Dialog", Font.BOLD, 14));
        Supprimer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Supprimer.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        Supprimer.setBackground(new Color(204, 102, 102));
        Supprimer.setBounds(6, 245, 114, 25);
        panel_1.add(Supprimer);

        JButton modifier = new JButton("Modifier");
        modifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");
                    String sql = "Select *from salle where num='"+num.getText()+"'";

                    pst = conn.prepareStatement(sql);
                    ResultSet  rs = pst.executeQuery();
                    if(rs.next()== true) {

                        if(reservation.getSelectedItem().equals("non")) {
                            String sql2 = " update salle set type='"+type.getSelectedItem()+"',reserve='"+reservation.getSelectedItem()+"',id=NULL,date=NULL,hour=NULL,departement='"+departement.getSelectedItem()+"' where num='"+num.getText()+"' ";
                            pst = conn.prepareStatement(sql2);
                            pst.executeUpdate();

                            String sql3 ="delete from reservation where num='"+num.getText()+"'";
                            pst = conn.prepareStatement(sql3);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(panel,"La salle numero "+num.getText()+" a été modifié");
                            type.setSelectedItem("cours");
                            departement.setSelectedItem("Informatique");
                            reservation.setSelectedItem("non");
                            lblprof.setVisible(false);
                            lblDate.setVisible(false);
                            prof.setVisible(false);
                            Date.setVisible(false);
                            lblhr.setVisible(false);
                            hr.setVisible(false);
                            hr.setText(" ");
                            prof.setText(" ");
                            Date.setText(" ");
                        }
                        if(reservation.getSelectedItem().equals("oui")) {
                            if (prof.getText().equals("") || Date.getText().equals("")|| num.getText().equals("")|| hr.getText().equals("")) {
                                JOptionPane.showMessageDialog(panel, "Veulliez remplir tous les champs !!");
                            }
                            if (!isDateValid(Date.getText())) {
                                JOptionPane.showMessageDialog(panel, "Veulliez respecter la format du date !!");
                            } else {
                                String sql3= "select id from user_en where lname='" + prof.getText() + "'";
                                pst = conn.prepareStatement(sql3);
                                ResultSet rs1= pst.executeQuery();

                                if(rs1.next()==true)  {

                                    String sql6= "select  *from reservation where  num='"+num.getText()+"' AND date_res='"+Date.getText()+"' AND heur_res='"+hr.getText()+"'";
                                    pst = conn.prepareStatement(sql6);
                                    ResultSet rs6= pst.executeQuery();
                                    if(rs6.next()==true)  {
                                        String sql7 = " update reservation set id='"+rs1.getInt(1)+"' where num='"+num.getText()+"' AND date_res='"+Date.getText()+"' AND heur_res='"+hr.getText()+"'";
                                        pst = conn.prepareStatement(sql7);
                                        pst.executeUpdate();

                                    }
                                    else {
                                        String sql4 = "insert into reservation(num,date_res,heur_res,id) values('" + num.getText() + "','" + Date.getText() + "','" + hr.getText() + "','" + rs1.getInt(1) + "')";
                                        pst = conn.prepareStatement(sql4);
                                        pst.executeUpdate();
                                    }
                                    String sql2 = " update salle set type='"+type.getSelectedItem()+"',reserve='"+reservation.getSelectedItem()+"' ,id='"+rs1.getInt(1)+"',date='"+Date.getText()+"',hour='"+hr.getText()+"',departement='"+departement.getSelectedItem()+"' where num='"+num.getText()+"' ";
                                    pst = conn.prepareStatement(sql2);
                                    pst.executeUpdate();


                                    JOptionPane.showMessageDialog(panel,"La salle numero "+num.getText()+" a été modifié");
                                    type.setSelectedItem("cours");
                                    departement.setSelectedItem("Informatique");
                                    reservation.setSelectedItem("non");
                                    lblprof.setVisible(false);
                                    lblDate.setVisible(false);
                                    prof.setVisible(false);
                                    Date.setVisible(false);
                                    lblhr.setVisible(false);
                                    hr.setVisible(false);
                                    prof.setText(" ");
                                    Date.setText(" ");
                                    num.setText(" ");
                                }
                                else{
                                    JOptionPane.showMessageDialog(panel,"Aucun utilisateur avec ce nom!!");
                                }
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(panel,"Aucune salle avec ce numero!!");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel,e);
                }
            }
        });
        modifier.setForeground(Color.WHITE);
        modifier.setFont(new Font("Dialog", Font.BOLD, 14));
        modifier.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        modifier.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        modifier.setBackground(new Color(204, 102, 102));
        modifier.setBounds(6, 190, 114, 25);
        panel_1.add(modifier);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(125, 27, 551, 417);
        panel.add(panel_2);
        panel_2.setLayout(null);


        num= new JTextField();
        num.setBounds(25, 39, 195, 35);
        panel_2.add(num);
        num.setColumns(10);

         lblnum= new JLabel("Numero de la salle :");
        lblnum.setBounds(25, 12, 125, 15);
        panel_2.add(lblnum);


         lbltype = new JLabel("type :");
        lbltype.setBounds(42, 104, 66, 15);
        panel_2.add(lbltype);

        String[] options = { "conference","cours","tp"};
        type = new JComboBox(options);
        type.setBounds(35, 124, 195, 35);
        panel_2.add(type);


        lbldepartement = new JLabel("Departement :");
        lbldepartement.setBounds(42, 165, 100, 15);
        panel_2.add(lbldepartement);

        String[] optio = { "Informatique","Mathematique","Economie"};
        departement  = new JComboBox(optio);
        departement.setBounds(35, 190, 195, 35);
        panel_2.add(departement );

         lblreservation = new JLabel("Resrevé  :");
        lblreservation.setBounds(260, 104, 66, 15);
        panel_2.add(lblreservation);

        String[] optios = { "oui","non"};
        reservation = new JComboBox(optios);
        reservation.setBounds(260, 124, 195, 35);
        panel_2.add(reservation);


         lblprof = new JLabel("Reservé par :");
        lblprof.setBounds(42, 230, 80, 15);
        panel_2.add(lblprof);

        prof = new JTextField();
        prof.setColumns(10);
        prof.setBounds(35, 250, 195, 35);
        panel_2.add(prof);


         lblDate = new JLabel("Date de reservation:(YYYY-MM-DD)");
        lblDate.setBounds(260, 165, 220, 15);
        panel_2.add(lblDate);

        Date = new JTextField();
        Date.setColumns(10);
        Date.setBounds(260, 190, 195, 35);
        panel_2.add(Date);


        lblhr = new JLabel("Heure de reservation:(ex:8:00-10:00)");
        lblhr.setBounds(260, 225, 220, 15);
        panel_2.add(lblhr);

        hr = new JTextField();
        hr.setColumns(10);
        hr.setBounds(260, 250, 195, 35);
        panel_2.add(hr);



        JPanel panel_3 = new JPanel();
        panel_3.setBounds(25, 77, 473, 232);
        panel_2.add(panel_3);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");
            String sql2 = "select  *from salle where reserve='oui'";
            pst = conn.prepareStatement(sql2);
            ResultSet  rs2 = pst.executeQuery();
            int i=0;
            while (rs2.next() == true)
            {i++;}
            JLabel label_1 = new JLabel("Nombre de salle reservé :  "+i);
            label_1.setBounds(25, 321, 220, 15);
            panel_2.add(label_1);

            String sql3 = "select  *from salle ";
            pst = conn.prepareStatement(sql3);
            ResultSet  rs3 = pst.executeQuery();
            int j=0;
            while (rs3.next() == true)
            {j++;}
            JLabel label_2 = new JLabel("Nombre total des salles :  "+j);
            label_2.setBounds(25, 350, 220, 15);
            panel_2.add(label_2);
            }

         catch (Exception e) {
            JOptionPane.showMessageDialog(panel,e);
        }



        reservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (reservation.getSelectedItem().equals("non")) {
                    lblprof.setVisible(false);
                    lblDate.setVisible(false);
                    prof.setVisible(false);
                    Date.setVisible(false);
                    lblhr.setVisible(false);
                    hr.setVisible(false);

                }
                if (reservation.getSelectedItem().equals("oui")) {
                    lblprof.setVisible(true);
                    lblDate.setVisible(true);
                    prof.setVisible(true);
                    Date.setVisible(true);
                    lblhr.setVisible(true);
                    hr.setVisible(true);

                }
            }});
        JButton Rechercher = new JButton("Rechercher");
        Rechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/exam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","anass_2018");
                    String sql2 = "select  *from salle where num='"+num.getText()+"'";
                    pst = conn.prepareStatement(sql2);
                    ResultSet  rs2 = pst.executeQuery();

                    if (rs2.next() == true)
                    { String ty=rs2.getString(2);
                        String ty2=rs2.getString(3);
                        String ty3=rs2.getString(7);
                        reservation.setSelectedItem(ty2);
                        type.setSelectedItem(ty);
                        departement.setSelectedItem(ty3);
                       if(rs2.getString(3).equals("non")){
                           lblprof.setVisible(false);
                           lblDate.setVisible(false);
                           prof.setVisible(false);
                           Date.setVisible(false);
                           lblhr.setVisible(false);
                           hr.setVisible(false);

                       }
                        if(rs2.getString(3).equals("oui"))
                        {/*System.out.print(Get_Res_Num( Integer.parseInt(num.getText()), rs2.getInt(3), rs2.getDate(4), rs2.getString(5)));*/
                            lblprof.setVisible(true);
                            lblDate.setVisible(true);
                            prof.setVisible(true);
                            Date.setVisible(true);
                            lblhr.setVisible(true);
                            hr.setVisible(true);

                            String sql = "select  fname,lname from user_en where id='"+ rs2.getInt(4)+"'";

                            ResultSet  r=null;
                            PreparedStatement p=null;
                            p = conn.prepareStatement(sql);
                            r= p.executeQuery();

                            Date da=rs2.getDate(5);
                            Date.setText(String.valueOf(da));
                            hr.setText(rs2.getString(6));
                           if(r.next()){
                               prof.setText(r.getString(2));
                           }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(panel,"Salle Introuvable");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel,e);
                }
            }
        });



        Rechercher.setForeground(Color.WHITE);
        Rechercher.setFont(new Font("Dialog", Font.BOLD, 14));
        Rechercher.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Rechercher.setBorder(new LineBorder(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"), 2, true));
        Rechercher.setBackground(new Color(204, 102, 102));
        Rechercher.setBounds(280, 44, 114, 25);
        panel_2.add(Rechercher);

    }













}
