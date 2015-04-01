package org.ikgroup.controller.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/reject")
@Controller
public class RejectionTestController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public void startProcess(){
		runtimeService.startProcessInstanceByKey("rejectionTest");
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public void sendMessage(String taskId){
		Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("seniorCmd_", 1);
		variables.put("toActivity_", "managerApproval");
		runtimeService.messageEventReceived("transitionMsg", t.getExecutionId(), variables);
	}
	
	
}
