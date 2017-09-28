package com.spring.boot.zk.autconfigure;

import java.util.List;

import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by liuhailin
 */
public interface IZKClient {

	/**
	 *
	 * @param path
	 * @param value
	 * @return path
	 */
	String createNode(String path, String value);

	void createNodeIfNotExist(String path, CreateMode mode);

	String createNodeWithCallBack(String path, String value, BackgroundCallback callback);

	String getNodeData(String path);

	String getNodeData(String path, Stat stat);

	boolean checkNode(String path);

	List<String> getNodeChildren(String path);

	/**
	 * 默认无视版本 -1;
	 * 
	 * @param path
	 * @return
	 */
	boolean deleteNode(String path);

	boolean deleteNode(String path, int version);

	String updateNode(String path, String value);

	String updateNode(String path, String value, int version);

}
