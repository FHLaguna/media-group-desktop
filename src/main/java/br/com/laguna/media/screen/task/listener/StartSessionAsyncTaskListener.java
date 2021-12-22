package br.com.laguna.media.screen.task.listener;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Label;

import br.com.laguna.media.base.configuration.ConfigurationProvider;
import br.com.laguna.media.base.response.code.ResponseCode;
import br.com.laguna.media.screen.button.StartSessionButtonPressListener;

public class StartSessionAsyncTaskListener implements TaskListener<Integer> {

    private Logger logger = Logger.getLogger(getClass());

    private Button button;

    private StartSessionButtonPressListener listener;

    private Label labelUrl;

    public StartSessionAsyncTaskListener(Button button, Label labelUrl, StartSessionButtonPressListener listener) {
	this.button = button;
	this.labelUrl = labelUrl;
	this.listener = listener;
    }

    @Override
    public void executeFailed(Task<Integer> arg0) {
	logger.error("Falhou");
    }

    @Override
    public void taskExecuted(Task<Integer> arg0) {
	Integer responseCode = arg0.getResult();
	if (ResponseCode.OK == responseCode) {
	    listener.setStarted(true);

	    logger.info("Session started");
	    button.setButtonData(StartSessionButtonPressListener.STOP_SESSION);
	    String url = "error";
	    try {
		url = "http://" + InetAddress.getLocalHost().getHostAddress();
		Integer port = ConfigurationProvider.getInstance().getPort();
		if (port != 80) {
		    url += ":" + port;
		}
	    } catch (Exception e) {}
	    labelUrl.setText("Use this URL: " + url);
	} else {
	    button.setButtonData(StartSessionButtonPressListener.START_SESSION);
	    Alert.alert("Session not started: " + responseCode, listener.getOwner());
	}
	button.setEnabled(true);
    }

}
