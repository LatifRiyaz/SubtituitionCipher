import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Cryption implements ActionListener{

    JFrame frame;                                  
    JTextField tf;
    JButton[] FunctionButtons = new JButton[2];
    JButton Clear, Submit;
    JRadioButton Encrypt, Decrypt, NewKey;
    JPanel panel;
    JLabel l;
    Font myFont = new Font("Times New Roman", Font.BOLD, 20);    
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String message;
    private char[] letters;
   
    Cryption(){          
        
        list = new ArrayList();
        shuffledList = new ArrayList(); 
        frame = new JFrame("Substitution Cipher En(De)Crypterv");        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());

	tf = new JTextField();
	tf.setBounds(50,25,400,100);
	tf.setFont(myFont);
	tf.setEditable(true);     
        
	Clear = new JButton("Clear Field");
        Submit = new JButton("Generate");        
        
 	Encrypt = new JRadioButton("Encryption");
	Decrypt = new JRadioButton("Decryption");
      NewKey = new JRadioButton("Initiate");                
	Encrypt.setBounds(70,190,150,50);
	Decrypt.setBounds(70,240,150,50);
      NewKey.setBounds(70,140,150,50);               

	ButtonGroup group = new ButtonGroup();
	group.add(Encrypt);
	group.add(Decrypt);
        group.add(NewKey);           
	Encrypt.addActionListener(this);		
	Decrypt.addActionListener(this);
        NewKey.addActionListener(this);              
        frame.add(Encrypt);
        frame.add(Decrypt);
        frame.add(NewKey);                
	frame.setLayout(null);
	frame.setVisible(true);

	FunctionButtons[0] = Clear;
        FunctionButtons[1] = Submit;

	for(int i = 0; i < 2; i++){

	    FunctionButtons[i].addActionListener(this);
	    FunctionButtons[i].setFont(myFont);
	    FunctionButtons[i].setFocusable(false);
	}

	Clear.setBounds(250,200,150,50);
        Submit.setBounds(250,140,150,50);
	panel = new JPanel();
	panel.setBackground(Color.WHITE);
	panel.add(Clear);
	panel.add(Submit);       
        frame.add(panel);
        frame.add(Clear);
        frame.add(Submit);
	frame.add(tf);
	frame.setVisible(true);        
    }     

    @Override
    public void actionPerformed(ActionEvent ae) {  
        
        if(Encrypt.isSelected() && ae.getSource() == Submit){
            message = tf.getText();
            letters = message.toCharArray(); 
            encrypt();
        }
        if(Decrypt.isSelected() && ae.getSource() == Submit){            
            message = tf.getText();
            letters = message.toCharArray(); 
            decrypt();
        }        
        if(NewKey.isSelected()){
            message = tf.getText();
            letters = message.toCharArray(); 
            newKey();
            getKey();
        }        
        if(ae.getSource() == Clear){
            tf.setText("");
        }        
    }
    
    public static void main(String args[]){  
        Cryption cr = new Cryption();                
    }
    
    private void newKey(){
        
	character = ' ';
	list.clear();
	shuffledList.clear();
	for(int i=32; i<127; i++){
     	    list.add(character);
	    character++;
        }       
	shuffledList = new ArrayList(list);
	Collections.shuffle(shuffledList);
	JOptionPane.showMessageDialog(frame, "Initiated");       
    }
    
    private void getKey(){

	System.out.println("Key  : ");
	for(Character x : list){
	    System.out.print(x);
	}
	System.out.println();
	for(Character x : shuffledList){
	    System.out.print(x);
	}	
	System.out.println();
    }

    private void encrypt(){
        
        StringBuilder sb = new StringBuilder();
	for(int i=0; i<letters.length; i++){
	    for(int j=0; j<list.size(); j++){
		if(letters[i] == list.get(j)){
		    letters[i] = shuffledList.get(j);
		    break;
		}	  
            }
	}
        
        System.out.print("\nEncrypted : ");
	for(char x : letters){
	    sb.append(x);
            System.out.print(x);
	}
	JOptionPane.showMessageDialog(frame, "Encrypted : " + sb.toString());        
    }
    private void decrypt(){
        
        StringBuilder sb = new StringBuilder();
	for(int i=0; i<letters.length; i++){
	    for(int j=0; j<shuffledList.size(); j++){
		if(letters[i] == shuffledList.get(j)){
		    letters[i] = list.get(j);
		    break;
		}	  
            }
	}
        
        System.out.print("\nDecrypted : ");
	for(char x : letters){
	    sb.append(x);
            System.out.print(x);
	}
        JOptionPane.showMessageDialog(frame, "Decrypted : " + sb.toString());        
    }         
}
    
// Created by Abdul Latif