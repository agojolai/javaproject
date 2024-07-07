package finalproject;

//back up
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Insets;



public class JavaForThree {

	public static void main(String[] args)  {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
			UIManager.put( "Component.hideMnemonics", false );
			UIManager.put( "ScrollBar.showButtons", true );
			UIManager.put( "ScrollBar.trackArc", 999 );
			UIManager.put( "ScrollBar.thumbArc", 999 );
			UIManager.put( "ScrollBar.trackInsets", new Insets( 2, 4, 2, 4 ) );
			UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
			UIManager.put( "ScrollBar.track", new Color( 0xe0e0e0 ) );
			UIManager.put( "TextComponent.arc", 5 );


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			new MainMenuFrame().setVisible(true);
		}
					
		});
	}

}
