package br.com.laguna.media.screen;

import java.net.URL;
import java.util.Objects;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

import br.com.laguna.media.base.configuration.ConfigurationProvider;
import br.com.laguna.media.screen.button.AddMediaFoldersButtonPressListener;
import br.com.laguna.media.screen.button.SaveButtonPressListener;
import br.com.laguna.media.screen.button.VLCInstallationButtonPressListener;
import br.com.laguna.media.screen.initializer.SettingsScreenInitializer;

public class SettingsScreen extends Window implements Application, Bindable {
    private static final String VLC_INSTALLATION_NO_FILE_MESSAGE = "The executable file was not set";

    private Window window = null;

    @BXML
    private PushButton addMediaFoldersButton;
    @BXML
    private PushButton buttonVLCInstallation;
    @BXML
    private Label labelVLCInstallation;
    @BXML
    private TablePane mediaFoldersTable;
    @BXML
    private PushButton buttonSave;
    @BXML
    private PushButton buttonBack;
    @BXML
    private TextInput inputPort;

    @Override
    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources) {
	addMediaFoldersButton.getButtonPressListeners()
		.add(new AddMediaFoldersButtonPressListener(this, mediaFoldersTable));
	buttonVLCInstallation.getButtonPressListeners()
		.add(new VLCInstallationButtonPressListener(this, labelVLCInstallation));
	labelVLCInstallation.setText(VLC_INSTALLATION_NO_FILE_MESSAGE);
	inputPort.setText(Objects.toString(ConfigurationProvider.getInstance().getPort(), "80"));
	buttonSave.getButtonPressListeners()
		.add(new SaveButtonPressListener(mediaFoldersTable, labelVLCInstallation, inputPort, this));

	SettingsScreenInitializer initializer = new SettingsScreenInitializer();
	initializer.initialize(mediaFoldersTable, labelVLCInstallation);
	buttonBack.getButtonPressListeners().add(new ButtonPressListener() {
	    @Override
	    public void buttonPressed(Button button) {
		SettingsScreen.this.close();
	    }
	});
    }

    @Override
    public void startup(final Display display, final Map<String, String> properties) throws Exception {
	BXMLSerializer bxmlSerializer = new BXMLSerializer();
	window = (Window) bxmlSerializer.readObject(SettingsScreen.class, "/settings_screen.bxml");
	window.open(display);
    }

    @Override
    public boolean shutdown(final boolean optional) throws Exception {
	this.close();
	return false;
    }

    @Override
    public void suspend() throws Exception {
    }

    @Override
    public void resume() throws Exception {
    }
}
