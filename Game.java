import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

class Game extends JFrame {

	private String[] scenerios = {"You are a skilled doctor, with five patients who all need different organ transp" +
			"lants. There are currently no organs available to give them, and if they don’t get their transplants " +
			"soon they will all die. You have a sixth patient, who is dying of an incurable disease. At the moment " +
			"you are giving him medicine to ease his pain and prolong his life. He is a compatible organ donor for " +
			"your five other patients, but the medicine he is taking will keep him alive just a day longer than they " +
			"have left. If you were to stop giving him medicine he would die before them, in a very painful way, " +
			"but you would then be able to use his organs to save the other five. What should you do?",
			"Heather is part of a four-person mining expedition. There is a cave-in and the four of them are trapped in " +
					"the mine. A rock has crushed the legs of one of her crew members and he will die without medical " +
					"attention. She’s established radio contact with the rescue team and learned it will be 36 hours " +
					"before the first drill can reach the space she is trapped in.\n" + "\n" + "She is able to calculate" +
					" that this space has just enough oxygen for three people to survive for 36 hours, but definitely " +
					"not enough for four people. The only way to save the other crew members is to refuse medical aid " +
					"to the injured crew member so that there will be just enough oxygen for the rest of the crew to " +
					"survive. What should Heather do and why?",
			"Enemy soldiers have taken over Jane’s village. They have orders to kill all remaining civilians over the age of " +
					"two. Jane and some of the townspeople have sought refuge in two rooms of the cellar of a large house. " +
					"Outside Jane hears the voices of soldiers who have come to search the house for valuables. Jane’s baby " +
					"begins to cry loudly in the other room.\n" +
					"His crying will summon the attention of the soldiers who will spare Jane’s baby’s life, but will" +
					" kill Jane and the others hiding in both rooms." +
					"If Jane turns on the noisy furnace to block the sound, the other room will become uncomfortably" +
					" hot for adults and children, but deadly for infants." +
					"To save her and the others Jane must activate the furnace, which will kill her baby.\n" +
					"Should Jane overheat her baby in order to save herself and the other townspeople? Explain your answer",
			"A pregnant woman leading a group of five people out of a cave on a coast is stuck in the mouth of that " +
					"cave. In a short time high tide will be upon them, and unless she is unstuck, they will all be " +
					"drowned except the woman, whose head is out of the cave. Fortunately, (or unfortunately,) someone " +
					"has with him a stick of dynamite. There seems no way to get the pregnant woman loose without using " +
					" dynamite which will inevitably kill her; but if they do not use it everyone else will drown.\n" +
					"What should they do?",
			"Imagine that a powerful alien were to visit earth, with the ability to eradicate war, famine and suffering." +
					" The alien says that he will do this, and turn the world into a utopia where humans will be happy " +
					"and peaceful forever more, but only if a price is paid. He demands a small child be given to him " +
					"so that he can perform hideous scientific experiments on it, causing the child unimaginable pain.\n" +
					"Should you hand over the child? Explain.",
			"Tom, hating his wife and wanting her dead, puts poison in her coffee, thereby killing her. Jane also hates " +
					"her husband, and would like him dead. One day her husband accidentally puts poison in his own " +
					"coffee, thinking it is cream. Jane realises this, and has the antidote that could save him, but " +
					"does not hand it over and her husband dies.\n" +
					"Is Jane’s failure to act as bad as Tom’s action? Explain",
			"You witness a man rob a bank, but instead of keeping the money for himself, he donates it to a local " +
					"orphanage. You know this orphanage has been struggling for funding, and this money will allow the " +
					"children to receive proper food, clothing and medical care. If you report the crime, the money will" +
					" be taken away from the orphanage and given back to the bank.\n" +
					"What should you do?",
			"You are an English teacher at a high school. One of your pupils is a very bright and gifted girl, whom you " +
					"have always enjoyed teaching. She has always achieved A grades throughout her school years, and is " +
					"now in her final year and getting ready to graduate. Unfortunately she has been very ill this term, " +
					"and missed several weeks of schooling. She has just turned in a report which is worth 40% of her " +
					"final grade, but you realise that she did not write it herself – she has copied a report found " +
					"online and tried to pass it off as her own work.\n" +
					"If you report her plagiarisation to the school authorities it will be entered on her permanent " +
					"record and she will no longer be eligible to attend the prestigious university that she has dreamed" +
					" of attending all through high school. If you refuse to accept the report, her final mark will be " +
					"very poor and may harm her chances of being chosen for this university. If you mark the paper as " +
					"though you believed it was her own work, she will do very well, and stand every chance of getting " +
					"her desired university place.\n" +
					"What should you do?",
			"Mark is a crewperson on a marine-research submarine traveling underneath a large iceberg. An onboard " +
					"explosion has damaged the ship, killed and injured several crewmembers. Additionally, it has " +
					"collapsed the only access corridor between the upper and lower parts of the ship. The upper section," +
					" where Mark and most of the others are located, does not have enough oxygen remaining for all of " +
					"them to survive until Mark has reached the surface. Only one remaining crewmember is located in the" +
					" lower section, where there is enough oxygen.\n" +
					"There is an emergency access hatch between the upper and lower sections of the ship. " +
					"If released by an emergency switch, it will fall to the deck and allow oxygen to reach the area " +
					"where Mark and the others are. However, the hatch will crush the crewmember below, since he was" +
					" knocked unconscious and is lying beneath it. Mark and the rest of the crew are almost out of air " +
					"though, and they will all die if Mark does not do this.\n" +
					"Should Mark release the hatch and crush the crewmember below to save himself and the other crew members? Explain.",
			"You are on a plane containing 150 people, currently flying over barren desert. Hijackers take over, " +
					"killing the pilot and co-pilot, and sealing themselves in the cockpit. There is no way for you to " +
					"open the door, but you could damage the ventilation system causing poisonous fumes to fill the " +
					"cockpit. If you do this the hijackers will die, but with no-one able to enter the cockpit and fly " +
					"the plane, it will crash in the desert killing everyone on board. If you do nothing, the hijackers " +
					"might land the plane safely, or they might crash it into a civilian target killing even more people.\n" +
					"What should you do?",
			"Your partner is dying from a rare disease. Luckily a cure has recently been invented, by one druggist who " +
					"lives fairly close to you. This druggist is selling the cure for ten times the amount it cost him " +
					"to make it. You try to raise the money, but even borrowing from friends and taking a loan from the " +
					"bank, you can only raise half the amount. You go to the druggist and offer to pay him half now and " +
					"half later, but he refuses, saying that he invented the cure and is determined to make money off it. " +
					"You beg him to sell it cheaper as your partner will die before you can raise the full amount, but " +
					"he still refuses.\n" +
					"You believe you could break into his store one night after he has gone home and steal the cure. " +
					"This would definitely save your partner, although you might be arrested for the crime.\n" +
					"What should you do?"};
	private int condition;
	private int partNum;
	private String[] responses = new String[scenerios.length];
	private static int score;
	private static int cScore;
	private int curPage;
	private long timerlen = 120000;
	private long tLeft;
	private TimerTask JJ;
	private Timer primer = new Timer();
	private boolean isRunning = false;
	private JPanel sureIGuess;
	private JPanel actualContent;
	private JPanel points;
	private ArrayList<Object> items = new ArrayList<>();
	private responder cate = new responder();

