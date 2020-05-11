* To get dispatcher servlet request data with in other places of springboot code
	ResettableStreamHttpServletRequest servletReq = (ResettableStreamHttpServletRequest)(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest());

* Spring boot property to allow Bean overriding 
	spring.main.allow-bean-definition-overriding: true
	
* Config loading based on property
	@Configuration
	@ConditionalOnProperty(
	    value="quantum.metrics.externalcall.monitoring.enabled", 
	    havingValue = "true",
	    matchIfMissing = false )
	@ComponentScan("com.xyz.techmobile.common.abc.*")
* After spring appln started to do for example enrich after RestTemplate bean created
	@PostConstruct
	public void enrichRestCleint() {
		restTemplate.getInterceptors().add(restTemplateQuantumInterceptor);
	}
* When override a bean but want to load only if no bean was found with that name
	@ConditionalOnMissingBean(RestTemplate.class)		
* Get the working restendpoint method name from httpservlet request 
	HttpServletRequest 
* Use properties from POM.xml
	@Autowired
	BuildProperties buildProperties;
		buildProperties.getName()
		buildProperties.getVersion()
* Springboot debugging 
	Pass these vmargument
	-Dlogging.level.org.springframework=debug
	Program arguments
	--debug
					