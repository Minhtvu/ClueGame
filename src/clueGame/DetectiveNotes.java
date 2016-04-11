package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog{
	public DetectiveNotes(){
		setLayout(new GridLayout(3,2));
		 JPanel panel = createPeopleSeenPanel();
		 add(panel);
		 panel = createPeopleGuessPanel();
		 add(panel);
		 panel = createWeaponSeenPanel();
		 add(panel);
		 panel = createWeaponGuessPanel();
		 add(panel);
		 panel = createRoomSeenPanel();
		 add(panel);
		 panel = createRoomGuessPanel();
		 add(panel);
	}

	private JPanel createRoomGuessPanel() {
		JPanel panel = new JPanel();
		JComboBox<String> b = new JComboBox<>();
		b.addItem("Conservatory");
		b.addItem("Kitchen");
		b.addItem("Ballroom");
		b.addItem("Billiard Room");
		b.addItem("Library");
		b.addItem("Study");
		b.addItem("Dining Room");
		b.addItem("Lounge");
		b.addItem("Hall");
		panel.add(b);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		return panel;
	}

	private JPanel createRoomSeenPanel() {
		JPanel panel = new JPanel();
		 panel.setLayout(new GridLayout(0,2));
		 JCheckBox b = new JCheckBox("Conservatory");
		 panel.add(b);
		 b = new JCheckBox("Kitchen");
		 panel.add(b);
		 b = new JCheckBox("Ballroom");
		 panel.add(b);
		 b = new JCheckBox("Billiard Room");
		 panel.add(b);
		 b = new JCheckBox("Library");
		 panel.add(b);
		 b = new JCheckBox("Study");
		 panel.add(b);
		 b = new JCheckBox("Dining Room");
		 panel.add(b);
		 b = new JCheckBox("Lounge");
		 panel.add(b);
		 b = new JCheckBox("Hall");
		 panel.add(b);
		 panel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		 return panel;
	}

	private JPanel createWeaponGuessPanel() {
		JPanel panel = new JPanel();
		JComboBox<String> b = new JComboBox<>();
		b.addItem("Knife");
		b.addItem("Lead Pipe");
		b.addItem("Revolver");
		b.addItem("Rope");
		b.addItem("Wrench");
		b.addItem("Candle Stick");
		panel.add(b);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		return panel;
	}

	private JPanel createWeaponSeenPanel() {
		JPanel panel = new JPanel();
		 panel.setLayout(new GridLayout(0,2));
		 JCheckBox b = new JCheckBox("Knife");
		 panel.add(b);
		 b = new JCheckBox("Lead Pipe");
		 panel.add(b);
		 b = new JCheckBox("Revolver");
		 panel.add(b);
		 b = new JCheckBox("Rope");
		 panel.add(b);
		 b = new JCheckBox("Wrench");
		 panel.add(b);
		 b = new JCheckBox("Candlestick");
		 panel.add(b);
		 panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		 return panel;
	}

	private JPanel createPeopleGuessPanel() {
		JPanel panel = new JPanel();
		JComboBox<String> b = new JComboBox<>();
		b.addItem("Professor Periwinkle");
		b.addItem("Mrs. Peacock");
		b.addItem("Miss Scarlett");
		b.addItem("Mr. Green");
		b.addItem("Colonel Mustard");
		b.addItem("Mrs. White");
		panel.add(b);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People Guess"));
		return panel;
	}

	private JPanel createPeopleSeenPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		JCheckBox b = new JCheckBox("Professor Periwinkle");
		panel.add(b);
		b = new JCheckBox("Mrs. Peacock");
		panel.add(b);
		b = new JCheckBox("Miss Scarlett");
		panel.add(b);
		b = new JCheckBox("Mr. Green");
		panel.add(b);
		b = new JCheckBox("Colonel Mustard");
		panel.add(b);
		b = new JCheckBox("Mrs. White");
		panel.add(b);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return panel;
	}
}
