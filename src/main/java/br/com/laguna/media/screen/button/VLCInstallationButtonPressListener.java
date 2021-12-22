package br.com.laguna.media.screen.button;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.FileBrowserSheet.Mode;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import br.com.laguna.media.screen.file.VLCInstallationFileHandler;

public class VLCInstallationButtonPressListener implements ButtonPressListener {

	private Window window;
	private FileBrowserSheet fileBrowser;
	private Label labelVLCInstallation;

	public VLCInstallationButtonPressListener(Window window, Label labelVLCInstallation) {
		this.window = window;
		this.labelVLCInstallation = labelVLCInstallation;
	}

	@Override
	public void buttonPressed(Button button) {
		fileBrowser = new FileBrowserSheet(Mode.OPEN);
		fileBrowser.open(window, new VLCInstallationFileHandler(window, labelVLCInstallation, fileBrowser));
	}

}
