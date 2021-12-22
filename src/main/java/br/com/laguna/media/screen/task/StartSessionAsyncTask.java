package br.com.laguna.media.screen.task;

import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskExecutionException;

import br.com.laguna.media.DesktopAppStart;
import br.com.laguna.media.server.base.service.facade.ServerServiceFacade;
import br.com.laguna.media.server.group.create.service.CreateGroupServiceRequest;
import br.com.laguna.media.server.group.create.service.response.CreateGroupServiceResponse;

public class StartSessionAsyncTask extends Task<Integer> {

    private ServerServiceFacade serverServiceFacade;

    public StartSessionAsyncTask() {
	this.serverServiceFacade = new ServerServiceFacade();
    }

    @Override
    public Integer execute() throws TaskExecutionException {
	CreateGroupServiceRequest request = new CreateGroupServiceRequest();
	request.setSource(DesktopAppStart.class);
	CreateGroupServiceResponse response = serverServiceFacade.createGroup(request);

	return response.getResponseCode();
    }

}
