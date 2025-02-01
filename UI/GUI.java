package UI;
import DB.DB_Control;
import Map.GoogleAPI;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	public DB_Control con = DB_Control.GetInstance();
	public GoogleAPI api = new GoogleAPI();
	
	JPanel cardPanel;
	GUI gui;
	CardLayout card;

	public void setFrame(GUI guiro) {

		JFrame jf = new JFrame();
		LoginPanel lp = new LoginPanel(guiro);
		SignupPanel sp = new SignupPanel(guiro);
		CheckPanel cp = new CheckPanel(guiro);
		adminUser au = new adminUser(guiro);
		
		card = new CardLayout();

		cardPanel = new JPanel(card);
		cardPanel.add(lp.mainPanel, "Login");//1
		cardPanel.add(sp.mainPanel, "Register");//2
		cardPanel.add(cp.mainPanel, "Main");//3
		cardPanel.add(au.mainPanel, "adminUser");//4

		jf.add(cardPanel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 700);
		jf.setVisible(true);
	}

}