import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class WindowsController implements ActionListener {
	Windows windows;
	JCheckBox jcheckbox;
	JFrame jframe;
	JTextArea jTextArea;
	JTextArea jTextAreaReplace;

	WindowsController(Windows windows) {
		this.windows = windows;
		this.jframe = windows.frame;
		this.jcheckbox = windows.jcheckSubFolders;
		this.jTextArea = windows.jTextArea;
		this.jTextAreaReplace = windows.jTextAreaReplace;
	}

	public void actionPerformed(ActionEvent e) {

		IES_Executer ies_Executer = new IES_Executer(windows, jcheckbox.isSelected(), jTextArea.getText(),
				jTextAreaReplace.getText());
		if (jcheckbox.isSelected()) {
			JOptionPane.showMessageDialog(jframe, "程式所在的資料夾以及所有子階資料夾中的IES檔都修改成功", "Meteor IES修改器",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(jframe, "程式所在資料夾中的IES檔都修改了", "Meteor IES修改器",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
