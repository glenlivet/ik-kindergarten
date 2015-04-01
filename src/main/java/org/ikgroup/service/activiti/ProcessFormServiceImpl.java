package org.ikgroup.service.activiti;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessFormServiceImpl implements ProcessFormService {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;

	@Override
	public List<FormProperty> getStartFormProperties(String processKey) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processKey).latestVersion().singleResult();
		return formService.getStartFormData(pd.getId()).getFormProperties();
	}

	@Override
	public List<FormProperty> getFormProperties(String taskId) {
		
		return formService.getTaskFormData(taskId).getFormProperties();
	}

}
