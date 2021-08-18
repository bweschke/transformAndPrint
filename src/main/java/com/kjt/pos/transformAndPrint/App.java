package com.kjt.pos.transformAndPrint;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	
	final static jLpr jlpr = new jLpr();
	
    public static void main( String[] args )
    {
    	
    	final Logger log = LoggerFactory.getLogger(App.class);

        //System.out.println( "Hello World!" );
    	try {
    		//jlpr.printFile(fileName, hostName, PASSTHRU);
			jlpr.printFile(args[0].toString(), args[1].toString(), args[2].toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("IO Exception from printer\n", e);
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error("Interrupted Exception frmo printer\n", e);
			System.exit(1);
		}
    }
}
