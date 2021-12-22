package br.com.laguna.media.screen.button;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.TablePane.RowSequence;
import org.apache.pivot.wtk.TextInput;

import br.com.laguna.media.base.configuration.ConfigurationProvider;
import br.com.laguna.media.base.configuration.ConfigurationMediaFolder;

import org.apache.pivot.wtk.Window;

public class SaveButtonPressListener implements ButtonPressListener {

    private Label labelVLCInsallation;
    private TablePane mediaFoldersTable;
    private Window window;
    private TextInput inputPort;

    public SaveButtonPressListener(TablePane mediaFoldersTable, Label labelVLCInstallation, TextInput inputPort, Window window) {
	this.mediaFoldersTable = mediaFoldersTable;
	this.labelVLCInsallation = labelVLCInstallation;
	this.inputPort = inputPort;
	this.window = window;
    }

    @Override
    public void buttonPressed(Button button) {
	String port = inputPort.getText();
	if (StringUtils.isBlank(port)) {
	    Alert.alert("Please type the port!", window);
	    return;
	}
	if (!StringUtils.isNumeric(port)) {
	    Alert.alert("The port must be numeric!", window);
	    return;
	}
	
	ConfigurationProvider.getInstance().setPort(Integer.parseInt(port));
	
	ConfigurationProvider.getInstance().setVlcMediaPlayerPath(labelVLCInsallation.getText());

	List<ConfigurationMediaFolder> mediaFolders = new ArrayList<ConfigurationMediaFolder>();
	RowSequence rows = mediaFoldersTable.getRows();
	for (int i = 1; i < rows.getLength(); i++) {
	    Row row = rows.get(i);

	    Label labelPath = (Label) row.get(0);
	    String path = labelPath.getText();

	    BoxPane boxPaneCheckBox = (BoxPane) row.get(1);
	    Checkbox isSubfoldersIncludedCheckbox = (Checkbox) boxPaneCheckBox.get(0);
	    boolean isSubfoldersIncluded = isSubfoldersIncludedCheckbox.isSelected();

	    ConfigurationMediaFolder mediaFolder = new ConfigurationMediaFolder(path, isSubfoldersIncluded);
	    mediaFolders.add(mediaFolder);
	}
	ConfigurationProvider.getInstance().setMediaFolders(mediaFolders);
	System.out.println(ConfigurationProvider.getInstance());
	try {
	    ConfigurationProvider.save();
	    Alert.alert("Saved successfully!", window);
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error("Erro ao salvar!", e);
	    Alert.alert("Error saving!", window);
	}
    }

}
