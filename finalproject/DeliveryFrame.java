package finalproject;
//backup
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class DeliveryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField prodNametxtField;
	private JTextField suppNametxtField;
	private JTextField quantitytxtField;
	private JTextArea prodDesctxtArea;
    private String prices;
    
	private JComboBox<String> suppCodeBox; // Changed to JComboBox
	private JComboBox<String> prodCodeBox; // Changed to JComboBox
	
	private List<String> supplierCodes = new ArrayList<>();
    private List<String> supplierNames = new ArrayList<>();
    
    private List<String> productCodes = new ArrayList<>();
    private List<String> productNames = new ArrayList<>();
    
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color btnCOLOR =new Color(53, 73, 95);
	private BevelBorder stBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	
	static MainMenuFrame mf = new MainMenuFrame();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeliveryFrame  frame = new DeliveryFrame ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeliveryFrame () {
		setTitle("Delivery");
		setResizable(false);
		setBounds(15, 104, 800, 292);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel1.setBounds(10, 10, 766, 239);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel suppCode = new JLabel("Supplier Code : ");
		suppCode.setFont(stFont);
		suppCode.setBounds(7, 14, 100, 20);
		panel1.add(suppCode);
		
		suppCodeBox = new JComboBox();
		suppCodeBox.setFont(stFont);
		suppCodeBox.setBounds(145, 14, 189, 22);
		panel1.add(suppCodeBox);
		
		JLabel prodCode = new JLabel("Product Code :");
		prodCode.setFont(stFont);
		prodCode.setBounds(7, 44, 100, 20);
		panel1.add(prodCode);
		
		prodCodeBox = new JComboBox();
		prodCodeBox.setFont(stFont);
		prodCodeBox.setBounds(145, 44, 189, 22);
		panel1.add(prodCodeBox);
		
		JLabel prodDesc = new JLabel("Product Descriptions :");
		prodDesc.setFont(stFont);
		prodDesc.setBounds(7, 74, 127, 20);
		panel1.add(prodDesc);
		
		prodDesctxtArea = new JTextArea();
		prodDesctxtArea.setFont(stFont);
		prodDesctxtArea.setEnabled(false);
        JScrollPane sPane = new JScrollPane(prodDesctxtArea, 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , 
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        sPane.setBounds(145, 74, 289, 40);
		panel1.add(sPane);
		
		JLabel suppName = new JLabel("Supplier Name : ");
		suppName.setFont(stFont);
		suppName.setBounds(447, 14, 100, 20);
		panel1.add(suppName);
		
		suppNametxtField = new JTextField();
		suppNametxtField.setFont(stFont);
		suppNametxtField.setEnabled(false);
		suppNametxtField.setColumns(10);
		suppNametxtField.setBounds(557, 14, 189, 22);
		panel1.add(suppNametxtField);
		
		JLabel prodName = new JLabel("Product Name :");
		prodName.setFont(stFont);
		prodName.setBounds(447, 44, 100, 20);
		panel1.add(prodName);
		
		prodNametxtField = new JTextField();
		prodNametxtField.setFont(stFont);
		prodNametxtField.setEnabled(false);
		prodNametxtField.setColumns(10);
		prodNametxtField.setBounds(557, 44, 189, 22);
		panel1.add(prodNametxtField);
		
		JLabel quantityLbl = new JLabel("Quantity Delivered :");
		quantityLbl.setFont(stFont);
		quantityLbl.setBounds(447, 74, 120, 20);
		panel1.add(quantityLbl);
		
		quantitytxtField = new JTextField();
		quantitytxtField.setFont(stFont);
		quantitytxtField.setColumns(10);
		quantitytxtField.setBounds(577, 74, 52, 22);
		panel1.add(quantitytxtField);
		
		JTextArea deliveryDesctxtArea = new JTextArea();
		deliveryDesctxtArea.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		deliveryDesctxtArea.setEnabled(false);
		deliveryDesctxtArea.setBorder(stBorder);
	       JScrollPane sPane1 = new JScrollPane(deliveryDesctxtArea, 
	        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , 
	        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		sPane1.setBounds(10, 120, 745, 85);
		panel1.add(sPane1);
	
		JButton btnNew = new JButton("New");
		btnNew.setBackground(btnCOLOR);
		btnNew.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNew.setBounds(436, 210, 100, 20);
		panel1.add(btnNew);
        btnNew.setMnemonic(KeyEvent.VK_N);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(btnCOLOR);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.setBounds(546, 210, 100, 20);
		panel1.add(btnAdd);
        btnAdd.setMnemonic(KeyEvent.VK_A);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(btnCOLOR);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBack.setBounds(656, 210, 100, 20);
		panel1.add(btnBack);
        btnBack.setMnemonic(KeyEvent.VK_B);
        
		setLocationRelativeTo(null);
		
		btnBack.addActionListener(e -> {
			dispose();
			mf.setVisible(true);
			});
		
		//packcodeBox FUNCTIONALITY
		loadSupplier(); // Load suppliers from Supplier.txt
        loadProduct(); // Load products from Product.txt

        suppCodeBox.setSelectedIndex(-1);
        prodCodeBox.setSelectedIndex(-1);

        suppCodeBox.addActionListener(e -> 
        {
        	int selectedIndex = suppCodeBox.getSelectedIndex();
        	if (selectedIndex != -1) 
        	{
        		suppNametxtField.setText(supplierNames.get(selectedIndex));
        	}
        	else 
        	{
        		suppNametxtField.setText("");
        	}
        });

        prodCodeBox.addActionListener(e -> 
        {
        	int selectedIndex = prodCodeBox.getSelectedIndex();
        	if (selectedIndex != -1) 
        	{
        		updateProductDetails(selectedIndex);
        	}
        	else 
        	{
        		clearProductDetails();
        	}   
        });

        // Action listener for New button
        btnNew.addActionListener(e -> {
        // Enable the supplier combo box
        suppCodeBox.setEnabled(true);
        prodCodeBox.setEnabled(true); 
        suppCodeBox.setSelectedIndex(-1);
        prodCodeBox.setSelectedIndex(-1);
    
        // Clear all fields
        prodNametxtField.setText("");
        suppNametxtField.setText("");
        quantitytxtField.setText("");
        prodDesctxtArea.setText("");
        deliveryDesctxtArea.setText("");

        JOptionPane.showMessageDialog(null, "There are new deliveries available.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        });

        StringBuilder deliveryDetails = new StringBuilder();
        deliveryDetails.append("Product Code\tProduct Name\t\tPackage Description\tVariant Description\tQuantity\t\n");
        for (int i = 0; i < 200; i++) {
        	deliveryDetails.append("-");
        }
        deliveryDetails.append("\n");

        // Action Listener for the add button
        btnAdd.addActionListener(e -> {
        	String supplierCode = (String) suppCodeBox.getSelectedItem();
        	String supplierName = (String) suppNametxtField.getText();
        	String productCode = (String) prodCodeBox.getSelectedItem();
        	String productName = prodNametxtField.getText();
        	String productDescription = prodDesctxtArea.getText(); // Retrieve product description
        	String quantity = quantitytxtField.getText();
        	String price = prices;

        	// Check if any field is empty or quantity is not provided
        	if (supplierCode == null || supplierCode.isEmpty() || supplierName.isEmpty() ||
        			productCode == null || productCode.isEmpty() ||
        			productName.isEmpty() || quantity.isEmpty()) {
        		JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
        		return;
        	}

        	// Validate quantity (assuming it should be a number greater than 0)
        	try {
        		int qty = Integer.parseInt(quantity);
        		if (qty <= 0) {
        			JOptionPane.showMessageDialog(null, "Invalid quantity count.", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        	} catch (NumberFormatException ex) {
        		JOptionPane.showMessageDialog(null, "Quantity should be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        		return;
        	}

        	// Check if the product already exists in Delivery.txt
            boolean productExists = false;
            List<String> lines = new ArrayList<>();
            File deliveryFile = new File("Delivery.txt");
            if (deliveryFile.exists()) {
                try (Scanner scanner = new Scanner(deliveryFile)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains(productName)) {
                            // Product found, update the quantity
                            String[] parts = line.split(",");
                            int existingQty = Integer.parseInt(parts[5].trim());
                            int newQty = existingQty + Integer.parseInt(quantity);
                            line = String.format("%s,%s,%s,%s,%s,%d,%s", parts[0], parts[1], parts[2], parts[3], parts[4], newQty, parts[6]);
                            productExists = true;
                        }
                        lines.add(line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error reading Delivery.txt", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Prepare the record to write to Delivery.txt
            String record = String.format("%s,%s,%s,%s,%s,%s,%s%n",
                    supplierCode, supplierName, productCode, productName, productDescription, quantity, price);

            // If product doesn't exist, append the new record
            if (!productExists) {
                lines.add(record);
            }
        	
        	// Append product details to deliveryDesctxtArea
        	StringBuilder productDetails = new StringBuilder();

        	productDetails.append(String.format("%-10s\t%-30s\t%s\t%s\t%n", 
        			productCode, productName, productDescription, quantity));


        	if (deliveryDesctxtArea.getText().isEmpty()) {
        		deliveryDesctxtArea.append(deliveryDetails.toString());
        		deliveryDesctxtArea.append(productDetails.toString());
        	} else {
        		deliveryDesctxtArea.append(productDetails.toString());
        	}
        	
        	// Disable supplier combobox, clear product combobox and product name
            suppCodeBox.setEnabled(false);
            suppNametxtField.setEnabled(false);
            prodCodeBox.setSelectedIndex(-1);
            prodNametxtField.setEnabled(false);
            prodNametxtField.setText("");
            quantitytxtField.setText("");
   
        	// Write the updated records back to Delivery.txt
            try (FileWriter writer = new FileWriter("Delivery.txt")) {
                for (String line : lines) {
                    writer.write(line + System.lineSeparator());
                }
                writer.flush();
                JOptionPane.showMessageDialog(null, "Delivery information successfully saved.", "Save", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error writing to Delivery.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }

        	MainMenuFrame.of.refreshProduct();
        });
	}
	
	// Method to load suppliers from Supplier.txt
	private void loadSupplier() {
		File supplierFile = new File("Supplier.txt");
		if (!supplierFile.exists()) {
			try {
				supplierFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		supplierCodes.clear();
		supplierNames.clear();
		try (Scanner scanner = new Scanner(new File("Supplier.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts.length >= 2) {
					String supplierCode = parts[0].trim();
					String supplierName = parts[1].trim();
					supplierCodes.add(supplierCode);
					supplierNames.add(supplierName);
					suppCodeBox.addItem(supplierCode); // Add supplier code to combo box
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error reading Supplier.txt", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Method to load products from Product.txt
	private void loadProduct() {
		File productFile = new File("Product.txt");
		if (!productFile.exists()) {
			try {
				productFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productCodes.clear();
		productNames.clear();

		try (Scanner scanner = new Scanner(new File("Product.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts.length >= 8) {
					String productCode = parts[6].trim();
					String productName = parts[7].trim();
					productCodes.add(productCode);
					productNames.add(productName);
					prodCodeBox.addItem(productCode); // Add product code to combo box
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error reading Product.txt", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateProductDetails(int selectedIndex) {
		File productFile = new File("Product.txt");
		if (!productFile.exists()) {
			try {
				productFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (Scanner scanner = new Scanner(new File("Product.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts.length >= 8 && parts[6].trim().equals(productCodes.get(selectedIndex))) {
					String productName = parts[7].trim();
					String packageCode = parts[2].trim();
					String packageName = parts[3].trim();
					String variantCode = parts[4].trim();
					String variantName = parts[5].trim();
					prices = parts[8].trim();
            
					prodNametxtField.setText(productName);

					prodDesctxtArea.revalidate();
					prodDesctxtArea.repaint();

					StringBuilder prodDesc = new StringBuilder();
        
					prodDesc.append(String.format("%-6s\t%-10s\t%-6s\t%-10s",
							packageCode, packageName, variantCode, variantName));

					// Set the complete product description in the text area
					prodDesctxtArea.setText(prodDesc.toString());
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error reading Product.txt", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearProductDetails() {
		prodNametxtField.setText("");
		prodDesctxtArea.setText("");
	}

	public void refreshProduct() {
		productCodes.clear();
		productNames.clear();
		loadProduct();
		prodCodeBox.removeAllItems();
		for (String pCodes : productCodes) {
			prodCodeBox.addItem(pCodes);
		}
		prodCodeBox.setSelectedIndex(-1);
	}

	public void refreshSupplier() {
		supplierCodes.clear();
		supplierNames.clear();
		loadSupplier();
		suppCodeBox.removeAllItems();
		for (String sCodes : supplierCodes) {
			suppCodeBox.addItem(sCodes);
		}
		suppCodeBox.setSelectedIndex(-1);
	} 	
}