	private class responder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
				case "startPG":
					String care = ((JTextField)items.get(0)).getText();
					condition = Integer.parseInt(care);
					care = ((JTextField)items.get(1)).getText();
					partNum = Integer.parseInt(care);
					items.remove(1);
					items.remove(0);
					nextPage();
					break;
				case "nextPG":
					//some stuff involving showing the waitpage
					if(isRunning){
						JJ.cancel();
						isRunning = false;
					}

					care = ((JTextArea)items.get(0)).getText();
					responses[curPage] = care;
					items.remove(0);

					score += givePoint(curPage, condition);
					cScore += givePoint(curPage, condition + 3);

					String wl = winLoss(condition, curPage);

					showWaitPage();
					Timer timer = new Timer();
					long del = (long) ((Math.random() * 15000) + 60000);
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							winLossPage(wl);
							timer.cancel();
						}
					}, del);

					//possibly some stuff involving telling the participant if they were supposed to get points or not
					//some stuff involving points
					break;
				case "continue":
					nextPage();
					break;
				case "finished":
					//write to file
					String fileStr = "Responses_P"+partNum+"C"+condition+".txt";
					try {
						Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileStr), "utf-8"));
						for (String i : responses) {
							writer.write("" + i + "\n\n");
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


	Game(){
		this.getContentPane().setBackground(Color.darkGray);
		actualContent = new JPanel();
		actualContent.setBackground(Color.darkGray);
		actualContent.setLayout(new BoxLayout(actualContent, BoxLayout.Y_AXIS));
		sureIGuess = new JPanel();
		sureIGuess.setBackground(Color.darkGray);
		sureIGuess.setLayout(new BoxLayout(sureIGuess, BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//TODO: add score and such to be an overlay
		this.condition = -1;
		score = 0;
		cScore = 0;
		this.curPage = -1;

		this.points = setPointPanel();

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);

		this.firstPage();

		this.pack();
		this.setVisible(true);
	}

	private int givePoint(int page, int cond){
		switch(cond){ //1: acceptance, 2: rejection, 3: frustration, 4: opp acceptance, 5: opp rejection, 6: opp frustration
			case 1:
				switch(page){
					case 0:
					case 1:
					case 2:
					case 3:
					case 6:
					case 7:
					case 8:
					case 10:
						return 1;
					case 5:
					case 4:
					case 9:
						return 0;
				}
				break;
			case 2:
				switch(page){
					case 0:
					case 1:
					case 3:
					case 5:
					case 6:
					case 7:
					case 8:
					case 10:
						return 0;
					case 2:
					case 4:
					case 9:
						return 1;
				}
				break;
			case 3:
				switch(page){
					case 1:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
						return 0;
					case 0:
					case 2:
						return 1;
				}
				break;
			case 4:
				switch(page){
					case 0:
					case 1:
					case 2:
					case 3:
					case 6:
					case 7:
					case 8:
					case 10:
						return 0;
					case 5:
					case 4:
					case 9:
						return 1;
				}
				break;
			case 5:
				switch (page){
					case 0:
					case 1:
					case 3:
					case 5:
					case 6:
					case 7:
					case 8:
					case 10:
						return 1;
					case 2:
					case 4:
					case 9:
						return 0;
				}
				break;
			case 6:
				switch(page){
					case 0:
					case 2:
					case 3:
					case 5:
					case 7:
					case 8:
					case 9:
						return 0;
					case 1:
					case 4:
					case 6:
					case 10:
						return 1;
				}
				break;
			default:
				return 0;
		}
		return 0;
	}

	private String winLoss(int cond, int page){
		switch(cond){ //1: acceptance, 2: rejection, 3: frustration, 4: opp acceptance, 5: opp rejection, 6: opp frustration
			case 1:
				switch(page){
					case 0:
					case 1:
					case 2:
					case 3:
					case 6:
					case 7:
					case 8:
					case 10:
						return "win";
					case 5:
					case 4:
					case 9:
						return "loss";
				}
				break;
			case 2:
				switch(page){
					case 0:
					case 1:
					case 3:
					case 5:
					case 6:
					case 7:
					case 8:
					case 10:
						return "loss";
					case 2:
					case 4:
					case 9:
						return "win";
				}
				break;
			case 3:
				switch(page){
					case 1:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
						return "win";
					case 0:
					case 2:
						return "loss";
				}
				break;
			default:
				return "loss";
		}
		return "loss";
	}

	private void firstPage(){
		String intro = "In a moment you will play a short game where you will be asked to respond to a number of " +
				"scenarios. These responses will be sent to a judge and compared against your partner's response. " +
				"Whomever the judge decides to have the better response will be awarded a point and the " +
				"game will continue. You will have 2 minutes to respond to each scenario.";

		JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		top.setBackground(Color.darkGray);
		JPanel middle = new JPanel();
		middle.setLayout(new GridBagLayout());
		middle.setBackground(Color.darkGray);
		middle.setMaximumSize(new Dimension(2000, 75));
		GridBagConstraints c = new GridBagConstraints();

		String content = intro;
		String bText = "Begin";


		JLabel cont = new JLabel(content);
		JLabel contTemp = new JLabel();
		contTemp.setText("<html><body><p style='width: 500px; font-size: 15px; color: #a762b1'>" + cont.getText() + "</p><br></body></html>");
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
		items.add(input0);
		items.add(inputt);
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

		if(curPage<scenerios.length) {
			JPanel top = new JPanel();
			top.setLayout(new GridBagLayout());
			top.setBackground(Color.darkGray);
			JPanel middle = new JPanel();
			middle.setLayout(new GridBagLayout());
			middle.setBackground(Color.darkGray);
			middle.setMaximumSize(new Dimension(2000, 75));
			GridBagConstraints c = new GridBagConstraints();

			String content = scenerios[curPage];
			String bText = "Submit";


			JLabel cont = new JLabel(content);
			JLabel contTemp = new JLabel();
			contTemp.setText("<html><body><p style='width: 700px; font-size: 17px; color: #a762b1'>" + cont.getText() + "</p><br></body></html>");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			c.gridheight = 8;
			top.add(contTemp, c);

			JTextArea inputt = new JTextArea(5, 300);
			inputt.setFont(new Font("Veranda", Font.PLAIN, 16));
			inputt.setLineWrap(true);
			inputt.setWrapStyleWord(true);
			JScrollPane inHolder = new JScrollPane(inputt);
			inHolder.setPreferredSize(new Dimension(1000, 100));
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			middle.add(inHolder, c);
			items.add(inputt);

			JButton button = new JButton(bText);
			button.addActionListener(cate);
			button.setActionCommand("nextPG");

			this.points = this.setPointPanel();
			actualContent.add(this.points);


			actualContent.add(top);
			actualContent.add(middle);
			actualContent.add(Box.createRigidArea(new Dimension(1, 15)));
			actualContent.add(button);
			actualContent.add(Box.createRigidArea(new Dimension(1, 25)));

			JJ = createTask();
			primer.scheduleAtFixedRate(JJ, 1000, 1000);
			isRunning = true;

			actualContent.revalidate();
		}
		else{
			String ending = "Thanks for playing. Please notify an administrator that you are finished and they will continue with the test.";

			JPanel top = new JPanel();
			top.setBackground(Color.darkGray);
			top.setLayout(new GridBagLayout());
			JPanel mid = new JPanel();
			mid.setBackground(Color.darkGray);
			mid.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;

			JLabel end = new JLabel(ending);
			end.setText("<html><body><p style='font-size:28; color: white'>" + end.getText() + "</p></body></html>");
			c.gridy = 0;
			top.add(end, c);

			JLabel fscor = new JLabel("The final scores were: ");
			fscor.setText("<html><body><p style='font-size:19; color: white'>" + fscor.getText() + "</p></body></html>");
			c.gridy = 0;
			mid.add(fscor, c);

			JLabel yscor = new JLabel("Your Score: ");
			yscor.setText("<html><body><p style='font-size:19; color: white'>" + yscor.getText() + score + "</p></body></html>");
			c.gridy = 1;
			mid.add(yscor, c);

			JLabel tscor = new JLabel("Their score: ");
			tscor.setText("<html><body><p style='font-size:19; color: white'>" + tscor.getText() + cScore +"</p></body></html>");
			c.gridy = 2;
			mid.add(tscor, c);

			JPanel bottom = new JPanel();
			bottom.setBackground(Color.darkGray);
			bottom.setLayout(new GridBagLayout());
			JButton button = new JButton("Complete");
			button.addActionListener(cate);
			button.setActionCommand("finished");
			c.gridy = 0;
			bottom.add(button, c);

			actualContent.add(top);
			actualContent.add(mid);
			actualContent.add(Box.createRigidArea(new Dimension(1, 15)));
			actualContent.add(bottom);
			actualContent.add(Box.createRigidArea(new Dimension(1, 25)));

			actualContent.revalidate();
		}
	}

	private void showWaitPage(){
		actualContent.removeAll();

		JPanel wht = new JPanel();
		wht.setBackground(Color.darkGray);
		wht.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel cont = new JLabel("Thank you for your submission; please wait while your response is considered and the test will continue when the judge has made a decision.");
		JLabel contTemp = new JLabel();
		contTemp.setText("<html><body><p style='width: 500px; font-size: 15px; color: #a762b1'>" + cont.getText() + "</p><br></body></html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		wht.add(contTemp);

		actualContent.add(wht);

		actualContent.revalidate();
	}

	private JPanel setPointPanel(){
		JPanel temp = new JPanel();

		temp.setLayout(new BorderLayout());
		temp.setBackground(Color.darkGray);
		temp.setMaximumSize(new Dimension(2000, 30));
		JLabel mPoints = new JLabel("My Points: " + score);
		mPoints.setText("<html><body><p style='font-size: 15px; color: #000000'>" + mPoints.getText() + "</p><br></body></html>");
		JLabel tPoints = new JLabel("Their Points: " + cScore);
		tPoints.setText("<html><body><p style='font-size: 15px; color: #000000'>" + tPoints.getText() + "</p><br></body></html>");
		temp.add(mPoints, BorderLayout.PAGE_START);
		temp.add(tPoints);

		//create timer and place in top
		long eTime = System.currentTimeMillis()+timerlen;
		tLeft = eTime - System.currentTimeMillis();
		long min = tLeft/60000;
		long sec = ((tLeft%60000)/1000);
		String time = String.format("%02d:%02d", min, sec);
		JLabel cTempp = new JLabel();
		cTempp.setText("<html><body><p style='font-size: 19px; color: #000000'>" + time + "</p><br></body></html>");
		sureIGuess.add(cTempp);
		temp.add(sureIGuess, BorderLayout.PAGE_END);

		return temp;
	}

	private void winLossPage(String wll){
		actualContent.removeAll();

		JPanel sure = new JPanel();
		sure.setBackground(Color.darkGray);
		sure.setLayout(new GridBagLayout());

		String outcome = "";
		switch(wll){
			case "win":
				outcome = "Your response was accepted, a point will be awarded to you.";
				break;
			case "loss":
				outcome = "Your response was rejected, no point will be awarded to you.";
				break;
		}
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel out = new JLabel(outcome);
		out.setText("<html><body><p style='font-size: 24; color:#a762b1'>" + out.getText() + "</p></body></html>");
		c.gridy = 0;
		sure.add(out, c);
		JLabel spacer = new JLabel(" ");
		c.gridy = 1;
		c.gridheight = 2;
		sure.add(spacer, c);

		JPanel bottom = new JPanel();
		bottom.setBackground(Color.darkGray);
		bottom.setLayout(new GridBagLayout());

		JButton button = new JButton();
		button.setText("<html><body><p style='font-size:16;'>Press to Continue</p></body></html>");
		button.addActionListener(cate);
		button.setActionCommand("continue");
		c.gridy = 0;
		bottom.add(button, c);

		actualContent.add(Box.createRigidArea(new Dimension(1, 27)));
		actualContent.add(sure);
		//actualContent.add(Box.createRigidArea(new Dimension(1, 25)));
		actualContent.add(bottom);
		actualContent.add(Box.createRigidArea(new Dimension(1, 25)));

		actualContent.revalidate();
	}
	private void updateTimer(){
		tLeft = tLeft - 1000;
		sureIGuess.removeAll();
		long min = tLeft/60000;
		long sec = ((tLeft%60000)/1000);
		String time = String.format("%02d:%02d", min, sec);
		JLabel cTempp = new JLabel();
		cTempp.setText("<html><body><p style='font-size: 19px; color: #000000'>" + time + "</p><br></body></html>");
		sureIGuess.add(cTempp);
		sureIGuess.revalidate();
		if(tLeft <= 0){
			cate.actionPerformed(new ActionEvent(cTempp, ActionEvent.ACTION_PERFORMED, "nextPG", 0));
		}
	}
	private TimerTask createTask(){
		TimerTask TT = new TimerTask(){
			public void run() {
				updateTimer();
			}
		};
		return TT;
	}
}

