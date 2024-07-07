package finalproject;
//backup
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class OrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField pricetxtField;
	private JTextField availquanttxtField;
	private JTextField cashTendertxtField;
	private JTextField customertxtField;
	private JTextField orderquanttxtField;
	private JTextField changetxtField;
	private JTextField totAmounttxtField;
	
    private JComboBox<String> prodNamecodeBox;
    
    // Flag to track if an order has been added
    private boolean isOrderAdded = false;

    // Private List for Product Name combo box
    private List<String> productNames = new ArrayList<>();
    private List<String> quantities = new ArrayList<>();
    private List<String> prices = new ArrayList<>();
    static MainMenuFrame mf = new MainMenuFrame();
    
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color btnCOLOR =new Color(53, 73, 95);
	private BevelBorder stBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame  frame = new OrderFrame ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderFrame () {
		setTitle("Order");
		setResizable(false);
		setBounds(15, 104, 717, 393);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        // Loading Area
        loadProduct();
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(UIManager.getBorder("Button.border"));
		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel1.setBounds(10, 10, 681, 338);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel cusName = new JLabel("Customer Name : ");
		cusName.setFont(stFont);
		cusName.setBounds(7, 14, 113, 20);
		panel1.add(cusName);
		
		customertxtField = new JTextField();
		customertxtField.setFont(stFont);
		customertxtField.setColumns(10);
		customertxtField.setBounds(130, 14, 478, 22);
		panel1.add(customertxtField);
	
		
		JLabel prodName = new JLabel("Product Name :");
		prodName.setFont(stFont);
		prodName.setBounds(7, 44, 100, 20);
		panel1.add(prodName);
		
        prodNamecodeBox = new JComboBox<>(productNames.toArray(new String[0]));
		prodNamecodeBox.setFont(stFont);
		prodNamecodeBox.setBounds(127, 44, 189, 22);
		panel1.add(prodNamecodeBox);
		
		JLabel orderquant = new JLabel("Quantity Order :");
		orderquant.setFont(stFont);
		orderquant.setBounds(7, 74, 113, 20);
		panel1.add(orderquant);
		
		orderquanttxtField = new JTextField();
		orderquanttxtField.setFont(stFont);
		orderquanttxtField.setColumns(10);
		orderquanttxtField.setBounds(127, 74, 189, 22);
		panel1.add(orderquanttxtField);
		
		JLabel availquant = new JLabel("Available Quantity : ");
		availquant.setFont(stFont);
		availquant.setBounds(351, 44, 120, 20);
		panel1.add(availquant);
		
		availquanttxtField = new JTextField();
		availquanttxtField.setFont(stFont);
		availquanttxtField.setEnabled(false);
		availquanttxtField.setColumns(10);
		availquanttxtField.setBounds(481, 44, 153, 22);
		panel1.add(availquanttxtField);
		
		JLabel priceLabel = new JLabel("Price :");
		priceLabel.setFont(stFont);
		priceLabel.setBounds(351, 74, 58, 20);
		panel1.add(priceLabel);
		
		pricetxtField = new JTextField();
		pricetxtField.setFont(stFont);
		pricetxtField.setEnabled(false);
		pricetxtField.setColumns(10);
		pricetxtField.setBounds(419, 74, 189, 22);
		panel1.add(pricetxtField);
		
		JLabel totAmount = new JLabel("Total Amount :");
		totAmount.setFont(stFont);
		totAmount.setBounds(7, 104, 86, 20);
		panel1.add(totAmount);
		
		totAmounttxtField = new JTextField();
		totAmounttxtField.setEnabled(false);
		totAmounttxtField.setFont(stFont);
		totAmounttxtField.setColumns(10);
		totAmounttxtField.setBounds(127, 104, 100, 22);
		panel1.add(totAmounttxtField);
		
		JLabel cashTender = new JLabel("Cash Tender :");
		cashTender.setFont(stFont);
		cashTender.setBounds(237, 104, 86, 20);
		panel1.add(cashTender);
		
		cashTendertxtField = new JTextField();
		cashTendertxtField.setFont(stFont);
		cashTendertxtField.setColumns(10);
		cashTendertxtField.setBounds(333, 104, 100, 22);
		panel1.add(cashTendertxtField);
		
		JButton btnChange = new JButton("Change ");
		btnChange.setFont(stFont);
		btnChange.setBounds(445, 104, 78, 20);
		panel1.add(btnChange);
		
		changetxtField = new JTextField();
		changetxtField.setEnabled(false);
		changetxtField.setFont(stFont);
		changetxtField.setColumns(10);
		changetxtField.setBounds(533, 104, 100, 22);
		panel1.add(changetxtField);
		
		JTextArea orderTxtArea = new JTextArea();
		orderTxtArea.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		orderTxtArea.setEnabled(false);
		orderTxtArea.setBorder(stBorder);
	    JScrollPane sPane = new JScrollPane(orderTxtArea,
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , 
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	    sPane.setBounds(10, 144, 661, 146);
	    panel1.add(sPane);

	
		JButton btnNew = new JButton("New");
		btnNew.setBackground(btnCOLOR);
		btnNew.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNew.setBounds(241, 308, 100, 20);
		panel1.add(btnNew);
        btnNew.setMnemonic(KeyEvent.VK_N);
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.setBackground(btnCOLOR);
		btnCompute.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCompute.setBounds(351, 308, 100, 20);
		panel1.add(btnCompute);
        btnCompute.setMnemonic(KeyEvent.VK_C);
        
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(btnCOLOR);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.setBounds(461, 308, 100, 20);
		panel1.add(btnAdd);
        btnAdd.setMnemonic(KeyEvent.VK_A);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(btnCOLOR);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBack.setBounds(571, 308, 100, 20);
		panel1.add(btnBack);
        btnBack.setMnemonic(KeyEvent.VK_B);
        
		setLocationRelativeTo(null);
	
		btnBack.addActionListener(e -> 
        {

                boolean noInputs = customertxtField.getText().isEmpty() &&
                                    availquanttxtField.getText().isEmpty() &&
                                    pricetxtField.getText().isEmpty() &&
                                    totAmounttxtField.getText().isEmpty() &&
                                    orderTxtArea.getText().isEmpty();
            
                if (noInputs) 
                {
                    dispose();
                    mf.setVisible(true);
                } else if (totAmounttxtField.getText().isEmpty() || cashTendertxtField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please complete your order before exiting.\nYou can go back after you are done.",
                    		"Information", JOptionPane.INFORMATION_MESSAGE);
                } else 
                {
                    dispose();
                    mf.setVisible(true);
                }
        });

        prodNamecodeBox.setSelectedIndex(-1); // Set selected index to -1 initially

        // ActionListener for prodNamecodeBox
        prodNamecodeBox.addActionListener(e -> {
            int selectedIndex = prodNamecodeBox.getSelectedIndex();
            if (selectedIndex != -1) {
                availquanttxtField.setText(quantities.get(selectedIndex));
                pricetxtField.setText(String.format("%.2f", Double.parseDouble(prices.get(selectedIndex))));
                orderquanttxtField.setText(""); // Clear order quantity text field
                cashTendertxtField.setText(""); // Clear cash tender text field
                totAmounttxtField.setText("");
                changetxtField.setText(""); // Clear change text field
                btnCompute.setEnabled(false);
            } else {
                availquanttxtField.setText("");
                pricetxtField.setText("");
                orderquanttxtField.setText("");
                cashTendertxtField.setText("");
                changetxtField.setText("");
                totAmounttxtField.setText(""); // Clear total amount text field
            }
        });
        
        orderquanttxtField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                clearTotalAmount();
                btnCompute.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clearTotalAmount();
                btnCompute.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                clearTotalAmount();
                btnCompute.setEnabled(false);
            }

            private void clearTotalAmount() {
                totAmounttxtField.setText(""); // Clear total amount text field
            }
        });

        // btnChange FUNCTIONALITY
        btnChange.addActionListener(e -> {
            try {
                String totalText = totAmounttxtField.getText();
                String cashText = cashTendertxtField.getText();

                if (!totalText.isEmpty() && !cashText.isEmpty()) {
                    Double total = Double.parseDouble(totalText);
                    Double cash = Double.parseDouble(cashText);

                    if (cash >= total) {
                        Double change = cash - total;
                        String changeText = String.format("%.2f", change);
                        changetxtField.setText(changeText);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cash tendered is less than total amount.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter values for total amount and cash tender.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // btnNew FUNCTIONALITY
        btnNew.addActionListener(e -> {
        	if (!totAmounttxtField.getText().isEmpty() && !cashTendertxtField.getText().isEmpty()) {
                customertxtField.setText("");
                orderquanttxtField.setText("");
                availquanttxtField.setText("");
                pricetxtField.setText("");
                totAmounttxtField.setText("");
                cashTendertxtField.setText("");
                changetxtField.setText("");
                customertxtField.setEditable(true);
                prodNamecodeBox.setSelectedIndex(-1);
                prodNamecodeBox.setEnabled(true);
                customertxtField.setEnabled(true);
                orderTxtArea.setText("");
                isOrderAdded = false; // Reset flag for new customer
                JOptionPane.showMessageDialog(null, "Hello, new customer!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please compute the order and enter the cash tender before starting a new order.",
                		"Oder completion", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // btnCompute FUNCTIONALITY
        btnCompute.addActionListener(e -> {
            boolean required = orderTxtArea.getText().isEmpty();
            String customText = customertxtField.getText();
            String orderline = orderquanttxtField.getText();
            int selectedIndex = prodNamecodeBox.getSelectedIndex();

            if (isOrderAdded) { // Check if an order has been added
                if (!required) {
                    if (!customText.isEmpty() && !orderline.isEmpty() && selectedIndex != -1) {
                        try {
                            String prodname = productNames.get(selectedIndex);
                            double price = Double.parseDouble(pricetxtField.getText());
                            int order = Integer.parseInt(orderquanttxtField.getText());
                            
                            if (order <= 0) {
                                JOptionPane.showMessageDialog(null, "Order quantity must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            
                            double total = price * order;
                            String amount = String.format("%.2f", total);
                            totAmounttxtField.setText(amount);

                            String record = prodname + ", " + order + ", " + amount + System.lineSeparator();

                            try (FileWriter writer = new FileWriter("Sales.txt", true)) {
                                writer.write(record);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter the customer name or the order quantity first.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please add an order before computing.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please add an order before computing.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            prodNamecodeBox.setEnabled(true);
            btnCompute.setEnabled(false);
        });

        // btnAdd FUNCTIONALITY
        btnAdd.addActionListener(e -> {
        	String customName = customertxtField.getText();
            if (!customName.matches("[a-zA-Z. ]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid customer name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String orderline = orderquanttxtField.getText();
            int selectedIndex = prodNamecodeBox.getSelectedIndex();

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String productName = productNames.get(selectedIndex);
            String availQuantStr = availquanttxtField.getText();
            String prodPrice = pricetxtField.getText();

            if (!orderline.isEmpty() && !orderline.equals("0")) {
                try {
                    int orderQuantity = Integer.parseInt(orderline);
                    int availableQuantity = Integer.parseInt(availQuantStr);

                    if (orderQuantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Order quantity must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (orderQuantity <= availableQuantity) {

                        boolean isNewCustomer = orderTxtArea.getText().isEmpty();

                        StringBuilder orderHeader = new StringBuilder();
                    	
                        orderHeader.append("Customer Name\tProduct Name\t\tPrice\tQuantity Order\t\n");
                        for (int i = 0; i < 180; i++) {
                        	orderHeader.append("-");
                        }
                        orderHeader.append("\n\n");
                        
                        StringBuilder customDetails = new StringBuilder();
                       // customDetails.append(customName + "\t\t" + productName + "\t\t" + prodPrice + "\t"  + orderQuantity + "\n");
                        customDetails.append(String.format("%s\t\t%-30s\t%s\t%s%n", customName, productName, prodPrice, orderQuantity));
                        
                        StringBuilder orderDetails = new StringBuilder();
                        //orderDetails.append("\t\t" + productName + "\t\t" + prodPrice + "\t"  + orderQuantity + "\n");
                        orderDetails.append(String.format("\t\t%-30s\t%s\t%s%n", productName, prodPrice, orderQuantity));
           
                        if (isNewCustomer) {
                            
                        	orderTxtArea.append(orderHeader.toString());
                        	orderTxtArea.append(customDetails.toString());
                           
                        } else {
                        	orderTxtArea.append(orderDetails.toString());
                        }

                        int newAvailableQuantity = availableQuantity - orderQuantity;
                        availquanttxtField.setText(String.valueOf(newAvailableQuantity));
                        quantities.set(selectedIndex, String.valueOf(newAvailableQuantity)); // Update the quantities list

                        updateDeliveryFile(productName, newAvailableQuantity);

                        customertxtField.setEnabled(false);
                        prodNamecodeBox.setEnabled(true);

                        isOrderAdded = true;

                        JOptionPane.showMessageDialog(null, "Order added successfully.\nYou can now proceed to computing.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Order quantity is greater than available quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid order quantity (not zero).", "Error", JOptionPane.ERROR_MESSAGE);
            }
            btnCompute.setEnabled(true);
        });
    }
    
	private void updateDeliveryFile(String productName, int newQuantity) {
	    String deliveryFilePath = "Delivery.txt"; // Path to your delivery file
	    String tempFilePath = "temp.txt"; // Temporary file path

	    // Create a list to hold updated lines
	    List<String> updatedLines = new ArrayList<>();

	    try (Scanner scanner = new Scanner(new File(deliveryFilePath))) {
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(",");

	            // Check if the line contains at least 7 parts and matches the product name
	            if (parts.length >= 7 && parts[3].trim().equals(productName)) {
	                parts[5] = String.valueOf(newQuantity); // Update the quantity
	                line = String.join(",", parts); // Reconstruct the line with updated quantity
	            }

	            updatedLines.add(line); // Add the line to the updated lines list
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error reading Delivery.txt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return; // Exit method if an error occurs during file reading
	    }

	    // Write updated lines to temp file
	    try (FileWriter writer = new FileWriter(tempFilePath)) {
	        for (String updatedLine : updatedLines) {
	            writer.write(updatedLine + System.lineSeparator());
	        }
	        System.out.println("Temporary file (temp.txt) updated successfully.");
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error writing to temp.txt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return; // Exit method if an error occurs during file writing
	    }

	    // Rename temp file to Delivery.txt
	    File originalFile = new File(deliveryFilePath);
	    File tempFile = new File(tempFilePath);
	    if (tempFile.exists()) {
	        try {
	            Files.move(tempFile.toPath(), originalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("Delivery.txt updated successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Error updating Delivery.txt. Could not rename temp file back.");

	            // Try to delete the temporary file if renaming fails
	            if (!tempFile.delete()) {
	                System.err.println("Failed to delete temp file.");
	            }
	        }
	    } else {
	        System.err.println("Error: Temporary file temp.txt does not exist.");
	    }
	}

    private void loadProduct() {
    	File deliveryFile = new File("Delivery.txt");
        if (!deliveryFile.exists()) {
            try {
                deliveryFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        productNames.clear();
        quantities.clear();
        prices.clear();
    	
        try (Scanner scanner = new Scanner(new File("Delivery.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String productName = parts[3].trim();
                    String Quantity = parts[5].trim();
                    String price = String.format("%.2f", Double.parseDouble(parts[6].trim())); // Format price to two decimal places

                    int index = productNames.indexOf(productName);
                    if (index != -1) {
                        int currentQuantity = Integer.parseInt(quantities.get(index));
                        int newQuantity = currentQuantity + Integer.parseInt(Quantity);
                        quantities.set(index, String.valueOf(newQuantity));
                    } else {
                        productNames.add(productName);
                        quantities.add(Quantity);
                        prices.add(price);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshProduct() {
    	quantities.clear();
        prices.clear();
        productNames.clear();
        loadProduct();
        prodNamecodeBox.removeAllItems();
        for (String pCodes : productNames) {
        	prodNamecodeBox.addItem(pCodes);
        }
        prodNamecodeBox.setSelectedIndex(-1);
    }
}