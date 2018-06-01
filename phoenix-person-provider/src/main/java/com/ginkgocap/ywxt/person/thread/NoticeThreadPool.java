package com.ginkgocap.ywxt.person.thread;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.dynamic.service.DynamicNewsService;
import com.ginkgocap.ywxt.person.model.PermIds;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.service.PersonPermissionService;

@Service("noticeThreadPool")
public class NoticeThreadPool implements InitializingBean, DisposableBean {

	@Autowired
	private DynamicNewsService dynamicNewsService;
	@Autowired
	private PersonPermissionService personPermissionService;

	private static ExecutorService executor = null;

	private static int threadCount = 5;

	private static Logger logger = LoggerFactory
			.getLogger(NoticeThreadPool.class);

	static {
		executor = Executors.newFixedThreadPool(threadCount);
	}

	public void noticeDataCenter(
			final Map<String, Object> params) throws Exception {
		if (params == null )
			return;

		executor.execute(new Runnable() {
			public void run() {
				
				Map<String, Object> result= dynamicNewsService.insertNewsAndRelationByParam(params);
				if ("false".equals(result.get("result"))) {
					System.out.println("创建修改人脉，发送动态时出错：参数为：" + params + "\n错误消息为：" + result.get("msg"));
				}
			}
		});
	}

	public void permissControl(final PermIds permIds, final Person person,  final Long userId) throws Exception {
		final NoticeThreadPool pool = this;
		executor.execute(new Runnable() {
			public void run() {
				try {
					personPermissionService.permissControl(permIds, person, userId, pool);
					System.out.println("人脉权限设置成功，人脉id为：" + person.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void destroy() throws Exception {
		if (executor != null) {
			executor.shutdown();
		}
	}

	public void afterPropertiesSet() throws Exception {
	}
}
