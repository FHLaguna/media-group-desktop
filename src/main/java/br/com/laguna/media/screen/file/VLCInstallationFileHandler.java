package br.com.laguna.media.screen.file;

import java.io.File;

import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;

public class VLCInstallationFileHandler implements SheetCloseListener {

	private FileBrowserSheet fileBrowser;
	private Window window;
	private Label labelVLCInstallation;

	public VLCInstallationFileHandler(Window window, Label labelVLCInstallation, FileBrowserSheet fileBrowser) {
		this.window = window;
		this.labelVLCInstallation = labelVLCInstallation;
		this.fileBrowser = fileBrowser;
	}

	@Override
	public void sheetClosed(Sheet sheet) {
		if (sheet.getResult()) {
			Sequence<File> selectedFiles = fileBrowser.getSelectedFiles();

			if (selectedFiles.getLength() != 1) {
				Alert.alert("Invalid folder selection", window);
				return;
			}
			labelVLCInstallation.setText(selectedFiles.get(0).getAbsolutePath());
		}

	}
}
