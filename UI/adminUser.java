package UI;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 회원가입 ui
public class adminUser extends JPanel{

	GUI gui;
	JPanel mainPanel;
	JPanel subPanel;

	
	JButton updateButton;
	JButton cancelButton;
	
	
	JTextField id_txt;
	JTextField tn_txt;
	JTextField name_txt;
	JTextField phone_txt;
	JTextField ds_txt;
	JTextField de_txt;
	
	String selectedID = "";
	Font font = new Font("회원가입", Font.BOLD, 40);
	
	List<String> idList = null;
	JComboBox<String> idComboBox = null;


	public adminUser(GUI gui) {

		this.gui = gui;
		getIDS();
		
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel addminLabel = new JLabel("관리자 권환 ");
		addminLabel.setFont(font);
		addminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		
		JLabel idLabel = new JLabel("아이디 : ");
		JLabel tnLabel = new JLabel("운송장번호 : ");
		JLabel nameLabel = new JLabel("이름 : ");
		JLabel phoneLabel = new JLabel("전화번호 : ");
		JLabel dsLabel = new JLabel("현재위치 : ");
		JLabel deLabel = new JLabel("도착장소 : ");
		
		JTextField id_txt = new JTextField(15);
		JTextField tn_txt = new JTextField(15);
		JTextField name_txt = new JTextField(15);
		JTextField phone_txt = new JTextField(15);
		JTextField ds_txt = new JTextField(15);
		JTextField de_txt = new JTextField(15);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30, 5, 0, 0);
		
		c.gridx = 0;
		c.gridy = 0;
		subPanel.add(new JLabel("ID목록"), c);
		
		c.gridx = 1;
		c.gridy = 0;
		subPanel.add(idComboBox, c);


		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(idLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(id_txt, c);
		
		
		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(tnLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		subPanel.add(tn_txt, c);

		c.gridx = 0;
		c.gridy = 3;
		subPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		subPanel.add(name_txt, c);

		c.gridx = 0;
		c.gridy = 4;
		subPanel.add(phoneLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		subPanel.add(phone_txt, c);
		
		c.gridx = 0;
		c.gridy = 5;
		subPanel.add(dsLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		subPanel.add(ds_txt, c);

		c.gridx = 2;
		c.gridy = 5;
		subPanel.add(updateButton = new JButton("지번주소 수정"), c);
		updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		c.gridx = 0;
		c.gridy = 6;
		subPanel.add(deLabel, c);

		c.gridx = 1;
		c.gridy = 6;
		subPanel.add(de_txt, c);	
		
		
		cancelButton = new JButton("취소");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		mainPanel.add(addminLabel);
		mainPanel.add(subPanel);
		mainPanel.add(cancelButton);
		
		
		cancelButton.addActionListener(new ActionListener() { // 취소

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				id_txt.setText("");
				tn_txt.setText("");
				name_txt.setText("");
				phone_txt.setText("");
				ds_txt.setText("");
				de_txt.setText("");
				
				gui.card.show(gui.cardPanel,"Login"); // 로그인 화면
			}
		});
		
		updateButton.addActionListener(new ActionListener() { // 주소 수정

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gui.con.DS_Update(selectedID, ds_txt.getText());

					}
				});
		idComboBox.addActionListener(new ActionListener() { // 주소 수정

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				List<String> s_list = new ArrayList<String>();
				JComboBox<String> idBox = (JComboBox) e.getSource();
				getIDS();
				selectedID = idBox.getSelectedItem().toString();
				s_list = gui.con.Select(selectedID);


				id_txt.setText(selectedID);
				tn_txt.setText(s_list.get(0));
				ds_txt.setText(s_list.get(1));
				de_txt.setText(s_list.get(2));
				name_txt.setText(s_list.get(3));
				phone_txt.setText(s_list.get(4));
				
				s_list.clear();
			}
		});

		
		
	}
	public void getIDS(){
		idList = gui.con.Select_AllID();
		idComboBox = new JComboBox<String>(idList.toArray(new String[idList.size()]));
	}

}