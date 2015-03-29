package org.ikgroup.service.activiti;

public interface ProcessRecordItemService {
	
	/**
	 * 保存流程启动日志
	 * 
	 * @param instanceId
	 * @param userId
	 */
	public void saveProcessStartRecord(String instanceId, String userId);

}
