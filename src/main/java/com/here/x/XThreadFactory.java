package com.here.x;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class XThreadFactory implements ThreadFactory {
		private String prefix;
		private AtomicInteger threadCount = new AtomicInteger(0);
		private ThreadGroup threadGroup;

		public XThreadFactory(String prefix) {
			this.prefix = prefix;
			threadGroup = new ThreadGroup(prefix);
		}

		@Override
		public Thread newThread(Runnable runnable) {
			Thread thread = new Thread(getThreadGroup(), runnable, nextThreadName());
			thread.setPriority(Thread.NORM_PRIORITY);
			thread.setDaemon(false);
			return thread;
		}

		protected String nextThreadName() {
			return prefix + this.threadCount.incrementAndGet();
		}

		private ThreadGroup getThreadGroup() {
			return threadGroup;
		}
	}