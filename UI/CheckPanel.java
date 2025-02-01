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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CheckPanel extends JPanel{

	GUI gui;
	JPanel mainPanel;
	JPanel subPanel;	
	
	
	JButton selectButton;
	JButton addrButton;
	JButton cancelButton;
	
	Font font = new Font("배송조회", Font.BOLD, 40);
	Font sub_font = new Font("배송조회", Font.BOLD, 25);
	
	JLabel Map;	
	String start = null;
	
	
	public CheckPanel(GUI gui){
		this.gui = gui;
		
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel titleLabel = new JLabel("배송조회 화면 ");
		titleLabel.setFont(font);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		//label
		JLabel selecttnLabel = new JLabel("배송 조회할 아이디 : ");
		JLabel tnLabel = new JLabel("운송장번호 : ");
		JLabel nameLabel = new JLabel("이름 : ");
		JLabel phoneLabel = new JLabel("전화번호 : ");
		JLabel addrLabel = new JLabel("주소 : ");
		JLabel dsLabel = new JLabel("현재위치 : ");
		JLabel deLabel = new JLabel("도착위치 : ");

		//textbox
		JTextField select_txt = new JTextField(15);
		JTextField tn_txt = new JTextField(15);
		JTextField name_txt = new JTextField(15);
		JTextField phone_txt = new JTextField(15);
		JTextField addr_txt = new JTextField(15);
		JTextField ds_txt = new JTextField(15);
		JTextField de_txt = new JTextField(15);
		
		// readonly
		tn_txt.setEditable(false);
		name_txt.setEditable(false);
		phone_txt.setEditable(false);
		addr_txt.setEditable(false);
		ds_txt.setEditable(false);
		de_txt.setEditable(false);
		
		//그리드
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30, 5, 0, 0);

		c.gridx = 0;
		c.gridy = 0;
		subPanel.add(selecttnLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		subPanel.add(select_txt, c); //운송장검색
		
		c.gridx = 2;
		c.gridy = 0;
		subPanel.add(selectButton = new JButton("검색"), c);

		
		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(tnLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(tn_txt, c);

		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		subPanel.add(name_txt, c);

		c.gridx = 0;
		c.gridy = 3;
		subPanel.add(phoneLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		subPanel.add(phone_txt, c);

		c.gridx = 0;
		c.gridy = 4;
		subPanel.add(addrLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		subPanel.add(addr_txt, c);
		
		
		c.gridx = 2;
		c.gridy = 4;
		subPanel.add(addrButton = new JButton("지도"), c);

		c.gridx = 0;
		c.gridy = 5;
		subPanel.add(dsLabel, c);
		

		c.gridx = 1;
		c.gridy = 5;
		subPanel.add(ds_txt, c);

		c.gridx = 0;
		c.gridy = 6;
		subPanel.add(deLabel, c);

		
		c.gridx = 1;
		c.gridy = 6;
		subPanel.add(de_txt, c);
		
		

		//registerButton = new JButton("회원가입");
		//registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainPanel.add(titleLabel);
		mainPanel.add(subPanel);
		mainPanel.add(cancelButton = new JButton("취소"));
		//mainPanel.add(registerButton);
		
		selectButton.addActionListener(new ActionListener() { // 검색버튼

					@Override
					public void actionPerformed(ActionEvent e) {
						List<String> list = new ArrayList<String>();
						list = gui.con.Select(select_txt.getText());
						if (list.size()<6) {
							JOptionPane.showMessageDialog(null,
									"아이디를 다시 확인해주세요", "검색 오류", 1);
						} else {
							tn_txt.setText(list.get(0));
							ds_txt.setText(list.get(1));
							de_txt.setText(list.get(2));
							name_txt.setText(list.get(3));
							phone_txt.setText(list.get(4));
							addr_txt.setText(list.get(5));
							list.clear();
						}

					}
				});
		
		addrButton.addActionListener(new ActionListener() { // 검색버튼

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame mapframe = new JFrame();
				//gui.api.downloadMap(addr_txt.getText());
				gui.api.downloadMap(ds_txt.getText(), de_txt.getText());
				Map = new JLabel(gui.api.getMap(de_txt.getText()));
				gui.api.fileDelete(de_txt.getText());	
				mapframe.add(Map);
				
				mapframe.setTitle("google mpas");
				mapframe.setVisible(true);
				mapframe.pack();		
				
			}
		});
		
		
		cancelButton.addActionListener(new ActionListener() { // 취소

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				select_txt.setText("");
				tn_txt.setText("");
				name_txt.setText("");
				phone_txt.setText("");
				addr_txt.setText("");
				ds_txt.setText("");
				de_txt.setText("");

				gui.card.show(gui.cardPanel,"Login"); // 로그인 화면
			}
		});
		
	}
}