/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.remoting.netty;

import io.netty.channel.DefaultChannelConfig;

public class NettyServerConfig implements Cloneable {
    /**
     * NameServer 监听端口，默认初始化为9876. {@code nettyServerConfig.setListenPort(9876);}
     * BrokerServer 监听端口，默认初始化为10911. {@code nettyServerConfig.setListenPort(10911);}
     *
     * @see org.apache.rocketmq.namesrv.NamesrvStartup#createNamesrvController(String[])
     * @see BrokerStartup#createBrokerController(String[])
     */
    private int listenPort = 8888;
    /**
     * worker 工作线程数量。
     * 执行握手，编解码工作。
     *
     * @see NettyRemotingServer#start()
     * @see
     */
    private int serverWorkerThreads = 8;
    /**
     * public 业务线程池。 不同的业务处理逻辑会有自己的线程池，如果该任务类型（RequestCode）
     * 未注册线程池则使用这个 public 线程池。参考dubbo的线程池任务执行策略all，message，direct等
     * 执行真正业务的线程池。
     *
     * @see RequestCode
     */
    private int serverCallbackExecutorThreads = 0;
    /**
     * EventLoopGroup中的worker线程池，处理io任务的，
     * 如网络请求，解析请求包，转发到业务线程池执行具体
     * 的逻辑。
     *
     * @see NettyRemotingServer#start()
     */
    private int serverSelectorThreads = 3;
    /**
     * 发送 oneway 消息的并发度。
     * broker端参数。
     */
    private int serverOnewaySemaphoreValue = 256;
    /**
     * 发送 async 消息的并发度。
     * broker端参数。
     */
    private int serverAsyncSemaphoreValue = 64;
    /**
     * netty channel 的最大空闲时间（单位：秒），默认值是120s。
     */
    private int serverChannelMaxIdleTimeSeconds = 120;

    /**
     * socket 的发送缓存区大小，默认64k
     */
    private int serverSocketSndBufSize = NettySystemConfig.socketSndbufSize;
    /**
     * socket 的接收缓存区大小，默认64k
     */
    private int serverSocketRcvBufSize = NettySystemConfig.socketRcvbufSize;
    /**
     * ByteBuf是否使用池化。
     *
     * @see DefaultChannelConfig#allocator netty 默认就是池化的。
     * @see NettyRemotingServer#start()
     */
    private boolean serverPooledByteBufAllocatorEnable = true;

    /**
     * make make install
     * <p>
     * <p>
     * ../glibc-2.10.1/configure \ --prefix=/usr \ --with-headers=/usr/include \
     * --host=x86_64-linux-gnu \ --build=x86_64-pc-linux-gnu \ --without-gd
     */
    private boolean useEpollNativeSelector = false;

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public int getServerWorkerThreads() {
        return serverWorkerThreads;
    }

    public void setServerWorkerThreads(int serverWorkerThreads) {
        this.serverWorkerThreads = serverWorkerThreads;
    }

    public int getServerSelectorThreads() {
        return serverSelectorThreads;
    }

    public void setServerSelectorThreads(int serverSelectorThreads) {
        this.serverSelectorThreads = serverSelectorThreads;
    }

    public int getServerOnewaySemaphoreValue() {
        return serverOnewaySemaphoreValue;
    }

    public void setServerOnewaySemaphoreValue(int serverOnewaySemaphoreValue) {
        this.serverOnewaySemaphoreValue = serverOnewaySemaphoreValue;
    }

    public int getServerCallbackExecutorThreads() {
        return serverCallbackExecutorThreads;
    }

    public void setServerCallbackExecutorThreads(int serverCallbackExecutorThreads) {
        this.serverCallbackExecutorThreads = serverCallbackExecutorThreads;
    }

    public int getServerAsyncSemaphoreValue() {
        return serverAsyncSemaphoreValue;
    }

    public void setServerAsyncSemaphoreValue(int serverAsyncSemaphoreValue) {
        this.serverAsyncSemaphoreValue = serverAsyncSemaphoreValue;
    }

    public int getServerChannelMaxIdleTimeSeconds() {
        return serverChannelMaxIdleTimeSeconds;
    }

    public void setServerChannelMaxIdleTimeSeconds(int serverChannelMaxIdleTimeSeconds) {
        this.serverChannelMaxIdleTimeSeconds = serverChannelMaxIdleTimeSeconds;
    }

    public int getServerSocketSndBufSize() {
        return serverSocketSndBufSize;
    }

    public void setServerSocketSndBufSize(int serverSocketSndBufSize) {
        this.serverSocketSndBufSize = serverSocketSndBufSize;
    }

    public int getServerSocketRcvBufSize() {
        return serverSocketRcvBufSize;
    }

    public void setServerSocketRcvBufSize(int serverSocketRcvBufSize) {
        this.serverSocketRcvBufSize = serverSocketRcvBufSize;
    }

    public boolean isServerPooledByteBufAllocatorEnable() {
        return serverPooledByteBufAllocatorEnable;
    }

    public void setServerPooledByteBufAllocatorEnable(boolean serverPooledByteBufAllocatorEnable) {
        this.serverPooledByteBufAllocatorEnable = serverPooledByteBufAllocatorEnable;
    }

    public boolean isUseEpollNativeSelector() {
        return useEpollNativeSelector;
    }

    public void setUseEpollNativeSelector(boolean useEpollNativeSelector) {
        this.useEpollNativeSelector = useEpollNativeSelector;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (NettyServerConfig) super.clone();
    }
}
