package UI;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// ù ui
public class LoginPanel extends JPanel implements ActionListener {

	JPanel mainPanel;
	JPanel subPanel;
	JTextField id_txt;
	JPasswordField pw_txt;
	
	GUI gui;
	Font font = new Font("ȸ������", Font.BOLD, 40);
	

	public LoginPanel(GUI gui) {
		this.gui = gui;

		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel titleLabel = new JLabel("�α��� ȭ�� ");
		titleLabel.setFont(font);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		

		JLabel idLabel = new JLabel(" ���̵� : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		subPanel.add(idLabel, c);

		id_txt = new JTextField(15);
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		subPanel.add(id_txt, c);

		JLabel passLabel = new JLabel(" ��й�ȣ : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20, 0, 0, 0);
		subPanel.add(passLabel, c);

		 
		pw_txt = new JPasswordField(15);
		c.insets = new Insets(20, 5, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(pw_txt, c);


		JPanel buttonPanel = new JPanel();
		JButton loginButton = new JButton("�α���");
		buttonPanel.add(loginButton);

		JButton signupButton = new JButton("ȸ������");
		buttonPanel.add(signupButton);

		JButton adminButton = new JButton("������");
		buttonPanel.add(adminButton);
		
		mainPanel.add(titleLabel);
		mainPanel.add(subPanel);
		mainPanel.add(buttonPanel);
				

		loginButton.addActionListener(this);

		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gui.card.show(gui.cardPanel, "Register");
			}
		});
		
		adminButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gui.card.show(gui.cardPanel, "adminUser");
			}
		});


	}

	// �α��� ��ư
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jb = (JButton) e.getSource();

		String id = id_txt.getText();
		String pw = pw_txt.getText();

		String ret = gui.con.Login(id, pw);

		if (pw.equals(ret)) {
			JOptionPane.showMessageDialog(this, "�α��� ����", "�α��� ����", 1);
			gui.card.show(gui.cardPanel, "Main");
			id_txt.setText("");
			pw_txt.setText("");

		} else
			JOptionPane
					.showMessageDialog(this, "�α��� ����", "�α��� ����", 1);

	}

}
