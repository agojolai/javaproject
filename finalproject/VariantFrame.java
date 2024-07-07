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
public class VariantFrame extends JFrame {

	private JPanel contentPane;
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color BtnCOLOR =new Color(53, 73, 95);
	static MainMenuFrame mf = new MainMenuFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VariantFrame frame = new VariantFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VariantFrame() {
		setTitle("Variant");
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
		
		JLabel varCode = new JLabel("Variant Code : ");
		varCode.setFont(stFont);
		varCode.setBounds(7, 14, 100, 20);
		panel1.add(varCode);
		
		JTextField varCodeTxtField = new JTextField();
		varCodeTxtField.setFont(stFont);
		varCodeTxtField.setBounds(117, 14, 189, 22);
		panel1.add(varCodeTxtField);
		varCodeTxtField.setColumns(10);
		
		JLabel varName = new JLabel("Variant Name :");
		varName.setFont(stFont);
		varName.setBounds(7, 44, 100, 20);
		panel1.add(varName);
		
		JTextField varNameTxtField = new JTextField();
		varNameTxtField.setFont(stFont);
		varNameTxtField.setBounds(117, 44, 189, 22);
		panel1.add(varNameTxtField);
		varNameTxtField.setColumns(10);
		
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
          		String variantCode = varCodeTxtField.getText();
				String variantName = varNameTxtField.getText();
			
				// Check if either field is empty
	            if (variantCode.isEmpty() || variantName.isEmpty()) {
	                if (variantCode.isEmpty() && variantName.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else if (variantCode.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a variant code.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Please enter a variant name.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                return;
	            }
	            
	            // Check if variant code already exists
	            if (variantCodeExists(variantCode, variantName)) {
	                JOptionPane.showMessageDialog(null, "Variant code or variant name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
	                varCodeTxtField.setText("");
	                varNameTxtField.setText("");
	                return;
	            }
				
				String record = String.format("%s,%s%n", variantCode, variantName.toString());

				// Append data to File
				try (FileWriter writer = new FileWriter("Variant.txt", true)) {
					writer.write(record);
					writer.flush();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

				varCodeTxtField.setText("");
	            varNameTxtField.setText("");

				JOptionPane.showMessageDialog(null, "Variant information successfully saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
				
				MainMenuFrame.prodf.refreshVariant();
        }); // end of btnAdd
	}
	
	// Method to check if variant code already exists in Category.txt
    private boolean variantCodeExists(String variantCode, String variantName) {
       try (Scanner scanner = new Scanner(new File("Variant.txt"))) {
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               String[] parts = line.split(",");
               if (parts.length >= 2 && parts[0].trim().equals(variantCode) || parts[1].trim().equals(variantName)) {
                   return true; // 
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return false; // variant code not found
   }
}