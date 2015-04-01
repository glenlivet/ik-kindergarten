package org.ikgroup.service.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessOperationServiceImpl implements ProcessOperationService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ProcessInstance startProcessInstanceByKey(String processKey,
			String userId, Map<String, Object> variables) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(
				processKey, variables);
		runtimeService.addUserIdentityLink(pi.getId(), userId,
				IdentityLinkType.STARTER);
		return pi;
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processKey,
			Map<String, String> properties, String userId) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processKey).latestVersion()
				.singleResult();
		ProcessInstance pi = formService.submitStartFormData(pd.getId(),
				properties);
		runtimeService.addUserIdentityLink(pi.getId(), userId,
				IdentityLinkType.STARTER);
		return pi;
	}

	@Override
	public void cancelProcessInstanceByInstanceId(String processInstanceId,
			String reason, String userId) {
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) repositoryService.createProcessDefinitionQuery().processDefinitionKey("rejectionTest").singleResult();
		ActivityImpl activiti = pd.findActivity("formDocumentation");
		System.out.println();
	}

	@Override
	public void suspendProcessInstanceByInstanceId(String processInstanceId,
			String reason, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateProcessInstanceByInstanceId(String processInstanceId,
			String reason, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void approveCommand(String taskId, String message, String userId) {
		// TODO Auto-generated method stub
		List<FormProperty> fpl = formService.getStartFormData("aa")
				.getFormProperties();

	}

}
