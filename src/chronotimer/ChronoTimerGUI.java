package chronotimer;
import io.Command;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import race.AbstractEvent;
import race.IND;
//In run config: use width 850, height 600 for this Applet
public class ChronoTimerGUI extends JApplet {

	/**
	 * 
	 */
	private Timer time = new Timer();
	private ChronoTimer t = new ChronoTimer();
	IND ind;
	private static final long serialVersionUID = 1L;
	private String channelToConnect;
	private String typeOfSensorToConnect;
	private String inputFromCalcPad = "";
	private String eventType = "";
	private boolean isOn = false;
	private LocalTime currentTime;
	
	/**
	 * Create the applet.
	 */
	JPanel panel = new JPanel();
	JButton powerButton = new JButton("Power");
	final JTextArea consoleText = new JTextArea();
	final JTextArea printerText = new JTextArea();
	JLabel lblNewLabel = new JLabel("CHRONOTIMER POWER RANGERS");	
	JLabel startLbl = new JLabel("Start");
	JLabel enableDisplayLbl = new JLabel("Enable/Disable");
	JButton channel1 = new JButton("1");
	JButton channel3 = new JButton("3");
	JButton channel5 = new JButton("5");
	JButton channel7 = new JButton("7");
	JCheckBox check1 = new JCheckBox("");
	JCheckBox check3 = new JCheckBox("");
	JCheckBox check5 = new JCheckBox("");
	JCheckBox check7 = new JCheckBox("");
	JLabel finishLbl = new JLabel("Finish");
	JLabel enableDisableLbl2 = new JLabel("Enable/Disable");
	JButton finishButton2 = new JButton("2");
	JButton finishButton4 = new JButton("4");
	JButton finishButton6 = new JButton("6");
	JButton finishButton8 = new JButton("8");
	final ArrayList<JButton> channelsToTrigger = new ArrayList<JButton>();
	JCheckBox check2 = new JCheckBox("");
	JCheckBox check4 = new JCheckBox("");
	JCheckBox check6 = new JCheckBox("");	
	JCheckBox check8 = new JCheckBox("");
	final ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	final JTextArea textFromButtons = new JTextArea();
	JButton btnPrinterPower = new JButton("Print");
	JButton Calc1 = new JButton("1");
	JButton Calc2 = new JButton("2");
	JButton Calc3 = new JButton("3");
	JButton Calc4 = new JButton("4");
	JButton Calc5 = new JButton("5");
	JButton Calc6 = new JButton("6");
	JButton Calc7 = new JButton("7");
	JButton Calc8 = new JButton("8");
	JButton Calc9 = new JButton("9");
	JButton CalcStar = new JButton("*");
	JButton Calc0 = new JButton("0");
	JButton CalcPound = new JButton("#");
	final ArrayList<JButton>calcButtons = new ArrayList<JButton>();
	JButton eventButton = new JButton("Make Event");
	JButton btnSwap = new JButton("SWAP");
	JButton connectCommandBtn = new JButton("Connect");
	JRadioButton gateSensor = new JRadioButton("GATE");
	JRadioButton eyeSensor = new JRadioButton("EYE");
	JRadioButton padSensor = new JRadioButton("PAD");
	JRadioButton pushSensor = new JRadioButton("PUSH");
	ButtonGroup sensorGrp = new ButtonGroup();
	final ArrayList<JRadioButton> sensorButtons = new ArrayList<JRadioButton>();
	JRadioButton channel5Sensor = new JRadioButton("5");
	JRadioButton channel6Sensor = new JRadioButton("6");
	JRadioButton channel7Sensor = new JRadioButton("7");
	JRadioButton channel8Sensor = new JRadioButton("8");
	JRadioButton channel4Sensor = new JRadioButton("4");
	JRadioButton channel2Sensor = new JRadioButton("2");
	JRadioButton channel1Sensor = new JRadioButton("1");
	JRadioButton channel3Sensor = new JRadioButton("3");
	ButtonGroup channelGroup = new ButtonGroup();
	final ArrayList<JRadioButton> channelSensorButtons = new ArrayList<JRadioButton>();
	JRadioButton indEvent = new JRadioButton("IND");
	JRadioButton indParaEvent = new JRadioButton("PARIND");
	JRadioButton grpEvent = new JRadioButton("GRP");
	JRadioButton paraGrpEvent = new JRadioButton("PARGRP");
	ButtonGroup eventGroup = new ButtonGroup();
	final ArrayList<JRadioButton>eventTypes = new ArrayList<JRadioButton>();
	JButton numButton = new JButton("NUM");
	JButton exportButton = new JButton("EXPORT");
	JButton clearButton = new JButton("CLEAR");
	JButton btnNewButton = new JButton("NEWRUN");
	JButton btnEndrun = new JButton("ENDRUN");

	
	
	
	public ChronoTimerGUI() {
		createContents();		
	}
	
