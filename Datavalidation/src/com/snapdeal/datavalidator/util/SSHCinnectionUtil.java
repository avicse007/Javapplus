package com.snapdeal.datavalidator.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.LocalPortForwarder;

public class SSHCinnectionUtil {
	
	public static Connection connection = null;
	private static List<LocalPortForwarder> lpf = null;

	private void ssh(String sship,String sshusername,String privateKeyFilePath) throws Exception {
		if (connection == null) {
			synchronized (SSHCinnectionUtil.class) {
				if (connection == null) {
					try {
						connection = new Connection(sship, 22);
						connection.connect();
						File key = null;
						key = new File(privateKeyFilePath);
						boolean isAuthenticated = connection.authenticateWithPublicKey(sshusername, key, null);
						if (isAuthenticated == false) {
							throw new IOException("Authentication failed.");
						}
						System.out.println("SSH connection created successfully !!!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	public static void closeConnection() {
        if (connection != null)
        {
            connection.close();
        System.out.println("SSH connection closed !!!");
        }
    }
	
	private void forwardLocalPort(Integer localport, String remotehost,
			Integer remoteport) {
		try {
			LocalPortForwarder temp = connection.createLocalPortForwarder(
					localport, remotehost, remoteport);
			lpf.add(temp);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Exception  while port forwarding "+e);

		}
	}
	
	public static void myStart(String sship,String user,String dbip,int forwardPort, int port,String privateKeyFilePath) throws Exception
	{
		SSHCinnectionUtil manager = new SSHCinnectionUtil();
		lpf = new ArrayList<LocalPortForwarder>();
		manager.ssh(sship,user,privateKeyFilePath);		
		manager.forwardLocalPort(forwardPort,	dbip , port);
	}
}
