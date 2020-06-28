import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Game extends JFrame{

	private long[] times;
	private ArrayList<Integer> wrong = new ArrayList<>();
	private String[][] words;
	private JPanel actualContent;
	private int curPage;
	private ArrayList<Object> whatMatters = new ArrayList<>();
	private responder cate = new responder();
	private long startTick;
	private long curTii;
	private long difTime;
	private JPanel reminder;
	private String cColor = "";
	private boolean blank = false;
	static JLabel obj1 = new JLabel(); //for key purposes

	Game(){
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F"), "red");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("J"), "blue");

		obj1.getActionMap().put("red", new changeAction("red"));
		obj1.getActionMap(). put("blue", new changeAction("blue"));
		add(obj1);

		this.getContentPane().setBackground(Color.BLACK);
		actualContent = new JPanel();
		actualContent.setBackground(Color.black);
		actualContent.setLayout(new BoxLayout(actualContent, BoxLayout.Y_AXIS));

		//TODO: MAKE THIS ACTUALLY WORK
		reminder = new JPanel();
		reminder.setBackground(Color.black);
		reminder.setLayout(new BoxLayout(reminder, BoxLayout.Y_AXIS));
		JLabel rLabL = new JLabel("F: red");
		rLabL.setText("<html><body><p style='font-size: 12px; color: #000000'>" + rLabL.getText() + "</p><br></body></html>");
		JLabel rLabR = new JLabel("J: blue");
		rLabR.setText("<html><body><p style='font-size: 12px; color: #000000'>" + rLabR.getText() + "</p><br></body></html>");
		reminder.add(rLabL, BorderLayout.PAGE_START);
		reminder.add(rLabR, BorderLayout.LINE_END);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.curPage = -1;
		this.words = fillWords();
		times = new long[words.length];

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.firstPage();

		this.pack();
		this.setVisible(true);
	}

	private class changeAction extends AbstractAction{
		String testColor;

		changeAction(String cc){
			this.testColor = cc;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(testColor){
				case "blue":
					if(cColor.equals("blue")){
						//record time
						curTii = System.currentTimeMillis();
						difTime = curTii - startTick - 3000;
						times[curPage] = difTime;
						//go to next page
						nextPage();
					}
					else if((wrong.size()>0 && wrong.get(wrong.size()-1)!=curPage)||wrong.size()==0){
						wrong.add(curPage);
					}
					break;
				case "red":
					if(cColor.equals("red")){
						//record time
						curTii = System.currentTimeMillis();
						difTime = curTii - startTick - 3000;
						times[curPage] = difTime;
						//go to next page
						nextPage();
					}
					else if((wrong.size()>0 && wrong.get(wrong.size()-1)!=curPage)||wrong.size()==0){
						wrong.add(curPage);
					}
					break;
				default:
					break;
			}
		}
	}

	private class responder implements ActionListener {
		private int particNum = 0;
		private int condition = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
				case "startPG":
					String partN = ((JTextField)whatMatters.get(0)).getText();
					particNum = Integer.parseInt(partN);
					whatMatters.remove(0);
					String cond = ((JTextField)whatMatters.get(0)).getText();
					condition = Integer.parseInt(cond);
					whatMatters.remove(0);
					nextPage();
					break;
				case "finished":
					String fileStr = "Stroop_P"+particNum+"C"+condition+".txt";
					try {
						Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileStr), "utf-8"));
						for (double i : times) {
							writer.write("" + i + "\n");
						}
						writer.write("\nWrong: \n");
						for(int q: wrong){
							writer.write(""+q + "; ");
						}
						writer.close();
					}
					catch (IOException ex){
						System.out.println("An error has occurred. Error Code 0x001");
					}
					JFrame.getWindows()[0].dispatchEvent(new WindowEvent(JFrame.getFrames()[0], WindowEvent.WINDOW_CLOSING));
					break;
			}
		}
	}

	private void firstPage(){
		String intro = "In a moment you will do a short reaction-time task where you will be asked to respond to a number of " +
				"cues. There will be a blank screen for 3 seconds, then a word will appear in either the color red or the " +
				"color blue. You will have to press the corresponding key as quickly as possible--press F for red and press " +
				"J for blue. When the correct key has been pressed the screen will go black for 3 seconds again followed by " +
				"another word. This will continue until the end of the task. When the task ends it will then ask you to " +
				"notify your administrator, please do so.";

		JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		top.setBackground(Color.black);
		JPanel middle = new JPanel();
		middle.setLayout(new GridBagLayout());
		middle.setBackground(Color.black);
		middle.setMaximumSize(new Dimension(2000, 75));
		GridBagConstraints c = new GridBagConstraints();

		String content = intro;
		String bText = "Begin";


		JLabel cont = new JLabel(content);
		JLabel contTemp = new JLabel();
		contTemp.setText("<html><body><p style='width: 500px; font-size: 17px; color: #a762b1'>" + cont.getText() + "</p><br></body></html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 8;
		top.add(contTemp, c);

		JLabel star = new JLabel("Participant Number: ");
		star.setForeground(Color.white);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		middle.add(star, c);
		JTextField inputt = new JTextField(20);
		whatMatters.add(inputt);
		inputt.setColumns(20);
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1.1;
		middle.add(inputt, c);

		JLabel space = new JLabel("  ");
		c.gridy = 1;
		c.gridx = 0;
		middle.add(space, c);

		JLabel sta0 = new JLabel("Condition: ");
		sta0.setForeground(Color.white);
		c.gridx = 0;
		c.gridy = 2;
		middle.add(sta0, c);
		JTextField input0 = new JTextField(20);
		whatMatters.add(input0);
		c.gridx = 1;
		c.gridy = 2;
		c.weighty = 1.3;
		middle.add(input0, c);

		JButton button = new JButton(bText);
		button.addActionListener(cate);
		button.setActionCommand("startPG");

		actualContent.add(top);
		actualContent.add(middle);
		actualContent.add(Box.createRigidArea(new Dimension(1, 15)));
		actualContent.add(button);
		actualContent.add(Box.createRigidArea(new Dimension(1, 25)));

		this.add(actualContent);
	}

	private void nextPage(){
		curPage = curPage + 1;
		actualContent.removeAll();

		if(curPage<words.length) { //TODO: edit this down to else

			blank = true;
			repaint();

			String word = words[curPage][0];
			String color = words[curPage][1];
			this.cColor = color;

			JPanel contentt = new JPanel();
			contentt.setBackground(Color.black);
			contentt.setLayout(new GridBagLayout());


			JLabel cont = new JLabel(word);
			JLabel contTemp = new JLabel();
			contTemp.setText("<html><body><p style='text-align:center; width: 700px; font-size: 50px; color:"+ color + "'>" + cont.getText() + "</p><br></body></html>");
			contTemp.setVerticalAlignment(JLabel.CENTER);
			contentt.add(contTemp);

			actualContent.add(contentt);
			actualContent.revalidate();
			startTick = System.currentTimeMillis();
		}
		else{
			repaint();
			String ending = "Thanks for playing. Please notify an administrator that you are finished and they will continue with the test.";

			JPanel top = new JPanel();
			top.setBackground(Color.black);
			top.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;

			JLabel end = new JLabel(ending);
			end.setText("<html><body><p style='font-size:28; color: white'>" + end.getText() + "</p></body></html>");
			c.gridy = 0;
			top.add(end, c);

			JPanel bottom = new JPanel();
			bottom.setBackground(Color.black);
			bottom.setLayout(new GridBagLayout());
			JButton button = new JButton("Complete");
			button.addActionListener(cate);
			button.setActionCommand("finished");
			c.gridy = 0;
			bottom.add(button, c);

			actualContent.add(top);
			actualContent.add(Box.createRigidArea(new Dimension(1, 15)));
			actualContent.add(bottom);
			actualContent.add(Box.createRigidArea(new Dimension(1, 25)));

			actualContent.revalidate();
		}
	}
	private String[][] fillWords(){
		String[][] outty = new String[][]{
				{"Ridicule", "red"}, {"Liberty", "blue"}, {"Useless", "blue"}, {"Elevator", "red"},
				{"Lost", "blue"}, {"Scissors", "blue"}, {"Music", "red"}, {"Glacier", "blue"},
				{"Bored", "red"}, {"Misery", "blue"}, {"Slow", "red"}, {"Upset", "blue"}, {"Red", "blue"},
				{"Kettle", "blue"}, {"Hairpin", "red"}, {"Chair", "red"}, {"Kiss", "red"},
				{"Rage", "blue"}, {"Failure", "red"}, {"Idiot", "blue"}, {"Home", "blue"}, {"Blue", "red"},
				{"Killer", "red"}, {"Incentive", "red"}, {"Hostage", "red"}, {"Graduate", "blue"},

				{"Ridicule", "blue"}, {"Liberty", "red"}, {"Useless", "red"}, {"Elevator", "blue"},
				{"Lost", "red"}, {"Scissors", "red"}, {"Music", "blue"}, {"Glacier", "red"},
				{"Bored", "blue"}, {"Misery", "red"}, {"Slow", "blue"}, {"Upset", "red"}, {"Red", "red"},
				{"Kettle", "red"}, {"Hairpin", "blue"}, {"Chair", "blue"}, {"Kiss", "blue"},
				{"Rage", "red"}, {"Failure", "blue"}, {"Idiot", "red"}, {"Home", "red"}, {"Blue", "blue"},
				{"Killer", "blue"}, {"Incentive", "blue"}, {"Hostage", "blue"}, {"Graduate", "red"}
		};
		return outty;
	}
	public void paint(Graphics g){
		if(blank){
			g.setColor(Color.BLACK);
			g.fillRect(0, 20, getWidth(), getHeight()-20);
			//set timer for 5 seconds
			long kk = System.currentTimeMillis();
			boolean bb = false;
			while(System.currentTimeMillis()-kk < 3000){
				bb = !bb;
			}
			blank = false;
		}
		super.paint(g);
	}
}