	private void createContents(){
		currentTime = LocalTime.now();
		panel.setSize(800, 600);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		alSingleButtons h = new alSingleButtons();
		
		
		powerButton.setBackground(Color.RED);
		powerButton.setToolTipText("Turn Chronotimer ON/OFF");
		powerButton.setBounds(33, 20, 80, 29);
		powerButton.addActionListener(new alPower());
		panel.add(powerButton);
		
		consoleText.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(consoleText);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(246, 246, 252, 179);
		panel.add(scroll);
		
		printerText.setEditable(false);
		printerText.setText("OFF");
		JScrollPane scroll2 = new JScrollPane(printerText);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setBounds(556, 52, 262, 160);
		panel.add(scroll2);
	
		lblNewLabel.setBounds(280, 6, 190, 16);
		panel.add(lblNewLabel);
		startLbl.setBounds(234, 52, 29, 16);
		panel.add(startLbl);
		enableDisplayLbl.setBounds(169, 93, 95, 16);
		panel.add(enableDisplayLbl);
		channel1.setBounds(291, 44, 41, 29);
		panel.add(channel1);
		channel3.setBounds(337, 44, 41, 29);
		panel.add(channel3);
		channel5.setBounds(385, 44, 41, 29);
		panel.add(channel5);
		channel7.setBounds(431, 44, 41, 29);
		panel.add(channel7);
		check1.setBounds(291, 85, 29, 28);
		panel.add(check1);
		check3.setBounds(337, 85, 29, 28);
		panel.add(check3);
		check5.setBounds(385, 85, 29, 28);
		panel.add(check5);
		check7.setBounds(431, 85, 29, 28);
		panel.add(check7);
		finishLbl.setBounds(225, 164, 38, 16);
		panel.add(finishLbl);
		enableDisableLbl2.setBounds(169, 200, 95, 16);
		panel.add(enableDisableLbl2);
		finishButton2.setBounds(291, 159, 41, 29);
		panel.add(finishButton2);
		finishButton4.setBounds(337, 159, 41, 29);
		panel.add(finishButton4);
		finishButton6.setBounds(387, 159, 41, 29);
		panel.add(finishButton6);
		finishButton8.setBounds(431, 159, 41, 29);
		panel.add(finishButton8);
		
		channelsToTrigger.add(channel1);
		channelsToTrigger.add(channel3);
		channelsToTrigger.add(channel5);
		channelsToTrigger.add(channel7);
		channelsToTrigger.add(finishButton2);
		channelsToTrigger.add(finishButton4);
		channelsToTrigger.add(finishButton6);
		channelsToTrigger.add(finishButton8);
		
		alTrigger s = new alTrigger();
		for(int i = 0; i < channelsToTrigger.size(); i++){
			channelsToTrigger.get(i).addActionListener(s);
		}
		
		check2.setBounds(291, 195, 29, 28);
		panel.add(check2);
		check4.setBounds(337, 196, 29, 28);
		panel.add(check4);
		check6.setBounds(387, 196, 29, 28);
		panel.add(check6);
		check8.setBounds(431, 196, 29, 28);
		panel.add(check8);
		
		checkBoxes.add(check1);
		checkBoxes.add(check2);
		checkBoxes.add(check3);
		checkBoxes.add(check4);
		checkBoxes.add(check5);
		checkBoxes.add(check6);
		checkBoxes.add(check7);
		checkBoxes.add(check8);
		
		alToggle x = new alToggle();
		for(int i = 0; i < checkBoxes.size(); i++){
			checkBoxes.get(i).addActionListener(x);
		}
		
		textFromButtons.setBounds(643, 257, 98, 20);
		panel.add(textFromButtons);
		
		btnPrinterPower.setBounds(630, 20, 117, 29);
		btnPrinterPower.addActionListener(new alPrint());
		panel.add(btnPrinterPower);
		
		JButton Calc1 = new JButton("1");
		Calc1.setBounds(643, 278, 41, 29);
		panel.add(Calc1);
		Calc2.setBounds(672, 278, 41, 29);
		panel.add(Calc2);
		Calc3.setBounds(701, 278, 41, 29);
		panel.add(Calc3);
		Calc4.setBounds(643, 305, 41, 29);
		panel.add(Calc4);
		Calc5.setBounds(672, 305, 41, 29);
		panel.add(Calc5);
		Calc6.setBounds(701, 305, 41, 29);
		panel.add(Calc6);
		Calc7.setBounds(643, 333, 41, 29);
		panel.add(Calc7);
		Calc8.setBounds(672, 333, 41, 29);
		panel.add(Calc8);
		Calc9.setBounds(701, 333, 41, 29);
		panel.add(Calc9);
		CalcStar.setBounds(643, 360, 41, 29);
		panel.add(CalcStar);
		Calc0.setBounds(672, 360, 41, 29);
		panel.add(Calc0);
		CalcPound.setBounds(701, 360, 41, 29);
		panel.add(CalcPound);
		
		calcButtons.add(Calc0);
		calcButtons.add(Calc1);
		calcButtons.add(Calc2);
		calcButtons.add(Calc3);
		calcButtons.add(Calc4);
		calcButtons.add(Calc5);
		calcButtons.add(Calc6);
		calcButtons.add(Calc7);
		calcButtons.add(Calc8);
		calcButtons.add(Calc9);
		calcButtons.add(CalcStar);
		calcButtons.add(CalcPound);
		
		updateString y = new updateString();
		
		for(int i = 0; i < calcButtons.size(); i++){
			calcButtons.get(i).addActionListener(y);
		}
		
		eventButton.setBounds(46, 439, 117, 29);
		eventButton.addActionListener(new alEvent());
		
		panel.add(eventButton);
		

	
		btnSwap.addActionListener(h);
		btnSwap.setBounds(653, 410, 70, 29);
		panel.add(btnSwap);
	
		connectCommandBtn.setBounds(318, 546, 96, 29);
		connectCommandBtn.addActionListener(h);
		panel.add(connectCommandBtn);
		
		
		gateSensor.setBounds(236, 455, 68, 23);
		panel.add(gateSensor);
		
		eyeSensor.setBounds(318, 455, 60, 23);
		panel.add(eyeSensor);
		
		padSensor.setBounds(386, 455, 68, 23);
		panel.add(padSensor);
		
		pushSensor.setBounds(456, 455, 68, 23);
		panel.add(pushSensor);
		
		sensorGrp.add(gateSensor);
		sensorGrp.add(eyeSensor);
		sensorGrp.add(padSensor);
		sensorGrp.add(pushSensor);
		
		sensorButtons.add(gateSensor);
		sensorButtons.add(eyeSensor);
		sensorButtons.add(padSensor);
		sensorButtons.add(pushSensor);
	
		for(int i = 0; i < sensorButtons.size(); i++){
			sensorButtons.get(i).addActionListener(new alSensor());
		}
		
		channel5Sensor.setBounds(281, 511, 46, 23);
		panel.add(channel5Sensor);
		channel6Sensor.setBounds(323, 511, 46, 23);
		panel.add(channel6Sensor);
		channel7Sensor.setBounds(368, 509, 46, 23);
		panel.add(channel7Sensor);
		channel8Sensor.setBounds(414, 509, 46, 23);
		panel.add(channel8Sensor);
		channel4Sensor.setBounds(414, 488, 46, 23);
		panel.add(channel4Sensor);
		channel2Sensor.setBounds(323, 488, 46, 23);
		panel.add(channel2Sensor);
		channel1Sensor.setBounds(281, 488, 46, 23);
		panel.add(channel1Sensor);
		channel3Sensor.setBounds(368, 488, 46, 23);
		panel.add(channel3Sensor);
		
		channelGroup.add(channel5Sensor);
		channelGroup.add(channel6Sensor);
		channelGroup.add(channel7Sensor);
		channelGroup.add(channel8Sensor);
		channelGroup.add(channel4Sensor);
		channelGroup.add(channel2Sensor);
		channelGroup.add(channel1Sensor);
		channelGroup.add(channel3Sensor);
		
		channelSensorButtons.add(channel1Sensor);
		channelSensorButtons.add(channel2Sensor);
		channelSensorButtons.add(channel3Sensor);
		channelSensorButtons.add(channel4Sensor);
		channelSensorButtons.add(channel5Sensor);
		channelSensorButtons.add(channel6Sensor);
		channelSensorButtons.add(channel7Sensor);
		channelSensorButtons.add(channel8Sensor);
		
		indEvent.setBounds(33, 290, 141, 23);
		panel.add(indEvent);
		
		indParaEvent.setBounds(33, 325, 141, 23);
		panel.add(indParaEvent);
		
	
		grpEvent.setBounds(33, 361, 141, 23);
		panel.add(grpEvent);
		
		paraGrpEvent.setBounds(33, 396, 141, 23);
		panel.add(paraGrpEvent);
		
		eventGroup.add(indEvent);
		eventGroup.add(indParaEvent);
		eventGroup.add(grpEvent);
		eventGroup.add(paraGrpEvent);
	
		eventTypes.add(indEvent);
		eventTypes.add(indParaEvent);
		eventTypes.add(grpEvent);
		eventTypes.add(paraGrpEvent);		
		for(int i = 0; i < eventTypes.size(); i++){
			eventTypes.get(i).addActionListener(new alUpdateEvent());
		}
		
		numButton.setBounds(591, 410, 60, 29);
		numButton.addActionListener(h);
		panel.add(numButton);
		
		exportButton.setBounds(725, 410, 90, 29);
		exportButton.addActionListener(h);
		panel.add(exportButton);
		
		clearButton.setBounds(626, 454, 117, 29);
		clearButton.addActionListener(h);
		panel.add(clearButton);
		
		btnNewButton.setBounds(565, 546, 117, 29);
		btnNewButton.addActionListener(h);
		panel.add(btnNewButton);
	
		btnEndrun.setBounds(680, 546, 117, 29);
		btnEndrun.addActionListener(h);
		panel.add(btnEndrun);
	
		
		for(int i = 0; i < channelSensorButtons.size(); i++){
			channelSensorButtons.get(i).addActionListener(new alChannel());
		}	
		time.scheduleAtFixedRate(new updateDisplay(), 0, 10);
}
	
