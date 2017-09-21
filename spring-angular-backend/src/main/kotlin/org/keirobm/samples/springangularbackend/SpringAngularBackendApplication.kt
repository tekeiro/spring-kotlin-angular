package org.keirobm.samples.springangularbackend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters

@EntityScan(
        basePackageClasses = arrayOf(
            SpringAngularBackendApplication::class,
            Jsr310JpaConverters::class
        )
)
@SpringBootApplication
class SpringAngularBackendApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringAngularBackendApplication::class.java, *args)
}
