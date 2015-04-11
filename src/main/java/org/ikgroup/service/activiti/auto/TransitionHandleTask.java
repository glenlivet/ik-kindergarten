package org.ikgroup.service.activiti.auto;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransitionHandleTask implements ActivityBehavior {

	private static final long serialVersionUID = -3737399971994968945L;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		int cmd = Integer.parseInt(execution.getVariable("seniorCmd_")
				.toString());
		if (cmd == 0) {
			return;
		} else if (cmd == 1) {
			// rejection
			handleJump(execution);
		}

	}

	/**
	 * handle Task Jump
	 * 
	 * @param execution
	 */
	private void handleJump(ActivityExecution execution) {

		String activityId = execution.getVariable("toActivity_").toString();
		String processDefinitionId = execution.getProcessDefinitionId();

		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		// the destination activity
		ActivityImpl pointActivity = pd.findActivity(activityId);
		// the current activity
		ActivityImpl currActivity = (ActivityImpl) execution.getActivity();
		// build a temporary outgoing transition for current activity
		PvmTransition tempTransition = (currActivity)
				.createOutgoingTransition();
		((TransitionImpl) tempTransition).setDestination(pointActivity);
		// take the transition
		execution.take(tempTransition);
		// remove the temporary transition
		pointActivity.getIncomingTransitions().remove(tempTransition);
		currActivity.getOutgoingTransitions().remove(tempTransition);
	}
	
}
