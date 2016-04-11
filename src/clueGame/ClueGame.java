package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame{	
	public Board board;
	public DetectiveNotes d;
	
	
	public ClueGame(){
		board = new Board();
		board.initialize();
		setSize(1060, 840);
		add(board);
		d = new DetectiveNotes();
		d.setSize(400, 400);
		makeMenu();
		
	}
	public void makeMenu(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
	}

	private JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File"); 
		menu.add(createFileDetectiveNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}

		}
		item.addActionListener(new MenuItemListener());

		return item;
	}

	private JMenuItem createFileDetectiveNotesItem()
	{
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				d.setVisible(true);
			}

		}
		item.addActionListener(new MenuItemListener());

		return item;
	}
	
	public static void main(String [] args){
		ClueGame game = new ClueGame();
		game.setVisible(true);
	}
	
}
