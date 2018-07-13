package com.huateng.report.imports.updater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.cncc.cjdp.common.utils.Base64Tool.OutputStream;



	public class StandaloneWriter extends XMLWriter {
	    public StandaloneWriter(PrintStream out, OutputFormat format)
	            throws UnsupportedEncodingException
	        {
	            super(out,format);
	        }
	    
	    protected void writeDeclaration()
	            throws IOException
	        {
	           OutputFormat format = getOutputFormat();
	            String encoding = format.getEncoding();
	            if(!format.isSuppressDeclaration())
	            {
	                writer.write("<?xml version=\"1.0\"");
	                if(encoding.equals("UTF8"))
	                {
	                    if(!format.isOmitEncoding())
	                        writer.write(" encoding=\"UTF-8\"");
	                } else
	                {
	                    if(!format.isOmitEncoding())
	                        writer.write(" encoding=\"" + encoding + "\"");
	                    
	                }
	                writer.write(" standalone=\"yes\"");
	                writer.write("?>");
	                if(format.isNewLineAfterDeclaration())
	                    println();
	            }
	        }
	}
