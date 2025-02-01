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

// 회원가입 ui
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
	
	Font font = new Font("회원가입", Font.BOLD, 40);
	String id = "", pass = "", passRe = "", name = "", phone = "", addr = "";

	public SignupPanel(GUI gui) {

		this.gui = gui;
		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		JLabel idLabel = new JLabel("아이디 : ");
		JLabel passLabel = new JLabel("비밀번호 : ");
		JLabel passReLabel = new JLabel("비밀번호 재확인 : ");
		JLabel nameLabel = new JLabel("이름 : ");
		JLabel phoneLabel = new JLabel("핸드폰번호 : ");
		JLabel addrLabel = new JLabel("주소 : ");

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
		subPanel.add(id_txt, c); // 아이디

		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(passLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(pass_txt, c); // pass

		c.gridx = 2;
		c.gridy = 1;
		subPanel.add(new JLabel("특수문자 + 8자"), c); // 보안설정

		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(passReLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		subPanel.add(passRe_txt, c); // password 재확인

		c.gridx = 0;
		c.gridy = 3;
		subPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		subPanel.add(name_txt, c); // 이름

		c.gridx = 0;
		c.gridy = 4;
		subPanel.add(phoneLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		subPanel.add(phone_txt, c);	//전화번호

		c.gridx = 0;
		c.gridy = 5;
		subPanel.add(addrLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		subPanel.add(addr_txt, c);	// 주소
		
		c.gridx = 2;
		c.gridy = 5;
		subPanel.add(new JLabel("지번 주소!"), c); // 보안설정

		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel signupLabel = new JLabel("회원가입 화면 ");
		signupLabel.setFont(font);
		signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel();
		registerButton = new JButton("회원가입");
		buttonPanel.add(registerButton);
		
		cancelButton = new JButton("취소");
		buttonPanel.add(cancelButton);

		mainPanel.add(signupLabel);
		mainPanel.add(subPanel);
		mainPanel.add(buttonPanel);

		
		registerButton.addActionListener(new ActionListener() { // 회원가입버튼

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						id = id_txt.getText();
						pass = new String(pass_txt.getPassword());
						passRe = new String(passRe_txt.getPassword());
						name = name_txt.getText();
						phone = phone_txt.getText();
						addr = addr_txt.getText();
						
						
						Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); // 8자/영문+특문+숫자
						Matcher passMatcher = passPattern1.matcher(pass);

						if (!passMatcher.find()) {
							JOptionPane.showMessageDialog(null,
									"비밀번호는 영문+특수문자+숫자 8자로 구성되어야 합니다",
									"비밀번호 오류", 1);
						} else if (!pass.equals(passRe)) {
							JOptionPane.showMessageDialog(null,
									"비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);

						} else {
							String check_id = gui.con.Mem_Insert(id, pass, name, phone, addr);	//회원가입
							String tn = getTime();
							if(check_id.isEmpty()){
								JOptionPane.showMessageDialog(null,	"정보를 제대로 입력해주세요!", "오류", 1); // 화면으로
							}
							else if (check_id.contains("PRIMARY")) {
								JOptionPane.showMessageDialog(null, "아이디 중복!", "아이디 중복 오류", 1);
							}
							else{
								gui.con.Insert(tn, id, "우송대학교", addr);	//정보 입력
								id_txt.setText("");
								pass_txt.setText("");
								passRe_txt.setText("");
								name_txt.setText("");
								phone_txt.setText("");
								addr_txt.setText("");
								gui.card.show(gui.cardPanel,"Login"); // 다 완료되면 로그인
							}
						}
					}
				});
		cancelButton.addActionListener(new ActionListener() { // 취소

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				id_txt.setText("");
				pass_txt.setText("");
				passRe_txt.setText("");
				name_txt.setText("");
				phone_txt.setText("");
				addr_txt.setText("");
				gui.card.show(gui.cardPanel,"Login"); // 로그인 화면
			}
		});
	}
	
	public String getTime() {

		// 현재 날짜/시간
		LocalDateTime now = LocalDateTime.now();

		// 포맷팅
		String formatedNow = now.format(DateTimeFormatter
				.ofPattern("yyyy/MM/dd-HH:mm:ss"));

		// 포맷팅 현재 날짜
		return formatedNow;
	}
}