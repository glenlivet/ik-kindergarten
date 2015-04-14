package org.ikgroup.service.activiti;

import java.util.List;

import org.activiti.engine.form.FormProperty;

/**
 * 表单信息服务
 * 
 * @author glenlivet
 *
 */
public interface ProcessFormService {

	/**
	 * 获取初始化表单元素信息
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	public List<FormProperty> getStartFormProperties(String processDefinitionId);

	/**
	 * 获取某个任务节点的表单元素
	 * 
	 * @param taskId
	 *            任务节点id
	 * @return
	 */
	public List<FormProperty> getFormProperties(String taskId);
}
