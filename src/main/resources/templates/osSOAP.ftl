<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:v3="http://www.portoseguro.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/" xmlns:v31="http://www.portoseguro.com.br/corporativo/integration/AcionamentoPortoSocorroIntegrationServiceSCA/V3_0/" xmlns:v1="http://www.portoseguro.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/" xmlns:v11="http://www.portoseguro.com.br/pomar/business/AcionamentoPomarEBM/V1_0/" xmlns:v12="http://www.portoseguro.com.br/ebo/CarroReserva/V1_0" xmlns:v13="http://www.portoseguro.com.br/ebo/LojaLocadora/V1_0" xmlns:v14="http://www.portoseguro.com.br/ebo/Endereco/V1_0" xmlns:v15="http://www.portoseguro.com.br/ebo/Cidade/V1_0" xmlns:v16="http://www.portoseguro.com.br/ebo/Common/V1_0" xmlns:v17="http://www.portoseguro.com.br/ebo/Telefone/V1_0" xmlns:v18="http://www.portoseguro.com.br/ebo/Locadora/V1_0" xmlns:v19="http://www.portoseguro.com.br/ebo/VeiculoLoja/V1_0">
   <soapenv:Header/>
   <soapenv:Body>
      <v3:criarAlterarServicoRequest xmlns:v3='http://www.portoseguro.com.br/corporativo/integration/DadosAtendimentoSocorristaIntegrationServiceABCS/V3_0/' xmlns:v31='http://www.portoseguro.com.br/corporativo/integration/AcionamentoPortoSocorroIntegrationServiceSCA/V3_0/' xmlns:v1='http://www.portoseguro.com.br/corporativo/integration/AcionamentoWebIntegrationServiceABCS/V1_0/' xmlns:v11='http://www.portoseguro.com.br/pomar/business/AcionamentoPomarEBM/V1_0/'>
		  <v31:cargaLegado>
		    <v1:servico>
		      <v11:Vendedor>${vendor}</v11:Vendedor>
		      <v11:Loja>${store}</v11:Loja>
		      <v11:LocalizacaoAtendimento>
		      <#list oslist as item>
		        <v11:localizacaoAtendimento>
		          <v11:tipo_Endereco>1</v11:tipo_Endereco>
		          <v11:nome_Logradouro>Av. Bernardino de Campos</v11:nome_Logradouro>
		          <v11:numero_Logradouro>327</v11:numero_Logradouro>
		          <v11:Empresa>${item.name}</v11:Empresa>
		          <v11:Sistema>${item.so}</v11:Sistema>
		        </v11:localizacaoAtendimento>
		       </#list>
		      </v11:LocalizacaoAtendimento>
		    </v1:servico>
		  </v31:cargaLegado>
		</v3:criarAlterarServicoRequest>
   </soapenv:Body>
</soapenv:Envelope>