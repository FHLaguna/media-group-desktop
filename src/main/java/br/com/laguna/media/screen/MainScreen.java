package br.com.laguna.media.screen;

import java.net.URL;

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
import org.apache.pivot.wtk.Window;

import br.com.laguna.media.screen.button.StartSessionButtonPressListener;

public class MainScreen extends Window implements Application, Bindable {
    private Window window = null;

    @BXML
    private PushButton buttonSettings;

    @BXML
    private PushButton buttonStartSession;

    @BXML
    private SettingsScreen settingsScreen;

    @BXML
    private Label labelUrl;

    private StartSessionButtonPressListener startSessionButtonPressListener;

    @Override
    public void initialize(final Map<String, Object> namespace, final URL location, final Resources resources) {
	this.startSessionButtonPressListener = new StartSessionButtonPressListener(this, labelUrl);
	buttonStartSession.getButtonPressListeners().add(startSessionButtonPressListener);
	buttonSettings.getButtonPressListeners().add(new ButtonPressListener() {
	    @Override
	    public void buttonPressed(Button button) {
		settingsScreen.open(MainScreen.this);
	    }
	});
    }

    @Override
    public void startup(final Display display, final Map<String, String> properties) throws Exception {
	BXMLSerializer bxmlSerializer = new BXMLSerializer();
	window = (Window) bxmlSerializer.readObject(MainScreen.class, "/main_screen.bxml");
	display.getHostWindow().setSize(700, 500);
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
