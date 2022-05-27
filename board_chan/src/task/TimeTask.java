package task;

import java.text.SimpleDateFormat;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import service.MemberService;

public class TimeTask implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MemberService memberService = MemberService.getInstance();
		memberService.removePicture();
		
		System.out.println(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
				+ " : 더미데이터 삭제 완료");
	}
}
