package org.ikgroup.controller.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/task")
@Controller
public class TaskOperationController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@ResponseBody
	public void complete(String taskId) {
		taskService.complete(taskId);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test(@RequestParam String taskId) {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		String activityId = task.getTaskDefinitionKey();
		ActivityExecution execution = (ActivityExecution) runtimeService
				.createExecutionQuery().activityId(activityId).singleResult();
		String processDefinitionId = execution.getProcessDefinitionId();
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);

		ActivityImpl pointActivity = pd.findActivity("formDocumentation");
		ActivityImpl currActivity = (ActivityImpl) pd.findActivity(execution
				.getCurrentActivityId());
		// 缓存当前节点的外流transition
		List<PvmTransition> trans = new ArrayList<PvmTransition>();
		for (PvmTransition t : currActivity.getOutgoingTransitions()) {
			trans.add(t);
		}
		// 清空当前外流trans
		currActivity.getOutgoingTransitions().clear();
		// build a temporary outgoing transition for current activity
		PvmTransition tempTransition = currActivity.createOutgoingTransition();
		((TransitionImpl) tempTransition).setDestination(pointActivity);
		taskService.complete(taskId);

		// remove the temporary transition
		pointActivity.getIncomingTransitions().remove(tempTransition);
		currActivity.getOutgoingTransitions().remove(tempTransition);
		currActivity.getOutgoingTransitions().addAll(trans);

	}
}
