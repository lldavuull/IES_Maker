
public class ChangeReplace {
	IESFileContent iesfile;
	String Text;
	String TextReplace;

	public ChangeReplace(IESFileContent iesfile, String Text, String TextReplace) {
		this.iesfile = iesfile;
		this.Text = Text;
		this.TextReplace = TextReplace;

		if (!Text.isEmpty()) {
			if (TextReplace.isEmpty()) {
				TextReplace = "";
			}
			for (int i = 0; i < iesfile.Text_ArrayListString.size(); i++) {
				iesfile.Text_ArrayListString.set(i,
						iesfile.Text_ArrayListString.get(i).toString().replace(Text, TextReplace));
				// System.out.println(Text);
				// System.out.println(TextReplace);
			}
		}

	}

}
