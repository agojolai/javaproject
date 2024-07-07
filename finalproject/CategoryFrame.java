package finalproject;
//backup
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

@SuppressWarnings("serial")
public class CategoryFrame extends JFrame {

	private JPanel contentPane;
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color BtnCOLOR =new Color(53, 73, 95);
	static MainMenuFrame mf = new MainMenuFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryFrame frame = new CategoryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryFrame() {
		setTitle("Category");
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
		
		JLabel catCode = new JLabel("Category Code : ");
		catCode.setFont(stFont);
		catCode.setBounds(7, 14, 100, 20);
		panel1.add(catCode);
		
		JTextField catCodeTxtField = new JTextField();
		catCodeTxtField.setFont(stFont);
		catCodeTxtField.setBounds(117, 14, 189, 22);
		panel1.add(catCodeTxtField);
		catCodeTxtField.setColumns(10);
		
		JLabel catName = new JLabel("Category Name :");
		catName.setFont(stFont);
		catName.setBounds(7, 44, 100, 20);
		panel1.add(catName);
		
		JTextField catNameTxtField = new JTextField();
		catNameTxtField.setFont(stFont);
		catNameTxtField.setBounds(117, 44, 189, 22);
		panel1.add(catNameTxtField);
		catNameTxtField.setColumns(10);
		
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
				String categoryCode = catCodeTxtField.getText();
				String categoryName = catNameTxtField.getText();
			
				/// Check if either field is empty
	            if (categoryCode.isEmpty() || categoryName.isEmpty()) {
	                if (categoryCode.isEmpty() && categoryName.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else if (categoryCode.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Please enter a category code.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Please enter a category name.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                return;
	            }
	            
	            // Check if category code already exists
	            if (categoryCodeExists(categoryCode, categoryName)) {
	                JOptionPane.showMessageDialog(null, "Category code or category name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
	                catCodeTxtField.setText("");
	                catNameTxtField.setText("");
	                return;
	            }
				
				String record = String.format("%s,%s%n", categoryCode, categoryName.toString());

				// Append data to File
				try (FileWriter writer = new FileWriter("Category.txt", true)) {
					writer.write(record);
					writer.flush();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

				catCodeTxtField.setText("");
	            catNameTxtField.setText("");

				JOptionPane.showMessageDialog(null, "Category information successfully saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
				
				MainMenuFrame.prodf.refreshCategory();
    		});
	}
	
	 // Method to check if category code already exists in Category.txt
     private boolean categoryCodeExists(String categoryCode, String categoryName) {
        try (Scanner scanner = new Scanner(new File("Category.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(categoryCode) || parts[1].trim().equals(categoryName)) {
                    return true; // Category code found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Category code not found
    }
}