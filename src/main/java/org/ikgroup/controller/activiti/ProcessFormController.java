package org.ikgroup.controller.activiti;

import java.util.List;

import org.activiti.engine.form.FormProperty;
import org.ikgroup.service.activiti.ProcessFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/form")
@Controller
public class ProcessFormController {
	
	@Autowired
	private ProcessFormService processFormService;
	
	/**
	 * 获取流程表单信息
	 * 
	 * @param processKey
	 * @return
	 */
	@RequestMapping(value = "/properties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FormProperty> getStartFormProperties(@RequestParam String processKey){
		return processFormService.getStartFormProperties(processKey);
	}

	@RequestMapping(value = "/taskProperties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FormProperty> getTaskFormProperties(@RequestParam String taskId){
		return processFormService.getFormProperties(taskId);
	}
}
