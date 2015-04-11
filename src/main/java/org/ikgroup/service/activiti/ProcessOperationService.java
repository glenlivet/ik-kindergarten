package org.ikgroup.service.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 流程相关操作Service.
 * 包括：发起、撤销、冻结、恢复。
 * 
 * @author glenlivet
 *
 */
public interface ProcessOperationService {
	
	/**
	 * 根据流程定义key和用户id来启动流程
	 * 
	 * @param processKey	流程定义key
	 * @param userId	发起人id
	 */
	public ProcessInstance startProcessInstanceByKey(String processKey, String userId, Map<String,Object> variables);
	
	/**
	 * 传入form信息发起流程
	 * 
	 * @param processKey
	 * @param properties
	 * @param userId
	 * @return
	 */
	public ProcessInstance startProcessInstanceByKey(String processKey, Map<String, String> properties, String userId);
	
	/**
	 * 撤销流程
	 * 
	 * @param processInstanceId	流程实例id
	 * @param userId	用户id
	 */
	public void cancelProcessInstanceByInstanceId(String processInstanceId, String reason, String userId);
	
	/**
	 * 冻结流程
	 * 
	 * @param processInstanceId	流程实例id
	 * @param reason	原因
	 * @param userId	用户id
	 */
	public void suspendProcessInstanceByInstanceId(String processInstanceId, String reason, String userId);
	
	/**
	 * 恢复流程
	 * 
	 * @param processInstanceId	流程实例id
	 * @param reason	原因
	 * @param userId	用户id
	 */
	public void activateProcessInstanceByInstanceId(String processInstanceId, String reason, String userId);
	
	public void approveCommand(String taskId, String message, String userId);

}
