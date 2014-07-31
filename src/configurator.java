import java.awt.*;
import java.applet.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JTextField;
//import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class configurator extends Applet implements ActionListener, WindowListener
{
//Data Storages
	String[][] MB_storage = {
	{"GB M41P-S3P s.775 2xDDR3", "775", "DDR3","SATA2", "60"},
	{"GB M770-DS3,s.AM3,4xDDR3", "AM3", "DDR3","SATA2", "76"},
	{"ASRock P65Twins s.1155,DDR3","1155","DDR2","SATA3","100"},
	{"ASUS M2N,AM2,DDR2","AM2","DDR2","SATA2","69"}
	};
	String[][] CPU_storage = {
	{"C2Duo E6300, 2.7Ghz,s.775","775","","","125"},
	{"Athlon 5200+", "AM2","","","100"},
	{"i3-2100,s.1155", "1155","","","190"},
	{"C2Duo E8200 2.66Ghz, 6mb l2 cache, s.775", "775","","","220"}
	};
	String[][] RAM_storage = {
	{"2GB DDR2 800","DDR2","","","35"},
	{"1GB DDR3 1333","DDR3","","","15"},
	{"2GB DDR3 1333","DDR3","","","26"},
	{"4GB DDR3 1333","DDR3","","","47"}
	};
	String[][] HDD_storage = {
	{"Hitachi 320GB SATA2","SATA2","","","160"},
	{"Western Digital 500GB SATA3","SATA3","","","180"},
	{"Seagate 1TB SATA2","SATA2","","","210"},
	{"Samsung 500GB SATA2","SATA2","","","180"}
	};
	String[][] VC_storage ={
	{"Sapphire HD6770 1GB DDR5","","","","250"},
	{"Sapphire HD5670 512MB DDR3","","","","140"},
	{"Sapphire HD4850 1GB DDR3","","","","120"},
	{"Gigabyte 560GTX 1GB DDR5","","","","230"}
	};
	String[][] PSU_storage ={
	{"Fortron 350W","","","","60"},
	{"Chieftec 400W","","","","75"},
	{"Fortron Epsilon 600W","","","","120"},
	{"Chieftec 750W","","","","190"}
	};
	String[][] DVD_storage ={
	{"LG GH22NS40 Black SATA2","","","","36"},
	{"NEC GN213 DVD-ROM SATA2","","","","25"},
	{"LiteOn NS291 DVD-RW IDE", "", "", "", "36"},
	{"Sony DVD-RW GS22 SATA2", "","","", "39"}
	
	};
	String[] component_type = {"MB","CPU","RAM","HDD","DVD","PSU","VC"};
	
//==========================================================================
//Panel options
		//main panel
		Panel main = new Panel();
			//menu NORTH panel
			Panel menu1 = new Panel();
				JButton finish = new JButton("Сметни общо");
				JButton clear = new JButton("Изчисти всичко");
				JButton component_add = new JButton("Добави!");
			//CENTER panel	
			JPanel content = new JPanel();
				JTextField[]  	Content_type =	new JTextField[13];
				JTextField[]	Content_model = new JTextField[13];
				JTextField[]  	Content_price = new JTextField[13];
				String[]			counts		  = {"1","2","3","4","5","6","7","8","9","10"};
				@SuppressWarnings("rawtypes")
				JComboBox[]		Content_count = new JComboBox[13];
				JButton[]		Content_remove= new JButton[13];
				JButton[] 		Content_add = 	new JButton[13];
			//SOUTH Panel
			JPanel bottom = new JPanel();
				JTextField lbl_sum = new JTextField(5);
				String[] cncy = {"Лев","Долар","Евро"};
				JComboBox<String> currency = new JComboBox<String>(cncy);
				
//===================================================================
//Main Control options
		JFrame choice = 			new JFrame("Choose component");
		JComboBox<String> type = 	new JComboBox<String>(component_type);
		JComboBox<String> model =	new JComboBox<String>();
		JTextField lbl_price = 	new JTextField(4);
		JButton input = 			new JButton("Добави");
		JButton final_add =  		new JButton("Край");
		
//===================================================================
		
//Help variables

		String str,money;
		int[] free={0,0,0,0,0,0,0,0,0,0,0,0,0};
		int i=0, pos=0;
//===================================================================
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void init()
		{
			//set Layout
			main.setLayout(new BorderLayout());
			menu1.setLayout(new FlowLayout());
			GridBagLayout grid = new GridBagLayout();		
			GridBagConstraints g = new GridBagConstraints();
		//	content.setLayout(new GridLayout(0,5));
			content.setLayout(grid);
			choice.setLayout(new GridLayout(5,0));
			bottom.setLayout(new FlowLayout());
			
			add(main);
			
			//set items SOUTH
			bottom.add(currency);
			bottom.add(lbl_sum);
			
			//set items NORTH
			menu1.add(clear);
			
			//initialize main Panel
			main.add(menu1, BorderLayout.NORTH);
			main.add(content,BorderLayout.CENTER);
			main.add(bottom, BorderLayout.SOUTH);
			lbl_sum.setEditable(false);
			
			
			//initialize choice Frame
			choice.add(type);
			choice.add(model);
			choice.add(lbl_price);
			lbl_price.setEditable(false);
			lbl_price.setAlignmentX(CENTER_ALIGNMENT);
			choice.add(input);
			choice.add(final_add);
			final_add.addActionListener(this);
			
			//set items CENTER 
			JLabel lbl_vid=new JLabel("Вид");
			g.fill=GridBagConstraints.HORIZONTAL;
			g.gridx=0;
			g.gridy=0;
			content.add(lbl_vid,g);
			
			JLabel lbl_model2=new JLabel("Модел");
			g.fill=GridBagConstraints.NONE;
			g.gridx=1;
			g.gridy=0;
			content.add(lbl_model2,g);
			
			JLabel lbl_count2=new JLabel("Брой");
			g.fill=GridBagConstraints.NONE;
			g.gridx=2;
			g.gridy=0;
			content.add(lbl_count2,g);
			
			JLabel lbl_price2=new JLabel("Цена в лева");
			g.fill=GridBagConstraints.NONE;
			g.gridx=3;
			g.gridy=0;
			content.add(lbl_price2,g);
			
			JLabel lbl_add2=new JLabel("Добави");
			lbl_add2.setVerticalAlignment(JLabel.CENTER);
			g.fill=GridBagConstraints.NONE;
			g.gridx=4;
			g.gridy=0;
			content.add(lbl_add2,g);
			
			JLabel lbl_del2=new JLabel("Премахни");
			lbl_del2.setVerticalAlignment(JLabel.CENTER);
			g.fill=GridBagConstraints.NONE;
			g.gridx=5;
			g.gridy=0;
			content.add(lbl_del2,g);
			
			Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
			for(int k=0;k<13;k++)
			{
				Content_type[k] = new JTextField("", 5);
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=0;
				g.gridy=k+1;
					Content_type[k].setBorder(blackline);
					Content_type[k].setEditable(false);
					content.add(Content_type[k],g);
			}
			for(int k=0;k<13;k++)
			{
				Content_model[k] = new JTextField("",23);
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=1;
				g.gridy=k+1;
					Content_model[k].setEditable(false);
					Content_model[k].setBorder(blackline);
					content.add(Content_model[k],g);
			}
			for(int k=0;k<13;k++){
				Content_count[k] = new JComboBox(counts);
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=2;
				g.gridy=k+1;
					Content_count[k].setEditable(true);
					Content_count[k].setBorder(blackline);
					content.add(Content_count[k],g);
			}
			
			for(int k=0;k<13;k++){
				Content_price[k] = new JTextField("0",5);
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=3;
				g.gridy=k+1;
					Content_price[k].setEditable(false);
					Content_price[k].setBorder(blackline);
					content.add(Content_price[k],g);
			}
			for(int k=0;k<13;k++){
				Content_add[k] = new JButton("Добави");	
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=4;
				g.gridy=k+1;
					Content_add[k].addActionListener(this);
					content.add(Content_add[k],g);
			}
			for(int k=0;k<13;k++){
				Content_remove[k] = new JButton("Премахни");
				g.fill=GridBagConstraints.VERTICAL;
				g.gridx=5;
				g.gridy=k+1;
					Content_remove[k].addActionListener(this);
					content.add(Content_remove[k],g);
			}

			//set ActionListeners
			clear.addActionListener(this);
			type.addActionListener(this);
			model.addActionListener(this);
			input.addActionListener(this);
			finish.addActionListener(this);
			currency.addActionListener(this);
			//set WindowListeners
			choice.addWindowListener(this);
		}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== final_add) choice.setVisible(false);
		
		for(int k=0;k<13;k++) 		if(e.getSource()==Content_remove[k])onComponentDel(k);
		for(int k=0;k<13;k++) 		if(e.getSource()== Content_add[k]) onComponentAdd(k);
		for(int k=0;k<13;k++)		if(e.getSource()== Content_count[k]) onSum();
		
		if(e.getSource()== type) onType();
		if(e.getSource()== model)onModel();
		if(e.getSource()== input)onInput();
		if(e.getSource()== clear)onClear();
		if(e.getSource()== currency) onCurrency();
	}
	public void onCurrency(){
		if(currency.getSelectedItem() == "Долар"){
			lbl_sum.setText(String.valueOf(Math.round(Integer.parseInt(money)/1.53)));
		}
		if(currency.getSelectedItem() == "Евро"){
			lbl_sum.setText(String.valueOf((Math.round(Integer.parseInt(money)/1.96) )));
		}
		if(currency.getSelectedItem() == "Лев"){
			onSum();
		}
	}
	public void onClear()
	{
		for(int s=0;s<13;s++)
		{
			Content_type[s].setText("");
			Content_model[s].setText("");
			Content_price[s].setText("0");
		}
		onSum();
	}
	public void onSum()
	{	
		int sum=0;
		for(int k=0;k<13;k++)
		{
			if(Content_price[k].getText()!="0")
			{
				str = Content_price[k].getText();
				String str2=Content_count[k].getSelectedItem().toString();
				
				sum+=(Integer.parseInt(str)*Integer.parseInt(str2));
			}
		}
		lbl_sum.setText(String.valueOf(sum));
		money = lbl_sum.getText();
	
	}
	public void onComponentDel(int k){

			Content_type[k].setText("");
			Content_model[k].setText("");
			Content_price[k].setText("0");
			onSum();
	}
	public void onComponentAdd(int k)
	{
		choice.setVisible(true);
		choice.setSize(450, 200);
		choice.setLocation(200,200);
		//choice.addWindowListener(this);
		pos=k;
		free[pos]=1;
	}
	public void onInput()
	{
		Content_type[pos].setText((String) type.getSelectedItem());
		Content_model[pos].setText((String) model.getSelectedItem());
		Content_price[pos].setText(lbl_price.getText());
		onSum();
	}
	public void onModel()
	{
		for(int i=0;i<4;i++)
		{
			if(model.getSelectedItem() == MB_storage[i][0])
			lbl_price.setText(MB_storage[i][4]);
			
			if(model.getSelectedItem() == CPU_storage[i][0])
				lbl_price.setText(CPU_storage[i][4]);
			
			if(model.getSelectedItem() == RAM_storage[i][0])
				lbl_price.setText(RAM_storage[i][4]);
			
			if(model.getSelectedItem() == HDD_storage[i][0])
				lbl_price.setText(HDD_storage[i][4]);
			
			if(model.getSelectedItem() == DVD_storage[i][0])
				lbl_price.setText(DVD_storage[i][4]);
			
			if(model.getSelectedItem() == VC_storage[i][0])
				lbl_price.setText(VC_storage[i][4]);
			
			if(model.getSelectedItem() == PSU_storage[i][0])
				lbl_price.setText(PSU_storage[i][4]);
		}
	}
	public void onType()
	{

		if(type.getSelectedItem() == "MB"){
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(MB_storage[i][0]);
		}
		if(type.getSelectedItem() == "CPU") {
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(CPU_storage[i][0]);
		}
		if(type.getSelectedItem() == "RAM")	{
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(RAM_storage[i][0]);
		}
		if(type.getSelectedItem() == "HDD")	{
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(HDD_storage[i][0]);
		}
		if(type.getSelectedItem() == "DVD")	{
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(DVD_storage[i][0]);
		}
		if(type.getSelectedItem() == "PSU") {
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(PSU_storage[i][0]);
		}
		if(type.getSelectedItem() == "VC") {
			model.removeAllItems();
			for(int i=0;i<4;i++)
				model.addItem(VC_storage[i][0]);
			}
		
	}
	public void windowClosing(WindowEvent e){
			choice.setVisible(false);
		//	choice.dispose();
	}
	public void windowClosed(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}