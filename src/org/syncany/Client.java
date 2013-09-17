package org.syncany;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.syncany.config.Config;
import org.syncany.operations.StatusOperation;
import org.syncany.operations.StatusOperation.ChangeSet;
import org.syncany.operations.StatusOperation.StatusOperationResult;
import org.syncany.operations.SyncDownOperation;
import org.syncany.operations.SyncUpOperation;
import org.syncany.operations.SyncUpOperation.SyncUpOperationOptions;

public class Client {
	protected static final Logger logger = Logger.getLogger(Client.class.getSimpleName());	
	protected Config config;
	
	public Client() {
		// Fressen
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}
	
	public Config getConfig() {
		return config;
	}

	public void createDirectories() throws Exception {   
		logger.log(Level.INFO, "Creating profile directories ...");
		
		config.getAppDir().mkdirs();
		config.getCacheDir().mkdirs();
		config.getDatabaseDir().mkdirs();
	}		
	
	public void up() throws Exception {
		new SyncUpOperation(config).execute();
	}
	
	public void up(SyncUpOperationOptions options) throws Exception {
		new SyncUpOperation(config, options).execute();
	}
	
	public void down() throws Exception {
		new SyncDownOperation(config).execute();
	}

	public ChangeSet status() throws Exception {
		return ((StatusOperationResult) new StatusOperation(config).execute()).getChangeSet();		
	}
}