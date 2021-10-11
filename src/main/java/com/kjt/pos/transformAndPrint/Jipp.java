package com.kjt.pos.transformAndPrint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jipp {
	
	private static Logger log = LoggerFactory.getLogger(Jipp.class);
	
	public void printFile(String fileName, String hostName) throws IOException, InterruptedException {	
		
		java.net.Socket socket = null; 
		java.net.InetAddress addr = null; 
		java.net.InetSocketAddress socketAddress = null; 
		java.io.BufferedReader reader= null; 
		java.io.BufferedWriter writer= null; 
		java.io.InputStream input = null; 
		java.io.OutputStream output= null;
	
		int port =9100; 
		int timeout=5000; 
		byte buffer[] = new byte[1000];

	
		try{
	
		    addr = java.net.InetAddress.getByName(hostName);
		    socket = new java.net.Socket(addr, port); 
		    socket.setSoTimeout(timeout); 
		    
			File f = new File(fileName);
			if (!(f.exists() && f.isFile() && f.canRead())) {
				throw new IOException("Jipp Error opening print file");
			}		    
	
		    output = socket.getOutputStream();
		    log.info("TestPrinter output stream "+output);
		    input = socket.getInputStream();
		    log.info("TestPrinter input stream "+input);
		    
			FileInputStream fs = new FileInputStream(f);
			int readCounter;
			do {
				readCounter = fs.read(buffer);
				if (readCounter > 0) {
					output.write(buffer, 0, readCounter);
				}
			} while (readCounter > 0);
			buffer[0] = 0;
			output.write(buffer, 0, 1);
			output.flush();
			fs.close();
		    
	
			log.info("TestPrinter output stream "+output); 
		    socket.shutdownOutput();
		    reader = new java.io.BufferedReader(new java.io.InputStreamReader(input));
		    log.info("TestPrinter read input stream "+reader);
		    log.info("Printer output: "+reader.readLine());
	
		} catch (Exception e) {   
	
		    e.printStackTrace();
		    log.warn("TestPrinter"+e);

		} finally {
		    if (input != null) 
		      try { 
		        input.close();
		        log.info("TestPrinter closed input stream ");
		      } catch (Exception e) {
		        log.warn("TestPrinter Issue closing input stream ",e);
		      }
		    if (socket != null) 
		      try { 
		        socket.close(); 
		        log.info("TestPrinter closed socket connection ");
		      } catch (Exception e) {
		        log.warn("TestPrinter Issue closing socket ",e);
		      }
		    if (output != null) 
		      try { 
		        output.close(); 
		        log.info("TestPrinter closed output stream ");
		      } catch (Exception e) {
		        log.warn("TestPrinter Issue closing output stream ",e);
		      }
		}	
	}

}
