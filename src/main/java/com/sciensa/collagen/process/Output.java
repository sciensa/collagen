package com.sciensa.collagen.process;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class Output {

	public String outPut(Map<String, Object> map, String templateString) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {

		InputStream inputStream = new ByteArrayInputStream(templateString.getBytes(StandardCharsets.UTF_8));
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		Template template = new Template("template.ftl", reader);
		Writer consoleWriter = new StringWriter();
		if(map.isEmpty()){
			return consoleWriter.toString();
		}
		template.process(map, consoleWriter);
		return consoleWriter.toString();
	}

}
