package br.com.laguna.media.screen.component.builder;

import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Row;

import br.com.laguna.media.screen.button.RemoveButtonPressListener;

public class MediaFolderRemoveButtonBuilder {

	public PushButton build(TablePane mediaFoldersTable, Row row) {
		PushButton removeButton = new PushButton();
		removeButton.setButtonData("X");
		try {
			removeButton.setStyles(
					"{backgroundColor:'#FF3333', borderColor:'#FF3333', color:'#FFFFFF', font:'Arial bold 16'}");
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		removeButton.getButtonPressListeners().add(new RemoveButtonPressListener(mediaFoldersTable, row));
		return removeButton;
	}

}
