package finalproject;
//backup
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

@SuppressWarnings("serial")
public class SupplierFrame extends JFrame {

	private JPanel contentPane;
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color BtnCOLOR =new Color(53, 73, 95);
	static MainMenuFrame mf = new MainMenuFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierFrame frame = new SupplierFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SupplierFrame() {
		setTitle("Supplier Information");
		setResizable(false);
		setBounds(15, 104, 350, 162);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel1.setBounds(10, 10, 320, 109);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel suppCode = new JLabel("Supplier Code : ");
		suppCode.setFont(stFont);
		suppCode.setBounds(7, 14, 100, 20);
		panel1.add(suppCode);
		
		JTextField suppCodeTxtField = new JTextField();
		suppCodeTxtField.setFont(stFont);
		suppCodeTxtField.setBounds(117, 14, 189, 22);
		panel1.add(suppCodeTxtField);
		suppCodeTxtField.setColumns(10);
		
		JLabel suppName = new JLabel("Supplier Name :");
		suppName.setFont(stFont);
		suppName.setBounds(7, 44, 100, 20);
		panel1.add(suppName);
		
		JTextField suppNameTxtField = new JTextField();
		suppNameTxtField.setFont(stFont);
		suppNameTxtField.setBounds(117, 44, 189, 22);
		panel1.add(suppNameTxtField);
		suppNameTxtField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(BtnCOLOR);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.setBounds(96, 76, 100, 20);
		panel1.add(btnAdd);
		btnAdd.setMnemonic(KeyEvent.VK_A);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(BtnCOLOR);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBack.setBounds(206, 76, 100, 20);
		panel1.add(btnBack);
		btnBack.setMnemonic(KeyEvent.VK_B);
		
		setLocationRelativeTo(null);
		
		btnBack.addActionListener(e -> {
			dispose();
			mf.setVisible(true);
			});
		
	    //btnAdd FUNCTIONALITY
	      btnAdd.addActionListener(e -> {
					String supplierCode = suppCodeTxtField.getText();
					String supplierName = suppNameTxtField.getText();
				
		            if (supplierCode.isEmpty() || supplierName.isEmpty()) {
		                if (supplierCode.isEmpty() && supplierName.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
		                } else if (supplierCode.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please enter a supplier code.", "Error", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Please enter a supplier name.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		                return;
		            }
		            
		            // Check if supplier code already exists
		            if (supplierCodeExists(supplierCode, supplierName)) {
		                JOptionPane.showMessageDialog(null, "Supplier code or supplier name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
		                suppCodeTxtField.setText("");
		                suppNameTxtField.setText("");
		                return;
		            }
					
					String record = String.format("%s,%s%n", supplierCode, supplierName.toString());

					// Append data to File
					try (FileWriter writer = new FileWriter("Supplier.txt", true)) {
						writer.write(record);
						writer.flush();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}

					suppCodeTxtField.setText("");
		            suppNameTxtField.setText("");

		            JOptionPane.showMessageDialog(null, "Supplier information successfully saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
		           
		            MainMenuFrame.df.refreshSupplier();
	      }); // end of btnAdd
		}

		// Method to check if supplier code already exists in Category.txt
	    private boolean supplierCodeExists(String supplierCode, String supplierName){
	       try (Scanner scanner = new Scanner(new File("Supplier.txt"))) {
	           while (scanner.hasNextLine()) {
	               String line = scanner.nextLine();
	               String[] parts = line.split(",");
	               if (parts.length >= 2 && parts[0].trim().equals(supplierCode) || parts[1].trim().equals(supplierName)) {
	                   return true; // 
	               }
	           }
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return false; // supplier code not found
	   }
	}