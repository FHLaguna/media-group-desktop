package br.com.laguna.media.screen.button;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TablePane.Row;

public class RemoveButtonPressListener implements ButtonPressListener {

	private Row row;
	private TablePane table;

	public RemoveButtonPressListener(TablePane table, TablePane.Row row) {
		this.table = table;
		this.row = row;
	}

	@Override
	public void buttonPressed(Button button) {
		table.getRows().remove(row);
	}

}
