package br.com.laguna.media.screen.component.builder;

import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Label;

public class MediaFolderLabelBuilder {

	public Label build(String text) {
		Label label = new Label();
		try {
			label.setStyles(
					"{horizontalAlignment:'center', verticalAlignment:'center', wrapText:true, color:'#FFFFFF'}");
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		label.setText(text);
		return label;
	}
}
