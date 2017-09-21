package org.keirobm.samples.springangularbackend

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder) : SpringApplicationBuilder {
		return application.sources(SpringAngularBackendApplication::class.java)
	}

}
