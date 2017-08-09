package com.sciensa.collagen.process;

import java.io.IOException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class Process {

	public String processJSON(String payload, String templateString) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Input input = new Input();
		Output output = new Output();
		return output.outPut(input.process(payload), templateString);
	}
}
