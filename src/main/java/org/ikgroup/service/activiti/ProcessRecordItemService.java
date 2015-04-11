package org.ikgroup.service.activiti;

public interface ProcessRecordItemService {
	
	/**
	 * 保存流程启动日志
	 * 
	 * @param instanceId
	 * @param userId
	 */
	public void saveProcessStartRecord(String instanceId, String userId);
	
	/**
	 * 保存流程结束日志
	 * 
	 * @param instanceId
	 */
	public void saveProcessCompletedRecord(String instanceId);
	
	/**
	 * 保存流程取消日志
	 * 
	 * @param instanceId
	 * @param userId
	 * @param reason
	 */
	public void saveProcessCancelledRecord(String instanceId, String userId, String reason);

}
