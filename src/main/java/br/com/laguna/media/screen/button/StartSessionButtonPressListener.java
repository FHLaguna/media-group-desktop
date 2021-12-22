package br.com.laguna.media.screen.button;

import org.apache.log4j.Logger;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TaskAdapter;
import org.apache.pivot.wtk.Window;

import br.com.laguna.media.base.response.code.ResponseCode;
import br.com.laguna.media.screen.task.StartSessionAsyncTask;
import br.com.laguna.media.screen.task.listener.StartSessionAsyncTaskListener;
import br.com.laguna.media.server.base.service.facade.ServerServiceFacade;
import br.com.laguna.media.server.group.end.service.response.EndGroupServiceResponse;

public class StartSessionButtonPressListener implements ButtonPressListener {

    public static final String STOPPING = "ENDING...";

    public static final String STARTING = "CREATING...";

    public static final String START_SESSION = "CREATE GROUP";

    public static final String STOP_SESSION = "END GROUP";

    private boolean isStarted = false;

    private Logger logger = Logger.getLogger(getClass());

    private ServerServiceFacade serverServiceFacade;

    private Window owner;

    private Label labelUrl;

    public StartSessionButtonPressListener(Window owner, Label labelUrl) {
	this.owner = owner;
	this.labelUrl = labelUrl;
	this.serverServiceFacade = new ServerServiceFacade();
    }

    @Override
    public void buttonPressed(Button button) {
	if (isStarted) {
	    logger.info("Stopping session...");
	    button.setButtonData(STOPPING);

	    EndGroupServiceResponse response = serverServiceFacade.endGroup();

	    if (ResponseCode.OK == response.getResponseCode()) {
		isStarted = false;
		logger.info("Session stopped");
		button.setButtonData(START_SESSION);
		labelUrl.setText("");
	    } else {
		button.setButtonData(STOP_SESSION);
		Alert.alert("Session not stopped: " + response.getResponseCode(), owner);
	    }
	} else {
	    logger.info("Starting session...");
	    button.setButtonData(STARTING);
	    button.setEnabled(false);
	    StartSessionAsyncTask asyncTask = new StartSessionAsyncTask();
	    StartSessionAsyncTaskListener taskListener = new StartSessionAsyncTaskListener(button, labelUrl, this);
	    asyncTask.execute(new TaskAdapter<Integer>(taskListener));
	}
    }

    public boolean isStarted() {
	return isStarted;
    }

    public void setStarted(boolean isStarted) {
	this.isStarted = isStarted;
    }

    public Window getOwner() {
	return owner;
    }

}
