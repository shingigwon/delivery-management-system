package UI;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

// ȸ������ ui
public class SignupPanel extends JPanel {

	GUI gui;
	JPanel mainPanel;
	JPanel subPanel;
	
	JTextField id_txt;
	JPasswordField pass_txt;
	JPasswordField passRe_txt;
	JTextField name_txt;
	JTextField phone_txt;
	JTextField addr_txt;
		
	JButton registerButton;
	JButton cancelButton;
	
	Font font = new Font("ȸ������", Font.BOLD, 40);
	String id = "", pass = "", passRe = "", name = "", phone = "", addr = "";

	public SignupPanel(GUI gui) {

		this.gui = gui;
		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		JLabel idLabel = new JLabel("���̵� : ");
		JLabel passLabel = new JLabel("��й�ȣ : ");
		JLabel passReLabel = new JLabel("��й�ȣ ��Ȯ�� : ");
		JLabel nameLabel = new JLabel("�̸� : ");
		JLabel phoneLabel = new JLabel("�ڵ�����ȣ : ");
		JLabel addrLabel = new JLabel("�ּ� : ");

		id_txt = new JTextField(15);
		pass_txt = new JPasswordField(15);
		passRe_txt = new JPasswordField(15);
		name_txt = new JTextField(15);
		phone_txt = new JTextField(15);
		addr_txt = new JTextField(15);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(15, 5, 0, 0);

		c.gridx = 0;
		c.gridy = 0;
		subPanel.add(idLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		subPanel.add(id_txt, c); // ���̵�

		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(passLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(pass_txt, c); // pass

		c.gridx = 2;
		c.gridy = 1;
		subPanel.add(new JLabel("Ư������ + 8��"), c); // ���ȼ���

		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(passReLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		subPanel.add(passRe_txt, c); // password ��Ȯ��

		c.gridx = 0;
		c.gridy = 3;
		subPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		subPanel.add(name_txt, c); // �̸�

		c.gridx = 0;
		c.gridy = 4;
		subPanel.add(phoneLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		subPanel.add(phone_txt, c);	//��ȭ��ȣ

		c.gridx = 0;
		c.gridy = 5;
		subPanel.add(addrLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		subPanel.add(addr_txt, c);	// �ּ�
		
		c.gridx = 2;
		c.gridy = 5;
		subPanel.add(new JLabel("���� �ּ�!"), c); // ���ȼ���

		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel signupLabel = new JLabel("ȸ������ ȭ�� ");
		signupLabel.setFont(font);
		signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel();
		registerButton = new JButton("ȸ������");
		buttonPanel.add(registerButton);
		
		cancelButton = new JButton("���");
		buttonPanel.add(cancelButton);

		mainPanel.add(signupLabel);
		mainPanel.add(subPanel);
		mainPanel.add(buttonPanel);

		
		registerButton.addActionListener(new ActionListener() { // ȸ�����Թ�ư

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						id = id_txt.getText();
						pass = new String(pass_txt.getPassword());
						passRe = new String(passRe_txt.getPassword());
						name = name_txt.getText();
						phone = phone_txt.getText();
						addr = addr_txt.getText();
						
						
						Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); // 8��/����+Ư��+����
						Matcher passMatcher = passPattern1.matcher(pass);

						if (!passMatcher.find()) {
							JOptionPane.showMessageDialog(null,
									"��й�ȣ�� ����+Ư������+���� 8�ڷ� �����Ǿ�� �մϴ�",
									"��й�ȣ ����", 1);
						} else if (!pass.equals(passRe)) {
							JOptionPane.showMessageDialog(null,
									"��й�ȣ�� ���� ���� �ʽ��ϴ�", "��й�ȣ ����", 1);

						} else {
							String check_id = gui.con.Mem_Insert(id, pass, name, phone, addr);	//ȸ������
							String tn = getTime();
							if(check_id.isEmpty()){
								JOptionPane.showMessageDialog(null,	"������ ����� �Է����ּ���!", "����", 1); // ȭ������
							}
							else if (check_id.contains("PRIMARY")) {
								JOptionPane.showMessageDialog(null, "���̵� �ߺ�!", "���̵� �ߺ� ����", 1);
							}
							else{
								gui.con.Insert(tn, id, "��۴��б�", addr);	//���� �Է�
								id_txt.setText("");
								pass_txt.setText("");
								passRe_txt.setText("");
								name_txt.setText("");
								phone_txt.setText("");
								addr_txt.setText("");
								gui.card.show(gui.cardPanel,"Login"); // �� �Ϸ�Ǹ� �α���
							}
						}
					}
				});
		cancelButton.addActionListener(new ActionListener() { // ���

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				id_txt.setText("");
				pass_txt.setText("");
				passRe_txt.setText("");
				name_txt.setText("");
				phone_txt.setText("");
				addr_txt.setText("");
				gui.card.show(gui.cardPanel,"Login"); // �α��� ȭ��
			}
		});
	}
	
	public String getTime() {

		// ���� ��¥/�ð�
		LocalDateTime now = LocalDateTime.now();

		// ������
		String formatedNow = now.format(DateTimeFormatter
				.ofPattern("yyyy/MM/dd-HH:mm:ss"));

		// ������ ���� ��¥
		return formatedNow;
	}
}