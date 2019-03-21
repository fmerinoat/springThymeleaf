package com.example.backendninja.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



// TODO: Auto-generated Javadoc
/**
 * The Class TaskComponent.
 */
@Component("taskComponent")
public class TaskComponent {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(TaskComponent.class);
	
	
	/**
	 * Do task.
	 * Para hacer una tarea automatica en este caso cada 5 segundos
	 */
	@Scheduled(fixedDelay = 5000)
	public void doTask(){
		LOG.info("TIME IS: " + new Date());
	}
}
