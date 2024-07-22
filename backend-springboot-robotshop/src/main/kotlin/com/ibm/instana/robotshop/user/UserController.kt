package com.ibm.instana.robotshop.user

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.ResponseObject
import com.ibm.instana.robotshop.models.User
import com.ibm.instana.robotshop.utils.ServiceRunValidate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import kotlin.random.Random


@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V1}/user")
class UserController (private val restTemplate: RestTemplate){

    @PostMapping("/validate")
    @ResponseBody
    fun validateUserExists(@RequestBody requestBody:User): ResponseEntity<ResponseObject> {
        return ResponseEntity(ResponseObject(message = "Success"),HttpStatus.OK)
    }


    @PostMapping("/create")
    @ResponseBody
    fun createUser(@RequestBody requestBody: User): ResponseEntity<Any> {
        return if (requestBody.name.isNullOrEmpty() || requestBody.password.isNullOrEmpty()) {
            ResponseEntity(ResponseObject(message = "Empty User Name or Password"), HttpStatus.BAD_REQUEST)
        } else {
            ServiceRunValidate(restTemplate).performAction()
            ResponseEntity(
                UserCreated(
                    userId = Random.nextInt(100001, 9999999).toString(),
                    userName = requestBody.name,
                    createdAt = System.currentTimeMillis().toString(),
                    userEmail = requestBody.emailId.toString()
                ), HttpStatus.OK
            )
        }
    }


    @GetMapping("/validate_create")
    @ResponseBody
    fun createValidateUser(): String {
        return "Success"
    }


    data class UserCreated(
        val userName: String,
        val userEmail: String,
        val userId: String,
        val createdAt: String
    )



}