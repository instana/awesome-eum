package com.ibm.instana.robotshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RobotshopApplication

fun main(args: Array<String>) {
	runApplication<RobotshopApplication>(*args)
}
