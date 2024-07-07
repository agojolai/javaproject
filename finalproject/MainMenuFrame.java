package finalproject;
//back up
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"serial"})	

public class MainMenuFrame extends JFrame {
	
    static CategoryFrame cf = new CategoryFrame();
    static PackagingFrame pf = new PackagingFrame();
    static VariantFrame vf = new VariantFrame();
    static ProductFrame prodf = new ProductFrame ();
    static SupplierFrame sf = new SupplierFrame();
    static DeliveryFrame df = new DeliveryFrame();
    static OrderFrame of = new OrderFrame();
    
	private JPanel contentPane;
	private BevelBorder stBorder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	private Font stFont = new Font("Segoe UI", Font.PLAIN, 13);

    private List<ProductEntry> productEntries = new ArrayList<>();
    private List<ProductDelivery> productDeliveries = new ArrayList<>();
	private JTextArea textArea;
	
	static File category_file = new File("Category.txt");
	static File package_file = new File("Package.txt");
	static File variant_file = new File("Variant.txt");
	static File product_file = new File("Product.txt");
	static File supplier_file = new File("Supplier.txt");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			checkAndCreateFiles();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuFrame() {
		setTitle("Product Delivery Management System");
		setResizable(false);
		setBounds(100, 100, 910, 625);
		setFont(stFont);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JMenuBar main = new JMenuBar();
		main.setBounds(0, 0, 886, 22);
		contentPane.add(main);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(stFont);
		main.add(fileMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu prodInfo = new JMenu("Product Information");
		prodInfo.setFont(stFont);
		fileMenu.add(prodInfo);
		prodInfo.setMnemonic(KeyEvent.VK_P);

		
		JMenuItem addCat = new JMenuItem("Category");
		addCat.setFont(stFont);
		prodInfo.add(addCat);
		addCat.setMnemonic(KeyEvent.VK_C);

		
		JMenuItem addPack = new JMenuItem("Packaging");
		addPack.setFont(stFont);
		prodInfo.add(addPack);
		addPack.setMnemonic(KeyEvent.VK_P);

		
		JMenuItem addVar = new JMenuItem("Variant");
		addVar.setFont(stFont);
		prodInfo.add(addVar);
		addVar.setMnemonic(KeyEvent.VK_V);
		
		JMenuItem addProd = new JMenuItem("Product");
		addProd.setFont(stFont);
		prodInfo.add(addProd);
		addProd.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem suppInfo = new JMenuItem("Supplier Information");
		suppInfo.setFont(stFont);
		fileMenu.add(suppInfo);
		suppInfo.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem ex = new JMenuItem("Exit");
		ex.setFont(stFont);
		fileMenu.add(ex);
		ex.setMnemonic(KeyEvent.VK_X);

		
		JMenu transMenu = new JMenu("Transaction");
		transMenu.setFont(stFont);
		main.add(transMenu);
		transMenu.setMnemonic(KeyEvent.VK_T);
		
		JMenuItem delivery = new JMenuItem("Delivery");
		delivery.setFont(stFont);
		transMenu.add(delivery);
		delivery.setMnemonic(KeyEvent.VK_D);
		
		JMenuItem order = new JMenuItem("Order");
		order.setFont(stFont);
		transMenu.add(order);
		order.setMnemonic(KeyEvent.VK_O);
		
		JMenu reportMenu = new JMenu("Report");
		reportMenu.setFont(stFont);
		main.add(reportMenu);
		reportMenu.setMnemonic(KeyEvent.VK_R);
		
		JMenuItem product = new JMenuItem("Product");
		product.setFont(stFont);
		reportMenu.add(product);
		product.setMnemonic(KeyEvent.VK_P);
		
		JMenuItem sales = new JMenuItem("Sales");
		sales.setFont(stFont);
		reportMenu.add(sales);
		sales.setMnemonic(KeyEvent.VK_S);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setFont(stFont);
		main.add(aboutMenu);
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem system = new JMenuItem("System");
		system.setFont(stFont);
		aboutMenu.add(system);
		system.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem authors = new JMenuItem("Authors");
		authors.setFont(stFont);
		aboutMenu.add(authors);
		authors.setMnemonic(KeyEvent.VK_A);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		
		textArea.setEnabled(false);
		textArea.setBorder(stBorder);
		JScrollPane scrollPane = new JScrollPane(textArea, 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , 
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scrollPane.setBounds(32, 59, 830, 483);
		contentPane.add(scrollPane);
		
		setLocationRelativeTo(null);
		
		// SET VISIBLES OF FRAMES
		//FILEMENU
	     addCat.addActionListener(e -> {
	    	 cf.setVisible(true);
	    	 setVisible(false);
	    	 });
	     addPack.addActionListener(e -> {
	    	pf.setVisible(true);
	    	setVisible(false);
	     	});
	     addVar.addActionListener(e -> {
	    	 vf.setVisible(true);
	    	 setVisible(false);
	         });
	     addProd.addActionListener(e -> {
	    	 prodf.setVisible(true);
	    	 setVisible(false);
	     	 });
	     suppInfo.addActionListener(e -> {
	    	 sf.setVisible(true);
	    	 setVisible(false);
	     	});
	     ex.addActionListener(e -> dispose());
	     delivery.addActionListener(e -> {
	    	 df.setVisible(true);
	    	 setVisible(false);
	     	 });
	     order.addActionListener(e -> {
	    	 of.setVisible(true);
	    	 setVisible(false);
	     	 });

	     // OUTPUT OF PRODUCT AND SALES
		sales.addActionListener(e -> displaySales());
		product.addActionListener(e -> displayProduct());

		// ABOUT ACTION LISTENERS
		system.addActionListener(e -> showAboutSystem());
		authors.addActionListener(e -> showAboutAuthors());
	}
	
	private void showAboutSystem() {

		String message = "<html><body style='width: 350px; font-family: Segoe UI, Segoe UI;'>";
	    message += "<h2 style='color: #6e78b8;'>Welcome to the Product Delivery Management System:</h2>";
	    message += "<p>Your ultimate tool for <b>efficient delivery management</b>.</p>";
	    
	    message += "<h3 style='color: #6e78b8;'>Features:</h3>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Manage <b>product information</b>, suppliers, deliveries, and orders.</li>";
	    message += "<li>Generate reports on <b>product sales</b>.</li>";
	    message += "</ul>";
	    
	    message += "<h3 style='color: #6e78b8;'>Benefits:</h3>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Streamline operations and track <b>sales effectively</b>.</li>";
	    message += "<li>User-friendly interface for <b>ease of use</b>.</li>";
	    message += "</ul>";
	    
	    message += "<h3 style='color: #6e78b8;'>Instructions:</h3>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Use the '<b>File</b>' menu to manage product information and suppliers.</li>";
	    message += "<li>Perform transactions (<b>deliveries, orders</b>) under '<b>Transaction</b>'.</li>";
	    message += "<li>Generate reports ('<b>Report</b>' menu) to view product sales.</li>";
	    message += "</ul>";
	    
	    message += "<p style='color: #6e78b8;'>Created as the final requirement in OOP (Java).</p>";
	    message += "</body></html>";

	    JOptionPane.showMessageDialog(null, message, "System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	private void showAboutAuthors() {
	    // HTML message content
	    String message = "<html><body style='width: 350px; font-family: Segoe UI, Segoe UI;'>";
	    message += "<h2 style='color: #6e78b8;'>Authors and Contributions:</h2>";

	    // Laira Anne D. Agojo
	    message += "<div style='margin-bottom: 15px;'>";
	    message += "<h3 style='color: #537395;'>AGOJO, Laira Anne D.</h3>";
	    message += "<p style='margin-bottom: 5px;'>Chief Architect and Visionary</p>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Designed the UI.</li>";
	    message += "<li>Conceptualized the system architecture.</li>";
	    message += "<li>Selected colors and designs.</li>";
	    message += "<li>Integrated codes to create a functional system.</li>";
	    message += "</ul>";
	    message += "</div>";

	    // Josephine Claire G. Nogot
	    message += "<div style='margin-bottom: 15px;'>";
	    message += "<h3 style='color: #537395;'>NOGOT, Josephine Claire G.</h3>";
	    message += "<p style='margin-bottom: 5px;'>Code Wizard and UI Enthusiast</p>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Oversaw the development process.</li>";
	    message += "<li>Performed bug finding and debugging.</li>";
	    message += "<li>Implemented error handling and exceptions.</li>";
	    message += "<li>Ensured comprehensive error checking across all frames.</li>";
	    message += "</ul>";
	    message += "</div>";

	    // Carl Louie A. Semera
	    message += "<div style='margin-bottom: 15px;'>";
	    message += "<h3 style='color: #537395;'>SEMERA, Carl Louie A.</h3>";
	    message += "<p style='margin-bottom: 5px;'>Master Debugger and Coffee Lover</p>";
	    message += "<ul style='list-style-type: disc; margin-left: 20px;'>";
	    message += "<li>Managed delivery and order functionalities.</li>";
	    message += "<li>Resolved bugs and optimized program functions.</li>";
	    message += "<li>Contributed to error fixes and improvements.</li>";
	    message += "<li>Assisted in fixing functions.</li>";
	    message += "</ul>";
	    message += "</div>";

	    message += "</body></html>";

	    // Show message dialog
	    JOptionPane.showMessageDialog(null, message, "Authors", JOptionPane.INFORMATION_MESSAGE);
	}

	//  TRANSACTION AND REPORT ACTION LISTENERS
	private void displayProduct() {
	productDeliveries.clear();
	textArea.setText("");
	//textArea.append("Product Report\n\n");
	File deliveryFile = new File("Delivery.txt");

		if (!deliveryFile.exists()) 
	{
    	textArea.setText("Delivery data not available yet.");
    	return;
	}
    try (BufferedReader reader = new BufferedReader(new FileReader("Delivery.txt"))) 
	{
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 7) 
			{	
                String productCode = parts[2].trim();
                String productName = parts[3].trim();
                String details = parts[4].trim();
                Integer quantity = Integer.parseInt(parts[5].trim());
                
				boolean found = false;
				
                for(ProductDelivery scan : productDeliveries )
				{
					if(scan.getProductName().equals(productName) && scan.getProductCode().equals(productCode) && scan.getDetail().equals(details) )
					{	scan.addToTotalQuantity(quantity);
						found = true;
						break;
					}
				}

				if (!found) 
				{
                    productDeliveries.add(new ProductDelivery(productCode, productName, details, quantity));
                }
            }
        }


    } catch (IOException e) {
        e.printStackTrace();
    }
	
    StringBuilder result = new StringBuilder();
	result.append("\t\t\tProduct Report\n\n\n");
    result.append("Product Code\tProduct Name\t\tPackage Description\tVariant Description\tAvailable Quantity\t\n");
    for (int i = 0; i < 200; i++) {
        result.append("-");
    }
    result.append("\n\n");
    
    for (ProductDelivery scan : productDeliveries) {
        result.append(String.format("%-6s\t%-30s\t%s\t%d%n%n", 
                                    scan.getProductCode(), 
                                    scan.getProductName(), 
                                    scan.getDetail(), 
                                    scan.getTotalQuantity()));
    }

	// Display results in the text area
	if (textArea != null) 
	{
		textArea.setText(result.toString());
	}
	}
	
	public void displaySales() {
		productEntries.clear();
		textArea.setText("");
		File salesFile = new File("Sales.txt");
    
		if (!salesFile.exists()) 
		{
			textArea.setText("Sales data not available yet.");
			return;
		}
        try (BufferedReader reader = new BufferedReader(new FileReader("Sales.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String productName = parts[0].trim();
					Integer totalquantity = Integer.parseInt(parts[1].trim());
                    double totalPrice = Double.parseDouble(parts[2].trim());
                    
                    boolean found = false;
                    	
                    for (ProductEntry entry : productEntries) {
                        if (entry.getProductName().equals(productName)) {
							entry. addToTotalQuantity(totalquantity);
                            entry.addToTotalPrice(totalPrice);
                            found = true;
                            break;
                        }
                    }
                    if (!found) 
					{
                        productEntries.add(new ProductEntry(productName, totalquantity, totalPrice));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		StringBuilder result = new StringBuilder();
		result.append("\t\t\t    Sales Report\n\n\n");
		result.append("Product Name\t\tTotal Quantity Sold\tTotal Amount\n");
		for (int i = 0; i < 180; i++) {
	        result.append("-");
	    }
	    result.append("\n\n");
		double totalSum = 0;
		
		for (ProductEntry entry : productEntries) {
		    result.append(String.format("%-30s\t%d\t\t%.2f%n%n", 
		                                entry.getProductName(), 
		                                entry.getTotalQuantity(), 
		                                entry.getTotalPrice()));
		    totalSum += entry.getTotalPrice();
		}

		for (int i = 0; i < 180; i++) {
	        result.append("-");
	    }

		result.append("\n\nAccumulated Total Sales: ").append(String.format("%.2f", totalSum));
	
		// Display results in the text area
		if (textArea != null) 
		{
			textArea.setText(result.toString());

        }
    }
    
    private static void checkAndCreateFiles() {
        try {
            if (!category_file.exists()) {
                category_file.createNewFile();
            }
            if (!package_file.exists()) {
                package_file.createNewFile();
            }
            if (!variant_file.exists()) {
                variant_file.createNewFile();
            }
            if (!product_file.exists()) {
                product_file.createNewFile();
            }
            if (!supplier_file.exists()) {
                supplier_file.createNewFile();
            }
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error: " + io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // To get the sum of all sales and if it is a same product it will added as one.
 	class ProductEntry 
 	{
 		private String productName;
 		private double totalPrice;
		private Integer totalQuantity;
 	
 		public ProductEntry(String productName, Integer totalquantity, double totalPrice) 
 		{
 			this.productName = productName;
 			this.totalPrice = totalPrice;
			this.totalQuantity = totalquantity;

 		}
 	
 		public String getProductName() {
 			return productName;
 		}
 	
 		public double getTotalPrice() {
 			return totalPrice;
 		}

		public Integer getTotalQuantity(){
			return totalQuantity;
		}
 	
 		public void addToTotalPrice(double amount) 
 		{
 			this.totalPrice += amount;
 		}

		 public void addToTotalQuantity(Integer quantity)
		 {
			 this.totalQuantity += quantity;
		 }
 	}
 	
 	class ProductDelivery
	{
		private String productName;
		private String productCode;
		private String detailez;
		private Integer totalQuantity;

		public ProductDelivery(String productCode,String productName,String details,Integer quantity)
		{
			this.productCode = productCode;
			this.productName = productName;
			this.detailez = details;
			this.totalQuantity = quantity;
		}

		public String getProductName()
		{
			return productName;
		}

		public String getProductCode() {
			return productCode;
		}

		public String getDetail() {
			return detailez;
		}

		public Integer getTotalQuantity() {
			return totalQuantity;
		}

		public void addToTotalQuantity(Integer quanti)
		{
			this.totalQuantity += quanti;
		}
	}
}