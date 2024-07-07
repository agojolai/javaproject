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
public class PackagingFrame extends JFrame {

	private JPanel contentPane;
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color BtnCOLOR =new Color(53, 73, 95);
	static MainMenuFrame mf = new MainMenuFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackagingFrame frame = new PackagingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PackagingFrame() {
		setTitle("Packaging");
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
		
		JLabel packCode = new JLabel("Package Code : ");
		packCode.setFont(stFont);
		packCode.setBounds(7, 14, 100, 20);
		panel1.add(packCode);
		
		JTextField packCodeTxtField = new JTextField();
		packCodeTxtField.setFont(stFont);
		packCodeTxtField.setBounds(117, 14, 189, 22);
		panel1.add(packCodeTxtField);
		packCodeTxtField.setColumns(10);
		
		JLabel packName = new JLabel("Package Name :");
		packName.setFont(stFont);
		packName.setBounds(7, 44, 100, 20);
		panel1.add(packName);
		
		JTextField packNameTxtField = new JTextField();
		packNameTxtField.setFont(stFont);
		packNameTxtField.setBounds(117, 44, 189, 22);
		panel1.add(packNameTxtField);
		packNameTxtField.setColumns(10);
		
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
					String packagingCode = packCodeTxtField.getText();
					String packagingName = packNameTxtField.getText();
				
					// Check if either field is empty
		            if (packagingCode.isEmpty() || packagingName.isEmpty()) {
		                if (packagingCode.isEmpty() && packagingName.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
		                } else if (packagingCode.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Please enter a packaging code.", "Error", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Please enter a packaging name.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		                return;
		            }
		            
		            // Check if package code already exists
		            if (packageCodeExists(packagingCode, packagingName)) {
		                JOptionPane.showMessageDialog(null, "Package code or package name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
		                packCodeTxtField.setText("");
		                packNameTxtField.setText("");
		                return;
		            }
					
					String record = String.format("%s,%s%n", packagingCode, packagingName.toString());

					// Append data to File
					try (FileWriter writer = new FileWriter("Package.txt", true)) {
						writer.write(record);
						writer.flush();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}

					packCodeTxtField.setText("");
		            packNameTxtField.setText("");

					JOptionPane.showMessageDialog(null, "Packaging information successfully saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
					
					MainMenuFrame.prodf.refreshPackage();
					});
		}

		// Method to check if package code already exists in Category.txt
	    private boolean packageCodeExists(String packageCode, String packageName) {
	       try (Scanner scanner = new Scanner(new File("Package.txt"))) {
	           while (scanner.hasNextLine()) {
	               String line = scanner.nextLine();
	               String[] parts = line.split(",");
	               if (parts.length >= 2 && parts[0].trim().equals(packageCode) || parts[1].trim().equals(packageName)) {
	                   return true; // 
	               }
	           }
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return false; // package code not found
	   }
	}	