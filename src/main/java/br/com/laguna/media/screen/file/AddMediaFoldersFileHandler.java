package br.com.laguna.media.screen.file;

import java.io.File;

import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Row;

import br.com.laguna.media.screen.component.builder.MediaFolderBoxPaneCheckboxBuilder;
import br.com.laguna.media.screen.component.builder.MediaFolderLabelBuilder;
import br.com.laguna.media.screen.component.builder.MediaFolderRemoveButtonBuilder;

public class AddMediaFoldersFileHandler implements SheetCloseListener {

	private FileBrowserSheet fileBrowser;
	private TablePane mediaFoldersTable;
	private MediaFolderLabelBuilder labelBuilder;
	private MediaFolderRemoveButtonBuilder removeButtonBuilder;
	private MediaFolderBoxPaneCheckboxBuilder boxPaneCheckboxBuilder = new MediaFolderBoxPaneCheckboxBuilder();

	public AddMediaFoldersFileHandler(TablePane mediaFoldersTable, FileBrowserSheet fileBrowser) {
		this.mediaFoldersTable = mediaFoldersTable;
		this.fileBrowser = fileBrowser;
		this.labelBuilder = new MediaFolderLabelBuilder();
		this.removeButtonBuilder = new MediaFolderRemoveButtonBuilder();
		this.boxPaneCheckboxBuilder = new MediaFolderBoxPaneCheckboxBuilder();
	}

	@Override
	public void sheetClosed(Sheet sheet) {
		if (sheet.getResult()) {
			Sequence<File> selectedFiles = fileBrowser.getSelectedFiles();

			for (int i = 0; i < selectedFiles.getLength(); i++) {
				Row row = new Row();
				row.setHeight("1*");
				File file = selectedFiles.get(i);
				row.add(labelBuilder.build(file.getAbsolutePath()));

				BoxPane boxPaneCheckBox = boxPaneCheckboxBuilder.build(false);
				row.add(boxPaneCheckBox);

				PushButton removeButton = removeButtonBuilder.build(mediaFoldersTable, row);
				row.add(removeButton);

				mediaFoldersTable.getRows().add(row);
			}
		}

	}

}
