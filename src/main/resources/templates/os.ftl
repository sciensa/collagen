{
"result":[
<#list oslist as item>
  {
	  "operatinglsystem": "${item.so}",
	  "company": "${item.name}",
	  "vendor": "${vendor}"
  }<#sep>, </#sep>
</#list>
]
}
