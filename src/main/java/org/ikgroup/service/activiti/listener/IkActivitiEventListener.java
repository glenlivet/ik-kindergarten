package org.ikgroup.service.activiti.listener;

import org.activiti.engine.delegate.event.ActivitiCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.ikgroup.service.activiti.ProcessRecordItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IkActivitiEventListener implements ActivitiEventListener {

	Logger logger = LoggerFactory.getLogger(IkActivitiEventListener.class);

	@Autowired
	private ProcessRecordItemService processRecordItemSerivce;

	@Override
	public void onEvent(ActivitiEvent event) {
		switch (event.getType()) {
		case PROCESS_CANCELLED:
			ActivitiEntityEvent e = (ActivitiEntityEvent) event;
			handleProcessCancelled(e);
			break;
		case PROCESS_COMPLETED:
			ActivitiCancelledEvent ev = (ActivitiCancelledEvent) event;
			handleProcessCompleted(ev);
			break;
		default:
			break;
		}
	}

	/**
	 * 流程完毕
	 * 
	 * @param ev
	 */
	private void handleProcessCompleted(ActivitiCancelledEvent ev) {

		// 向流程信息纪录表里查一条纪录
		String instanceId = ev.getProcessInstanceId();
		processRecordItemSerivce.saveProcessCompletedRecord(instanceId);
		logger.debug("Process[Id: " + instanceId + "] is completed!");
	}

	/**
	 * 流程取消
	 * 
	 * @param e
	 */
	private void handleProcessCancelled(ActivitiEntityEvent e) {
		// TODO
	}

	@Override
	public boolean isFailOnException() {
		// onEvent抛出异常时，是否影响事务
		return true;
	}

}
