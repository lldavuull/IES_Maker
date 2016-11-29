
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.nio.charset.MalformedInputException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Windows {
	JFrame frame;
	int xPixels=480;
	int yPixels=240;
	
	public JPanel jPanel;
	public JCheckBox jcheckSubFolders;
	public JButton jbuttonExecuteIESchange;
	public JTextArea jTextArea;
	public JTextArea jTextAreaReplace;
	public JCheckBox jcheckUplight;
	public JCheckBox jcheckCalLumen;
	
	public JTextArea jTextLength;
	public JTextArea jTextWidth;
	public JTextArea jTextHeight;
	
	public JTextArea jTextMultiple;
	public JTextArea jTextWattage;
	
	public Windows(String Name) {
		
		frame = new JFrame(Name);
		frame.setSize(xPixels, yPixels);
		frame.setLocation(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void CreateIESFrame() {

		jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(xPixels, yPixels));
		
		

		jcheckSubFolders = new JCheckBox("包含子階資料夾");
		jcheckSubFolders.setPreferredSize(new Dimension(150, 30));
		jPanel.add(jcheckSubFolders);
		
		jbuttonExecuteIESchange = new JButton("修改程式所在資料夾的全部IES檔");
		jbuttonExecuteIESchange.setPreferredSize(new Dimension(290, 30));
		jPanel.add(jbuttonExecuteIESchange);
		
		
		jTextArea = new JTextArea(null, 2, 17);
		jPanel.add(new JLabel("尋找"));
		jPanel.add(jTextArea);
		
		jTextAreaReplace = new JTextArea(null, 2, 17);
//		JPanel jPanelReplace = new JPanel();
		jPanel.add(new JLabel("取代為"));
		jPanel.add(jTextAreaReplace);
//		
		
		jcheckUplight = new JCheckBox("製作Uplight");
		jcheckUplight.setPreferredSize(new Dimension(245, 30));
		jPanel.add(jcheckUplight);
		
		jcheckCalLumen = new JCheckBox("製作流明表");
		jcheckCalLumen.setPreferredSize(new Dimension(200, 30));
		jPanel.add(jcheckCalLumen);
		
		jTextLength = new JTextArea(null, 1, 10);
		jPanel.add(new JLabel("長:"));
		jPanel.add(jTextLength);
		
		jTextWidth = new JTextArea(null, 1, 10);
		jPanel.add(new JLabel("寬:"));
		jPanel.add(jTextWidth);
		
		jTextHeight = new JTextArea(null, 1, 10);
		jPanel.add(new JLabel("高:"));
		jPanel.add(jTextHeight);
		
		jTextMultiple = new JTextArea(null, 1, 15);
		jPanel.add(new JLabel("亮度倍數:"));
		jPanel.add(jTextMultiple);
		
		jTextWattage = new JTextArea(null, 1, 17);
		jPanel.add(new JLabel("瓦數:"));
		jPanel.add(jTextWattage);
		
//		jbuttonExecuteIESchange.setPreferredSize(new Dimension(300, 30));
		Container framContainter = frame.getContentPane();
//		jbuttonExecuteIESchange.setBounds(20, 20, 100, 40);
		jbuttonExecuteIESchange.addActionListener(new WindowsController(this));
//		framContainter.add(BorderLayout.EAST, jbuttonExecuteIESchange);
//		framContainter.add(BorderLayout.CENTER, jcheckSubFolders);
		// framContainter.add(BorderLayout.NORTH, jTextArea);
		framContainter.add(jPanel);
//		framContainter.add(BorderLayout.SOUTH, jPanelReplace);

		frame.setVisible(true);
	}
}
