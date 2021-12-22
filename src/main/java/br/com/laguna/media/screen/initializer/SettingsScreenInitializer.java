package br.com.laguna.media.screen.initializer;

import java.util.List;

import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Row;
import org.apache.pivot.wtk.TablePane.RowSequence;
import org.springframework.util.StringUtils;

import br.com.laguna.media.base.configuration.ConfigurationProvider;
import br.com.laguna.media.base.configuration.ConfigurationMediaFolder;
import br.com.laguna.media.screen.component.builder.MediaFolderBoxPaneCheckboxBuilder;
import br.com.laguna.media.screen.component.builder.MediaFolderLabelBuilder;
import br.com.laguna.media.screen.component.builder.MediaFolderRemoveButtonBuilder;

public class SettingsScreenInitializer {

	private MediaFolderLabelBuilder mediaFolderLabelBuilder;

	private MediaFolderRemoveButtonBuilder mediaFolderRemoveButtonBuilder;

	private MediaFolderBoxPaneCheckboxBuilder mediaFolderBoxPaneCheckboxBuilder;

	public SettingsScreenInitializer() {
		this.mediaFolderLabelBuilder = new MediaFolderLabelBuilder();
		this.mediaFolderRemoveButtonBuilder = new MediaFolderRemoveButtonBuilder();
		this.mediaFolderBoxPaneCheckboxBuilder = new MediaFolderBoxPaneCheckboxBuilder();
	}

	public void initialize(TablePane mediaFoldersTable, Label labelVLCInstallation) {
		String vlcMediaPlayerPath = ConfigurationProvider.getInstance().getVlcMediaPlayerPath();
		if (!StringUtils.isEmpty(vlcMediaPlayerPath)) {
			labelVLCInstallation.setText(vlcMediaPlayerPath);
		}

		RowSequence rows = mediaFoldersTable.getRows();

		List<ConfigurationMediaFolder> mediaFolders = ConfigurationProvider.getInstance().getMediaFolders();
		if (mediaFolders != null) {
			for (ConfigurationMediaFolder mediaFolder : mediaFolders) {
				Row row = new Row();
				row.setHeight("1*");

				row.insert(mediaFolderLabelBuilder.build(mediaFolder.getPath()), 0);
				row.insert(mediaFolderBoxPaneCheckboxBuilder.build(mediaFolder.isSubfoldersIncluded()), 1);
				row.insert(mediaFolderRemoveButtonBuilder.build(mediaFoldersTable, row), 2);

				rows.add(row);
			}
		}
	}

}
