package org.ikgroup.controller.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/task")
@Controller
public class TaskOperationController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@ResponseBody
	public void complete(String taskId){
		
		
		taskService.complete(taskId);
		
		
	}
	
}
