package br.com.laguna.media.screen.button;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.FileBrowserSheet.Mode;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.Window;

import br.com.laguna.media.screen.file.AddMediaFoldersFileHandler;

public class AddMediaFoldersButtonPressListener implements ButtonPressListener {

	private Window window;
	private FileBrowserSheet fileBrowser;
	private TablePane mediaFoldersTable;

	public AddMediaFoldersButtonPressListener(Window window,
			TablePane mediaFoldersTable) {
		this.window = window;
		this.mediaFoldersTable = mediaFoldersTable;
	}

	@Override
	public void buttonPressed(Button button) {
		fileBrowser = new FileBrowserSheet(Mode.SAVE_TO);
		fileBrowser.open(window, new AddMediaFoldersFileHandler(
				mediaFoldersTable, fileBrowser));
	}

}
