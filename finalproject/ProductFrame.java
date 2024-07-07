package finalproject;
//backup
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField prodCodeTxtField;
	private JTextField packNameTxtField;
	private JTextField catNameTxtField;
	private JTextField prodNameTxtField;
	private JTextField varNameTxtField;
	private JTextField priceTxtField;
    private JComboBox<String> catCodeBox;
    private JComboBox<String> packCodeBox;
    private JComboBox<String> varCodeBox;

    // list to hold category codes, and names
    private List<String> categoryCodes = new ArrayList<>();
    private List<String> categoryNames = new ArrayList<>();

    // list to hold package codes, and names
    private List<String> packageCodes = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();

    // list to hold variant codes, and names
    private List<String> variantCodes = new ArrayList<>();
    private List<String> variantNames = new ArrayList<>();

	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);
	private Color BtnCOLOR =new Color(53, 73, 95);
	static MainMenuFrame mf = new MainMenuFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame  frame = new ProductFrame ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductFrame () {
		setTitle("Product");
		setResizable(false);
		setBounds(15, 104, 717, 292);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        // Loading the category, package, variant's codes and names
        loadCategory();
        loadPackage();
        loadVariant();
		
		JPanel panel1 = new JPanel();
		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel1.setBounds(10, 10, 681, 239);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
        // Code Sections:
		
		JLabel catCode = new JLabel("Category Code : ");
		catCode.setFont(stFont);
		catCode.setBounds(7, 14, 100, 20);
		panel1.add(catCode);
		
		catCodeBox = new JComboBox<>(categoryCodes.toArray(new String[0]));
		catCodeBox.setFont(stFont);
		catCodeBox.setBounds(117, 14, 189, 22);
		panel1.add(catCodeBox);
		
		JLabel packCode = new JLabel("Package Code :");
		packCode.setFont(stFont);
		packCode.setBounds(7, 44, 100, 20);
		panel1.add(packCode);
		
		packCodeBox = new JComboBox<>(packageCodes.toArray(new String[0]));
		packCodeBox.setFont(stFont);
		packCodeBox.setBounds(117, 44, 189, 22);
		panel1.add(packCodeBox);
			
		JLabel varCode = new JLabel("Variant Code : ");
		varCode.setFont(stFont);
		varCode.setBounds(7, 74, 100, 20);
		panel1.add(varCode);
		
		varCodeBox = new JComboBox<>(variantCodes.toArray(new String[0]));
		varCodeBox.setFont(stFont);
		varCodeBox.setBounds(117, 74, 189, 22);
		panel1.add(varCodeBox);
		
		JLabel prodCode = new JLabel("Product Code :");
		prodCode.setFont(stFont);
		prodCode.setBounds(7, 104, 100, 20);
		panel1.add(prodCode);
		
		prodCodeTxtField = new JTextField();
		prodCodeTxtField.setFont(stFont);
		prodCodeTxtField.setColumns(10);
		prodCodeTxtField.setBounds(117, 104, 189, 22);
		panel1.add(prodCodeTxtField);
		
        // Name Sections:
		
		JLabel catName = new JLabel("Category Name : ");
		catName.setFont(stFont);
		catName.setBounds(351, 14, 100, 20);
		panel1.add(catName);
		
		catNameTxtField = new JTextField();
		catNameTxtField.setFont(stFont);
		catNameTxtField.setEnabled(false);
		catNameTxtField.setColumns(10);
		catNameTxtField.setBounds(461, 14, 189, 22);
		panel1.add(catNameTxtField);
		
		JLabel packName = new JLabel("Package Name :");
		packName.setFont(stFont);
		packName.setBounds(351, 44, 100, 20);
		panel1.add(packName);
		
		packNameTxtField = new JTextField();
		packNameTxtField.setFont(stFont);
		packNameTxtField.setEnabled(false);
		packNameTxtField.setColumns(10);
		packNameTxtField.setBounds(461, 44, 189, 22);
		panel1.add(packNameTxtField);
		
		JLabel varName = new JLabel("Variant Name : ");
		varName.setFont(stFont);
		varName.setBounds(351, 74, 100, 20);
		panel1.add(varName);
		
		varNameTxtField = new JTextField();
		varNameTxtField.setFont(stFont);
		varNameTxtField.setEnabled(false);
		varNameTxtField.setColumns(10);
		varNameTxtField.setBounds(461, 74, 189, 22);
		panel1.add(varNameTxtField);
		
		JLabel prodName = new JLabel("Product Name :");
		prodName.setFont(stFont);
		prodName.setBounds(351, 104, 100, 20);
		panel1.add(prodName);
		
		prodNameTxtField = new JTextField();
		prodNameTxtField.setFont(stFont);
		prodNameTxtField.setColumns(10);
		prodNameTxtField.setBounds(461, 104, 189, 22);
		panel1.add(prodNameTxtField);
		
		JLabel priceLabel = new JLabel("Price :");
		priceLabel.setFont(stFont);
		priceLabel.setBounds(179, 144, 46, 20);
		panel1.add(priceLabel);
		
		priceTxtField = new JTextField();
		priceTxtField.setFont(stFont);
		priceTxtField.setColumns(10);
		priceTxtField.setBounds(235, 144, 189, 22);
		panel1.add(priceTxtField);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(BtnCOLOR);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.setBounds(440, 194, 100, 20);
		panel1.add(btnAdd);
		btnAdd.setMnemonic(KeyEvent.VK_A);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(BtnCOLOR);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBack.setBounds(550, 194, 100, 20);
		panel1.add(btnBack);
		btnBack.setMnemonic(KeyEvent.VK_B);
		
		setLocationRelativeTo(null);
		
		btnBack.addActionListener(e -> {
			dispose();
			mf.setVisible(true);
			});
		
	    //catcodeBox FUNCTIONALITY
		catCodeBox.addActionListener(e -> {
		    int selectedIndex = catCodeBox.getSelectedIndex();
		    if (selectedIndex >= 0 && selectedIndex < categoryCodes.size()) {
		        catNameTxtField.setText(categoryNames.get(selectedIndex));
		    } else {
		        catNameTxtField.setText("");
		    }
		});
		 
	    //packcodeBox FUNCTIONALITY
		packCodeBox.addActionListener(e -> {
		    int selectedIndex = packCodeBox.getSelectedIndex();
		    if (selectedIndex >= 0 && selectedIndex < packageCodes.size()) {
		        packNameTxtField.setText(packageNames.get(selectedIndex));
		    } else {
		        packNameTxtField.setText("");
		    }
		});
		
	    //varcodeBox FUNCTIONALITY
		varCodeBox.addActionListener(e -> {
		    int selectedIndex = varCodeBox.getSelectedIndex();
		    if (selectedIndex >= 0 && selectedIndex < variantCodes.size()) {
		        varNameTxtField.setText(variantNames.get(selectedIndex));
		    } else {
		        varNameTxtField.setText("");
		    }
		});
		
	    //btnAdd FUNCTIONALITY
        btnAdd.addActionListener(e -> 
        {
            int selectedIndex = catCodeBox.getSelectedIndex();
            int selectedIndex2 = packCodeBox.getSelectedIndex();
            int selectedIndex3 = varCodeBox.getSelectedIndex();
            if (selectedIndex != -1 && selectedIndex2 != -1 && selectedIndex3 != -1)
            {
                String categoryCode = categoryCodes.get(selectedIndex);
                String packageCode = packageCodes.get(selectedIndex2);
                String variantCode = variantCodes.get(selectedIndex3); 

                String categoryName = catNameTxtField.getText();
                String packageName = packNameTxtField.getText();
                String variantName = varNameTxtField.getText();

                String productCode = prodCodeTxtField.getText();
                String productName = prodNameTxtField.getText();
                String price = priceTxtField.getText();
                
                // Validate price as integer
                try {
                    float validatedPrice = Float.parseFloat(price);
                    if (validatedPrice <= 0) {
                        JOptionPane.showMessageDialog(null, "Price must be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit method if price is not valid
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Price must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit method if price is not a valid integer
                }

                if(!productCode.isEmpty() && !productName.isEmpty() && !price.isEmpty()) {
                	if (productExists(productCode, productName)) {
                        JOptionPane.showMessageDialog(null, "Product code or name already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                          
                        String record = categoryCode + ", " + categoryName + ", " +
                                        packageCode + ", " + packageName + ", " +
                                        variantCode + ", " + variantName + ", " +
                                        productCode + ", " + productName + ", " +
                                        price + System.lineSeparator();

                        try (FileWriter writer = new FileWriter("Product.txt",true))
                            {
                                writer.write(record);
                            }
                            catch (IOException ioException)
                            {
                                ioException.printStackTrace();
                            }
                            catCodeBox.setSelectedIndex(-1);
                            packCodeBox.setSelectedIndex(-1);
                            varCodeBox.setSelectedIndex(-1);

                            catNameTxtField.setText("");
                            packNameTxtField.setText("");
                            varNameTxtField.setText("");
                            prodCodeTxtField.setText("");
                            prodNameTxtField.setText("");
                            priceTxtField.setText("");

                            JOptionPane.showMessageDialog(null, "Product information successfully saved.",  "Save", JOptionPane.INFORMATION_MESSAGE);
                            
                            MainMenuFrame.df.refreshProduct();
                }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        catCodeBox.setSelectedIndex(-1);
        packCodeBox.setSelectedIndex(-1);
        varCodeBox.setSelectedIndex(-1);
	}

	private void loadCategory() {
		File categoryFile = new File("Category.txt");
        if (!categoryFile.exists()) {
            try {
                categoryFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        categoryCodes.clear();
        categoryNames.clear();
        try (Scanner scanner = new Scanner(new File("Category.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String categoryCode = parts[0].trim();
                    String categoryName = parts[1].trim();

                    // Check if categoryCode is already in the list
                    if (!categoryCodes.contains(categoryCode)) {
                        categoryCodes.add(categoryCode);
                        categoryNames.add(categoryName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPackage() {
    	File packageFile = new File("Package.txt");
        if (!packageFile.exists()) {
            try {
                packageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    	packageCodes.clear();
        packageNames.clear();
        try(Scanner scanner = new Scanner(new File("Package.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if(parts.length >= 2)
                {
                    String packageCode = parts[0].trim();
                    String packageName = parts[1].trim();

                    if (!packageCodes.contains(packageCode)) {
                    packageCodes.add(packageCode);
                    packageNames.add(packageName);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadVariant() {
    	File variantFile = new File("Variant.txt");
        if (!variantFile.exists()) {
            try {
                variantFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	
    	variantCodes.clear();
        variantNames.clear();
        try(Scanner scanner = new Scanner(new File("Variant.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if(parts.length >= 2)
                {
                    String variantCode = parts[0].trim();
                    String variantName = parts[1].trim();

                    if (!variantCodes.contains(variantCode)) {
                    variantCodes.add(variantCode);
                    variantNames.add(variantName);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCategory() {
        categoryCodes.clear();
        categoryNames.clear();
        loadCategory();
        catCodeBox.removeAllItems();
        for (String cCodes : categoryCodes) {
        	catCodeBox.addItem(cCodes);
        }
        catCodeBox.setSelectedIndex(-1);
    } 

    public void refreshPackage() {
        packageCodes.clear();
        packageNames.clear();
        loadPackage();
        packCodeBox.removeAllItems();
        for (String pCodes : packageCodes) {
        	packCodeBox.addItem(pCodes);
        }
        packCodeBox.setSelectedIndex(-1);
    } 

    public void refreshVariant() {
        variantCodes.clear();
        variantNames.clear();
        loadVariant();
        varCodeBox.removeAllItems();
        for (String vCodes : variantCodes) {
        	varCodeBox.addItem(vCodes);
        }
        varCodeBox.setSelectedIndex(-1);
    }
    
    private boolean productExists(String productCode, String productName) {
    	File productFile = new File("Product.txt");
        if (!productFile.exists()) {
            return false; // If product file doesn't exist, product does not exist
        }
    	
    	try (Scanner scanner = new Scanner(new File("Product.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 8 && parts[6].trim().equals(productCode) || parts[7].trim().equals(productName)) {
                    return true; // Product code and name already exist
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Product code and name not found
    }
}