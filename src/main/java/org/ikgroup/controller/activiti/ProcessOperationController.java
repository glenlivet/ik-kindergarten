package org.ikgroup.controller.activiti;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.impl.util.json.JSONObject;
import org.ikgroup.service.activiti.ProcessOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/process")
@Controller
public class ProcessOperationController {

	@Autowired
	private ProcessOperationService processOperationService;

	@RequestMapping(value = "/startWithForm", method = RequestMethod.POST)
	@ResponseBody
	public void startWithForm(@RequestParam String vacationRequestJson) {

		JSONObject vacationRequest = new JSONObject(vacationRequestJson);
		JSONObject formProperties = vacationRequest.optJSONObject("formData");
		String processKey = vacationRequest.optString("processKey");
		String starter_ = vacationRequest.optString("starter_");
		@SuppressWarnings("rawtypes")
		Iterator i = formProperties.keys();
		Map<String, String> map = new HashMap<String, String>();
		while (i.hasNext()) {
			String s = i.next().toString();
			map.put(s, formProperties.get(s).toString());
		}
		processOperationService.startProcessInstanceByKey(processKey, map,
				starter_);
	}
}
