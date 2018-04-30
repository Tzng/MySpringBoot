package team.myl.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 标题：TimeTesk
 * </p>
 * <p>
 * 描述：定时任务
 * </p>
 * 
 * @data 2018年4月29日 下午10:48:26
 * @author tangbin
 * @version 1.0
 */
@Component
public class TimeTesk {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 定义三秒执行的任务
	 */
	@Scheduled(fixedRate = 3000)
	public void ShowTime() {
		System.out.println("现在时间：" + DATE_FORMAT.format(new Date()));
	}
}