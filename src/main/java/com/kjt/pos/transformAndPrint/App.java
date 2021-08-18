package com.kjt.pos.transformAndPrint;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	final static jLpr jlpr = new jLpr();
	
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	try {
    		//jlpr.printFile(fileName, hostName, PASSTHRU);
			jlpr.printFile(args[0].toString(), args[1].toString(), args[2].toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
