package com.news.logic.commonsdk

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class ThreadPoolManager private constructor() {
    /**
     * 核心线程池的数量，同时能够执行的线程数量
     */
    private val corePoolSize: Int

    /**
     * 最大线程池数量，表示当缓冲队列满的时候能继续容纳的等待任务的数量
     */
    private val maximumPoolSize: Int

    /**
     * 存活时间
     */
    private val keepAliveTime: Long = 1
    private val unit = TimeUnit.HOURS
    private var executor: ThreadPoolExecutor?

    /**
     * 执行任务
     *
     * @param runnable
     */
    fun execute(runnable: Runnable?) {
        if (executor == null) {
            //线程池执行者。
            //参1:核心线程数;参2:最大线程数;参3:线程休眠时间;参4:时间单位;参5:线程队列;参6:生产线程的工厂;参7:线程异常处理策略
            executor = ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                LinkedBlockingQueue(),  //   Executors.defaultThreadFactory(),
                DefaultThreadFactory(Thread.NORM_PRIORITY, "tiaoba-pool-"),
                ThreadPoolExecutor.AbortPolicy()
            )
        }
        if (runnable != null) {
            executor!!.execute(runnable)
        }
    }

    /**
     * 移除任务
     */
    fun remove(runnable: Runnable?) {
        if (runnable != null) {
            executor!!.remove(runnable)
        }
    }

    /**
     * 创建线程的工厂，设置线程的优先级，group，以及命名
     */
    private class DefaultThreadFactory internal constructor(
        private val threadPriority: Int,
        threadNamePrefix: String
    ) :
        ThreadFactory {
        /**
         * 线程的计数
         */
        private val threadNumber = AtomicInteger(1)
        private val group: ThreadGroup
        private val namePrefix: String
        override fun newThread(r: Runnable): Thread {
            val t = Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0)
            if (t.isDaemon) {
                t.isDaemon = false
            }
            t.priority = threadPriority
            return t
        }

        companion object {
            /**
             * 线程池的计数
             */
            private val poolNumber = AtomicInteger(1)
        }

        init {
            group = Thread.currentThread().threadGroup
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-"
        }
    }

    companion object {
        private var threadManager: ThreadPoolManager? = null

        /**
         * 懒汉多线程单例
         *
         * @return
         */
        val instance: ThreadPoolManager?
            get() {
                if (threadManager == null) {
                    synchronized(ThreadPoolManager::class.java) {
                        if (threadManager == null) {
                            threadManager =
                                ThreadPoolManager()
                        }
                    }
                }
                return threadManager
            }
    }

    init {
        /**
         * 给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行
         */
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1
        //虽然maximumPoolSize用不到，但是需要赋值，否则报错
        maximumPoolSize = corePoolSize
        executor = ThreadPoolExecutor( //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
            corePoolSize,  //5,先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
            maximumPoolSize,  //表示的是maximumPoolSize当中等待任务的存活时间
            keepAliveTime,
            unit,  //缓冲队列，用于存放等待任务，Linked的先进先出
            LinkedBlockingQueue(),  //创建线程的工厂
            //  Executors.defaultThreadFactory(),
            DefaultThreadFactory(
                Thread.NORM_PRIORITY,
                "tiaoba-pool-"
            ),  //用来对超出maximumPoolSize的任务的处理策略
            ThreadPoolExecutor.AbortPolicy()
        )
    }
}