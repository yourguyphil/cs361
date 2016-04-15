package chronotimer;
import io.Command;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import race.IND;
import race.Lane;

public class ChronoTimerEmulator extends JApplet {

	/**
	 * 
	 */
	ChronoTimer t = new ChronoTimer();
	IND ind;
	private static final long serialVersionUID = 1L;
	Command c;
	private String channelToConnect;
	private String typeOfSensorToConnect;
	private String inputFromCalcPad = "";
	private String eventType = "";
	boolean isOn = false;
	private LocalTime currentTime;
	
	/**
	 * Create the applet.
	 */
	public ChronoTimerEmulator() {
		setSize(800,600);
		currentTime = LocalTime.now();
		JPanel panel = new JPanel();
		panel.setSize(800, 600);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton powerButton = new JButton("Power");
		powerButton.setBounds(33, 20, 68, 29);
		powerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!isOn) {
						Command.execute(t, currentTime,"ON",null);
						isOn = true;
					}
					else {
						Command.execute(t, currentTime,"OFF",null);
						isOn = false;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(isOn) System.out.println("ON");
				else System.out.println("OFF");
			}
		});
		panel.add(powerButton);
		
		
		JTextPane consoleText = new JTextPane();
		consoleText.setBounds(246, 246, 252, 179);
		panel.add(consoleText);
		
		JTextPane printerText = new JTextPane();
		printerText.setBounds(516, 52, 262, 160);
		panel.add(printerText);
		
	
		
		JLabel lblNewLabel = new JLabel("CHRONOTIMER POWER RANGERS");
		lblNewLabel.setBounds(387, 6, 140, 16);
		panel.add(lblNewLabel);
		
		JLabel startLbl = new JLabel("Start");
		startLbl.setBounds(234, 52, 29, 16);
		panel.add(startLbl);
		
		JLabel enableDisplayLbl = new JLabel("Enable/Disable");
		enableDisplayLbl.setBounds(169, 93, 95, 16);
		panel.add(enableDisplayLbl);
		
		JButton channel1 = new JButton("1");
		channel1.setBounds(291, 44, 29, 29);
		panel.add(channel1);
		
		JButton channel3 = new JButton("3");
		channel3.setBounds(337, 44, 29, 29);
		panel.add(channel3);
		
		JButton channel5 = new JButton("5");
		channel5.setBounds(385, 44, 29, 29);
		panel.add(channel5);
		
		JButton channel7 = new JButton("7");
		channel7.setBounds(431, 44, 29, 29);
		panel.add(channel7);
		
		JCheckBox check1 = new JCheckBox("");
		check1.setBounds(291, 85, 29, 28);
		panel.add(check1);
		
		JCheckBox check3 = new JCheckBox("");
		check3.setBounds(337, 85, 29, 28);
		panel.add(check3);
		
		JCheckBox check5 = new JCheckBox("");
		check5.setBounds(385, 85, 29, 28);
		panel.add(check5);
		
		JCheckBox check7 = new JCheckBox("");
		check7.setBounds(431, 85, 29, 28);
		panel.add(check7);
		
		JLabel finishLbl = new JLabel("Finish");
		finishLbl.setBounds(225, 164, 38, 16);
		panel.add(finishLbl);
		
		JLabel enableDisableLbl2 = new JLabel("Enable/Disable");
		enableDisableLbl2.setBounds(169, 200, 95, 16);
		panel.add(enableDisableLbl2);
		
		JButton finishButton2 = new JButton("2");
		finishButton2.setBounds(291, 159, 29, 29);
		panel.add(finishButton2);
		
		JButton finishButton4 = new JButton("4");
		finishButton4.setBounds(337, 159, 29, 29);
		panel.add(finishButton4);
		
		JButton finishButton6 = new JButton("6");
		finishButton6.setBounds(387, 159, 29, 29);
		panel.add(finishButton6);
		
		JButton finishButton8 = new JButton("8");
		finishButton8.setBounds(431, 159, 29, 29);
		panel.add(finishButton8);
		
		ArrayList<JButton> channelsToTrigger = new ArrayList<JButton>();
		channelsToTrigger.add(channel1);
		channelsToTrigger.add(channel3);
		channelsToTrigger.add(channel5);
		channelsToTrigger.add(channel7);
		channelsToTrigger.add(finishButton2);
		channelsToTrigger.add(finishButton4);
		channelsToTrigger.add(finishButton6);
		channelsToTrigger.add(finishButton8);
		
		ActionListener trigger = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < channelsToTrigger.size(); i++){
					if(e.getSource() == channelsToTrigger.get(i)){
						String c = "TRIG " + channelsToTrigger.get(i).getText();
						System.out.println(c);
						try {
							currentTime = LocalTime.now();							
							Command.execute(t,currentTime,c,null);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						consoleText.setText(c + "\n");
					}
				}
			}
		};

		for(int i = 0; i < channelsToTrigger.size(); i++){
			channelsToTrigger.get(i).addActionListener(trigger);
		}

		
		JCheckBox check2 = new JCheckBox("");
		check2.setBounds(291, 195, 29, 28);
		panel.add(check2);
		
		JCheckBox check4 = new JCheckBox("");
		check4.setBounds(337, 196, 29, 28);
		panel.add(check4);
		
		JCheckBox check6 = new JCheckBox("");
		check6.setBounds(387, 196, 29, 28);
		panel.add(check6);
		
		JCheckBox check8 = new JCheckBox("");
		check8.setBounds(431, 196, 29, 28);
		panel.add(check8);
		
		ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
		checkBoxes.add(check1);
		checkBoxes.add(check2);
		checkBoxes.add(check3);
		checkBoxes.add(check4);
		checkBoxes.add(check5);
		checkBoxes.add(check6);
		checkBoxes.add(check7);
		checkBoxes.add(check8);
		
		ActionListener toggle = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < checkBoxes.size(); i++){
					if(e.getSource() == checkBoxes.get(i)){
						String c = "TOGGLE " + (i+1);
						try {
							currentTime = LocalTime.now();
							Command.execute(t,currentTime,c,null);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(c);
						consoleText.setText(c);
					}
				}
			}
		};
		
		for(int i = 0; i < checkBoxes.size(); i++){
			checkBoxes.get(i).addActionListener(toggle);
		}
		
		JTextArea textFromButtons = new JTextArea();
		textFromButtons.setBounds(643, 257, 87, 20);
		panel.add(textFromButtons);
		
		JButton btnPrinterPower = new JButton("Print");
		btnPrinterPower.setBounds(586, 20, 117, 29);
		btnPrinterPower.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//c = Command.commandFromString("PRINT");
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,"PRINT",null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("PRINT");
				Lane lane = new Lane();
				printerText.setText("" + lane.getStartedRacers());
			}
		});
		panel.add(btnPrinterPower);
		
		
	
		
		JButton Calc1 = new JButton("1");
		Calc1.setBounds(643, 278, 29, 29);
		panel.add(Calc1);
		
		JButton Calc2 = new JButton("2");
		Calc2.setBounds(672, 278, 29, 29);
		panel.add(Calc2);
		
		JButton Calc3 = new JButton("3");
		Calc3.setBounds(701, 278, 29, 29);
		panel.add(Calc3);
		
		JButton Calc4 = new JButton("4");
		Calc4.setBounds(643, 305, 29, 29);
		panel.add(Calc4);
		
		JButton Calc5 = new JButton("5");
		Calc5.setBounds(672, 305, 29, 29);
		panel.add(Calc5);
		
		JButton Calc6 = new JButton("6");
		Calc6.setBounds(701, 305, 29, 29);
		panel.add(Calc6);
		
		JButton Calc7 = new JButton("7");
		Calc7.setBounds(643, 333, 29, 29);
		panel.add(Calc7);
		
		JButton Calc8 = new JButton("8");
		Calc8.setBounds(672, 333, 29, 29);
		panel.add(Calc8);
		
		JButton Calc9 = new JButton("9");
		Calc9.setBounds(701, 333, 29, 29);
		panel.add(Calc9);
		
		JButton CalcStar = new JButton("*");
		CalcStar.setBounds(643, 360, 29, 29);
		panel.add(CalcStar);
		
		JButton Calc0 = new JButton("0");
		Calc0.setBounds(672, 360, 29, 29);
		panel.add(Calc0);
		
		JButton CalcPound = new JButton("#");
		CalcPound.setBounds(701, 360, 29, 29);
		panel.add(CalcPound);
		
		JButton calcSpecial = new JButton(":");
		calcSpecial.setBounds(730, 360, 29, 29);
		panel.add(calcSpecial);
		
		ArrayList<JButton>calcButtons = new ArrayList<JButton>();
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
		calcButtons.add(calcSpecial);
		
		ActionListener updateStringInput = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < calcButtons.size(); i++){
					if(e.getSource() == calcButtons.get(i)){
						inputFromCalcPad += calcButtons.get(i).getText();
						textFromButtons.setText(inputFromCalcPad);
					}
				}
			}
		};
		
		for(int i = 0; i < calcButtons.size(); i++){
			calcButtons.get(i).addActionListener(updateStringInput);
		}




		
		
		JButton eventButton = new JButton("Make Event");
		eventButton.setBounds(46, 439, 117, 29);
		eventButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "EVENT " + eventType;
				System.out.println("EVENT " + eventType);
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,c,null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		panel.add(eventButton);
		
		JButton btnSwap = new JButton("SWAP");
		btnSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSwap.setBounds(653, 410, 60, 29);
		panel.add(btnSwap);
		
		JButton connectCommandBtn = new JButton("Connect");
		connectCommandBtn.setBounds(318, 546, 96, 29);
		connectCommandBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String test = "CONN " + typeOfSensorToConnect + " " + channelToConnect;
				System.out.println(test);
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,test,null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				consoleText.setText(test);
			}
			
		});
		panel.add(connectCommandBtn);
		
		JRadioButton gateSensor = new JRadioButton("GATE");
		
		gateSensor.setBounds(236, 455, 68, 23);
		
	
		
		
		panel.add(gateSensor);
		
		JRadioButton eyeSensor = new JRadioButton("EYE");
		eyeSensor.setBounds(318, 455, 60, 23);
		panel.add(eyeSensor);
		
		JRadioButton padSensor = new JRadioButton("PAD");
		padSensor.setBounds(386, 455, 68, 23);
		panel.add(padSensor);
		
		JRadioButton pushSensor = new JRadioButton("PUSH");
		pushSensor.setBounds(456, 455, 68, 23);
		panel.add(pushSensor);
		
		ButtonGroup sensorGrp = new ButtonGroup();
		sensorGrp.add(gateSensor);
		sensorGrp.add(eyeSensor);
		sensorGrp.add(padSensor);
		sensorGrp.add(pushSensor);
		
		ArrayList<JRadioButton> sensorButtons = new ArrayList<JRadioButton>();
		sensorButtons.add(gateSensor);
		sensorButtons.add(eyeSensor);
		sensorButtons.add(padSensor);
		sensorButtons.add(pushSensor);

		
		
		ActionListener updateSensorType = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < sensorButtons.size(); i++){
					if(sensorButtons.get(i) == e.getSource()){
						typeOfSensorToConnect = sensorButtons.get(i).getText();
					}
				}
			}
		};
		
		for(int i = 0; i < sensorButtons.size(); i++){
			sensorButtons.get(i).addActionListener(updateSensorType);
		}
		
		
		JRadioButton channel5Sensor = new JRadioButton("5");
		channel5Sensor.setBounds(281, 511, 46, 23);
		panel.add(channel5Sensor);
		
		JRadioButton channel6Sensor = new JRadioButton("6");
		channel6Sensor.setBounds(323, 511, 46, 23);
		panel.add(channel6Sensor);
		
		JRadioButton channel7Sensor = new JRadioButton("7");
		channel7Sensor.setBounds(368, 509, 46, 23);
		panel.add(channel7Sensor);
		
		JRadioButton channel8Sensor = new JRadioButton("8");
		channel8Sensor.setBounds(414, 509, 46, 23);
		panel.add(channel8Sensor);
		
		JRadioButton channel4Sensor = new JRadioButton("4");
		channel4Sensor.setBounds(414, 488, 46, 23);
		panel.add(channel4Sensor);
		
		JRadioButton channel2Sensor = new JRadioButton("2");
		channel2Sensor.setBounds(323, 488, 46, 23);
		panel.add(channel2Sensor);
		
		JRadioButton channel1Sensor = new JRadioButton("1");
		channel1Sensor.setBounds(281, 488, 46, 23);
		panel.add(channel1Sensor);
		
		JRadioButton channel3Sensor = new JRadioButton("3");
		channel3Sensor.setBounds(368, 488, 46, 23);
		panel.add(channel3Sensor);
		
		ButtonGroup channelGroup = new ButtonGroup();
		channelGroup.add(channel5Sensor);
		channelGroup.add(channel6Sensor);
		channelGroup.add(channel7Sensor);
		channelGroup.add(channel8Sensor);
		channelGroup.add(channel4Sensor);
		channelGroup.add(channel2Sensor);
		channelGroup.add(channel1Sensor);
		channelGroup.add(channel3Sensor);
		
		ArrayList<JRadioButton> channelSensorButtons = new ArrayList<JRadioButton>();
		channelSensorButtons.add(channel1Sensor);
		channelSensorButtons.add(channel2Sensor);
		channelSensorButtons.add(channel3Sensor);
		channelSensorButtons.add(channel4Sensor);
		channelSensorButtons.add(channel5Sensor);
		channelSensorButtons.add(channel6Sensor);
		channelSensorButtons.add(channel7Sensor);
		channelSensorButtons.add(channel8Sensor);
		
		JRadioButton indEvent = new JRadioButton("IND");
		indEvent.setBounds(33, 290, 141, 23);
		panel.add(indEvent);
		
		JRadioButton indParaEvent = new JRadioButton("PARIND");
		indParaEvent.setBounds(33, 325, 141, 23);
		panel.add(indParaEvent);
		
		JRadioButton grpEvent = new JRadioButton("GRP");
		grpEvent.setBounds(33, 361, 141, 23);
		panel.add(grpEvent);
		
		JRadioButton paraGrpEvent = new JRadioButton("PARGRP");
		paraGrpEvent.setBounds(33, 396, 141, 23);
		panel.add(paraGrpEvent);
		
		ButtonGroup eventGroup = new ButtonGroup();
		eventGroup.add(indEvent);
		eventGroup.add(indParaEvent);
		eventGroup.add(grpEvent);
		eventGroup.add(paraGrpEvent);

		ArrayList<JRadioButton>eventTypes = new ArrayList<JRadioButton>();
		eventTypes.add(indEvent);
		eventTypes.add(indParaEvent);
		eventTypes.add(grpEvent);
		eventTypes.add(paraGrpEvent);	
		
		ActionListener updateEventType = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < eventTypes.size(); i++){
						if(e.getSource() == eventTypes.get(i)){
							eventType = eventTypes.get(i).getText();
							if(eventType.equals("IND")) ind = new IND();
						}
					}
			}
		};
		
		for(int i = 0; i < eventTypes.size(); i++){
			eventTypes.get(i).addActionListener(updateEventType);
		}
		
		JButton numButton = new JButton("NUM");
		numButton.setBounds(586, 410, 60, 29);
		numButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "NUM " + inputFromCalcPad;
				inputFromCalcPad = "";
				textFromButtons.setText(inputFromCalcPad);
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,c,null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(c);
				ind.start(currentTime,0);
			}
		});
		panel.add(numButton);
		
	
		
		JButton timeButton = new JButton("TIME");
		timeButton.setBounds(718, 410, 60, 29);
		timeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = "TIME " + inputFromCalcPad;
				inputFromCalcPad = "";
				textFromButtons.setText(inputFromCalcPad);
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,command,null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(command);
			}
		});
		panel.add(timeButton);
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBounds(626, 454, 117, 29);
		clearButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				inputFromCalcPad = "";
				textFromButtons.setText(inputFromCalcPad);
			}
			
			
		});
		panel.add(clearButton);
		
		JButton btnNewButton = new JButton("NEWRUN");
		btnNewButton.setBounds(529, 546, 117, 29);
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "NEWRUN";
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,c,null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(c);
			}
			
		});
		panel.add(btnNewButton);
		
		JButton btnEndrun = new JButton("ENDRUN");
		btnEndrun.setBounds(643, 546, 117, 29);
		btnEndrun.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "ENDRUN";
				try {
					currentTime = LocalTime.now();
					Command.execute(t,currentTime,c,null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(c);
			}
			
		});
		panel.add(btnEndrun);

		ActionListener updateChannelType = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < channelSensorButtons.size(); i++){
					if(channelSensorButtons.get(i) == e.getSource()){
						channelToConnect = channelSensorButtons.get(i).getText();
					}
				}
			}
		};
		
		for(int i = 0; i < channelSensorButtons.size(); i++){
			channelSensorButtons.get(i).addActionListener(updateChannelType);
		}
		
	}
}
