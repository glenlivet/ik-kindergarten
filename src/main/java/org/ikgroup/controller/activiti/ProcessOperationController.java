package org.ikgroup.controller.activiti;

import org.ikgroup.service.activiti.ProcessOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProcessOperationController {
	
	@Autowired
	private ProcessOperationService processOperationService;
	
	@RequestMapping(value = "/process/start", method = RequestMethod.POST)
	@ResponseBody
	public void startProcess(@RequestParam String processKey, @RequestParam String userId){
		processOperationService.startProcessInstanceByKey(processKey, userId,null);
	}

}
