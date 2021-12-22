package br.com.laguna.media.screen.component.builder;

import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Checkbox;

public class MediaFolderBoxPaneCheckboxBuilder {

	public BoxPane build(boolean selected) {
		Checkbox checkbox = new Checkbox();
		checkbox.setSelected(selected);
		BoxPane boxPaneCheckBox = new BoxPane();
		try {
			boxPaneCheckBox.setStyles("{horizontalAlignment:'center', verticalAlignment:'center'}");
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		boxPaneCheckBox.add(checkbox);
		return boxPaneCheckBox;
	}

}