	private class alPower implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(!On()) {
					Command.execute(t, currentTime,"ON",new String []{});
					setOn(true);
				}
				else {
					Command.execute(t, currentTime,"OFF",new String []{});
					setOn(false);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(On()) updateScreen("ON");
			else updateScreen("OFF");
		}
	}
	
	private class alTrigger implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < channelsToTrigger.size(); i++){
					if(e.getSource() == channelsToTrigger.get(i)){
						try {
							currentTime = LocalTime.now();							
							Command.execute(t,currentTime,"TRIG",new String [] { channelsToTrigger.get(i).getText()});
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						updateScreen("TRIG " + " " + channelsToTrigger.get(i).getText());
					}
				}
			}
		}
	}
	
	private class alToggle implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < checkBoxes.size(); i++){
					if(e.getSource() == checkBoxes.get(i)){
						try {
							currentTime = LocalTime.now();
							Command.execute(t,currentTime,"TOGGLE", new String[] {i + 1 + ""});
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						updateScreen("TOGGLE" + " " + (i+1));
					}
				}
			}
		}
	}
	
	private class alPrint implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//c = Command.commandFromString("PRINT");
			if(On()){
				String print = "";
				try {
					print = t.PRINT(Integer.parseInt(textFromButtons.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(print != null){
					updateScreen("PRINT" + "\n" + print);
				}
				else{
					updateScreen("PRINT Failed");
				}
			}
		}
	}
	
	private class updateString implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < calcButtons.size(); i++){
					if(e.getSource() == calcButtons.get(i)){
						inputFromCalcPad += calcButtons.get(i).getText();
						textFromButtons.setText(inputFromCalcPad);
					}
				}
			}
		}
	}
	
	private class alEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,"EVENT",new String []{eventType});
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateScreen("EVENT " + " " + eventType);
			}
		}
	}
	
	private class alSingleButtons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				if(e.getSource() == btnSwap){
					Command.execute(t, LocalTime.now(), "SWAP", new String[] {});
					updateScreen("Swap");
				}
				
				if(e.getSource() == connectCommandBtn){
					try {
						currentTime = LocalTime.now();
						Command.execute(t,currentTime,"CONN",new String[]{typeOfSensorToConnect, channelToConnect});
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					updateScreen("CONN " + " " + typeOfSensorToConnect + " " + channelToConnect);
				}
				
				if(e.getSource() == numButton){
					try {
						currentTime = LocalTime.now();
						Command.execute(t,currentTime,"NUM",new String []{inputFromCalcPad});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateScreen("NUM" + " " + inputFromCalcPad);
					inputFromCalcPad = "";
				}
				
				if(e.getSource() == exportButton){
					try {
						currentTime = LocalTime.now();
						Command.execute(t,currentTime,"EXPORT",new String [] {inputFromCalcPad});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateScreen( "EXPORT" + " " + inputFromCalcPad);
					inputFromCalcPad = "";
				}
				
				if(e.getSource() == clearButton){
					inputFromCalcPad = "";
					textFromButtons.setText(inputFromCalcPad);
				}
				
				if(e.getSource() == btnNewButton){
					try {
						currentTime = LocalTime.now();
						Command.execute(t,currentTime,"NEWRUN",new String[] {});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateScreen("NEWRUN");
				}
				
				if(e.getSource() == btnEndrun){
					try {
						currentTime = LocalTime.now();
						Command.execute(t,currentTime,"ENDRUN",new String [] {});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					updateScreen("ENDRUN");
				}
			}
		}
		
	}
	
	private class alSensor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < sensorButtons.size(); i++){
					if(sensorButtons.get(i) == e.getSource()){
						typeOfSensorToConnect = sensorButtons.get(i).getText();
					}
				}
			}
		}
		
	}	
		
	private class alUpdateEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < eventTypes.size(); i++){
					if(e.getSource() == eventTypes.get(i)){
						eventType = eventTypes.get(i).getText();
					}
				}	
			}
		}
		
	}	
	private class alChannel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(On()){
				for(int i = 0; i < channelSensorButtons.size(); i++){
					if(channelSensorButtons.get(i) == e.getSource()){
						channelToConnect = channelSensorButtons.get(i).getText();
					}
				}
			}
		}
		
	}

	private boolean On(){
		return isOn == true;
	}
	
	private void setOn(boolean state){
		isOn = state;
	}
	
	private void updateScreen(String text){
		printerText.setText(printerText.getText() + "\n" + text);
	}
	
	private class updateDisplay extends TimerTask{

		@Override
		public void run() {
			if(On()){
				for(AbstractEvent y : t.getRuns()){
					if(!y.toString().equals("")){
						consoleText.setText(consoleText.getText() + "\n" + y.toString());
					}
				}
			}
		}
		
	}
}
