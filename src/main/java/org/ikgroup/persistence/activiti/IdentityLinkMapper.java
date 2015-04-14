package org.ikgroup.persistence.activiti;

public interface IdentityLinkMapper {

	/**
	 * 获取流程实例的发起人
	 * 
	 * @param instanceId
	 *            流程实例id
	 * @return 发起人id
	 */
	public String getStarterOfProcessInstance(String instanceId);

}
