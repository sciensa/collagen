package com.sciensa.collagen.junit;

import java.io.IOException;

import org.junit.Test;

import com.sciensa.collagen.CollagenConnector;
import com.sciensa.collagen.process.Process;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import junit.framework.Assert;

public class ProcessTest {
	
	@Test
	public void processJSON() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Process process = new Process();
		String payload = "{\"vendor\":\"Luis Filipe\",\"store\":\"Americanas\",\"oslist\": [{\"so\": \"Windows 10\",\"name\": \"Microsoft\"},{\"so\": \"Mac OS Leopard\",\"name\": \"Apple\"},{\"so\": \"Android Kit Kat\",\"name\": \"Google\"}],\"price\":\"500,00\"}";
		String template = "{\"result\":[<#list oslist as item>{\"operatinglsystem\": \"${item.so}\",\"company\": \"${item.name}\",\"vendor\": \"${vendor}\"}<#sep>, </#sep></#list>]}";
		String result = process.processJSON(payload,template);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void processJSONTemplateError() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Process process = new Process();
		String payload = "{\"vendor\":\"Luis Filipe\",\"store\":\"Americanas\",\"oslist\": [{\"so\": \"Windows 10\",\"name\": \"Microsoft\"},{\"so\": \"Mac OS Leopard\",\"name\": \"Apple\"},{\"so\": \"Android Kit Kat\",\"name\": \"Google\"}],\"price\":\"500,00\"}";
		String template = "testeTemplate";
		String result = process.processJSON(payload,template);
		Assert.assertEquals("testeTemplate", result);
	}
	
	@Test
	public void processJSONPayloadEmpyt() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Process process = new Process();
		String payload = "";
		String template = "{\"result\":[<#list oslist as item>{\"operatinglsystem\": \"${item.so}\",\"company\": \"${item.name}\",\"vendor\": \"${vendor}\"}<#sep>, </#sep></#list>]}";
		String result = process.processJSON(payload,template);
		Assert.assertEquals("", result);
	}
	
	@Test
	public void processJSONPayloadError() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Process process = new Process();
		String payload = "aaaaa";
		String template = "{\"result\":[<#list oslist as item>{\"operatinglsystem\": \"${item.so}\",\"company\": \"${item.name}\",\"vendor\": \"${vendor}\"}<#sep>, </#sep></#list>]}";
		String result = process.processJSON(payload,template);
		Assert.assertEquals("", result);
	}
	
	@Test
	public void processSOAP() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Process process = new Process();
		String payload = "{\"vendor\":\"Luis Filipe\",\"store\":\"Americanas\",\"oslist\": [{\"so\": \"Windows 10\",\"name\": \"Microsoft\"},{\"so\": \"Mac OS Leopard\",\"name\": \"Apple\"},{\"so\": \"Android Kit Kat\",\"name\": \"Google\"}],\"price\":\"500,00\"}";
		String template = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v3=\"http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/\" xmlns:v31=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/\" xmlns:v1=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/\" xmlns:v11=\"http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/\" xmlns:v12=\"http://www.xxxxxxxx.com.br/ebo/CarroReserva/V1_0\" xmlns:v13=\"http://www.xxxxxxxx.com.br/ebo/LojaLocadora/V1_0\" xmlns:v14=\"http://www.xxxxxxxx.com.br/ebo/Endereco/V1_0\" xmlns:v15=\"http://www.xxxxxxxx.com.br/ebo/Cidade/V1_0\" xmlns:v16=\"http://www.xxxxxxxx.com.br/ebo/Common/V1_0\" xmlns:v17=\"http://www.xxxxxxxx.com.br/ebo/Telefone/V1_0\" xmlns:v18=\"http://www.xxxxxxxx.com.br/ebo/Locadora/V1_0\" xmlns:v19=\"http://www.xxxxxxxx.com.br/ebo/VeiculoLoja/V1_0\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v3:criarAlterarServicoRequest xmlns:v3='http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/' xmlns:v31='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/' xmlns:v1='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/' xmlns:v11='http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/'>\n" +
                "\t\t  <v31:cargaLegado>\n" +
                "\t\t    <v1:servico>\n" +
                "\t\t      <v11:Vendedor>${vendor}</v11:Vendedor>\n" +
                "\t\t      <v11:Loja>${store}</v11:Loja>\n" +
                "\t\t      <v11:LocalizacaoAtendimento>\n" +
                "\t\t      <#list oslist as item>\n" +
                "\t\t        <v11:localizacaoAtendimento>\n" +
                "\t\t          <v11:tipo_Endereco>1</v11:tipo_Endereco>\n" +
                "\t\t          <v11:nome_Logradouro>Av. Bernardino de Campos</v11:nome_Logradouro>\n" +
                "\t\t          <v11:numero_Logradouro>327</v11:numero_Logradouro>\n" +
                "\t\t          <v11:Empresa>${item.name}</v11:Empresa>\n" +
                "\t\t          <v11:Sistema>${item.so}</v11:Sistema>\n" +
                "\t\t        </v11:localizacaoAtendimento>\n" +
                "\t\t       </#list>\n" +
                "\t\t      </v11:LocalizacaoAtendimento>\n" +
                "\t\t    </v1:servico>\n" +
                "\t\t  </v31:cargaLegado>\n" +
                "\t\t</v3:criarAlterarServicoRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
		String result = process.processJSON(payload,template);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void processXML() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		CollagenConnector connector = new CollagenConnector();
		String payload = "<?xml version=\\\"1.0\\\" ?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v3=\"http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/\" xmlns:v31=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/\" xmlns:v1=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/\" xmlns:v11=\"http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/\" xmlns:v12=\"http://www.xxxxxxxx.com.br/ebo/CarroReserva/V1_0\" xmlns:v13=\"http://www.xxxxxxxx.com.br/ebo/LojaLocadora/V1_0\" xmlns:v14=\"http://www.xxxxxxxx.com.br/ebo/Endereco/V1_0\" xmlns:v15=\"http://www.xxxxxxxx.com.br/ebo/Cidade/V1_0\" xmlns:v16=\"http://www.xxxxxxxx.com.br/ebo/Common/V1_0\" xmlns:v17=\"http://www.xxxxxxxx.com.br/ebo/Telefone/V1_0\" xmlns:v18=\"http://www.xxxxxxxx.com.br/ebo/Locadora/V1_0\" xmlns:v19=\"http://www.xxxxxxxx.com.br/ebo/VeiculoLoja/V1_0\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v3:criarAlterarServicoRequest xmlns:v3='http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/' xmlns:v31='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/' xmlns:v1='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/' xmlns:v2='http://www.xxxxxxxx.com.br/novoServico' xmlns:v11='http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/'>\n" +
                "  <v31:cargaLegado>\n" +
                "    <v1:servico>\n" +
                "      <v11:Ano_Modelo_Veiculo>2012-01-01T00:00:00Z</v11:Ano_Modelo_Veiculo>\n" +
                "      <v11:Codigo_Cor_Veiculo>1</v11:Codigo_Cor_Veiculo>\n" +
                "      <v11:LocalizacaoAtendimento>\n" +
                "        <v11:localizacaoAtendimento>\n" +
                "          <v11:tipo_Endereco>1</v11:tipo_Endereco>\n" +
                "          <v11:nome_Logradouro>Av. Bernardino de Campos</v11:nome_Logradouro>\n" +
                "          <v11:numero_Logradouro>327</v11:numero_Logradouro>\n" +
                "          <v11:sub_Bairro>Paraíso</v11:sub_Bairro>\n" +
                "          <v11:bairro>Paraíso</v11:bairro>\n" +
                "          <v11:cidade>São Paulo</v11:cidade>\n" +
                "          <v11:uf>SP</v11:uf>\n" +
                "        </v11:localizacaoAtendimento>\n" +
                "        <v11:localizacaoAtendimento>\n" +
                "          <v11:tipo_Endereco>2</v11:tipo_Endereco>\n" +
                "          <v11:nome_Logradouro>Av. Bernardino de Rosas</v11:nome_Logradouro>\n" +
                "          <v11:numero_Logradouro>327</v11:numero_Logradouro>\n" +
                "          <v11:sub_Bairro>Paraíso</v11:sub_Bairro>\n" +
                "          <v11:bairro>Paraíso</v11:bairro>\n" +
                "          <v11:cidade>São Paulo</v11:cidade>\n" +
                "          <v11:uf>SP</v11:uf>\n" +
                "        </v11:localizacaoAtendimento>\n" +
                "      </v11:LocalizacaoAtendimento>\n" +
                "    </v1:servico>\n" +
                "    <v2:servico><preco>14</preco></v2:servico>\n" +
                "    <limite_quilometragem>20</limite_quilometragem>\n" +
                "  </v31:cargaLegado>\n" +
                "</v3:criarAlterarServicoRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
		String template = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v3=\"http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/\" xmlns:v31=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/\" xmlns:v1=\"http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/\" xmlns:v11=\"http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/\", xmlns:v2=\"http://www.xxxxxxxx.com.br/novoServico\", xmlns:v12=\"http://www.xxxxxxxx.com.br/ebo/CarroReserva/V1_0\" xmlns:v13=\"http://www.xxxxxxxx.com.br/ebo/LojaLocadora/V1_0\" xmlns:v14=\"http://www.xxxxxxxx.com.br/ebo/Endereco/V1_0\" xmlns:v15=\"http://www.xxxxxxxx.com.br/ebo/Cidade/V1_0\" xmlns:v16=\"http://www.xxxxxxxx.com.br/ebo/Common/V1_0\" xmlns:v17=\"http://www.xxxxxxxx.com.br/ebo/Telefone/V1_0\" xmlns:v18=\"http://www.xxxxxxxx.com.br/ebo/Locadora/V1_0\" xmlns:v19=\"http://www.xxxxxxxx.com.br/ebo/VeiculoLoja/V1_0\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v3:criarAlterarServicoRequest xmlns:v3='http://www.xxxxxxxx.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/' xmlns:v2=\"http://www.xxxxxxxx.com.br/novoServico\" xmlns:v31='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoxxxxxxxxxxxxIntegrationServiceSCA/V3_0/' xmlns:v1='http://www.xxxxxxxx.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/' xmlns:v11='http://www.xxxxxxxx.com.br/pomar/business/AcionamentoPomarEBM/V1_0/'>\n" +
                "\t\t  <v31:cargaLegado>\n" +
                "\t\t  \t<v1:servico>\n" +
                "\t\t      <v11:LocalizacaoAtendimento>\n" +
                "\t\t      <#list soapenv\\:Envelope.soapenv\\:Body.v3\\:criarAlterarServicoRequest.v31\\:cargaLegado.v1\\:servico.v11\\:LocalizacaoAtendimento.v11\\:localizacaoAtendimento as item>\n" +
                "\t\t        <v11:localizacaoAtendimento>\n" +
                "\t\t          <v11:tipo_Endereco>${item.v11\\:tipo_Endereco}</v11:tipo_Endereco>\n" +
                "\t\t          <v11:nome_Logradouro>${item.v11\\:nome_Logradouro}</v11:nome_Logradouro>\n" +
                "\t\t          <v11:numero_Logradouro>${item.v11\\:numero_Logradouro}</v11:numero_Logradouro>\n" +
                "\t\t        </v11:localizacaoAtendimento>\n" +
                "\t\t       </#list>\n" +
                "\t\t      </v11:LocalizacaoAtendimento>\n" +
                "\t\t    </v1:servico>\n" +
                "\t\t    <v2:servico>\n" +
                "\t\t    \t<v2:preco>${soapenv\\:Envelope.soapenv\\:Body.v3\\:criarAlterarServicoRequest.v31\\:cargaLegado.v2\\:servico.preco}</v11:preco>\n" +
                "\t\t    <v2:servico>\n" +
                "\t\t  </v31:cargaLegado>\n" +
                "\t\t</v3:criarAlterarServicoRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
		String result = connector.processxml(payload,template);
		Assert.assertNotNull(result);
	}

}
