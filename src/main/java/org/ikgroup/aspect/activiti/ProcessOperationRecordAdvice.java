package org.ikgroup.aspect.activiti;

import java.util.Date;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.ikgroup.domain.activiti.ProcessRecordItem;
import org.ikgroup.persistence.activiti.IdentityLinkMapper;
import org.ikgroup.service.activiti.ProcessRecordItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程操作纪录。
 * 包括： 流程启动
 * 
 * @author glenlivet
 *
 */
@Component
@Aspect
public class ProcessOperationRecordAdvice {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private IdentityLinkMapper identityLinkMapper;
	
	@Autowired
	private ProcessRecordItemService processRecordItemService;
	
	/**
	 * 纪录流程启动
	 * 
	 * @param joinPoint
	 * @param processInstance
	 */
	@AfterReturning(
			pointcut = "execution(* org.ikgroup.service.activiti.ProcessOperationServiceImpl.startProcessInstanceByKey(..))",
			returning = "processInstance"
			)
	@Transactional
	public void afterProcessInstanceStart(JoinPoint joinPoint, Object processInstance){
		ProcessInstance pi = (ProcessInstance) processInstance;
		String instanceId = pi.getProcessInstanceId();
		String userId = identityLinkMapper.getStarterOfProcessInstance(instanceId);
		processRecordItemService.saveProcessStartRecord(instanceId, userId);
	}

}
