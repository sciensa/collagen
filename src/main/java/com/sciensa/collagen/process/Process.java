package com.sciensa.collagen.process;

import java.io.IOException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 
 * @author Douglas Pimentel Rodrigues
 * Class responsible for processing transformations
 */
public class Process {

	/**
	 * 
	 * @param payload
	 * @param templateString
	 * @return String template formated
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String processJSON(String payload, String templateString) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Input input = new Input();
		Output output = new Output();
		return output.outPut(input.process(payload), templateString);
	}
}
